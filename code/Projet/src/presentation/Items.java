package presentation;

import abstraction.ArrowItem;
import abstraction.HeartItem;
import abstraction.ShieldItem;
import abstraction.SwordItem;
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
	
	public static void initImage() {
		shield = EventHandler.loadPixelatedImage("./data/Item/Shield.png", MainEventHandler.pxSize);
		arrow = EventHandler.loadPixelatedImage("./data/Item/Arrow.png", MainEventHandler.pxSize);
		heart = EventHandler.loadPixelatedImage("./data/Item/Heart.png", MainEventHandler.pxSize);
		sword = EventHandler.loadPixelatedImage("./data/Item/Sword.png", MainEventHandler.pxSize);
	}
	
	public static CtrlItem shield(Point coord) {
		return new CtrlItem(new ShieldItem(new CtrlShield(), coord), new PresImage(shield));
	}
	
	public static CtrlItem arrow(int value, Point coord) {
		return new CtrlItem(new ArrowItem(value, coord), new PresImage(arrow));
	}
	
	public static CtrlItem heart(int value, Point coord) {
		return new CtrlItem(new HeartItem(value, coord), new PresImage(heart));
	}
	
	public static CtrlItem sword(Point coord) {
		return new CtrlItem(new SwordItem(new CtrlSword(),coord), new PresImage(sword));
	}
}
