package presentation;

import controle.CtrlPlayer;
import javafx.scene.input.KeyCode;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.TransferableEvent;

public class PresPlayer extends PresWarrior {
	private static OrientedImage img_body;
	private static OrientedImage img_shoulder;
	private static OrientedImage img_leg;
	private static OrientedImage[] img_walkingLegs;
	private static AnimatedOrientedImage img_handFront, img_handBack;
	private static AnimatedOrientedImage img_sword;
	private static AnimatedOrientedImage img_shield;
	private static AnimatedOrientedImage img_bowBack;
	private static AnimatedOrientedImage img_lanceFront, img_lanceBack;
	private static AnimatedOrientedImage img_dagger;
	private static AnimatedProjectileLauncher img_bowFront;
	public static Point frontEquipment = new Point(-5, -20), backEquipment = new Point(3, -20);
	private CtrlPlayer ctrlPlayer;
	private boolean left, right, up, down;
	private PresInventory presInventory;
	public PresPlayer() {
		super(img_body, img_shoulder, img_leg, img_walkingLegs);
	}
	
	public void render() {
		getCtrlWarrior().lookingTo(new Point(mouseX(), mouseY()).add(CtrlPlayer.currentLevel.getPresLevel().getCamera()).sub(getCoord()));
		super.render();
	}
	
	public void calc(long timePassed) {
		super.calc(timePassed);
		presInventory.calcEvent(timePassed);
		if(getWalk()) {
			Point deplacement = new Point(((left ^ right)? getSpeedCoef().getX()*((right)? 1 : -1) : 0), ((up ^ down)? getSpeedCoef().getY()*((down)? 1 : -1) :0));
			deplacement.div(deplacement.getDist()).mult(getSpeed()*timePassed);
			getCtrlPlayer().move(deplacement);
		}
	}
	
	public void reset() {
		left = false;
		right = false;
		up = false;
		down = false;
		getPresEquipment()[0].reset();
		getPresEquipment()[1].reset();
	}
	
	public void keyPressed() {
		//System.out.println("Press " + keyCode());
		setMove(true, keyCode());
	}
	
	public void keyReleased() {
		//System.out.println("Releas " + keyCode());
		setMove(false, keyCode());
	}
	
	public void setMove(boolean value, KeyCode k) {
		if(k.equals(KeyCode.D)) {
			right = value;
		} else if(k.equals(KeyCode.Q)) {
			left = value;
		} else if(k.equals(KeyCode.Z)) {
			up = value;
		} else if(k.equals(KeyCode.S)) {
			down = value;
		}
		if(right ^ left || up ^ down) {
			getCtrlPlayer().setWalking(true);
		} else {
			getCtrlPlayer().setWalking(false);
		}
	}
	
	public void setCtrlPlayer(CtrlPlayer _ctrlPlayer) {
		ctrlPlayer = _ctrlPlayer;
	}
	
	public CtrlPlayer getCtrlPlayer() {
		return ctrlPlayer;
	}
	
	public void setPresInventory(PresInventory _presInventory) {
		presInventory = _presInventory;
	}
	
	public PresInventory getPresInventory() {
		return presInventory;
	}
	
	public void transferEvent(TransferableEvent e) {
		addEvent(e.in(0));
		getPresInventory().addEvent(e.transfer().in(0));
		for(PresEquipment k : getPresEquipment()) {
			k.addEvent(e.in(0));
		}
	}
	
	public static void initImage() {
		frontEquipment.mult(MainEventHandler.pxSize);
		backEquipment.mult(MainEventHandler.pxSize);
		img_body = new OrientedImage(new Point(-6, -30).mult(MainEventHandler.pxSize), "./data/Character/Body.png");
		img_shoulder = new OrientedImage(new Point(-5, -22).mult(MainEventHandler.pxSize), "./data/Character/Shoulder.png");
		img_leg = new OrientedImage(new Point(-6, -13).mult(MainEventHandler.pxSize), "./data/Character/Leg.png");
		img_walkingLegs = new OrientedImage[]{new OrientedImage(new Point(-10, -13).mult(MainEventHandler.pxSize), "./data/Character/Walking/0.png"),
				new OrientedImage(new Point(-7, -13).mult(MainEventHandler.pxSize), "./data/Character/Walking/1.png"),
				new OrientedImage(new Point(-8, -13).mult(MainEventHandler.pxSize), "./data/Character/Walking/2.png"),
				new OrientedImage(new Point(-5, -13).mult(MainEventHandler.pxSize), "./data/Character/Walking/3.png")};
		
		img_handBack = new AnimatedOrientedImage(backEquipment, "./data/Character/Equipment/Hand/Back.png");
		img_handFront = new AnimatedOrientedImage(frontEquipment, "./data/Character/Equipment/Hand/Front.png");
		
		img_sword = new AnimatedOrientedImage(frontEquipment, "./data/Character/Equipment/Sword");
		
		img_shield = new AnimatedOrientedImage(backEquipment, "./data/Character/Equipment/Shield");
		img_bowFront = new AnimatedProjectileLauncher(new Point(6, 1).mult(MainEventHandler.pxSize), frontEquipment, "./data/Character/Equipment/Bow/Front");
		img_bowBack = new AnimatedOrientedImage(backEquipment, "./data/Character/Equipment/Bow/Back");
		
		img_lanceFront = new AnimatedOrientedImage(frontEquipment, "./data/Character/Equipment/Lance/Front");
		img_lanceBack = new AnimatedOrientedImage(backEquipment, "./data/Character/Equipment/Lance/Back");
		
		img_dagger = new AnimatedOrientedImage(frontEquipment, "./data/Character/Equipment/Dagger");
		
		//System.out.println(img_lanceFront.getImages()[0].length + " " + img_lanceBack.getImages()[0].length);
	}
	
	public AnimatedOrientedImage getSword() {
		return new AnimatedOrientedImage(img_sword);
	}
	
	public AnimatedOrientedImage getDagger() {
		return new AnimatedOrientedImage(img_dagger);
	}
	
	public AnimatedOrientedImage getShield() {
		return new AnimatedOrientedImage(img_shield);
	}
	
	public AnimatedProjectileLauncher getBowFront() {
		return new AnimatedProjectileLauncher(img_bowFront);
	}
	
	public AnimatedOrientedImage getBowBack() {
		return new AnimatedOrientedImage(img_bowBack);
	}
	
	public AnimatedOrientedImage getLanceFront() {
		return new AnimatedOrientedImage(img_lanceFront);
	}
	
	public AnimatedOrientedImage getLanceBack() {
		return new AnimatedOrientedImage(img_lanceBack);
	}
	
	public AnimatedOrientedImage getHandBack() {
		return new AnimatedOrientedImage(img_handBack);
	}
	
	public AnimatedOrientedImage getHandFront() {
		return new AnimatedOrientedImage(img_handFront);
	}
}
