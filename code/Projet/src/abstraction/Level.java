package abstraction;

import java.util.Observable;

import shapeSceneFX.Point;

public class Level extends Observable {
	public static double leftSpace = 20;
	private Point camera = new Point(0, 0);
	/*public void updateCamera(List<Player> playerList) {
		Float value = null;
		for(Player k : playerList) {
			if(value == null || k.getCoord().getX() < value) {
				value = k.getCoord().getX();
			}
		}
		camera.setX(value-leftSpace*MainEventHandler.pxSize);
		setChanged();
		notifyObservers(null);
	}*/
	
	public Point getCamera() {
		return camera;
	}
}
