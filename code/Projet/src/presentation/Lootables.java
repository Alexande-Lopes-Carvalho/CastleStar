package presentation;

import abstraction.Lootable;
import abstraction.Rectangle;
import controle.CtrlItem;
import controle.CtrlLootable;
import javafx.scene.image.Image;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

/**
 * retourne des conteneurs qui feront apparaitre des items quand on les detruit, pour les placé dans un niveau
 * @author Administrator
 *
 */
public class Lootables {
	/**
	 * representation graphique d'un barril
	 */
	private static Image barrel;
	/**
	 * retourne un barril contenant des item donnée en parametre
	 * @param coord 
	 * @param items les items a faire apparaitre lors de la destruction du conteneur
	 * @return
	 */
	public static CtrlLootable barrel(Point coord, CtrlItem ... items) {
		return new CtrlLootable(new Lootable(2, 1, coord, new Rectangle(new Point(0, 15), new Point(13, 4))), new PresImage(barrel), items);
	}
	/**
	 * chargement des image
	 */
	public static void initImage() {
		barrel = EventHandler.loadPixelatedImage("./data/Lootable/Barrel.png", MainEventHandler.pxSize);
	}
}
