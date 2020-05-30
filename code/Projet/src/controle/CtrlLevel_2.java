package controle;

import java.io.File;

import abstraction.AStarGraph;
import abstraction.ConvexPolygon;
import abstraction.ElementCollidable;
import abstraction.ElementScene;
import abstraction.Orc;
import abstraction.Polygon;
import abstraction.Rectangle;
import abstraction.Skeleton;
import javafx.scene.image.Image;
import presentation.Items;
import presentation.Lootables;
import presentation.MainEventHandler;
import presentation.PresElementScene;
import presentation.PresImage;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class CtrlLevel_2 extends CtrlLevel {
	private Image tent;
	private Image lodge;
	private Image laundry;
	private Image collapsedWall;
	private Image[] mazeLine, mazeDiagonal;
	public CtrlLevel_2(CtrlPlayer c, MainEventHandler e) {
		super(e, 1712);
		loadWall("./data/Level_2/Wall", new Point(0, 0));
		loadBackground("./data/Level_2/Background", new Point(0, 83));
		addHitbox(new Point(0, 0),  new Rectangle(new Point(0d, 0d), new Point(50d, 160d)));
		
		add(tent(new Point(9, 63)));
		add(lodge(new Point(59, 42)));
		add(laundry(new Point(124, 72)));
		
		add(Lootables.barrel(new Point(158, 72), Items.arrow(5)));
		add(Lootables.barrel(new Point(180, 72), Items.arrow(5)));
		
		add(collapsedWall(new Point(261, 64)));
		
		add(Items.bow(new Point(281, 114)));
		
		addWaterBridge(new Point(399, 91));
		
		add(new CtrlSkeleton(new Skeleton(new Point(636, 96))));
		
		putMaze(830);
		
		add(Lootables.barrel(new Point(1244, 95), Items.heart(2), Items.arrow(5)));
		
		add(new CtrlOrc(new Orc(new Point(1138, 92)) {public int getNbVisit() {return 1500;}}, true));
		
		add(Lootables.barrel(new Point(1366, 100), Items.heart(2)));
		add(Lootables.barrel(new Point(1428, 103), Items.heart(2), Items.arrow(5)));
		
		add(new CtrlSkeleton(new Skeleton(new Point(1528, 87))));
		
		
		c.getPlayer().setCoord(new Point(110, 110));
		add(c);
		
		AStarGraph a = new AStarGraph(5, 5, 2422, 160, new Rectangle(new Point(-5, -3), new Point(10, 3)));
		Orc.setGraph(a);
		//add(new CtrlElementScene(new ElementScene(new Point(0, 0), 9000),a)); // A SUPPRIMER POUR ENLEVER LA VUE PATHFINDING
	}
	
	public void addWaterBridge(Point coord) {
		addHitbox(coord.copy().add(3, -8), new ConvexPolygon(new Point(0, 0), new Point(0, 0), new Point(8, 8), new Point(156, 8), new Point(148, 0)));
		addHitbox(coord.copy().add(58, 55), new ConvexPolygon(new Point(0, 0), new Point(0, 0), new Point(14, 14), new Point(186, 14), new Point(172, 0)));
		addHitbox(coord.copy().add(65, 62), new Rectangle(new Point(0, 0), new Point(10, 7)));
	}
	
	public void addHitbox(Point coord, Polygon poly) {
		add(new CtrlElementCollidable(new ElementCollidable(coord, poly), new PresElementScene() {
			public boolean doRender(Point cam){
			return false;
		}}));
	}
	
	public void initImage() {
		tent = EventHandler.loadPixelatedImage("./data/Level_2/Decor/Tent.png", MainEventHandler.pxSize);
		lodge = EventHandler.loadPixelatedImage("./data/Level_2/Decor/Lodge.png", MainEventHandler.pxSize);
		laundry =  EventHandler.loadPixelatedImage("./data/Level_2/Decor/Laundry.png", MainEventHandler.pxSize);
		collapsedWall = EventHandler.loadPixelatedImage("./data/Level_2/Decor/CollapsedWall.png", MainEventHandler.pxSize);
		mazeLine = loadImages("./data/Level_2/Decor/Maze/Line", MainEventHandler.pxSize);
		mazeDiagonal = loadImages("./data/Level_2/Decor/Maze/Diagonal", MainEventHandler.pxSize);
				
	}
	
	public CtrlElementCollidable tent(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 19), new Point(40, 11))), new PresImage(tent));
	}
	
	public CtrlElementCollidable lodge(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(-4, 41), new Point(63, 7))), new PresImage(lodge));
	}
	
	public CtrlElementCollidable laundry(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 11), new Point(30, 6))), new PresImage(laundry));
	}
	
	public CtrlElementCollidable collapsedWall(Point coord) {
		return new CtrlElementCollidable(new ElementCollidable(coord, new Rectangle(new Point(0, 19), new Point(63, 6))), new PresImage(collapsedWall));
	}
	
	public void putMaze(double x) {
		Point coord = new Point(x, 71);
		add(new CtrlElementCollidable(new ElementCollidable(coord.copy(), new Rectangle(new Point(0, 10), new Point(606,4))), new PresImage(mazeLine[0])));
		add(new CtrlElementCollidable(new ElementCollidable(coord.copy().add(25, 25), new Rectangle(new Point(0, 10), new Point(146,4))), new PresImage(mazeLine[1])));
		add(new CtrlElementCollidable(new ElementCollidable(coord.copy().add(292, 25), new Rectangle(new Point(0, 10), new Point(138,4))), new PresImage(mazeLine[2])));
		add(new CtrlElementCollidable(new ElementCollidable(coord.copy().add(557, 25), new Rectangle(new Point(0, 10), new Point(73,4))), new PresImage(mazeLine[3])));
		add(new CtrlElementCollidable(new ElementCollidable(coord.copy().add(115, 50), new Rectangle(new Point(0, 10), new Point(344,4))), new PresImage(mazeLine[4])));
		add(new CtrlElementCollidable(new ElementCollidable(coord.copy().add(516, 50), new Rectangle(new Point(0, 10), new Point(75,4))), new PresImage(mazeLine[5])));
		add(new CtrlElementCollidable(new ElementCollidable(coord.copy().add(77, 75), new Rectangle(new Point(0, 10), new Point(605, 4))), new PresImage(mazeLine[6])));
		putDiagonal(coord.copy().add(268, 0), new ConvexPolygon(new Point(0, 14), new Point(0,0), new Point(24, 25), new Point(44, 25), new Point(20, 0)), mazeDiagonal[0]);
		putDiagonal(coord.copy().add(25, 25), new ConvexPolygon(new Point(0, 11), new Point(0, 0), new Point(54, 54), new Point(79, 54), new Point(25, 0)), mazeDiagonal[1]);
		putDiagonal(coord.copy().add(222, 25), new ConvexPolygon(new Point(0, 10), new Point(0, 0), new Point(29, 29), new Point(49, 29), new Point(20, 0)), mazeDiagonal[2]);
		addHitbox(coord.copy().add(234, 46), new Rectangle(new Point(0, 0), new Point(10, 17)));
		putDiagonal(coord.copy().add(425, 25), new ConvexPolygon(new Point(0, 14), new Point(0, 0), new Point(21, 21), new Point(34, 21), new Point(34, 13), new Point(17,-4), new Point(0, -4)), mazeDiagonal[3]);
		addHitbox(coord.copy().add(430, 43), new Rectangle(new Point(0, 0), new Point(15, 17)));
		putDiagonal(coord.copy().add(487, 25), new ConvexPolygon(new Point(0, 10), new Point(0, 0), new Point(29, 29), new Point(51, 29), new Point(22, 0)), mazeDiagonal[4]);
		putDiagonal(coord.copy().add(557, 25), new ConvexPolygon(new Point(0, 14), new Point(0, 0), new Point(21, 21), new Point(34, 21), new Point(34, 13), new Point(21, 0)), mazeDiagonal[5]);
		putDiagonal(coord.copy().add(622, 25), new ConvexPolygon(new Point(0, 14), new Point(0, 0), new Point(46, 46), new Point(60, 46), new Point(60, 38), new Point(18, -4), new Point(8, -4)), mazeDiagonal[6]);
	}
	
	public void putDiagonal(Point coord, Polygon p, Image i) {
		CtrlElementCollidable c = new CtrlElementCollidable(new ElementCollidable(coord, p), new PresImage(i));
		c.setRenderPriority(coord.getY()+p.getCoord().getY());
		add(c);
	}
}
