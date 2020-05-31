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
import abstraction.Polygon;
import abstraction.Rectangle;
import javafx.scene.image.Image;
import presentation.MainEventHandler;
import presentation.PresElementScene;
import presentation.PresImage;
import presentation.PresLevel;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;
/**
 * Niveau
 * @author Administrator
 * @see Level
 * @see PresLevel
 */
public class CtrlLevel implements Observer {
	/**
	 * Liste des elemnt present
	 */
	private List<CtrlElementScene> ctrlElementSceneList;
	/**
	 * liste des element ayant une hitbox present
	 */
	private List<CtrlElementCollidable> ctrlElementCollidableList;
	/**
	 * liste de entité presente
	 */
	private List<CtrlEntity> ctrlEntityList;
	/**
	 * liste des joueur present
	 */
	private List<CtrlPlayer> ctrlPlayerList;
	/**
	 * liste des item present
	 */
	private List<CtrlItem> ctrlItemList;
	/**
	 * liste des enemi present
	 */
	private List<CtrlEnemy> ctrlEnemyList;
	/**
	 * liste des conteneur d'item present
	 */
	private List<CtrlLootable> ctrlLootableList;
	private Level level;
	private PresLevel presLevel;
	private MainEventHandler mainEventHandler;
	/**
	 * Au dela de endX on verifie s'il n'y a plus d'enemi visible pour declaré la fin du niveau et basculé vers le menu
	 */
	private double endX;
	public CtrlLevel(MainEventHandler _mainEventHandler, double _endX) {
		endX = _endX;
		mainEventHandler = _mainEventHandler;
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
		ctrlItemList = new ArrayList<CtrlItem>();
		ctrlLootableList = new ArrayList<CtrlLootable>();
		ctrlEnemyList = new ArrayList<>();
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
	
	public List<CtrlPlayer> getCtrlPlayerList(){
		return ctrlPlayerList;
	}
	
	public void add(CtrlElementScene e) {
		ctrlElementSceneList.add(e);
		presLevel.add(e.getPresElementScene());
	}
	
	public void add(CtrlItem e) {
		add((CtrlElementScene) e);
		ctrlItemList.add(e);
	}
	
	public void add(CtrlElementCollidable e) {
		add((CtrlElementScene)e);
		ctrlElementCollidableList.add(e);
	}
	
	public void add(CtrlEntity e) {
		add((CtrlElementCollidable) e);
		ctrlEntityList.add(e);
	}
	
	public void add(CtrlLootable e) {
		add((CtrlEntity) e);
		ctrlLootableList.add(e);
	}
	
	public void add(CtrlEnemy e) {
		add((CtrlEntity) e);
		ctrlEnemyList.add(e);
	}
	
	public void add(CtrlPlayer e) {
		add((CtrlEntity) e);
		ctrlPlayerList.add(e);
		presLevel.add(e.getPresPlayer());
	}
	
	public void remove(CtrlElementScene e) {
		ctrlElementSceneList.remove(e);
		presLevel.remove(e.getPresElementScene());
	}
	
	public void remove(CtrlItem e) {
		ctrlItemList.remove(e);
		ctrlElementSceneList.remove(e);
		presLevel.remove(e.getPresElementScene());
	}
	
	public void remove(CtrlLootable e) {
		ctrlLootableList.remove(e);
		ctrlElementSceneList.remove(e);
		ctrlElementCollidableList.remove(e);
		ctrlEntityList.remove(e);
		presLevel.remove(e.getPresElementScene());
	}
	
	public void remove(CtrlEntity e) {
		ctrlElementSceneList.remove(e);
		ctrlElementCollidableList.remove(e);
		ctrlEntityList.remove(e);
		presLevel.remove(e.getPresElementScene());
	}
	
	public void remove(CtrlEnemy e) {
		remove((CtrlEntity) e);
		ctrlEnemyList.remove(e);
		//System.out.println("REMOVE " + e);
	}
	
	public void remove(CtrlPlayer e) {
		remove((CtrlEntity) e);
		ctrlPlayerList.remove(e);
		if(ctrlPlayerList.size() == 0) {
			mainEventHandler.endLevel();
		}
	}
	
	public MainEventHandler getMainEventHandler() {
		return mainEventHandler;
	}
	/**
	 * procedure executé si le jouueur a terminé le niveau 
	 */
	public void levelSuccess() {
	}
	/**
	 * On relaie l'information aux item et au enemi, on verifie aussi si le niveau est terminé
	 * @param ctrlPlayer
	 * @see CtrlEnemy#playerMoved(CtrlPlayer)
	 * @see CtrlItem#playerMoved(CtrlPlayer)
	 */
	public void playerMoved(CtrlPlayer ctrlPlayer) {
		if(ctrlPlayer.getPlayer().getCoord().getX() >= endX) {
			//System.out.println("CheckEndLevel");
			boolean endLevel = true;
			for(CtrlEnemy k : ctrlEnemyList) {
				if(k.getPresElementScene().doRender(presLevel.getCamera())) { // s'il y a un enemi visible a l'ecran 
					//System.out.println(k.getEntity().getCoord() + " " + (k instanceof CtrlOrc) + " " + k);
					endLevel = false;
					break;
				}
			}
			if(endLevel) {
				levelSuccess();
				mainEventHandler.endLevel();
			}
		}
		ArrayList<CtrlItem> toRemoveList = new ArrayList<CtrlItem>();
		for(CtrlItem k : ctrlItemList) {
			if(k.playerMoved(ctrlPlayer)) {
				toRemoveList.add(k);
			}
		}
		for(CtrlItem k : toRemoveList) {
			remove(k);
		}
		for(CtrlEnemy k : ctrlEnemyList) {
			k.playerMoved(ctrlPlayer);
		}
	}
	
	public PresLevel getPresLevel() {
		return presLevel;
	}
	/**
	 * on charge le sol et on determine sa hitbox
	 * @param folder
	 * @param _coord
	 */
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
	/**
	 * on charge les mur et on determine sa hitbox
	 * @param folder
	 * @param _coord
	 */
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
	/**
	 * On charge plusieurs images contenu dans le repertoire path et on les redimensionne
	 * @param path
	 * @param pxSize
	 * @return
	 */
	public Image[] loadImages(String path, int pxSize) {
		File file = new File(path);
		File[] fileAr = (file.isDirectory())? file.listFiles() : new File[] {file};
		Arrays.sort(fileAr);
		Image[] res = new Image[fileAr.length];
		for(int i = 0; i < fileAr.length; i++) {
			//System.out.println(fileAr[i].getPath());
			res[i] = EventHandler.loadPixelatedImage(fileAr[i].getPath(), pxSize);
		}
		return res;
	}
	/**
	 * on charge les image
	 */
	public void initImage() {
	}
	
	/**
	 * Ajoute une hitbox invisible
	 * @param coord
	 * @param poly
	 */
	public void addHitbox(Point coord, Polygon poly) {
		add(new CtrlElementCollidable(new ElementCollidable(coord, poly), new PresElementScene() {
			public boolean doRender(Point cam){
			return false;
		}}));
	}
}
