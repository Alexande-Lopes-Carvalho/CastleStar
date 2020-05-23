package presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;
import shapeSceneFX.EventHandling.TransferableEvent;

public class PresLevel extends EventHandler {
	private Point camera;
	private List<PresElementScene> listElementScene;
	private List<PresPlayer> listPlayer;
	public PresLevel() {
		camera = new Point(0, 0);
		listElementScene = new ArrayList<PresElementScene>();
		listPlayer = new ArrayList<PresPlayer>();
	}
	
	protected void calc(long timePassed) {
		for(PresElementScene e : listElementScene) {
			e.calcEvent(timePassed);
		}
	}
	
	public void render() {
		background(0);
		Point translateCam = camera.copy().mult(-1);
		translate(translateCam);
		sortByRenderOrder();
		for(PresElementScene k : listElementScene) {
			if(k.doRender(camera)) { // si k apparaitra dans la fenetre alors on appele sa fonction render
				//System.out.println(k.getRenderPrio());
				k.render();
			}
		}
		//System.out.println();
		translateBack(translateCam);
	}	
	
	public void sortByRenderOrder() {
		Collections.sort(listElementScene);
	}
	
	public Point getCamera() {
		return camera;
	}
	
	public void setCamera(Point coord) {
		camera.set(coord.copy().mult(MainEventHandler.pxSize));
	}
	
	public void add(PresElementScene e) {
		listElementScene.add(e);
	}
	
	public void add(PresPlayer e) {
		listPlayer.add(e);
	}
	
	public void remove(PresElementScene e) {
		listElementScene.remove(e);
	}
	
	public void transferEvent(TransferableEvent e) {
		for(PresPlayer k : listPlayer) {
			//System.out.println("transferEvent to " + k);
			k.addEvent(e.in(0));
		}
	}
}
