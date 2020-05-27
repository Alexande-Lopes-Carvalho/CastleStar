package abstraction;

import java.util.ArrayList;
import java.math.*;
import java.util.List;

public class GraphGame {
	private List<Node> startNodeList;
	private Node EndNode;
	private Point dimensionNode;
	private ArrayList<ArrayList<Node>> graph;
	private int nbreNode;
	private ArrayList<Position> coordPlayerGraph; 
	
	
 	public GraphGame(ArrayList<CtrlElementCollidable> ctrlELementCollidableList,List<CtrlPlayer> ctrlPlayerList,Point size, int nbreNode) {
 		this.graph = new ArrayList<ArrayList<Node>>();
 		this.nbreNode = nbreNode;
 		Point dimension = new Point(size.getX()/nbreNode,size.getY()/nbreNode);
 		this.dimensionNode = dimension;
 		for (int i = 0 ;i < nbreNode;i++) {
 			ArrayList<Node> listNode = new ArrayList<Node>();
			for (int j = 0 ;j < nbreNode;j++) {
				Point coord = new Point(i*size.getX()/nbreNode,j*size.getY()/nbreNode);
				Node temp = new Node(coord,dimension,i,j,false,false);
				for(CtrlElementCollidable e : ctrlELementCollidableList) {
					ArrayList hitboxElement = e.getElementCollidable().getPolygon().getPoint();
					for (Point p : hitboxElement) {
						if(temp.pointInside(p)) {
							temp.setHasObstacle(true);
							if (e instanceof CtrlPlayer) {
								temp.setHasPlayer(true);
								this.coordPlayerGraph.add(new Position(i,j));
								this.EndNode = temp;
							}
							if (e instanceof CtrlEnnemy) {
								temp.setHasObstacle(false);
								startNodeList.add(temp);
							
							}
							
						}
						
					}
					
					
				}
				
			listNode.add(temp);
			}
			
 		graph.add(listNode);}
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

	

	public int getNbreNode(){
 		return nbreNode;
 	}
 	public List<Node> getVoisin(Node a){
 		Position nodePos = a.getPositionGraph();
 		int i = nodePos.getI();
 		int j = nodePos.getJ();
 		ArrayList <Node> neighbourNodes = new ArrayList<Node>();
 		if (i == 0 ) {
 			if (j == 0) {
 				neighbourNodes.add(graph.get(i).get(j+1));
 				neighbourNodes.add(graph.get(i+1).get(j));
 				neighbourNodes.add(graph.get(i+1).get(j+1));
 				
 				
 			}
 			else {
	 			for(int k = i;k<=i+1;i++) {
	 				if (k == i) {
	 					 				
	 	 				neighbourNodes.add(graph.get(k).get(j+1));
	 					
	 				}
	 				else {
	
	 	 				
	 	 				neighbourNodes.add(graph.get(k).get(j));
	 	 				neighbourNodes.add(graph.get(k).get(j+1));
	 					
	 				}
	 			
	 			
	 			}
 			}
 		}
 		else if (j == 0 && i!=0) {
 			if(i == this.getNbreNode()) {
 				neighbourNodes.add(graph.get(i-1).get(j)); 				
	 			neighbourNodes.add(graph.get(i-1).get(j+1));
	 			neighbourNodes.add(graph.get(i).get(j+1));
 				
 			}
 			else {
 			for(int k = i-1;k<=i+1;i++) {
 				if (k == i) {
 									
 	 				neighbourNodes.add(graph.get(k).get(j+1));
 					
 				}
 				else {

 					
 	 				neighbourNodes.add(graph.get(k).get(j));
 	 				neighbourNodes.add(graph.get(k).get(j+1));
 					
 				}
 			
 			
 			}
 			}
 			
 			
 		}
 		else if(j == this.getNbreNode() ) {
 			if (i == 0) {
 				neighbourNodes.add(graph.get(i).get(j-1));
 				neighbourNodes.add(graph.get(i+1).get(j));
 				neighbourNodes.add(graph.get(i+1).get(j-1));
 				
 				
 			}
 			else if (i == this.getNbreNode()) {
 				neighbourNodes.add(graph.get(i).get(j-1));
 				neighbourNodes.add(graph.get(i-1).get(j));
 				neighbourNodes.add(graph.get(i-1).get(j-1));
 				
 			}
 			else {
	 			for(int k = j-1;k<=j;j++) {
	 				if (k == j) {
	 					 				
	 	 				neighbourNodes.add(graph.get(i-1).get(k));
	 	 				neighbourNodes.add(graph.get(i+1).get(k));
	 					
	 				}
	 				else {
	
	 	 				
	 	 				neighbourNodes.add(graph.get(i-1).get(k));
	 	 				neighbourNodes.add(graph.get(i).get(k));
	 	 				neighbourNodes.add(graph.get(i+1).get(k));
	 					
	 				}
	 			
	 			
	 			}
 			}
 		}
 		else if (i ==this.nbreNode) {
 				for (int k = j-1;k<=j+1;k++) {
 					if (k == j) {
 						neighbourNodes.add(graph.get(i-1).get(k));
 					}
 					else {
 						neighbourNodes.add(graph.get(i-1).get(k));
 						neighbourNodes.add(graph.get(i).get(k));
 						
 					}
 				}
 		}

 		else {
 			for(int k = i-1;k<=i+1;i++) {
 				if (k == i) {
 					neighbourNodes.add(graph.get(k).get(j-1)); 	 				
 	 				neighbourNodes.add(graph.get(k).get(j+1));
 					
 				}
 				else {

 	 				neighbourNodes.add(graph.get(k).get(j-1));
 	 				neighbourNodes.add(graph.get(k).get(j));
 	 				neighbourNodes.add(graph.get(k).get(j+1));
 					
 				}
 			}
 			
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
	public void updatePlayer(CtrlWarrior ctrlWarrior) {
		for(Position pos: getCoordPlayerGraph()) {
			graph.get(pos.getI()).get(pos.getJ()).setHasPlayer(false);
			graph.get(pos.getI()).get(pos.getJ()).setHasObstacle(false);
			coordPlayerGraph.remove(pos);
		}
		
		ArrayList<Point> polygonPoint = ctrlPlayer.getPlayer().getPolygone().getPoint();
		for (Point p:polygonPoint) {
			Position graphPoint = NodeSearch(p);
			Node n = graph.get(graphPoint.getI()).get(graphPoint.getJ());
			n.setHasPlayer(true);
			n.setHasObstacle(true);
			coordPlayerGraph.add(graphPoint);
		}
		
	}
	
		
		
	}



}
