package presentation;

import java.io.File;

import javafx.scene.image.Image;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

/**
 * Plusieurs Images ayant un sens (gauche droite) 
 * @author Administrator
 *
 */
public class AnimatedOrientedImage {
	private Image[][] img = new Image[2][];
	/**
	 * coordonnée de l'image
	 */
	private Point coord;
	/**
	 * index de l'image selectionné
	 */
	private int index;
	/**
	 * Charge toute les images, s'il n'y a qu'un path de precisé on regarde s'il s'agit d'un repertoire et on charge tout les image se trouvant a l'interieur
	 * s'il s'agit d'une image on la charge,
	 * s'il y a plusieur path on charge les images decrite par les path
	 * 
	 * @param _coord coordonnée 
	 * @param path chemin vers les image
	 */
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
	/**
	 * constructeur par copie superficielle
	 * @param a
	 */
	public AnimatedOrientedImage(AnimatedOrientedImage a) {
		coord = a.getCoord().copy();
		index = a.getIndex();
		img = a.getImages();
	}
	
	public Image[][] getImages() {
		return img;
	}
	/**
	 * retourne l'image selectionné par l'index et par son sens
	 * 
	 * @param pos indique le sens de l'image, (false image normal, true image symetrique par rapport a l'axe Y)
	 * @return
	 */
	public Image getImage(boolean pos) {
		return img[(pos)? 1 : 0][index];
	}
	
	public void setCoord(Point _coord) {
		coord.set(_coord);
	}
	
	public Point getCoord() {
		return coord;
	}
	/**
	 * retourne le nombre d'image ayant un sens
	 * @return
	 */
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
	/**
	 * place l'image dans le tableau ainsi que sa version symetrique par rapport a l'axe Y
	 * @param index
	 * @param image
	 */
	private void setImage(int index, Image image) {
		img[0][index] = image;
		img[1][index] = OrientedImage.reverseImage(img[0][index]);
	}
	
	private void setSize(int size) {
		img[0] = new Image[size];
		img[1] = new Image[size];
	}
}
