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
	private List<CtrlEntity> ctrlEntityList;
	private List<CtrlPlayer> ctrlPlayerList;
	private Level level;
	private PresLevel presLevel;
	public CtrlLevel() {
		initImage();
		CtrlElementScene.currentLevel = this;
		level = new Level();
		presLevel = new PresLevel();
		presLevel.setCtrlLevel(this);
		presLevel.setCamera(level.getCamera());
		level.addObserver(this);
		ctrlElementSceneList = new ArrayList<CtrlElementScene>();
		ctrlElementCollidableList = new ArrayList<CtrlElementCollidable>();
		ctrlEntityList = new ArrayList<CtrlEntity>();
		ctrlPlayerList = new ArrayList<CtrlPlayer>();
	}
	
	public void updateCamera() {
		level.updateCamera(ctrlPlayerList);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1.equals(Level.CAMERA_UPDATE)) {
			presLevel.setCamera(level.getCamera());
		}
	}
	
	public List<CtrlElementScene> getCtrlElementSceneList(){
		return ctrlElementSceneList;
	}
	
	public List<CtrlElementCollidable> getCtrlElementCollidableList(){
		return ctrlElementCollidableList;
	}
	
	public List<CtrlEntity> getCtrlEntityList(){
		return ctrlEntityList;
	}
	
	public void add(CtrlElementScene e) {
		ctrlElementSceneList.add(e);
		presLevel.add(e.getPresElementScene());
	}
	
	public void add(CtrlElementCollidable e) {
		add((CtrlElementScene)e);
		ctrlElementCollidableList.add(e);
	}
	
	public void add(CtrlEntity e) {
		add((CtrlElementCollidable) e);
		ctrlEntityList.add(e);
	}
	
	public void add(CtrlPlayer e) {
		add((CtrlEntity) e);
		ctrlPlayerList.add(e);
		presLevel.add(e.getPresPlayer());
	}
	
	public void remove(CtrlEntity e) {
		ctrlElementSceneList.remove(e);
		ctrlElementCollidableList.remove(e);
		ctrlEntityList.remove(e);
		presLevel.remove(e.getPresElementScene());
	}
	
	public void remove(CtrlPlayer e) {
		remove((CtrlEntity) e);
		ctrlPlayerList.remove(e);
	}
	
	public void playerMoved(CtrlPlayer ctrlPlayer) {
		
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
			Rectangle rect = new Rectangle(new Point(0, pres.getHeight()/MainEventHandler.pxSize), new Point(0, 10));
			
			coord.add(pres.getWidth()/MainEventHandler.pxSize, 0);
			for(int i = 1; i < fileAr.length; i++) {
				//System.out.println(fileAr[i].getPath() + " " + coord);
				PresImage pi = new PresImage(fileAr[i].getPath());
				CtrlElementScene a = new CtrlElementScene(new ElementScene(coord.copy()), pi);
				a.setRenderPriority(-1);
				add(a);
				coord.add(pi.getWidth()/MainEventHandler.pxSize, 0);
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
			Rectangle rect = new Rectangle(new Point(0, pres.getHeight()/MainEventHandler.pxSize-10), new Point(0, 10));
			 
			coord.add(pres.getWidth()/MainEventHandler.pxSize, 0);
			for(int i = 1; i < fileAr.length; i++) {
				//System.out.println(fileAr[i].getPath() + " " + coord);
				PresImage pi = new PresImage(fileAr[i].getPath());
				CtrlElementScene a = new CtrlElementScene(new ElementScene(coord.copy()), pi);
				add(a);
				coord.add(pi.getWidth()/MainEventHandler.pxSize, 0);
			} 
			rect.getDimension().add(coord.getX()-_coord.getX(), 0);
			CtrlElementCollidable c = new CtrlElementCollidable(new ElementCollidable(_coord.copy(), rect), pres);
			add(c);
			//System.out.println(c.getElementCollidable().getCoord());
			//System.out.println(rect.getCoord() + " " + rect.getDimension());
		}
	}
	
	public void initImage() {
	}
}
