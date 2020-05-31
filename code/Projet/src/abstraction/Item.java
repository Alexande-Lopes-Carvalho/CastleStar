package abstraction;

import controle.CtrlPlayer;
import shapeSceneFX.Point;
/**
 * Item recuperable par un joueur
 * @author Administrator
 *
 */
public abstract class Item extends ElementScene{
	/**
	 * hitbox tel que si le joueur est dedans il recupere l'item
	 */
	private Ellipse ellipse;
	public Item(Point coord, Ellipse _ellipse) {
		super(coord);
		ellipse = _ellipse;
	}
	/**
	 * méthode a faire lorsque l'item est recuperé par un joueur
	 * @param player
	 * @return
	 */
	public abstract boolean pickedUpBy(CtrlPlayer player);  // retourne si l'item a été consommé (pour le supprimé du niveau)
	/**
	 * Si un joueur c'est deplacé on regarde si l'item doit etre recuperé par celui ci
	 * @param player
	 * @return
	 */
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
