package abstraction;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import controle.CtrlOrc;
import shapeSceneFX.Point;

public class Orc extends Enemy{

	private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private ArrayList<Node> path;
    private ArrayList<Point> pointPath;
    
	

	public Orc(int _nbArrow, Point _lookingTo, double _speed, int _maxLife, Point _coord, Rectangle _rectangle) {
		super(_nbArrow, _lookingTo, _speed, _maxLife, _coord, _rectangle);
	
	}
	public ArrayList<Point> getPointPath(){
		return this.pointPath;
	}

	public void setupPointPath() {
		ArrayList <Point> temp = new ArrayList<Point>(); 
		for(Node n : path) {
			temp.add(n.getCenter());
			
		}
	 this.pointPath = temp;
	}
	// process A* algorithm
	public void refreshItinary(GraphGame g) {
		Position start = g.NodeSearch(this.getCenterHitbox());
		Node startNode = g.getGraph().get(start.getI()).get(start.getJ());
		/*for (ArrayList<Node> l : g.getGraph()){
			for (Node n: l) {
			
				System.out.println(n.toString()+ n.isHasObstacle());

			
								}
			}
		*/
		System.out.println(g.getEndNode());
		System.out.println(startNode.toString());
		// Si le noeud de départ est égal au noeud d'arrivée 
        if (startNode == null && g.getEndNode()== null) {
            return;
        }

        if (startNode.isHasPlayer()){
            this.path = new ArrayList<>();
            return;
        }

        this.path = new ArrayList<>();

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        
        // Ajout du noeud de départ dans l'open list
        startNode.calcScore(g.getEndNode(),startNode);
        this.openList.add(startNode);
        while(openList.isEmpty()) {
        	Node current =getLowestCost();
        	this.openList.remove(current);
        	this.closedList.add(current);
        	
        	if(current.isHasPlayer()) {
        		System.out.println("c'est la wxin");
        		retracePath(current,startNode);
        		
        		
        		break;
        	}
        	for(Node n :g.getVoisin(current)) {
        		System.out.println(n.toString());
        		double tempScore = current.getH_cost()+current.distBwNode(n);
        		if (closedList.contains(n) || n.isHasObstacle()==true) {
        			continue;
        		}
        		if(!openList.contains(n)) {
        			n.calcScore(g.getEndNode(),startNode);
        			openList.add(n);
        			
        		}
        		if (tempScore<n.getH_cost()|| openList.contains(n)) {
        			n.setH_cost(tempScore);
        			n.setParent(current);
        			
        			
        		}
        		
        		System.out.println(current.getH_cost());
        		
        		
        			
        		
        	}
        	setupPointPath();
        
        	
        }
        
	}
        
	
        // Tant que l'open list n'est pas vide
        	/*while (!openList.isEmpty()) {
            Node current = getLowestCost();
         
          
            
           
            
            // Si le noeud courant correspond au noeud d'arrivée, on retrace le chemin
            if (current.isHasPlayer()){
            	
                retracePath(current,startNode);
                break;
            }

            openList.remove(current);
         
            System.out.println(current.isHasPlayer());
           
            closedList.add(current);

            for (Node n : g.getVoisin(current)) {
            	
                if (closedList.contains(n) || n.isHasObstacle()) {
                    continue;
                }
                	n.calcScore(g.getEndNode(), startNode);
              

                if (openList.contains(n)) {
                    if (n.getScore()<current.getScore()) { 
                        
                        n.setParent(current);
                        current = n;
                    }
                } else {
                    n.setF_cost(tempScore);
                    openList.add(n);
                    n.setParent(current);
                }

                n.calcH_cost(g.getEndNode());
                n.setScore(n.getF_cost() + n.getH_cost());

            }

        }
        setupPointPath();
        
    }*/
	
	
	// retourne le noeud ayant le score le plus petit
	private Node getLowestCost() {
        Node lowest = openList.get(0);
        for (Node n : openList) {
            if (n.getScore() < lowest.getScore()) {
                lowest = n;
            }
        }
        return lowest;
	}
        

	// Trace le chemin issu de l'algorithme de pathfinding
	private void retracePath(Node current,Node startNode) {
        Node temp = current;
        this.path.add(current);
        
        while (temp.getParent() != null) {
            this.path.add(temp.getParent());
            temp = temp.getParent();
        }
        
        this.path.add(startNode);
    }
	
	public ArrayList<Node> getPath() {
        return path;
    }
	


}
