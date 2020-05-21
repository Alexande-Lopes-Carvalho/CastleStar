package presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class PresLevel extends EventHandler {
	private Point camera;
	private List<PresElementScene> listElementScene;
	public PresLevel() {
		camera = new Point(0, 0);
		listElementScene = new ArrayList<PresElementScene>();
	}
	
	public void render() {
		background(0);
		Point translateCam = camera.copy().mult(-1);
		translate(translateCam);
		sortByRenderOrder();
		for(PresElementScene k : listElementScene) {
			if(doRender(k)) { // si k apparaitra dans la fenetre alors on appele sa fonction render
				k.render();
			}
		}
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
	
	public void remove(PresElementScene e) {
		listElementScene.remove(e);
	}
	
	public boolean doRender(PresElementScene e) {
		return (e.getCoord().getX() <= camera.getX() && e.getCoord().getX()+e.getWidth() >= camera.getX()+width()) || between(e.getCoord().getX(), camera.getX(), camera.getX()+width()) || between(e.getCoord().getX()+e.getWidth(), camera.getX(), camera.getX()+width());
	}
	
	private static boolean between(double value, double inf, double sup) {
		return value >= inf && value <= sup;
	} 
}
