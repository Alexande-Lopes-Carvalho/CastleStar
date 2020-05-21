package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import abstraction.Level;
import presentation.PresLevel;

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
}
