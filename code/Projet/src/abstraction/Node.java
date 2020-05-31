package abstraction;

import java.util.ArrayList;
import java.util.List;

import shapeSceneFX.Point;
/**
 * Represente un noeud dans notre graphe generé par une instance de AStarGraph
 * @author Administrator
 * @see AStarGraph
 */
public class Node {
	/**
	 * Coordonné du noeud
	 */
	private Point coord;
	/**
	 * Noeud qu'il faut prendre pour atteindre le depart
	 */
	private Node link;
	/**
	 * Liste des voisin du noeud
	 */
	private List<Node> neighbours;
	/**
	 * attribut lié a l'état du noeud
	 */
	private boolean lock, visit;
	/**
	 * attribut indiquant si le noeud est franchissable ou non
	 */
	private boolean wall, entity;
	/**
	 * attribut lié au distance
	 */
	private double hCost, gCost;
	public Node(Point _coord) {
		coord = _coord;
		neighbours = new ArrayList<Node>();
	}
	/**
	 * reinitialisation du noeud (sans supprimé l'information lié au wall)
	 */
	public void reset() {
		lock = false;
		visit = false;
		entity = false;
		hCost = 0;
		gCost = 0;
		link = null;
	}
	
	public void setWall(boolean value) {
		wall = value;
	}
	
	public void setEntity(boolean value) {
		entity = value;
	}
	/**
	 * Verrouillage du noeud, et visite de ses noeud voisin
	 * @param visitedNode
	 * @param end
	 */
	public void lock(List<Node> visitedNode, Node end) {
		for(Node k : neighbours) {
			if(!k.isLocked() && k.canVisit()) {
				if(!k.visit) {
					visitedNode.add(k);
				}
				k.visitedFrom(this, end);
			}
		}
		lock = true;
	}
	/**
	 * Noeud visité par le noeud k
	 * @param k
	 * @param end
	 */
	public void visitedFrom(Node k, Node end) {
		if(!visit) {
			hCost = getDist(end);
		}
		double gCostFromK = getDist(k)+k.getGCost();
		if(!visit || gCostFromK < gCost) {
			gCost = gCostFromK;
			link = k;
		}
		visit = true;
	}
	
	public Node getLink() {
		return link;
	}
	
	public void setLink(Node _link) {
		link = _link;
	}
	
	public double getFCost() {
		return gCost+hCost;
	}
	
	public double getGCost() {
		return gCost;
	}
	
	public double getDist(Node e) {
		return coord.getDist(e.coord);
	}
	/**
	 * Ajout de voisin
	 * @param n
	 */
	public void addNeighbours(Node n) {
		neighbours.add(n);
	}
	/**
	 * Suppression de voisin
	 * @param n
	 */
	public void removeNeighbours(Node n) {
		neighbours.remove(n);
	}
	
	public boolean isLocked() {
		return lock;
	}
	
	public boolean canVisit() {
		return !wall && !entity && !lock;
	}
	
	public List<Node> getNeighbours(){
		return neighbours;
	}
	
	public Point getCoord() {
		return coord.copy();
	}
	
	public boolean getWall() {
		return wall;
	}
	
	public boolean getEntity() {
		return entity;
	}
}
