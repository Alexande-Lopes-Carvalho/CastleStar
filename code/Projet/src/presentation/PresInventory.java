package presentation;

import controle.CtrlInventory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;
/**
 * represente l'inventaire d'un joueur
 * @author Administrator
 *
 */
public class PresInventory extends EventHandler {
	private static int pxSize = 3;
	private static int slotSpace = 7, heartSpace = 1;
	private static Image inventorySlot, selectedSlot;
	public static Image sword, bow, arrow, lance, dagger;
	private static Image[] heart;
	private Point coord;
	private Image[] inventoryImage;
	private int index;
	private CtrlInventory ctrlInventory;
	private int life, maxLife;
	private int nbArrow;
	public PresInventory(Point _coord, int _index, int length, int _life, int _maxLife, int _nbArrow, CtrlInventory _ctrlInventory) {
		coord = _coord;
		index = _index;
		inventoryImage = new Image[length];
		life = _life;
		maxLife = _maxLife;
		nbArrow = _nbArrow;
		ctrlInventory = _ctrlInventory;
	}
	
	public void render() {
		translate(coord);
		for(int i = 0; i < inventoryImage.length; i++) {
			double x = i*(slotSpace*pxSize+inventorySlot.getWidth());
			translate(x, 0);
			image(inventorySlot, 0, 0);
			
			if(inventoryImage[i] != null) {
				translate(inventorySlot.getWidth()/2, inventorySlot.getHeight()/2);
				imageMode(CENTER);
				image(inventoryImage[i], 0, 0);
				imageMode(CORNER);
				translateBack(inventorySlot.getWidth()/2, inventorySlot.getHeight()/2);
			}
			
			if(i == index) {
				translate(-1*pxSize, -1*pxSize);
				image(selectedSlot, 0, 0);
				translateBack(-1*pxSize, -1*pxSize);
			}
			translateBack(x, 0);
		}
		translate(0,  inventorySlot.getHeight()+slotSpace*pxSize);
		for(int i = 0; i < Math.ceil(maxLife/2); i++) {
			image(heart[((life-i*2 >= 2)? 2 : (life-i*2 >= 1)? 1 : 0)], i*(heartSpace*pxSize+heart[0].getWidth()), 0);
		}
		translate(0, heart[0].getHeight()+2*pxSize);
		image(arrow, 0, 0);
		stroke(255);
		text("x "+nbArrow, arrow.getWidth()+2*pxSize, 3*pxSize);
		stroke(0);
		translateBack(0, heart[0].getHeight()+2*pxSize);
		translate(0,  -(inventorySlot.getHeight()+slotSpace*pxSize));
		
		translateBack(coord);
	}
	/**
	 * change l'equipement selectionné
	 * la touche & selectionne le premier equipement
	 * la touche é le deuxieme ...
	 */
	public void keyPressed() {
		//System.out.println(keyCode());
		if(keyCode().equals(KeyCode.DIGIT1)) {
			ctrlInventory.setIndex(0);
		} else if(keyCode().equals(KeyCode.DIGIT2)) {
			ctrlInventory.setIndex(1);
		} else if(keyCode().equals(KeyCode.DIGIT3)) {
			ctrlInventory.setIndex(2);
		} else if(keyCode().equals(KeyCode.DIGIT4)) {
			ctrlInventory.setIndex(3);
		}
	}
	
	public void setInventoryImage(Image img, int index) {
		inventoryImage[index] = img;
	}
	
	public void setIndex(int _index) {
		index = _index;
	}
	
	public void setLife(int _life) {
		life = _life;
	}
	
	public void setNbArrow(int _nbArrow) {
		nbArrow = _nbArrow;
	}
	/**
	 * charge les image
	 */
	public static void initImage() {
		inventorySlot = loadPixelatedImage("./data/Inventory/InventorySlot.png", pxSize);
		selectedSlot = loadPixelatedImage("./data/Inventory/SelectedSlot.png", pxSize);
		sword = loadPixelatedImage("./data/Inventory/Sword.png", pxSize);
		bow = loadPixelatedImage("./data/Inventory/Bow.png", pxSize);
		heart = new Image[] {loadPixelatedImage("./data/Inventory/Heart_0.png", pxSize), loadPixelatedImage("./data/Inventory/Heart_1.png", pxSize), loadPixelatedImage("./data/Inventory/Heart_2.png", pxSize)};
		arrow = loadPixelatedImage("./data/Inventory/Arrow.png", pxSize);
		lance = loadPixelatedImage("./data/Inventory/Lance.png", pxSize);
		dagger = loadPixelatedImage("./data/Inventory/Dagger.png", pxSize);
	}
}
