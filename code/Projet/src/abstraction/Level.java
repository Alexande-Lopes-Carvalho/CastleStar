package abstraction;

import java.util.List;
import java.util.Observable;

import controle.CtrlPlayer;
import shapeSceneFX.Point;
/**
 * Niveau de jeu 
 * @author Administrator
 *
 */
public class Level extends Observable {
	public static final Object CAMERA_UPDATE = -1;
	/**
	 * information lié au comportement de la camera
	 */
	public static double leftSpace = 40, rightSpace = 140;
	/**
	 * camera
	 */
	private Point camera = new Point(0, 0);
	/**
	 * mise a jour de la position de la camera en fonction des joueur present dans le niveau
	 * @param playerList
	 */
	public void updateCamera(List<CtrlPlayer> playerList) {
		if(playerList.size() != 0) {
			Point res = playerList.get(0).getElementScene().getCoord();
			for(CtrlPlayer k : playerList) {
				if(k.getElementScene().getCoord().getX() < res.getX()) {
					res = k.getElementScene().getCoord();
				}
			}
			if(res.getX()-camera.getX() < leftSpace) {
				Point v = res.copy().sub(leftSpace, 0);
				v.set(v.getX(), 0);
				setCamera(v);
			} else if(res.getX()-camera.getX() > rightSpace) {
				Point v = res.copy().sub(rightSpace, 0);
				v.set(v.getX(), 0);
				setCamera(v);
			}
			
		}
	}
	
	public void setCamera(Point _camera) {
		camera.set(_camera);
		setChanged();
		notifyObservers(CAMERA_UPDATE);
	}
	
	public Point getCamera() {
		return camera;
	}
}
