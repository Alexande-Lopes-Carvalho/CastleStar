package presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controle.CtrlLevel;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;
import shapeSceneFX.EventHandling.EventHandler;
import shapeSceneFX.EventHandling.TransferableEvent;
/**
 * represente un niveau
 * @author Administrator
 *
 */
public class PresLevel extends EventHandler {
	/**
	 * camera du jeu
	 */
	private Point camera;
	/**
	 * les des element affichable present dans le niveau
	 */
	private List<PresElementScene> listElementScene;
	/**
	 * liste des joueur
	 */
	private List<PresPlayer> listPlayer;
	private CtrlLevel ctrlLevel;
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
		ctrlLevel.updateCamera();
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
		for(PresPlayer k : listPlayer) {
			k.getPresInventory().render();
		}
	}	
	/**
	 * tire les element affichable en fonction de leur renderPriority (algorithme du peintre)
	 * 
	 * @see PresElementScene#getRenderPrio()
	 */
	public void sortByRenderOrder() {
		Collections.sort(listElementScene);
	}
	
	public void setCtrlLevel(CtrlLevel _ctrlLevel) {
		ctrlLevel = _ctrlLevel;
	}
	
	public Point getCamera() {
		return camera;
	}
	
	public void setCamera(Point coord) {
		camera.set(coord.copy().mult(MainEventHandler.pxSize));
		camera.set(Math.round(camera.getX()), Math.round(camera.getY()));
	}
	
	public void add(PresElementScene e) {
		addEvent(new AddPresElementSceneEvent(e).in(0));
	}
	
	public void add(PresPlayer e) {
		listPlayer.add(e);
	}
	
	public void remove(PresElementScene e) {
		addEvent(new RemovePresElementSceneEvent(e).in(0));
	}
	/**
	 * transfere les evenement clavier souris au joueur
	 */
	public void transferEvent(TransferableEvent e) {
		addEvent(e.in(0));
		for(PresPlayer k : listPlayer) {
			//System.out.println("transferEvent to " + k);
			k.addEvent(e.transfer().in(0));
		}
	}
	/**
	 * Evenement qui supprime un PresElementScene du niveau (pour evité les ConcurrentModificationException)
	 * @author Administrator
	 */
	public class RemovePresElementSceneEvent implements Event{ // Present pour ne pas avoir de ConcurrentModificationException lors de la suppression (calc(long timePassed))
		private PresElementScene e;
		public RemovePresElementSceneEvent(PresElementScene _e) {
			e = _e;
		}
		
		@Override
		public void handleEvent() {
			listElementScene.remove(e);
		}
	}
	/**
	 * Evenement qui ajoute un PresElementScene au niveau (pour evité les ConcurrentModificationException)
	 * @author Administrator
	 *
	 */
	public class AddPresElementSceneEvent implements Event{
		private PresElementScene e;
		public AddPresElementSceneEvent(PresElementScene _e) {
			e = _e;
		}
		@Override
		public void handleEvent() {
			listElementScene.add(e);
		}
	}
}
