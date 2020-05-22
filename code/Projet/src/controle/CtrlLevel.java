package controle;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import abstraction.ElementCollidable;
import abstraction.ElementScene;
import abstraction.Level;
import abstraction.Rectangle;
import presentation.MainEventHandler;
import presentation.PresImage;
import presentation.PresLevel;
import shapeSceneFX.Point;

public class CtrlLevel implements Observer {
	private List<CtrlElementScene> ctrlElementSceneList;
	private List<CtrlElementCollidable> ctrlElementCollidableList;
	//private List<CtrlPlayer> ctrlPlayerList;
	private Level level;
	private PresLevel presLevel;
	public CtrlLevel() {
		CtrlElementScene.currentLevel = this;
		level = new Level();
		presLevel = new PresLevel();
		presLevel.setCamera(level.getCamera());
		level.addObserver(this);
		ctrlElementSceneList = new ArrayList<CtrlElementScene>();
		ctrlElementCollidableList = new ArrayList<CtrlElementCollidable>();
	}
	
	/*public void updateCamera() {
		List<Player> p = new Arraylist<Player>();
		for(Player k : ctrlPlayerList) {
			p.add(k.getPlayer());
		}
		level.updateCamera(p);
	}*/
	
	@Override
	public void update(Observable arg0, Object arg1) {
		presLevel.setCamera(level.getCamera());
	}
	
	public List<CtrlElementScene> getCtrlElementSceneList(){
		return ctrlElementSceneList;
	}
	
	public List<CtrlElementCollidable> getCtrlElementCollidableList(){
		return ctrlElementCollidableList;
	}
	
	public void add(CtrlElementScene e) {
		ctrlElementSceneList.add(e);
		presLevel.add(e.getPresElementScene());
	}
	
	public void add(CtrlElementCollidable e) {
		add((CtrlElementScene)e);
		ctrlElementCollidableList.add(e);
	}
	
	public void remove(CtrlEntity e) {
		ctrlElementCollidableList.remove(e);
		presLevel.remove(e.getPresElementScene());
	}
	
	public PresLevel getPresLevel() {
		return presLevel;
	}
	
	public void loadBackground(String folder, Point _coord) {
		File[] fileAr = new File(folder).listFiles();
		Arrays.sort(fileAr);
		Point coord = _coord.copy();
		if(fileAr.length >= 1) {
			PresImage pres = new PresImage(fileAr[0].getPath());
			Rectangle rect = new Rectangle(new Point(0, pres.getHeight()/MainEventHandler.pxSize), new Point(0, 1));
			
			coord.add(pres.getWidth()/MainEventHandler.pxSize, 0);
			for(int i = 1; i < fileAr.length; i++) {
				//System.out.println(fileAr[i].getPath() + " " + coord);
				CtrlElementScene a = new CtrlElementScene(new ElementScene(coord.copy()), new PresImage(fileAr[i].getPath()));
				a.setRenderPriority(-1);
				add(a);
				coord.add(a.getPresElementScene().getWidth()/MainEventHandler.pxSize, 0);
			}
			rect.getDimension().add(coord.getX()-_coord.getX(), 0);
			CtrlElementCollidable c = new CtrlElementCollidable(new ElementCollidable(_coord.copy(), rect), pres);
			c.setRenderPriority(-1);
			add(c);
			//System.out.println(c.getElementScene().getCoord());
			//System.out.println(rect.getCoord() + " " + rect.getDimension());
		}
	}
	
	public void loadWall(String folder, Point _coord) {
		File[] fileAr = new File(folder).listFiles();
		Arrays.sort(fileAr);
		Point coord = _coord.copy();
		if(fileAr.length >= 1) {
			PresImage pres = new PresImage(fileAr[0].getPath());
			Rectangle rect = new Rectangle(new Point(0, pres.getHeight()/MainEventHandler.pxSize-1), new Point(0, 1));
			 
			coord.add(pres.getWidth()/MainEventHandler.pxSize, 0);
			for(int i = 1; i < fileAr.length; i++) {
				//System.out.println(fileAr[i].getPath() + " " + coord);
				CtrlElementScene a = new CtrlElementScene(new ElementScene(coord.copy()), new PresImage(fileAr[i].getPath()));
				add(a);
				coord.add(a.getPresElementScene().getWidth()/MainEventHandler.pxSize, 0);
			} 
			rect.getDimension().add(coord.getX()-_coord.getX(), 0);
			CtrlElementCollidable c = new CtrlElementCollidable(new ElementCollidable(_coord.copy(), rect), pres);
			add(c);
			//System.out.println(c.getElementCollidable().getCoord());
			//System.out.println(rect.getCoord() + " " + rect.getDimension());
		}
	}
}
