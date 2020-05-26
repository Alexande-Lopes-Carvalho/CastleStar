package controle;

import PresItem.PresArrow;
import abstraction.Arrow;

public class CtrlArrow extends CtrlElementScene{
	private Arrow arrow;
	private PresArrow presArrow;
	public CtrlArrow(Arrow arrow,PresArrow presArrow) {
		super(arrow,presArrow);
		this.arrow = arrow;
		this.presArrow = presArrow;
		presArrow.setCtrlArrow(this);
	}
	public Arrow getArrow() {
		return arrow;
		
	}
	public PresArrow getPresArrow() {
		return presArrow;
	}
	public void move(Point deplacement){
		//d'ou viens currrentlevel? 
		Arrow.move(deplacement/presArrow.pxSize,currentLevel.getCtrlElementCollidableList());
		presArrow.()
		
		
	}
}
