package presentation;

import abstraction.ArrowItem;
import abstraction.BowItem;
import abstraction.DaggerItem;
import abstraction.HeartItem;
import abstraction.LanceItem;
import abstraction.ShieldItem;
import abstraction.SwordItem;
import controle.CtrlBow;
import controle.CtrlDagger;
import controle.CtrlItem;
import controle.CtrlLance;
import controle.CtrlShield;
import controle.CtrlSword;
import javafx.scene.image.Image;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;


/**
 * Permet de placé de crée des item, pour les placé dans un niveau
 * @author Administrator
 * 
 * @see CtrlItem
 */
public class Items {
	private static Image shield;
	private static Image arrow;
	private static Image heart;
	private static Image sword;
	private static Image bow;
	private static Image lance;
	private static Image dagger;
	/**
	 * Chargement des images
	 */
	public static void initImage() {
		shield = EventHandler.loadPixelatedImage("./data/Item/Shield.png", MainEventHandler.pxSize);
		arrow = EventHandler.loadPixelatedImage("./data/Item/Arrow.png", MainEventHandler.pxSize);
		heart = EventHandler.loadPixelatedImage("./data/Item/Heart.png", MainEventHandler.pxSize);
		sword = EventHandler.loadPixelatedImage("./data/Item/Sword.png", MainEventHandler.pxSize);
		bow = EventHandler.loadPixelatedImage("./data/Item/Bow.png", MainEventHandler.pxSize);
		lance = EventHandler.loadPixelatedImage("./data/Item/Lance.png", MainEventHandler.pxSize);
		dagger = EventHandler.loadPixelatedImage("./data/Item/Dagger.png", MainEventHandler.pxSize);
	}
	/**
	 * retourne un item bouclier
	 * @param coord coordonnée de l'item
	 * @return
	 */
	public static CtrlItem shield(Point coord) {
		return new CtrlItem(new ShieldItem(new CtrlShield(), coord), new PresImage(shield));
	}
	
	/**
	 * retourne un item bouclier
	 * @return
	 */
	public static CtrlItem shield() {
		return new CtrlItem(new ShieldItem(new CtrlShield(), new Point(0, 0)), new PresImage(shield));
	}
	/**
	 * retourne un item fleche
	 * @param value le nombre de fleche a donné au joueur qui l'a ramassé
	 * @param coord coordonnée de l'item
	 * @return
	 */
	public static CtrlItem arrow(int value, Point coord) {
		return new CtrlItem(new ArrowItem(value, coord), new PresImage(arrow));
	}
	/**
	 * retourne un item fleche
	 * @param value le nombre de fleche a donné au joeur qui l'a ramassé
	 * @return
	 */
	public static CtrlItem arrow(int value) {
		return new CtrlItem(new ArrowItem(value, new Point(0, 0)), new PresImage(arrow));
	}
	/**
	 * retourne un item coeur
	 * @param value le nombre d'unité de vie (demi coeur) a donnée au joueur qui l'a ramassé
	 * @param coord coordonnée de l'item
	 * @return
	 */
	public static CtrlItem heart(int value, Point coord) {
		return new CtrlItem(new HeartItem(value, coord), new PresImage(heart));
	}
	/**
	 * retourne un item coeur
	 * @param value le nombre d'unité de vie (demi coeur) a donnée au joueur qui l'a ramassé
	 * @return
	 */
	public static CtrlItem heart(int value) {
		return new CtrlItem(new HeartItem(value, new Point(0, 0)), new PresImage(heart));
	}
	/**
	 * retourne un item épée
	 * @param coord coorodnnée de l'item
	 * @return
	 */
	public static CtrlItem sword(Point coord) {
		return new CtrlItem(new SwordItem(new CtrlSword(),coord), new PresImage(sword));
	}
	/**
	 * retourne un item épée
	 * @return
	 */
	public static CtrlItem sword() {
		return new CtrlItem(new SwordItem(new CtrlSword(), new Point(0, 0)), new PresImage(sword));
	}
	/**
	 * retourne un item arc
	 * @param coord coordonnée de l'item
	 * @return
	 */
	public static CtrlItem bow(Point coord) {
		return new CtrlItem(new BowItem(new CtrlBow(), coord), new PresImage(bow));
	}
	/**
	 * retourne un item arc
	 * @return
	 */
	public static CtrlItem bow() {
		return new CtrlItem(new BowItem(new CtrlBow(), new Point(0, 0)), new PresImage(bow));
	}
	/**
	 * retourne un item lance
	 * @param coord coordonnée
	 * @return
	 */
	public static CtrlItem lance(Point coord) {
		return new CtrlItem(new LanceItem(new CtrlLance(), coord), new PresImage(lance));
	}
	/**
	 * retourne un item lance
	 * @return
	 */
	public static CtrlItem lance() {
		return new CtrlItem(new LanceItem(new CtrlLance(), new Point(0, 0)), new PresImage(lance));
	}
	/**
	 * retourne un item dague
	 * @param coord coordonnée de l'item
	 * @return
	 */
	public static CtrlItem dagger(Point coord) {
		return new CtrlItem(new DaggerItem(new CtrlDagger(), coord), new PresImage(dagger));
	}
	/**
	 * retourne un item dague
	 * @return
	 */
	public static CtrlItem dagger() {
		return new CtrlItem(new DaggerItem(new CtrlDagger(), new Point(0, 0)), new PresImage(dagger));
	}
}
