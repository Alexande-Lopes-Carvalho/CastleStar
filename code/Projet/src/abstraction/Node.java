package abstraction;

import java.util.ArrayList;
import java.util.List;

import shapeSceneFX.Point;

public class Node {
	private Point coord;
	private Node link;
	private List<Node> neighbours;
	private boolean lock, visit;
	private boolean wall, entity;
	private double hCost, gCost;
	public Node(Point _coord) {
		coord = _coord;
		neighbours = new ArrayList<Node>();
	}
	
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
	
	public void addNeighbours(Node n) {
		neighbours.add(n);
	}
	
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
