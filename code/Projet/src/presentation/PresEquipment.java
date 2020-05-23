package presentation;

import controle.CtrlEquipment;
import shapeSceneFX.Point;

public class PresEquipment extends PresElementScene {
	private OrientedImage[] img;
	private boolean facingLeft;
	private double rotation;
	private CtrlEquipment ctrlHand;
	private int indexImage;
	private Point imgCoord;
	public PresEquipment() {
		indexImage = 0;
	}
	
	public void render() {
		//System.out.println("in render PresEquipment");
		translate(imgCoord);
		rotate(rotation);
		image(img[indexImage].getImage(facingLeft), 0, 0);
		rotateBack(rotation);
		translateBack(imgCoord);
		//System.out.println("out render PresEquipment");
	}
	
	public void set(OrientedImage[] _img) {
		img = _img;
	}
	
	public void setOrentation(boolean _facingLeft, double _rotation) {
		facingLeft = _facingLeft;
		rotation = _rotation+((facingLeft)? Math.PI: 0);
		if(facingLeft) {
			imgCoord = new Point(-img[indexImage].getCoord().getX(), img[indexImage].getCoord().getY());
		} else {
			imgCoord = img[indexImage].getCoord();
		}
	}
	
	public void setCtrlHand(CtrlEquipment _ctrlHand) {
		ctrlHand = _ctrlHand;
	}
	
	@Override
	public boolean doRender(Point camera) {
		// TODO Auto-generated method stub
		return true;
	}
}
