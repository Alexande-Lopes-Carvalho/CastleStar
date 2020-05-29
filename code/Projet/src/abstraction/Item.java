package abstraction;

import controle.CtrlPlayer;
import shapeSceneFX.Point;

public abstract class Item extends ElementScene{
	private Ellipse ellipse;
	public Item(Point coord, Ellipse _ellipse) {
		super(coord);
		ellipse = _ellipse;
	}
	
	public abstract boolean pickedUpBy(CtrlPlayer player);  // retourne si l'item a été consommé (pour le supprimé du niveau)
	
	public boolean playerMoved(CtrlPlayer player) {  // retourne si l'item a été consommé (pour le supprimé du niveau)
		if(getEllipse().pointInside(getCoord().getVector(player.getEntity().getCenterHitbox()))) {
			return pickedUpBy(player);
		}
		return false;
	}
	
	public Ellipse getEllipse() {
		return ellipse;
	}
}
