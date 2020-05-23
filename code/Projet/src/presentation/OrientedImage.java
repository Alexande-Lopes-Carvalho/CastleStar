package presentation;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class OrientedImage {
	private Image[] img = new Image[2];
	private Point coord;
	public OrientedImage(String path, Point _coord) {
		this(EventHandler.loadPixelatedImage(path, MainEventHandler.pxSize), _coord);
	}
	
	public OrientedImage(Image image, Point _coord) {
		img[0] = image;
		img[1] = reverseImage(img[0]);
		coord = _coord;
	}
	
	public OrientedImage(Image[] image, Point _coord) {
		img = image;
		coord = _coord;
	}
	
	public Image[] getImages() {
		return img;
	}
	
	public Image getImage(boolean pos) {
		return img[(pos)? 1 : 0];
	}
	
	public Point getCoord() {
		return coord;
	}
	
	public static Image reverseImage(Image img) {
		WritableImage res = new WritableImage((int)img.getWidth(), (int)img.getHeight());
		PixelWriter writer = res.getPixelWriter();
		PixelReader reader = img.getPixelReader();
		for(int i = 0; i < res.getWidth()*res.getHeight(); i++) {
			writer.setArgb((int)(i%res.getWidth()), (int)(i/res.getWidth()), reader.getArgb((int)(res.getWidth()-1-i%res.getWidth()), (int)(i/res.getWidth())));
		}
		return res;
	}
}
