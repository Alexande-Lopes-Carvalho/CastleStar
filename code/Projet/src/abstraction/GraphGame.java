package abstraction;

import shapeSceneFX.Point;

import java.util.ArrayList;
import java.math.*;
import java.util.List;

public class GraphGame {
	
	private Node EndNode;
	private Point dimensionNode;
	private ArrayList<ArrayList<Node>> graph;
	private int nbreNode;
	private ArrayList<Position> coordPlayerGraph; 
	
	
 	public GraphGame(ArrayList<ElementCollidable> elementCollidableList,Point size, int nbreNode) {
 		this.graph = new ArrayList<ArrayList<Node>>();
 		this.nbreNode = nbreNode;
 		
 		Point dimension = new Point(size.getX()/nbreNode,size.getY()/nbreNode);
 		this.dimensionNode = dimension;
 		for (int i = 0 ;i < nbreNode;i++) {
 			
 			ArrayList<Node> listNode = new ArrayList<Node>();
			for (int j = 0 ;j < nbreNode;j++) {
				
				Point coord = new Point(i*size.getX()/nbreNode,j*size.getY()/nbreNode);
				Node temp = new Node(coord,dimension,i,j,false,false);
							listNode.add(temp);
			}
			
 		graph.add(listNode);
 		}
 		for(ElementCollidable e : elementCollidableList) {
 			System.out.println("oui");
 			// a faire par la suite avec tous les points du polygone de la hitbox
 			Position p = this.NodeSearch(e.getCenterHitbox());
 			if (p.getI()<this.nbreNode && p.getJ()<this.nbreNode) {
 				this.graph.get(p.getI()).get(p.getJ()).setHasObstacle(true); 
 				if (e instanceof Player) {
 					this.graph.get(p.getI()).get(p.getJ()).setHasPlayer(true);
 					this.EndNode = this.graph.get(p.getI()).get(p.getJ());
 				}
 	 			System.out.println(this.graph.get(p.getI()).get(p.getJ()).toString());
 			}
 			
 		}
 	}
 	
 	public Point getDimensionNode() {
		return dimensionNode;
	}

	public void setDimensionNode(Point dimensionNode) {
		this.dimensionNode = dimensionNode;
	}

	public ArrayList<Position> getCoordPlayerGraph() {
		return coordPlayerGraph;
	}

	public ArrayList<ArrayList<Node>> getGraph() {
		return graph;
	}

	
	public Node getEndNode() {
		return EndNode;
	}

	public void setEndNode(Node endNode) {
		EndNode = endNode;
	}

	public int getNbreNode(){
 		return nbreNode;
 	}
 	public List<Node> getVoisin(Node a){
 		Position nodePos = a.getPositionGraph();
 		int i = nodePos.getI();
 		int j = nodePos.getJ();
 		ArrayList<ArrayList<Node>> graphG = getGraph();
 		ArrayList <Node> neighbourNodes = new ArrayList<Node>();
 		
 		if (i-2<getNbreNode() || j-2<getNbreNode()) {
 			neighbourNodes.add(graphG.get(i+1).get(j-1));
 	 		neighbourNodes.add(graphG.get(i+1).get(j));
 	 		neighbourNodes.add(graphG.get(i+1).get(j+1));
 	 		neighbourNodes.add(graphG.get(i).get(j-1));
 	 		neighbourNodes.add(graphG.get(i).get(j+1));
 	 		neighbourNodes.add(graphG.get(i-1).get(j-1));
 	 		neighbourNodes.add(graphG.get(i-1).get(j));
 	 		neighbourNodes.add(graphG.get(i-1).get(j+1));
 	 			
 		}
 		
 		return neighbourNodes;
 		
 		
 	}
 		
 		



	/*permer de chercher le noeud ou se situe un point*/
	public Position NodeSearch(Point p) {
		double currentX = 0;
		double currentY = 0;
		int i = 0;
		int j = 0;
		while (currentX<p.getX()) {
			currentX+=getDimensionNode().getX();
			i++;		
		}
		while(currentY<p.getY()) {
			currentY+=getDimensionNode().getY();
			j++;
		}
		return new Position(i,j);
	}
	public void updatePlayer(Player player) {
		for(Position pos: getCoordPlayerGraph()) {
			graph.get(pos.getI()).get(pos.getJ()).setHasPlayer(false);
			graph.get(pos.getI()).get(pos.getJ()).setHasObstacle(false);
			coordPlayerGraph.remove(pos);
		}
		
		ArrayList<Point> polygonPoint = player.getPolygon().getPoints();
		for (Point p:polygonPoint) {
			Position graphPoint = NodeSearch(p);
			Node n = graph.get(graphPoint.getI()).get(graphPoint.getJ());
			n.setHasPlayer(true);
			n.setHasObstacle(true);
			coordPlayerGraph.add(graphPoint);
		}
		
	}
	
		
		
	}




