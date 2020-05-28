package presentation;

import controle.CtrlEquipment;
import javafx.scene.image.Image;
import shapeSceneFX.Point;

public class PresEquipment extends PresElementScene {
	private AnimatedOrientedImage img;
	private boolean facingLeft;
	private double rotation;
	private CtrlEquipment ctrlEquipment;
	private Point imgCoord;
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
	
	@Override
	public boolean doRender(Point camera) {
		return true;
	}
}
