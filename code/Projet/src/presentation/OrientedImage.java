package presentation;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

/**
 * Image ayant une coordonnée et un sens (gauche droite)
 * @author Administrator
 *
 */
public class OrientedImage {
	private Image[] img = new Image[2];
	private Point coord;
	/**
	 * charge l'image indiqué par le chemin path
	 * @param _coord
	 * @param path
	 */
	public OrientedImage(Point _coord, String path) {
		this(_coord, EventHandler.loadPixelatedImage(path, MainEventHandler.pxSize));
	}
	
	public OrientedImage(Point _coord, Image image) {
		img[0] = image;
		img[1] = reverseImage(img[0]);
		coord = _coord;
	}
	
	public OrientedImage(Point _coord, Image[] image) {
		img = image;
		coord = _coord;
	}
	
	public Image[] getImages() {
		return img;
	}
	/**
	 * retourne l'image en fonction du sens demandé
	 * @param pos indique le sens de l'image, (false image normal, true image symetrique par rapport a l'axe Y)
	 * @return
	 */
	public Image getImage(boolean pos) {
		return img[(pos)? 1 : 0];
	}
	
	public Point getCoord() {
		return coord;
	}
	/**
	 * retourne le symetrique de l'image par rapport a l'axe Y
	 * @param img
	 * @return
	 */
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
