package presentation;

import controle.CtrlEquipment;
import javafx.scene.image.Image;
import shapeSceneFX.Point;
/**
 * Represente le bres d'un personnage
 * @author Administrator
 *
 */
public class PresEquipment extends PresElementScene {
	private AnimatedOrientedImage img;
	/**
	 * indique dans quel sens on doit afficher l'image
	 */
	private boolean facingLeft;
	/**
	 * la rotation a appliqué pour l'affichage de l'image
	 */
	private double rotation;
	private CtrlEquipment ctrlEquipment;
	private Point imgCoord;
	/**
	 * dans certain cas on veut que le bras reste droit et ne s'aligne pas avec la vue du personnage (exemple arc), on utilise donc lockArm 
	 */
	private boolean lockArm;
	public PresEquipment() {
		imgCoord = new Point(0, 0);
	}
	
	public void render() {
		//System.out.println("in render PresEquipment");
		translate(imgCoord);
		rotate(rotation);
		image(getImage(), 0, 0);
		rotateBack(rotation);
		translateBack(imgCoord);
		//System.out.println("out render PresEquipment");
	}
	
	public void set(AnimatedOrientedImage _img) {
		img = _img;
	}
	
	public void setOrentation(boolean _facingLeft, Point _lookingTo) {
		facingLeft = _facingLeft;
		if(facingLeft) {
			imgCoord.set(-img.getCoord().getX(), img.getCoord().getY());
		} else {
			imgCoord.set(img.getCoord());
		}
		//rotation = _rotation+((facingLeft)? Math.PI: 0);
		calcRotation(_lookingTo);
	}
	/**
	 * met a jour la rotation 
	 * @param _lookingTo
	 */
	public void calcRotation(Point _lookingTo) {
		if(lockArm) {
			setRotation((getFacingLeft())? Math.PI*2: 0 );
		} else {
			setRotation( _lookingTo.copy().add(imgCoord.copy().div(MainEventHandler.pxSize).getVector(new Point(0, 0))).getAngle().getZ()+((facingLeft)? Math.PI: 0));
		}
	}
	
	public void setRotation(double value) {
		rotation = value;
	}
	
	public boolean getFacingLeft() {
		return facingLeft;
	}
	
	public void setLockArm(boolean a) {
		lockArm = a;
	}
	
	public AnimatedOrientedImage getAnimatedOrientedImage() {
		return img;
	}
	
	public Image getImage() {
		return img.getImage(facingLeft);
	}

	public void setCtrlEquipment(CtrlEquipment _ctrlEquipment) {
		ctrlEquipment = _ctrlEquipment;
	}
	
	public CtrlEquipment getCtrlEquipment() {
		return ctrlEquipment;
	}
	/**
	 * methode appeler pour reinitialisé le bras, principalement utilisé sur un Joueur a la fin d'un niveau 
	 */
	public void reset() { // pour joueur a fin de niveau 
	}
	
	@Override
	public boolean doRender(Point camera) {
		return true;
	}
}
