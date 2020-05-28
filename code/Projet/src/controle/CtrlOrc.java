package controle;


import abstraction.Orc;

import presentation.PresOrc;
import shapeSceneFX.Point;

public class CtrlOrc extends CtrlEnemy {
	private PresOrc presOrc;
	private Orc orc;
	public CtrlOrc(Orc orc, PresOrc presOrc) {
		super(orc,presOrc);
		this.orc =orc;
		this.presOrc = presOrc;
	}
	public CtrlOrc setupCtrlOrc() {
		return(this);
	}
	public void refreshItinary() {
		orc.refreshItinary(currentLevel.getGraphGame());
		
	
	}
	public void move(Point deplacement) {
		super.move(deplacement);
		
	}

}
