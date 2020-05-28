package controle;

import abstraction.ConvexPolygon;
import abstraction.ElementCollidable;
import abstraction.Orc;
import abstraction.Player;
import abstraction.Rectangle;
import javafx.scene.image.Image;
import presentation.Items;
import presentation.MainEventHandler;
import presentation.PresImage;
import presentation.PresOrc;
import presentation.PresPlayer;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

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
	public CtrlLevel_1() {
		super(new Point(2714,160));
		loadBackground("./data/Level_1/Background", new Point(29, 83));
		loadWall("./data/Level_1/Wall", new Point(105, 0));
		
		add(leftWall(new Point(0, 0)));

		add(bigBarrel(new Point(110, 55)));
		add(bigBarrel(new Point(145, 55)));
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
		
		add(bed(new Point(608, 88)));
		add(bed(new Point(631, 109)));
		
		add(door(new Point(652, 0)));
		
		add(kingChair(new Point(797, 94)));
		
		add(littleTables(new Point(837, 72)));
		
		add(Lootable.barrel(new Point(65, 109)));
		add(Lootable.barrel(new Point(58, 115)));
		add(Lootable.barrel(new Point(41, 133)));
		add(Lootable.barrel(new Point(187, 70)));
		add(Lootable.barrel(new Point(375, 138)));
		
		add(Lootable.barrel(new Point(1003, 71)));
		add(Lootable.barrel(new Point(1021, 71)));
		add(Lootable.barrel(new Point(1039, 71)));
		
		add(door(new Point(1179, 0)));
		
		add(littleHouse(new Point(1424, 20)));
		add(littleHouse(new Point(1485, 20)));
		add(littleHouse(new Point(1546, 20)));
		
		add(Lootable.barrel(new Point(1621, 71)));
		add(Lootable.barrel(new Point(1657, 71)));
		add(Lootable.barrel(new Point(1678, 71)));
		
		add(littleHouse(new Point(1707, 20)));
		
		add(door(new Point(1958, 0)));
		
		add(biggerTable(new Point(2119, 127)));
		add(biggerTable(new Point(2232, 127)));
		
		add(Lootable.barrel(new Point(2309, 71)));
		add(Lootable.barrel(new Point(2326, 71)));
		add(Lootable.barrel(new Point(2343, 71)));
		
		add(rightWall(new Point(2609, 0)));
		
		
		add(Items.shield(new Point(330, 105)));
		add(Items.heart(2, new Point(401, 96)));
		add(Items.arrow(5, new Point(421, 120)));
		add(Items.sword(new Point(539, 104)));
		add(Items.bow(new Point(590, 104)));

		
		CtrlPlayer p = new CtrlPlayer(new Player(10, new Point(50, 0), 10, new Point(110, 110)), new PresPlayer()); 
		CtrlOrc ctrlOrc = new CtrlOrc(new Orc(0, new Point(55,0),10, 25, new Point(500,110),new Rectangle(new Point(55,55))),new PresOrc());
		

		
		//p.damage(8);
		//p.equip(new CtrlSword());
		//p.equip(new CtrlShield());
		//p.equip(new CtrlBow());
		add(p);
		add(ctrlOrc);
		this.setupGraph();
		ctrlOrc.refreshItinary();
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
	
	private CtrlElementCollidable bigBarrel(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(31, 37))), new PresImage(bigBarrel));
	}
	
	private CtrlElementCollidable chair(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(1, 17), new Point(15, 4))), new PresImage(chair));
	}
	
	private CtrlElementCollidable leftWall(Point coord) {
		CtrlElementCollidable res = new CtrlElementCollidable(new ElementCollidable(coord, new ConvexPolygon(new Point(0, 0), new Point(106, 82), new Point(28.5, 159.5), new Point(20, 159.5), new Point(97.5, 82))), new PresImage(leftWall));
		res.setRenderPriority(-0.5);
		return res;
	}
	
	private CtrlElementCollidable table_0(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(4, 16), new Point(38, 16))), new PresImage(table_0));
	}
	
	private CtrlElementCollidable table_1(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(4, 16), new Point(44, 16))), new PresImage(table_1));
	}
	
	private CtrlElementCollidable biggerTable(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(4, 16), new Point(51, 16))), new PresImage(biggerTable));
	}
	
	private CtrlElementCollidable scarecrow(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(10, 32), new Point(11, 4))), new PresImage(scarecrow));
	}
	
	private CtrlElementCollidable arrowTarget(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 23), new Point(20, 6))), new PresImage(arrowTarget));
	}
	
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
	
	private CtrlElementCollidable door(Point coord) {
		add(doorBack(coord.copy().add(new Point(0, 43))));
		return doorFront(coord);
	}
	
	private CtrlElementCollidable kingChair(Point coord) {
		add(tablePot(coord.copy().add(12, 36)));
		return new CtrlElementCollidable(new ElementCollidable(coord, new ConvexPolygon(new Point(0, 41), new Point(0, 0), new Point(4, 4), new Point(24, 4), new Point(20, 0))), new PresImage(kingChair));
	}
	
	private CtrlElementCollidable tablePot(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new ConvexPolygon(new Point(-8, 9), new Point(0, 0), new Point(8, 8), new Point(28, 8), new Point(20, 0))), new PresImage(tablePot));
	}
	
	private CtrlElementCollidable littleTables(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 9), new Point(59, 5))), new PresImage(littleTables));
	}
	
	private CtrlElementCollidable littleHouse(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 61), new Point(56, 14))), new PresImage(littleHouse));
	}
	
	private CtrlElementCollidable rightWall(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(-20, 0), new Point(105, 160))), new PresImage(rightWall));
	}
}
