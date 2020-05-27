package abstraction;

import java.util.ArrayList;
import java.math.*;
import java.util.List;

public class GraphGame {
	private List<Node> startNodeList;
	private Node EndNode;
	private ArrayList<ArrayList<Node>> graph;
	private int nbreNode;
	private Position coordPlayerGraph; 
	
	
 	public GraphGame(ArrayList<CtrlElementCollidable> ctrlELementCollidableList,List<CtrlPlayer> ctrlPlayerList,Point size, int nbreNode) {
 		this.graph = new ArrayList<ArrayList<Node>>();
 		this.nbreNode = nbreNode;
 		Point dimension = new Point(size.getX()/nbreNode,size.getY()/nbreNode);
 		for (int i = 0 ;i < nbreNode;i++) {
 			ArrayList<Node> listNode = new ArrayList<Node>();
			for (int j = 0 ;j < nbreNode;j++) {
				Point coord = new Point(i*size.getX()/nbreNode,j*size.getY()/nbreNode);
				Node temp = new Node(coord,dimension,i,j,false,false);
				for(CtrlElementCollidable e : ctrlELementCollidableList) {
					Point coordElement = e.getElementCollidable().getCoord();
					if(temp.pointInside(coordElement)) {
						temp.setHasObstacle(true);
						if (e instanceof CtrlPlayer) {
							temp.setHasPlayer(true);
							this.coordPlayerGraph = new Position(i,j);
							this.EndNode = temp;
						}
						
					}
					
				}
				
			listNode.add(temp);
			}
			
 		graph.add(listNode);}
 	}
 	
 	public Position getCoordPlayerGraph() {
		return coordPlayerGraph;
	}

	public void setCoordPlayerGraph(Position coordPlayerGraph) {
		this.coordPlayerGraph = coordPlayerGraph;
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
 	private int dicho(double coordXY) {
 		int borneInf = 0;
 		int borneSup =this.getNbreNode();
 		int mid = (int)((borneInf+borneSup)/2);
 		while (graph.get(0).get(0).getDimension().getX()> Math.abs(borneSup-borneInf)) {
 			mid = (int)((borneInf+borneSup)/2);
 			
 			if (graph.get(mid).get(0).getCoord().getX() <coordXY) {
 	 			borneInf = mid;
 	 		}
 	 		else {
 	 			borneSup = mid;
 	 		}
 			
 		}
 		return mid;
 		
 		
 	}
	public void updatePlayerPosition(CtrlPlayer ctrlPlayer) {
		ctrlPlayer.getElementCollidable().getCoord();
		int newXPlayer = dicho(ctrlPlayer.getElementCollidable().getCoord().getX());
		int newYPlayer = dicho(ctrlPlayer.getElementCollidable().getCoord().getY());
		Node exPlayerPos = graph.get(coordPlayerGraph.getI()).get(coordPlayerGraph.getJ());
		exPlayerPos.setHasObstacle(false);
		exPlayerPos.setHasPlayer(false);
		Node newPlayerPos = graph.get(newXPlayer).get(newYPlayer);
		newPlayerPos.setHasObstacle(true);
		newPlayerPos.setHasPlayer(true);
		this.setCoordPlayerGraph(new Position(newXPlayer,newYPlayer));
		
	}
	

}
