package presentation;

import controle.CtrlPlayer;
import javafx.scene.input.KeyCode;
import shapeSceneFX.Point;

public class PresPlayer extends PresWarrior {
	private static OrientedImage img_body;
	private static OrientedImage img_shoulder;
	private static OrientedImage img_leg;
	private static OrientedImage[] img_walkingLegs;
	private static OrientedImage[] img_handFront, img_handBack;
	private static OrientedImage[] img_sword;
	private static OrientedImage[] img_shield;
	private static OrientedImage[] img_bowFront, img_bowBack;
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
		img_body = new OrientedImage("./data/Character/Body.png", new Point(-6, -30).mult(MainEventHandler.pxSize));
		img_shoulder = new OrientedImage("./data/Character/Shoulder.png", new Point(-5, -22).mult(MainEventHandler.pxSize));
		img_leg = new OrientedImage("./data/Character/Leg.png", new Point(-6, -13).mult(MainEventHandler.pxSize));
		img_walkingLegs = new OrientedImage[]{new OrientedImage("./data/Character/Walking/0.png", new Point(-10, -13).mult(MainEventHandler.pxSize)),
				new OrientedImage("./data/Character/Walking/1.png", new Point(-7, -13).mult(MainEventHandler.pxSize)),
				new OrientedImage("./data/Character/Walking/2.png", new Point(-8, -13).mult(MainEventHandler.pxSize)),
				new OrientedImage("./data/Character/Walking/3.png", new Point(-5, -13).mult(MainEventHandler.pxSize))};
		
		img_handBack = new OrientedImage[] {new OrientedImage("./data/Character/Equipment/Hand/Back.png", backEquipment)};
		img_handFront = new OrientedImage[] {new OrientedImage("./data/Character/Equipment/Hand/Front.png", frontEquipment)};
		
		img_sword = new OrientedImage[] {new OrientedImage("./data/Character/Equipment/Sword/0.png", frontEquipment),
				new OrientedImage("./data/Character/Equipment/Sword/1.png", frontEquipment), 
				new OrientedImage("./data/Character/Equipment/Sword/2.png", frontEquipment)};
		
		img_shield = new OrientedImage[] {new OrientedImage("./data/Character/Equipment/Shield/0.png", backEquipment),
				new OrientedImage("./data/Character/Equipment/Shield/1.png", backEquipment)};
		img_bowFront = new OrientedImage[] {new OrientedImage("./data/Character/Equipment/Bow/Front/0.png", frontEquipment),
				new OrientedImage("./data/Character/Equipment/Bow/Front/1.png", frontEquipment)};
		img_bowBack = new OrientedImage[] {new OrientedImage("./data/Character/Equipment/Bow/Back/0.png", backEquipment)};
	}
	
	public OrientedImage[] getSword() {
		return img_sword;
	}
	
	public OrientedImage[] getShield() {
		return img_shield;
	}
	
	public OrientedImage[] getBowFront() {
		return img_bowFront;
	}
	
	public OrientedImage[] getBowBack() {
		return img_bowBack;
	}
	
	public OrientedImage[] getHandBack() {
		return img_handBack;
	}
	
	public OrientedImage[] getHandFront() {
		return img_handFront;
	}
}
