package abstraction;

import java.util.ArrayList;
import java.util.List;

public class Node extends Rectangle{
	
	private boolean hasObstacle;
	private boolean hasPlayer;
	private Position  positionGraph;
	private double f_cost;
	private double h_cost;
	private double score;
	public Node(Point coord,Point dimension,int i,int j, boolean hasObstacle,boolean hasPlayer){
		super(coord,dimension);
		this.hasObstacle = hasObstacle;
		this.hasPlayer = hasPlayer;
		this.f_cost = 0;
		this.h_cost = 0;
		this.score = 0;
		this.positionGraph =new Position(i,j);
		
		
	}
	
	public Position getPositionGraph() {
		return positionGraph;
	}

	public void setPositionGraph(Position positionGraph) {
		this.positionGraph = positionGraph;
	}

	public double getF_cost() {
		return f_cost;
	}

	public void setF_cost(double f_cost) {
		this.f_cost = f_cost;
	}

	public double getH_cost() {
		return h_cost;
	}

	public void setH_cost(double h_cost) {
		this.h_cost = h_cost;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isHasObstacle() {
		return hasObstacle;
	}



	public void setHasObstacle(boolean hasObstacle) {
		this.hasObstacle = hasObstacle;
	}



	public boolean isHasPlayer() {
		return hasPlayer;
	}



	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}
	
	
	
	public double distBwNode(Node a) {
		return this.getCoord().getDist(a.getCoord());
	}
	public void calcF_cost(Node end) {
		setF_cost(this.distBwNode(end));
	}
	public void calcH_cost(Node start) {
		setH_cost(this.distBwNode(start));
	}
	
	public void calcScore(Node end, Node start) {
		calcF_cost(end);
		calcH_cost(start);
		setScore(this.getF_cost()+this.getH_cost());
	}
	
	

	

}