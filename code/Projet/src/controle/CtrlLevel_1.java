package controle;

import abstraction.AStarGraph;
import abstraction.ConvexPolygon;
import abstraction.ElementCollidable;
import abstraction.ElementScene;
import abstraction.Orc;
import abstraction.Player;
import abstraction.Rectangle;
import abstraction.Skeleton;
import javafx.scene.image.Image;
import presentation.Items;
import presentation.Lootables;
import presentation.MainEventHandler;
import presentation.PresImage;
import presentation.PresPlayer;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;
/**
 * Niveau 1
 * @author Administrator
 *
 */
public class CtrlLevel_1 extends CtrlLevel {
	private Image bigBarrel;
	private Image chair;
	private Image leftWall, rightWall;
	private Image table_0, table_1;
	private Image biggerTable;
	private Image scarecrow;
	private Image arrowTarget;
	private Image bed;
	private Image doorBack, doorFront;
	private Image kingChair, tablePot;
	private Image littleTables;
	private Image littleHouse;
	public CtrlLevel_1(CtrlPlayer c, MainEventHandler e) {
		super(e, 2515);
		loadBackground("./data/Level_1/Background", new Point(29, 83));
		loadWall("./data/Level_1/Wall", new Point(105, 0));
		
		add(leftWall(new Point(0, 0)));

		add(bigBarrel(new Point(108, 55)));
		add(bigBarrel(new Point(143, 55)));
		add(bigBarrel(new Point(213, 55)));
		
		add(chair(new Point(81, 91)));
		
		add(table_0(new Point(114, 89)));
		add(table_1(new Point(198, 89)));
		
		add(biggerTable(new Point(105, 127)));
		add(biggerTable(new Point(212, 127)));
		add(biggerTable(new Point(295, 127)));
		
		add(scarecrow(new Point(444, 62)));
		add(scarecrow(new Point(470, 92)));
		
		add(arrowTarget(new Point(501, 122)));
		
		add(bed(new Point(600, 80)));
		add(bed(new Point(628, 106)));
		
		add(door(new Point(652, 0)));
		
		add(kingChair(new Point(797, 94)));
		
		add(littleTables(new Point(837, 72)));
		
		add(Lootables.barrel(new Point(65, 109), Items.heart(1)));
		add(Lootables.barrel(new Point(58, 115), Items.heart(1), Items.heart(1)));
		add(Lootables.barrel(new Point(41, 133), Items.heart(1)));
		add(Lootables.barrel(new Point(187, 70), Items.heart(1), Items.heart(1)));
		add(Lootables.barrel(new Point(375, 138), Items.heart(1)));
		
		add(Lootables.barrel(new Point(1003, 71), Items.heart(1)));
		add(Lootables.barrel(new Point(1021, 71), Items.heart(1)));
		add(Lootables.barrel(new Point(1039, 71), Items.heart(1)));
		
		add(door(new Point(1179, 0)));
		
		add(littleHouse(new Point(1424, 20)));
		add(littleHouse(new Point(1485, 20)));
		add(littleHouse(new Point(1546, 20)));
		
		add(Lootables.barrel(new Point(1621, 71), Items.heart(1)));
		add(Lootables.barrel(new Point(1657, 71), Items.heart(1)));
		add(Lootables.barrel(new Point(1678, 71), Items.heart(1)));
		
		add(littleHouse(new Point(1707, 20)));
		
		add(door(new Point(1958, 0)));
		
		add(biggerTable(new Point(2119, 127)));
		add(biggerTable(new Point(2232, 127)));
		
		add(Lootables.barrel(new Point(2309, 71), Items.heart(1)));
		add(Lootables.barrel(new Point(2326, 71), Items.heart(1)));
		add(Lootables.barrel(new Point(2343, 71), Items.heart(1)));
		
		add(rightWall(new Point(2609, 0)));
		
		
		//add(Items.shield(new Point(330, 105)));
		//add(Items.heart(2, new Point(401, 96)));
		//add(Items.arrow(5, new Point(421, 120)));
		add(Items.sword(new Point(399, 96)));
		//add(Items.bow(new Point(590, 104)));
		add(Items.lance(new Point(418, 115)));
		add(Items.dagger(new Point(437, 134)));
		
		add(new CtrlOrc(new Orc(new Point(1047, 97)), false));
		add(new CtrlOrc(new Orc(new Point(1047, 128)), false));
		add(Items.shield(new Point(1435, 117)));
		add(new CtrlOrc(new Orc(new Point(1633, 117)), true));
		
		add(new CtrlOrc(new Orc(new Point(1776, 107)), false));
		add(new CtrlOrc(new Orc(new Point(1798, 125)), false));
		add(new CtrlOrc(new Orc(new Point(1918, 117)), false));
		
		add(new CtrlOrc(new Orc(new Point(2360, 96)), true));
		add(new CtrlOrc(new Orc(new Point(2407, 128)), true));
		
		//add(new CtrlSkeleton(new Skeleton(new Point(517, 131))));

		//System.out.println("PLAYR");
		c.getPlayer().setCoord(new Point(110, 110));
		add(c);
		
		AStarGraph a = new AStarGraph(5, 5, 3000, 160, new Rectangle(new Point(-5, -3), new Point(10, 3)));
		Orc.setGraph(a);
		//add(new CtrlElementScene(new ElementScene(new Point(0, 0), 9000),a)); // A SUPPRIMER POUR ENLEVER LA VUE PATHFINDING
	}
	
	public void levelSuccess() {
		getMainEventHandler().setMaxLevel(2);
	}
	
	public void initImage() {
		bigBarrel = EventHandler.loadPixelatedImage("./data/Level_1/Decor/BigBarrel.png", MainEventHandler.pxSize);
		chair = EventHandler.loadPixelatedImage("./data/Level_1/Decor/Chair.png", MainEventHandler.pxSize);
		leftWall = EventHandler.loadPixelatedImage("./data/Level_1/Decor/LeftWall.png", MainEventHandler.pxSize);
		table_0 = EventHandler.loadPixelatedImage("./data/Level_1/Decor/Table_0.png", MainEventHandler.pxSize);
		table_1 = EventHandler.loadPixelatedImage("./data/Level_1/Decor/Table_1.png", MainEventHandler.pxSize);
		biggerTable = EventHandler.loadPixelatedImage("./data/Level_1/Decor/BiggerTable.png", MainEventHandler.pxSize);
		scarecrow = EventHandler.loadPixelatedImage("./data/Level_1/Decor/Scarecrow.png", MainEventHandler.pxSize);
		arrowTarget = EventHandler.loadPixelatedImage("./data/Level_1/Decor/ArrowTarget.png", MainEventHandler.pxSize);
		bed = EventHandler.loadPixelatedImage("./data/Level_1/Decor/Bed.png", MainEventHandler.pxSize);
		doorBack = EventHandler.loadPixelatedImage("./data/Level_1/Decor/DoorBack.png", MainEventHandler.pxSize);
		doorFront = EventHandler.loadPixelatedImage("./data/Level_1/Decor/DoorFront.png", MainEventHandler.pxSize);
		kingChair = EventHandler.loadPixelatedImage("./data/Level_1/Decor/KingChair.png", MainEventHandler.pxSize);
		tablePot = EventHandler.loadPixelatedImage("./data/Level_1/Decor/TablePot.png", MainEventHandler.pxSize);
		littleTables = EventHandler.loadPixelatedImage("./data/Level_1/Decor/LittleTables.png", MainEventHandler.pxSize);
		littleHouse = EventHandler.loadPixelatedImage("./data/Level_1/Decor/LittleHouse.png", MainEventHandler.pxSize);
		rightWall = EventHandler.loadPixelatedImage("./data/Level_1/Decor/RightWall.png", MainEventHandler.pxSize);
	}
	/**
	 * Retourne un gros tonneau a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable bigBarrel(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(31, 37))), new PresImage(bigBarrel));
	}
	/**
	 * Retourne une chaise a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable chair(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(1, 17), new Point(15, 4))), new PresImage(chair));
	}
	/**
	 * Retourne un mur a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable leftWall(Point coord) {
		CtrlElementCollidable res = new CtrlElementCollidable(new ElementCollidable(coord, new ConvexPolygon(new Point(0, 0), new Point(106, 82), new Point(28.5, 159.5), new Point(20, 159.5), new Point(97.5, 82))), new PresImage(leftWall));
		res.setRenderPriority(-0.5);
		return res;
	}
	/**
	 * Retourne une table a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable table_0(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(4, 16), new Point(38, 16))), new PresImage(table_0));
	}
	/**
	 * Retourne une table a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable table_1(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(4, 16), new Point(44, 16))), new PresImage(table_1));
	}
	/**
	 * Retourne une grande table a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable biggerTable(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(4, 16), new Point(51, 16))), new PresImage(biggerTable));
	}
	/**
	 * Retourne un epouvantail a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable scarecrow(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(10, 32), new Point(11, 7))), new PresImage(scarecrow));
	}
    /**
     * Retourne une cible pour tirer a l'arc a la coordonné precisé
     * @param coord
     * @return
     */
	private CtrlElementCollidable arrowTarget(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 23), new Point(20, 6))), new PresImage(arrowTarget));
	}
	/**
	 * Retourne un lit a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable bed(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 39), new Point(56, 6))), new PresImage(bed));
	}
	
	private CtrlElementCollidable doorBack(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 29), new Point(118, 14))) , new PresImage(doorBack));
	}
	
	private CtrlElementCollidable doorFront(Point coord) {
		CtrlElementCollidable c = new CtrlElementCollidable(new ElementCollidable(coord, new ConvexPolygon(new Point(22, 102), new Point(0, 0), new Point(58, 58), new Point(97, 58), new Point(97, 0))), new PresImage(doorFront));
		c.setRenderPriority(102);
		return c;
	}
	/**
	 * Retourne une porte a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable door(Point coord) {
		add(doorBack(coord.copy().add(new Point(0, 43))));
		return doorFront(coord);
	}
	/**
	 * Retourne un trone a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable kingChair(Point coord) {
		add(tablePot(coord.copy().add(12, 36)));
		return new CtrlElementCollidable(new ElementCollidable(coord, new ConvexPolygon(new Point(0, 41), new Point(0, 0), new Point(4, 4), new Point(24, 4), new Point(20, 0))), new PresImage(kingChair));
	}
	/**
	 * Retourne une table avec un vase a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable tablePot(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new ConvexPolygon(new Point(-8, 9), new Point(0, 0), new Point(8, 8), new Point(28, 8), new Point(20, 0))), new PresImage(tablePot));
	}
	/**
	 * Retourne trois petite table a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable littleTables(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 9), new Point(59, 5))), new PresImage(littleTables));
	}
	/**
	 * Retourne une petite maison a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable littleHouse(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 61), new Point(56, 14))), new PresImage(littleHouse));
	}
	/**
	 * Retourne un mur a la coordonné precisé
	 * @param coord
	 * @return
	 */
	private CtrlElementCollidable rightWall(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(-20, 0), new Point(105, 160))), new PresImage(rightWall));
	}
}
