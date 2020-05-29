package presentation;

import controle.CtrlPlayer;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;

public class PresOrc extends PresEnemy {
	private static OrientedImage img_body;
	private static OrientedImage img_shoulder;
	private static OrientedImage img_leg;
	private static OrientedImage[] img_walkingLegs;
	private static AnimatedOrientedImage img_handFront, img_handBack;
	private static AnimatedOrientedImage img_sword;
	private static AnimatedOrientedImage img_shield;
	private static Point frontEquipment = new Point(-5, -20), backEquipment = new Point(3, -20);
	private static final int refreshRate = 100, actionRate = 900;
	private PresWeapon weapon;
	private PresShield shield;
	public PresOrc() {
		super(img_body, img_shoulder, img_leg, img_walkingLegs, actionRate);
	}
	
	public void action() {
		if(getCtrlPlayer().getPlayer().getCoord().getDist(getCtrlEnemy().getEnemy().getCoord()) < 35) {
			//System.out.println("ACTION FROM " + getCoord() + " " + weapon + " " + weapon.getCtrlEquipment());
			weapon.launchAction();
		}
	}
	
	public void setPresEquipmentFront(PresEquipment e) {
		super.setPresEquipmentFront(e);
		if(e instanceof PresWeapon) {
			weapon = (PresWeapon)e;
		}
	}
	
	public void setPresEquipmentBack(PresEquipment e) {
		super.setPresEquipmentBack(e);
		if(e instanceof PresShield) {
			shield = (PresShield)e;
			shield.requestUse();
		}
	}
	
	public void setCtrlPlayer(CtrlPlayer _ctrlPlayer) {
		super.setCtrlPlayer(_ctrlPlayer);
		//System.out.println("FOCUS FROM " + getCoord() + _ctrlPlayer);
		if(getCtrlPlayer() != null) {
			addEvent(new RefreshEvent().in(0));
		}
	}
	

	public class RefreshEvent implements Event{
		@Override
		public void handleEvent() {
			getCtrlEnemy().refreshItinary();
			addEvent(new RefreshEvent().in(refreshRate));
		}
	}
	
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
		return new AnimatedOrientedImage(img_sword);
	}
	
	public AnimatedOrientedImage getShield() {
		return new AnimatedOrientedImage(img_shield);
	}
	
	public AnimatedOrientedImage getHandBack() {
		return new AnimatedOrientedImage(img_handBack);
	}
	
	public AnimatedOrientedImage getHandFront() {
		return new AnimatedOrientedImage(img_handFront);
	}
}
