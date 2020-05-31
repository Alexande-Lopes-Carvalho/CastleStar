package abstraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controle.CtrlElementCollidable;
import controle.CtrlElementScene;
import controle.CtrlEntity;
import presentation.MainEventHandler;
import presentation.PresElementScene;
/*import presentation.MainEventHandler;
import presentation.PresElementScene;
import presentation.PresLevel;*/
import shapeSceneFX.Point;
/**
 * Utilisé pour calculé l'itineraire d'ennemi,
 * On genere un graphe par echantillonage du niveau, puis on verifie si l'hitbox renseigné peut etre presente sur chaque noeud
 * On ajoute systematiquement les Entité lorsque l'on doit trouver un itinéraire car les Entité peuvent se deplacé dans le niveau
 * 
 * La resolution de l'itinéraire se fait via l'arlgorithme du A*
 * 
 * Notre classe est une sous classe de PresElementScene uniquement pour avoir une representation de notre graph (debogage)
 * 
 * @author Administrator
 *
 */
public class AStarGraph  extends PresElementScene {
	/**
	 * matrice de Noeud
	 */
	private Node[][] nodes;
	/**
	 * espace qui separe les noeud les un des autre dans le graphe
	 */
	private double xSpace, ySpace;
	/**
	 * hitbox
	 */
	private Rectangle rect;
	/**
	 * liste des noeud visité lors de l'execution du A*
	 */
	private List<Node> visitedNode;
	/**
	 * liste des noeud a reinitialisé pour la prochaine recherche d'un itinéraire
	 */
	private List<Node> toCleanNode; // liste des Nodes visité/entité (pour allégé le reset)
	public AStarGraph(double _xSpace, double _ySpace, double width, double height, Rectangle _rect) {
		xSpace = _xSpace;
		ySpace = _ySpace;
		rect = _rect;
		nodes = new Node[(int)(width/xSpace)+1][(int)(height/ySpace)+1];
		toCleanNode = new ArrayList<Node>();
		visitedNode = new ArrayList<Node>();
		
		// Creation des noeuds
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[i].length; j++) {
				nodes[i][j] = new Node(new Point(i*xSpace, j*ySpace));
			}
		}
		
		// Ajout de voisin
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[i].length; j++) {
				for(int k = -1; k < 2; k++) {
					for(int l = -1; l < 2; l++) {
						if(!(k == 0 && l == 0) && between(i+k, 0, nodes.length-1) && between(j+l, 0, nodes[i].length-1)) {
							nodes[i][j].addNeighbours(nodes[i+k][j+l]);
						}
					}
				}
			}
		}
		
		// gestion des mur
		List<CtrlElementCollidable> list = new ArrayList<CtrlElementCollidable>(CtrlElementScene.currentLevel.getCtrlElementCollidableList());
		list.removeAll(CtrlElementScene.currentLevel.getCtrlEntityList());
		Rectangle r = new Rectangle(_rect.getDimension());
		for(int i = 0; i < nodes.length; i++) {
			for(int j = 0; j < nodes[i].length; j++) {
				//System.out.println(nodes[i][j].getCoord() + " " + i + " " + j);
				r.setCoord(nodes[i][j].getCoord().sub(r.getDimension().copy().mult(1/2.)));
				for(CtrlElementCollidable k : list) {
					boolean setWall = false;
					for(Point v : r.getPoints()) {
						if(k.getElementCollidable().getPolygon().pointInside(k.getElementCollidable().getCoord().getVector(v))) {
							setWall = true;
							break;
						}
					}
					if(!setWall) {
						for(Point v : k.getElementCollidable().getPolygon().getPoints()) {
							v.add(k.getElementCollidable().getCoord());
							if(r.pointInside(v)) {
								setWall = true;
								break;
							}
						}
					}
					//System.out.println(setWall);
					if(setWall) {
						nodes[i][j].setWall(setWall);
						break;
					}
				}
			}
		}
	}
	/**
	 * reinitialise le graph (sans supprimé les information propre au obstacle, sauf pour les entité), pour un nouveau calcul de l'itineraire
	 * @see Node#reset()
	 */
	public void reset() {
		for(Node a : toCleanNode) {
			a.reset();
		}
		toCleanNode.clear();
		for(Node a : visitedNode) {
			a.reset();
		}
		visitedNode.clear();
	}
	/**
	 * Ajoute un noeud propre a la coordonné de l'entité renseigné en parametre
	 * @param a
	 * @return
	 */
	private Node addNode(CtrlEntity a) {
		//System.out.println("START " + a.getEntity().getCenterHitbox());
		Node k = new Node(a.getEntity().getCenterHitbox());
		Point coord = k.getCoord();
		int x = (int)Math.floor(coord.getX()/xSpace), y = (int)Math.floor(coord.getY()/ySpace);
		ArrayList<Node> neighbours = new ArrayList<Node>();
		if(coord.getX()/xSpace == x) {
			if(coord.getY()/ySpace == y) {
				//System.out.println("NO NODE CREATED");
				return nodes[x][y];
			}
			neighbours.add(nodes[x][y]);
			neighbours.add(nodes[x][y+1]);
			//System.out.println("ALIGN ON X");
		}else if(coord.getY()/ySpace == y) {
			neighbours.add(nodes[x][y]);
			neighbours.add(nodes[x+1][y]);
			//System.out.println("ALIGN ON Y");
		} else {
			neighbours.add(nodes[x][y]);
			neighbours.add(nodes[x+1][y]);
			neighbours.add(nodes[x][y+1]);
			neighbours.add(nodes[x+1][y+1]);
			//System.out.println("NOT ALIGNED");
		}
		for(Node i : neighbours) {
			//System.out.println(i.getCoord());
			i.addNeighbours(k);
			k.addNeighbours(i);
		}
		return k;
	}
	/**
	 * Supprime un noeud du graphe
	 * @param a
	 */
	private void eraseNode(Node a) {
		ArrayList<Node> l = new ArrayList<>(a.getNeighbours());
		for(Node k : l) {
			a.removeNeighbours(k);
			k.removeNeighbours(a);
		}
	}
	
	/**
	 * Trouve l'itineraire pour que l'entité a rejoigne l'entité b a l'aide du A*
	 * @param nbVisite nombre de noeud maximale a visité avant l'abondond de la recherche
	 * @param a 
	 * @param b
	 * @return
	 */
	public List<Point> getPath(int nbVisite, CtrlEntity a, CtrlEntity b){
		reset();
		
		if(renderNodeA != null) {
			eraseNode(renderNodeA);  // A CHANGER
			eraseNode(renderNodeB);
		}
		
		Node nodeA = addNode(a), nodeB = addNode(b);
		renderNodeA = nodeA;
		renderNodeB = nodeB;
		nodeA.visitedFrom(nodeA, nodeB);
		nodeA.setLink(null);
		visitedNode.add(nodeA);
		setEntityNode(a, b);
		for(int i = 0; i < nbVisite; i++) {
			Node n = selectNode();
			//System.out.println(i + " " + n.getCoord());
			//printL(visitedNode);
			if(n == nodeB) {
				//System.out.println("FOUND " + n.getLink());
				return createPath(nodeB, a);
			}
			n.lock(visitedNode, nodeB);
			visitedNode.remove(n);
			toCleanNode.add(n);
		}
		//System.out.println("NOT FOUND");
		//c = a.getEntity().getCoord();
		//centerC = a.getEntity().getRectangle().getCenter();
		
		return null;
	}
	/**
	 * Prend en compte dans le graphe tout les obstacle lié au entité, sauf l'entité a et b
	 * @param a
	 * @param b
	 * @see AStarGraph#getPath(int, CtrlEntity, CtrlEntity)
	 */
	public void setEntityNode(CtrlEntity a, CtrlEntity b) {
		List<CtrlEntity> list = new ArrayList<CtrlEntity>(CtrlElementScene.currentLevel.getCtrlEntityList());
		list.remove(a);
		list.remove(b);
		Rectangle r = new Rectangle(rect.getDimension());
		r.setCoord(new Point(0, 0).sub(rect.getDimension().copy().mult(1/2.)));
		Point[] p = r.getPoints();
		//System.out.println("values");
		p[0].mult(-1);
		p[2].mult(-1);
		for(CtrlEntity k : list) {
			Point[] l = k.getEntity().getRectangle().getPoints();
			l[0].add(k.getEntity().getCoord()).add(p[2]);
			l[2].add(k.getEntity().getCoord()).add(p[0]);
			//System.out.println(l[0] + " " + l[2] + " " + k.getEntity().getCoord() + " " + k.getEntity().getCenterHitbox());
			for(int i = (int)Math.ceil(l[0].getX()/xSpace); i < (int)Math.ceil(l[2].getX()/xSpace); i++) {
				for(int j = (int)Math.ceil(l[0].getY()/ySpace); j < (int)Math.ceil(l[2].getY()/ySpace); j++) {
					if(between(i, 0, nodes.length) && between(j, 0, nodes[0].length)) {
						nodes[i][j].setEntity(true);
						toCleanNode.add(nodes[i][j]);
					}
				}
			}
		}
	}
	/**
	 * Selectionne un noeud selon l'algorithme du A*
	 * @return
	 */
	public Node selectNode() {
		Node res = null;
		for(Node l : visitedNode) {
			if(res == null || l.getFCost() < res.getFCost()) {
				res = l;
			}
		}
		return res;
	}
	/**
	 * interprete le graphe et genere l'itineraire que l'entité a devra prendre
	 * @param b
	 * @param a
	 * @return
	 */
	private List<Point> createPath(Node b, CtrlEntity a) {
		ArrayList<Point> path = new ArrayList<Point>();
		Point vec = a.getEntity().getCenterHitbox().getVector(a.getEntity().getCoord());
		Node k = b;
		while(k.getLink() != null) {
			path.add(k.getCoord().add(vec));
			k = k.getLink();
		}
		path.add(k.getCoord().add(vec));
		Collections.reverse(path);
		if(path.size() > 0) {
			path.remove(0);
		}
		return path;
	}
	
	
	private boolean between(int v, int s, int e) {
		return v >= s && v <= e;
	}
	
	// PARTIE GRAPHIQUE POUR TEST A SUPPRIMER
	public void printL(List<Node> e) {
		for(Node k : e) {
			System.out.print(k.getCoord() + " ");
		}
		System.out.println();
	}
	
	private Point cam;
	private Point c, centerC;
	private Node renderNodeA, renderNodeB;
	@Override
	public boolean doRender(Point camera) {
		cam = camera.copy();
		setRenderPriority(900);
		return true;
	}
	
	public void render() {
		rectMode(CENTER);
		int s = (int)((cam.getX()/MainEventHandler.pxSize)/xSpace)+1, e = (int)(((cam.getX()+width())/MainEventHandler.pxSize)/xSpace);
		for(int i = s; i < e+1 && i < nodes.length; i++) {
			for(int j = 0; j < nodes[i].length; j++) {
				render(nodes[i][j]);
			}
		}
		if(renderNodeA != null) {
			render(renderNodeA);
		}
		if(renderNodeB != null) {
			render(renderNodeB);
		}
		if(c != null) {
			fill(color(240, 229, 27));
			rect(c.copy().add(centerC).mult(MainEventHandler.pxSize), new Point(3, 3));
		}
		stroke(255);
		//System.out.println((NODEB != null) + " " + (NODEB.getLink() != null));
		if(renderNodeB != null && renderNodeB.getLink() != null) {
			//System.out.println("pass");
			Node k = renderNodeB;
			while(k.getLink() != null) {
				Node child = k.getLink();
				line(k.getCoord().mult(MainEventHandler.pxSize), child.getCoord().mult(MainEventHandler.pxSize));
				k = child;
			}
		}
		stroke(0);
		rectMode(CORNER);
	}
	
	public void render(Node e) {
		fill((e.getWall())? color(0) : (e.getEntity())? color(0, 255, 0) : (e == renderNodeA)? color(255, 0, 0) : (e == renderNodeB)? color(0, 0, 255) : color(255));
		rect(e.getCoord().mult(MainEventHandler.pxSize), new Point(3, 3));
		if(e.getLink() != null) {
			line(e.getCoord().mult(MainEventHandler.pxSize), e.getLink().getCoord().mult(MainEventHandler.pxSize));
		}
	}
}
