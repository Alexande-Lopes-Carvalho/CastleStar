package abstraction;

import java.util.List;

import controle.CtrlEnemy;
import shapeSceneFX.Point;
/**
 * Un orc
 * @author Administrator
 *
 */
public class Orc extends Enemy {
	/**
	 * Represente le nombre de visite avant l'abondon de la recherche de l'itineraire
	 * @see AStarGraph#getPath(int, controle.CtrlEntity, controle.CtrlEntity)
	 */
	private static final int nbVisit = 300;
	/**
	 * Graph utilisé pour la recherche de l'itineraire
	 */
	private static AStarGraph aStarGraph;
	/**
	 * liste des noeud a parcourir pour atteindre le joueur 
	 */
	private List<Point> pathList;
	public Orc(Point _coord) {
		super(200, 0, new Point(-50, 0), 0.05, 8, _coord, new Rectangle(new Point(-5, -3), new Point(10, 3)));
	}
	
	public int getNbVisit() {
		return nbVisit;
	}
	/**
	 * Calcule de l'itineraire via l'algorithme de pathfinding A*
	 */
	public void refreshItinary(CtrlEnemy e) {
		if(getPlayerFocused() != null) {
			pathList = aStarGraph.getPath(getNbVisit(), e, getPlayerFocused());
			if(pathList == null) {
				setObjective(null);
			} else if(pathList.size() > 0) {
				//System.out.println(pathList.size());
				setObjective(pathList.get(0));
			}
			
		}
	}
	
	public void endObjective() {
		pathList.remove(0);
		if(pathList.size() > 0) {
			setObjective(pathList.get(0));
		} else {
			setObjective(null);
		}
	}
	
	public static void setGraph(AStarGraph _aStarGraph) {
		aStarGraph = _aStarGraph;
	}
}
