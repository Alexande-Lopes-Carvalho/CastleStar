package presentation;

import controle.CtrlWarrior;
import javafx.scene.image.Image;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;
import shapeSceneFX.EventHandling.ScheduledEvent;
/**
 * represente un combattant
 * @author Administrator
 *
 */
public class PresWarrior extends PresElementScene {
	/**
	 * image indiquant que la sous classe de PresWarrior ne possede pas l'image qui a été demandé
	 */
	private static Image[] missingTexture;
	/**
	 * vitesse deplacement du combattant
	 */
	private static Point speedCoef = new Point(3, 2);
	private double speed;
	private CtrlWarrior ctrlWarrior;
	private OrientedImage body, shoulder, leg;
	private PresEquipment[] presEquipment;
	private OrientedImage[] walkingLeg;
	/**
	 * indique vers quel point le personnage regarde
	 */
	private Point lookingTo;
	private boolean walking;
	private int walkingIndex;
	private ScheduledEvent walkingEvent;
	public PresWarrior(OrientedImage _body, OrientedImage _shoulder, OrientedImage _leg, OrientedImage[] _walkingLeg) {
		body = _body;
		shoulder = _shoulder;
		leg = _leg;
		walkingLeg = _walkingLeg;
		lookingTo = new Point(0,0);
		presEquipment = new PresEquipment[2];
	}
	
	protected void calc(long timePassed) {
		for(PresEquipment k : presEquipment) {
			k.calcEvent(timePassed);
		}
	}
	
	public void render() {
		translate(getCoord());
		imageMode(CENTER);
		presEquipment[0].render();
		imageMode(CORNER);
		if(walking) {
			renderOrientedImage(walkingLeg[walkingIndex]);
		} else {
			renderOrientedImage(leg);
		}
		renderOrientedImage(body);
		imageMode(CENTER);
	    presEquipment[1].render();
	    imageMode(CORNER);
		renderOrientedImage(shoulder);
		translateBack(getCoord());
		//System.out.println("End Render Warrior");
	}
	
	public void renderOrientedImage(OrientedImage img) {
		translate(img.getCoord());
		image(img.getImage(lookingTo.getX() < 0), 0, 0);
		translateBack(img.getCoord());
	}
	
	public void setLookingTo(Point point) {
		//System.out.println("setLookingTo Pres Warrior " + point);
		lookingTo.set(point);
	}
	
	public void setWalk(boolean value) {
		removeEvent(walkingEvent);
		if(value) {
			walkingEvent = new WalkingEvent().in(0);
			addEvent(walkingEvent);
		}
		walking = value;
	}
	
	public boolean getWalk() {
		return walking;
	}
	
	public int getWalkingTimeAnimation() {
		return 120;
	}
	/**
	 * Evenement qui change l'index de l'image des jambe du personnage pour permettre une animation de deplacement (evenement lancé toute les 120 milliseconde si le personnage marche)
	 * @author Administrator
	 * @see PresWarrior#getWalkingTimeAnimation()
	 */
	public class WalkingEvent implements Event{
		@Override
		public void handleEvent() {
			walkingIndex = (walkingIndex+1)%walkingLeg.length;
			walkingEvent = new WalkingEvent().in(getWalkingTimeAnimation());
			addEvent(walkingEvent);
		}
	}
	
	public void setCtrlWarrior(CtrlWarrior _ctrlWarrior) {
		ctrlWarrior = _ctrlWarrior;
	}
	
	public CtrlWarrior getCtrlWarrior() {
		return ctrlWarrior;
	}
	
	public void setPresEquipmentBack(PresEquipment e) {
		presEquipment[0] = e;
		//System.out.println("back " + (presEquipment[0] == null));
	}
	
	public void setPresEquipmentFront(PresEquipment e) {
		presEquipment[1] = e;
		//System.out.println("front " + (presEquipment[1] == null));
	}
	
	public PresEquipment[] getPresEquipment() {
		return presEquipment;
	}
	
	public Point getSpeedCoef() {
		return speedCoef;
	}
	
	public void setSpeed(double _speed) {
		speed = _speed;
	}
	
	public double getSpeed() {
		return speed;
	}

	@Override
	public boolean doRender(Point camera) { 
		Image frontEquipment = presEquipment[1].getImage(), backEquipment = presEquipment[0].getImage();
		Point frontCoord = presEquipment[1].getAnimatedOrientedImage().getCoord().copy().add(presEquipment[1].getCoord()), backCoord = presEquipment[0].getAnimatedOrientedImage().getCoord().copy().add(presEquipment[0].getCoord());
		double maxLength = Math.max(Math.sqrt(Math.pow(frontEquipment.getWidth(), 2)+ Math.pow(frontEquipment.getHeight(), 2)),Math.sqrt(Math.pow(backEquipment.getWidth(), 2)+ Math.pow(backEquipment.getHeight(), 2)))/2.;
		//System.out.println(maxLength + " " + (backCoord.getX()-(frontCoord.getX())) + " " + frontCoord.getX() + " " + backCoord.getX());
		Point posDim = new Point(getCoord().getX()+frontCoord.getX()-maxLength, backCoord.getX()-(frontCoord.getX())+2*maxLength);
		//System.out.println(getCoord() + " " + posDim);
		return (posDim.getX() <= camera.getX() && posDim.getX()+posDim.getY() >= camera.getX()+width()) || between(posDim.getX(), camera.getX(), camera.getX()+width()) || between(posDim.getX()+posDim.getY(), camera.getX(), camera.getX()+width());
	}
	/**
	 * charge les images
	 */
	public static void initImage() {
		missingTexture = new Image[2];
		missingTexture[0] = loadPixelatedImage("./data/missingTexture.png", MainEventHandler.pxSize);
		missingTexture[1] = OrientedImage.reverseImage(missingTexture[0]);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getSword() {
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getDagger() {
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getShield() {
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedProjectileLauncher getBowFront() {
		return new AnimatedProjectileLauncher(new Point(0, 0), new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getBowBack(){
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getLanceFront() {
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getLanceBack() {
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getHandBack() {
		//System.out.println("image WarriorgetHandBack");
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
	/**
	 * méthode a overridé par les sous classe si les texture sont existante
	 * (pour construire les PresEquipment)
	 * @see PresEquipment
	 */
	public AnimatedOrientedImage getHandFront() {
		//System.out.println("image WarriorgetHandFront");
		return new AnimatedOrientedImage(new Point(0, 0), missingTexture);
	}
}
