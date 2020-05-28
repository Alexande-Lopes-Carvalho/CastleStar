import abstraction.Node;
import abstraction.GraphGame;
import java.io.*; 
import java.util.*; 
public final class Orc2 {

	private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private ArrayList<Node> path;
    
    
    public ArrayList<Node> refreshItinary(GraphGame g) {
    	
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        
        // Ajout du noeud de départ dans l'open list
    	this.openList.add(/*startNode*/);
    	
    	// Tant que l'open list n'est pas vide
    	while (!openList.isEmpty()) {
    		
    		Node currentNode = getLowestCost();
    		
    		openList.remove(/*index de current*/);
    		closedList.add(currentNode);
    		
    		// Si le noeud courrant correspond au noeud d'arrivée, on retrace le chemin
            if (currentNode.equals(/*endNode*/)) {
            	this.path = new ArrayList<>();
            	Node current = currentNode;
            	while (!(current.equals(/*startNode*/))) {
            		path.add(current);
            		current = current.getParent();
            	}
            	return Collections.reverse(path);
            }
    		
            for (Node n : g.getVoisin(current)) {
            	
            	// si le voisin est dans la closedList ou qu'il contient un obstacle
            	if (closedList.contains(n) || n.isHasObstacle()) {
                    continue;
                }
            	
            	// Mise à jour des différents scores du voisin n
            	n.calcF_cost(currentNode);
            	n.calcH_cost(currentNode);
            	n.calcScore(currentNode, currentNode);
            	
            	// Pour tous les noeuds voisins n dans l'openList
            	for (Node openNode : openList) {
            		if (n == openNode && (n.getF_cost() > openNode.getF_cost())) {
            			continue;
            		}
            	}
            	openList.add(n);
            }
    	}	
    }
    
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
}
