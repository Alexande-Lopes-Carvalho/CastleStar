package presentation;

import java.io.File;

import javafx.scene.image.Image;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class AnimatedOrientedImage {
	private Image[][] img = new Image[2][];
	private Point coord;
	private int index;
	public AnimatedOrientedImage(Point _coord, String ... path) {
		coord = _coord;
		index = 0;
		if(path.length == 1) {
			File file = new File(path[0]);
			File[] fileAr = (file.isDirectory())? file.listFiles() : new File[] {file};
			setSize(fileAr.length);
			for(int i = 0; i < fileAr.length; i++) {
				setImage(i, EventHandler.loadPixelatedImage(fileAr[i].getPath(), MainEventHandler.pxSize));
			}
		} else {
			setSize(path.length);
			for(int i = 0; i < path.length; i++) {
				setImage(i, EventHandler.loadPixelatedImage(path[i], MainEventHandler.pxSize));
			}
		}
	}
	
	public AnimatedOrientedImage(Point _coord, Image ... images) {
		coord = _coord;
		index = 0;
		setSize(images.length);
		for(int i = 0; i < images.length; i++) {
			setImage(i, images[i]);
		}
	}
	
	public AnimatedOrientedImage(AnimatedOrientedImage a) {
		coord = a.getCoord().copy();
		index = a.getIndex();
		img = a.getImages();
	}
	
	public Image[][] getImages() {
		return img;
	}
	
	public Image getImage(boolean pos) {
		return img[(pos)? 1 : 0][index];
	}
	
	public void setCoord(Point _coord) {
		coord.set(_coord);
	}
	
	public Point getCoord() {
		return coord;
	}
	
	public int getLength() {
		return img[0].length;
	}
	
	public void setIndex(int _index) {
		if(_index >= 0 && _index < img[0].length) {
			index = _index;
		}
	}
	
	public int getIndex() {
		return index;
	}
	
	private void setImage(int index, Image image) {
		img[0][index] = image;
		img[1][index] = OrientedImage.reverseImage(img[0][index]);
	}
	
	private void setSize(int size) {
		img[0] = new Image[size];
		img[1] = new Image[size];
	}
}
