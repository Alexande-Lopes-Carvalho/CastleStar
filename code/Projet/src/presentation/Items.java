package presentation;

import abstraction.ArrowItem;
import abstraction.BowItem;
import abstraction.HeartItem;
import abstraction.ShieldItem;
import abstraction.SwordItem;
import controle.CtrlBow;
import controle.CtrlItem;
import controle.CtrlShield;
import controle.CtrlSword;
import javafx.scene.image.Image;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class Items {
	private static Image shield;
	private static Image arrow;
	private static Image heart;
	private static Image sword;
	private static Image bow;
	
	public static void initImage() {
		shield = EventHandler.loadPixelatedImage("./data/Item/Shield.png", MainEventHandler.pxSize);
		arrow = EventHandler.loadPixelatedImage("./data/Item/Arrow.png", MainEventHandler.pxSize);
		heart = EventHandler.loadPixelatedImage("./data/Item/Heart.png", MainEventHandler.pxSize);
		sword = EventHandler.loadPixelatedImage("./data/Item/Sword.png", MainEventHandler.pxSize);
		bow = EventHandler.loadPixelatedImage("./data/Item/Bow.png", MainEventHandler.pxSize);
	}
	
	public static CtrlItem shield(Point coord) {
		return new CtrlItem(new ShieldItem(new CtrlShield(), coord), new PresImage(shield));
	}
	
	public static CtrlItem shield() {
		return new CtrlItem(new ShieldItem(new CtrlShield(), new Point(0, 0)), new PresImage(shield));
	}
	
	public static CtrlItem arrow(int value, Point coord) {
		return new CtrlItem(new ArrowItem(value, coord), new PresImage(arrow));
	}
	
	public static CtrlItem arrow(int value) {
		return new CtrlItem(new ArrowItem(value, new Point(0, 0)), new PresImage(arrow));
	}
	
	public static CtrlItem heart(int value, Point coord) {
		return new CtrlItem(new HeartItem(value, coord), new PresImage(heart));
	}
	
	public static CtrlItem heart(int value) {
		return new CtrlItem(new HeartItem(value, new Point(0, 0)), new PresImage(heart));
	}
	
	public static CtrlItem sword(Point coord) {
		return new CtrlItem(new SwordItem(new CtrlSword(),coord), new PresImage(sword));
	}
	
	public static CtrlItem sword() {
		return new CtrlItem(new SwordItem(new CtrlSword(), new Point(0, 0)), new PresImage(sword));
	}
	
	public static CtrlItem bow(Point coord) {
		return new CtrlItem(new BowItem(new CtrlBow(), coord), new PresImage(bow));
	}
	
	public static CtrlItem bow() {
		return new CtrlItem(new BowItem(new CtrlBow(), new Point(0, 0)), new PresImage(bow));
	}
}
