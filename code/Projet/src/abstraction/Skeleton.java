package abstraction;

import java.util.ArrayList;
import java.util.List;

import controle.CtrlEnemy;
import controle.CtrlEntity;
import controle.CtrlSkeleton;
import shapeSceneFX.Point;

public class Skeleton extends Enemy {
	
	private Point coord;
	private Point deplacement;
	public Skeleton(Point _coord) {
		super(200, 10, new Point(-50, 0), 0.05, 8, _coord, new Rectangle(new Point(-5, -3), new Point(10, 3)));

		coord = _coord;
	}

	@Override
	public void refreshItinary(CtrlEnemy e) {
		
		
		setDeplacement(new Point(getCoord().getX(),getPlayerFocused().getPlayer().getCoord().getY()));
		
		if (getDeplacement() == null) {
			setObjective(null);
		}
		else {
			setObjective(getDeplacement());
		}
		
			
		}
	public Point getDeplacement(){
		return deplacement ;
	}
	public void setDeplacement(Point depl) {
		this.deplacement =depl;
		
	}
	public void endObjective() {
		setDeplacement(null);
		}

	

	
}
