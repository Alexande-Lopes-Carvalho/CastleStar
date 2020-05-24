package presentation;

import controle.CtrlPlayer;
import javafx.scene.input.KeyCode;
import shapeSceneFX.Point;

public class PresPlayer extends PresWarrior {
	private static OrientedImage img_body;
	private static OrientedImage img_shoulder;
	private static OrientedImage img_leg;
	private static OrientedImage[] img_walkingLegs;
	private static AnimatedOrientedImage img_handFront, img_handBack;
	private static AnimatedOrientedImage img_sword;
	private static AnimatedOrientedImage img_shield;
	private static AnimatedOrientedImage img_bowFront, img_bowBack;
	private static Point frontEquipment = new Point(-5, -20), backEquipment = new Point(3, -20);
	private CtrlPlayer ctrlPlayer;
	private boolean left, right, up, down;
	public PresPlayer() {
		super(img_body, img_shoulder, img_leg, img_walkingLegs);
	}
	
	public void render() {
		getCtrlWarrior().lookingTo(new Point(mouseX(), mouseY()).add(CtrlPlayer.currentLevel.getPresLevel().getCamera()).sub(getCoord()));
		super.render();
	}
	
	public void calc(long timePassed) {
		super.calc(timePassed);
		if(getWalk()) {
			Point deplacement = new Point(((left ^ right)? getSpeedCoef().getX()*((right)? 1 : -1) : 0), ((up ^ down)? getSpeedCoef().getY()*((down)? 1 : -1) :0));
			deplacement.div(deplacement.getDist()).mult(getSpeed()*timePassed);
			getCtrlPlayer().move(deplacement);
		}
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
		img_bowFront = new AnimatedOrientedImage(frontEquipment, "./data/Character/Equipment/Bow/Front");
		img_bowBack = new AnimatedOrientedImage(backEquipment, "./data/Character/Equipment/Bow/Back");
	}
	
	public AnimatedOrientedImage getSword() {
		return img_sword;
	}
	
	public AnimatedOrientedImage getShield() {
		return img_shield;
	}
	
	public AnimatedOrientedImage getBowFront() {
		return img_bowFront;
	}
	
	public AnimatedOrientedImage getBowBack() {
		return img_bowBack;
	}
	
	public AnimatedOrientedImage getHandBack() {
		return img_handBack;
	}
	
	public AnimatedOrientedImage getHandFront() {
		return img_handFront;
	}
}
