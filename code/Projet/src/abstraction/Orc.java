package abstraction;

import java.util.List;

import controle.CtrlEnemy;
import shapeSceneFX.Point;

public class Orc extends Enemy {
	private static final int nbVisit = 300;
	private static AStarGraph aStarGraph;
	private List<Point> pathList;
	public Orc(Point _coord) {
		super(200, 0, new Point(-50, 0), 0.05, 8, _coord, new Rectangle(new Point(-5, -3), new Point(10, 3)));
	}
	
	public void refreshItinary(CtrlEnemy e) {
		if(getPlayerFocused() != null) {
			pathList = aStarGraph.getPath(nbVisit, e, getPlayerFocused());
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
