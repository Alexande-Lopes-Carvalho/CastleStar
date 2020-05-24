package presentation;

import controle.CtrlEquipment;
import javafx.scene.image.Image;
import shapeSceneFX.Point;

public class PresEquipment extends PresElementScene {
	private OrientedImage[] img;
	private boolean facingLeft;
	private double rotation;
	private CtrlEquipment ctrlEquipment;
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
	
	public int getIndexImage() {
		return indexImage;
	}
	
	public void setIndexImage(int _indexImage) {
		if(_indexImage >= 0 && _indexImage < getLengthImg()) {
			indexImage = _indexImage;
		}
	}
	
	public int getLengthImg() {
		return img.length;
	}
	
	public Image getCurrentImage() {
		return img[indexImage].getImage(facingLeft);
	}
	
	public Point getOrientedImageCoord() {
		return img[indexImage].getCoord();
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
