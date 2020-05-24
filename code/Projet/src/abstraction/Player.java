package abstraction;

import java.util.List;

import controle.CtrlElementCollidable;
import shapeSceneFX.Point;

public class Player extends Warrior {

	public Player(int _nbArrow, Point _lookingTo, int _maxLife, Point _coord) {
		super(_nbArrow, _lookingTo, 0.1, _maxLife, _coord, new Rectangle(new Point(-5, -3), new Point(10, 3)));
	}
	
	public void move(Point deplacement, List<CtrlElementCollidable> list) {
		// a corriger pour prendre en compte les collison 
		setCoord(getCoord().copy().add(deplacement));
	}
}
