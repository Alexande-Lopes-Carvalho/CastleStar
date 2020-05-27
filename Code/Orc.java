import abstraction.Node;
import abstraction.GraphGame;
import java.util.ArrayList;

public final class Orc extends Enemy {
	

    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private ArrayList<Node> path;
	
	
	// process A* algorithm
	public void refreshItinary(GraphGame g) {
		
		// Si le noeud de départ est égal au noeud d'arrivée 
        if (/*startNode*/ == null && /*endNode*/ == null) {
            return;
        }

        if (start.equals(/*endNode*/)) {
            this.path = new ArrayList<>();
            return;
        }

        this.path = new ArrayList<>();

        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        
        // Ajout du noeud de départ dans l'open list
        this.openList.add(/*startNode*/);

        // Tant que l'open list n'est pas vide
        while (!openList.isEmpty()) {
            Node current = getLowestCost();
            
            // Si le noeud courrant correspond au noeud d'arrivée, on retrace le chemin
            if (current.equals(/*endNode*/)) {
                retracePath(current);
                break;
            }

            openList.remove(current);
            closedList.add(current);

            for (Node n : g.getVoisin(current)) {

                if (closedList.contains(n) || n.isHasObstacle()) {
                    continue;
                }
                
                // tempScore correspond à la distance entre le noeud courrant et le noeud de départ
                double tempScore = current.getF_cost() + current.distBwNode(n);

                if (openList.contains(n)) {
                    if (tempScore < n.getF_cost()) {
                        n.setF_cost(tempScore);
                        n.setParent(current);
                    }
                } else {
                    n.setF_cost(tempScore);
                    openList.add(n);
                    n.setParent(current);
                }

                n.setH_cost(n.getH_cost(/*endNode*/));
                n.setScore(n.getF_cost() + n.getH_cost());

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
	
	// Trace le chemin issu de l'algorithme de pathfinding
	private void retracePath(Node current) {
        Node temp = current;
        this.path.add(current);
        
        while (temp.getParent() != null) {
            this.path.add(temp.getParent());
            temp = temp.getParent();
        }
        
        this.path.add(/*startNode*/);
    }
	
	public ArrayList<Node> getPath() {
        return path;
    }
	
}