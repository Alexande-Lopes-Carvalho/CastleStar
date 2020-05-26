package presentation;

import shapeSceneFX.Point;

public class PresOrc extends PresEnemy {
	private static OrientedImage img_body;
	private static OrientedImage img_shoulder;
	private static OrientedImage img_leg;
	private static OrientedImage[] img_walkingLegs;
	private static AnimatedOrientedImage img_handFront, img_handBack;
	private static AnimatedOrientedImage img_sword;
	private static AnimatedOrientedImage img_shield;
	private static Point frontEquipment = new Point(-5, -20), backEquipment = new Point(3, -20);
	public PresOrc() {
		super(img_body, img_shoulder, img_leg, img_walkingLegs);
	}
	
	// A COMPLETER
	// ...
	
	public static void initImage() {
		frontEquipment.mult(MainEventHandler.pxSize);
		backEquipment.mult(MainEventHandler.pxSize);
		img_body = new OrientedImage(new Point(-6, -32).mult(MainEventHandler.pxSize), "./data/Enemy/Orc/Body.png");
		img_shoulder = new OrientedImage(new Point(-6, -23).mult(MainEventHandler.pxSize), "./data/Enemy/Orc/Shoulder.png");
		img_leg = new OrientedImage(new Point(-6, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Orc/Leg.png");
		img_walkingLegs = new OrientedImage[]{new OrientedImage(new Point(-10, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Orc/Walking/0.png"),
				new OrientedImage(new Point(-7, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Orc/Walking/1.png"),
				new OrientedImage(new Point(-8, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Orc/Walking/2.png"),
				new OrientedImage(new Point(-5, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Orc/Walking/3.png")};
		
		img_handBack = new AnimatedOrientedImage(backEquipment, "./data/Enemy/Orc/Equipment/Hand/Back.png");
		img_handFront = new AnimatedOrientedImage(frontEquipment, "./data/Enemy/Orc/Equipment/Hand/Front.png");
		
		img_sword = new AnimatedOrientedImage(frontEquipment, "./data/Enemy/Orc/Equipment/Sword");
		
		img_shield = new AnimatedOrientedImage(backEquipment, "./data/Enemy/Orc/Equipment/Shield");
	}
	
	public AnimatedOrientedImage getSword() {
		return img_sword;
	}
	
	public AnimatedOrientedImage getShield() {
		return img_shield;
	}
	
	public AnimatedOrientedImage getHandBack() {
		return img_handBack;
	}
	
	public AnimatedOrientedImage getHandFront() {
		return img_handFront;
	}
}
