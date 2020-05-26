package abstraction;

import java.util.List;

import controle.CtrlEntity;
import shapeSceneFX.Point;

public class Arrow extends ElementScene{
	private static final float maxDist = 800;
	public static final Object KILL = 11;
	private static final double celerity = 0.25;
	private float dist;
	private boolean facingLeft;
	private int damage;
	private Rectangle rect;
	private List<? extends CtrlEntity> enemyList;
	public Arrow(boolean _facingLeft, int _damage, List<? extends CtrlEntity> _enemyList,Point _coord, Rectangle _rect) {
		super(_coord, _rect.getCoord().getY()+_rect.getDimension().getY()/2.);
		System.out.println(getRenderPriority() + " renderPrio " + (_rect.getCoord().getY()+_rect.getDimension().getY()/2.) + " " + _coord);
		facingLeft = _facingLeft;
		damage = _damage;
		enemyList = _enemyList;
		rect = _rect;
		dist = 0;
	}
	
	public void move(Point deplacement) {
		System.out.println(deplacement.getX());
		dist += deplacement.getX();
		Rectangle r = new Rectangle(getCoord().copy().add(rect.getCoord()), new Point(deplacement.getX()+rect.getDimension().getX(), rect.getDimension().getY()));
		setCoord(getCoord().copy().add(deplacement));
		for(CtrlEntity k : enemyList) {
			Point[] c = new Point[4];
			c[0] = new Point(k.getEntity().getCoord().copy().add(deplacement).add(k.getEntity().getRectangle().getCoord()));
			c[1] = c[0].copy().add(k.getEntity().getRectangle().getDimension().getX(), 0);
			c[2] = c[0].copy().add(k.getEntity().getRectangle().getDimension().getX(), k.getEntity().getRectangle().getDimension().getY());
			c[3] = c[0].copy().add(0, k.getEntity().getRectangle().getDimension().getY());
			for(Point l : c) {
				if(r.pointInside(l)) {
					k.damage(damage);
					kill();
					return;
				}
			}
		}
		if(dist >= maxDist) {
			kill();
		}
	}
	
	public void kill() {
		setChanged();
		notifyObservers(KILL);
	}
	
	public double getCelerity() {
		return ((facingLeft)? -1 : 1)*celerity;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public boolean getFacingLeft() {
		return facingLeft;
	}
}