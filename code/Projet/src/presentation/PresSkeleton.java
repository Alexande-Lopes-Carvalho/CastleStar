package presentation;

import abstraction.Arrow;
import abstraction.Bow;
import controle.CtrlArrow;
import controle.CtrlBow;
import controle.CtrlPlayer;
import controle.CtrlSkeleton;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;

public class PresSkeleton extends PresEnemy {
	private static OrientedImage img_body;
	private static OrientedImage img_shoulder;
	private static OrientedImage img_leg;
	private static OrientedImage[] img_walkingLegs;
	private static AnimatedOrientedImage img_handFront, img_handBack;
	private static AnimatedOrientedImage img_bowBack;
	private PresBow bow;
	private static AnimatedProjectileLauncher img_bowFront;
	private static final int refreshRate = 100, actionRate = 900;
	private static Point frontEquipment = new Point(-5, -20), backEquipment = new Point(3, -20);
	
	public PresSkeleton() {
		super(img_body, img_shoulder, img_leg, img_walkingLegs, actionRate);
		
		
	}
	public void action() {
		if(getCtrlPlayer().getPlayer().getCoord().getDist(getCtrlEnemy().getEnemy().getCoord()) < 200) {
			//System.out.println("ACTION FROM " + getCoord() + " " + weapon + " " + weapon.getCtrlEquipment());
			
			bow.launchAction();
			
		}
	}
	
	public void setPresEquipmentFront(PresEquipment e) {
		super.setPresEquipmentFront(e);
		if(e instanceof PresBow) {
			bow = (PresBow)e;
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
		img_body = new OrientedImage(new Point(-6, -29).mult(MainEventHandler.pxSize), "./data/Enemy/Skeleton/Body.png");
		img_shoulder = new OrientedImage(new Point(-5, -22).mult(MainEventHandler.pxSize),
				"./data/Enemy/Skeleton/Shoulder.png");
		img_leg = new OrientedImage(new Point(-6, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Skeleton/Leg.png");
		img_walkingLegs = new OrientedImage[] {
				new OrientedImage(new Point(-10, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Skeleton/Walking/0.png"),
				new OrientedImage(new Point(-7, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Skeleton/Walking/1.png"),
				new OrientedImage(new Point(-8, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Skeleton/Walking/2.png"),
				new OrientedImage(new Point(-5, -13).mult(MainEventHandler.pxSize), "./data/Enemy/Skeleton/Walking/3.png") };

		img_handBack = new AnimatedOrientedImage(backEquipment, "./data/Enemy/Skeleton/Equipment/Hand/Back.png");
		img_handFront = new AnimatedOrientedImage(frontEquipment, "./data/Enemy/Skeleton/Equipment/Hand/Front.png");
		img_bowFront = new AnimatedProjectileLauncher(new Point(6, 1).mult(MainEventHandler.pxSize), frontEquipment, "./data/Enemy/Skeleton/Equipment/Bow/Front");
		img_bowBack = new AnimatedOrientedImage(backEquipment, "./data/Enemy/Skeleton/Equipment/Bow/Back");
	}
	
	public AnimatedProjectileLauncher getBowFront() {
		return new AnimatedProjectileLauncher(img_bowFront);
	}
	
	public AnimatedOrientedImage getBowBack() {
		return new AnimatedOrientedImage(img_bowBack);
	}
	
	public AnimatedOrientedImage getHandBack() {
		return new AnimatedOrientedImage(img_handBack);
	}
	
	public AnimatedOrientedImage getHandFront() {
		return new AnimatedOrientedImage(img_handFront);
	}
}
