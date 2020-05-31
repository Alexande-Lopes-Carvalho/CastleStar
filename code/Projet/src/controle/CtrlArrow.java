package controle;


import presentation.MainEventHandler;
import presentation.PresArrow;
import shapeSceneFX.Point;

import java.util.Observable;

import abstraction.Arrow;
/**
 * Fleche
 * @author Administrator
 * @see Arrow
 * @see PresArrow
 */
public class CtrlArrow extends CtrlElementScene{
	private Arrow arrow;
	private PresArrow presArrow;
	public CtrlArrow(Arrow arrow) {
		super(arrow, new PresArrow(arrow.getCoord(), arrow.getCelerity(), arrow.getFacingLeft()));
		this.arrow = arrow;
		presArrow = (PresArrow) getPresElementScene();
		presArrow.setCtrlArrow(this);
		arrow.addObserver(this);
	}
	public Arrow getArrow() {
		return arrow;
		
	}
	public PresArrow getPresArrow() {
		return presArrow;
	}
	
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if(arg.equals(Arrow.KILL)) {
			currentLevel.remove((CtrlElementScene)this);
		}
	}
	
	public void move(Point deplacement){
		//d'ou viens currrentlevel? => CtrlElementScene via un champ static
		arrow.move(deplacement.div(MainEventHandler.pxSize));
	}
}
