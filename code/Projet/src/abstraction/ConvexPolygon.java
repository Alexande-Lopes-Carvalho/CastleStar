package abstraction;

import shapeSceneFX.Point;
import shapeSceneFX.Segment;

/**
 * Represente un Polygone Convexe c'est a dire tel que tout les angle du polygone sont inferieur a 180 degrée
 * 
 * @author Administrator
 *
 */

public class ConvexPolygon extends Polygon {
	private Point[] points;
	private boolean side;
	public ConvexPolygon(Point coord, Point ... _points) {
		super(coord);
		if(_points.length < 3) {
			throw new IllegalArgumentException("Exception at ConvexPolygon | this can't describe a convex polygon");
		}
		points = _points;
		side = new Segment(points[0], points[1]).isPointOnLeft(points[2]);
	}

	@Override
	public boolean pointInside(Point point) {
		Segment s = new Segment(null, points[0].copy().add(getCoord()));
		for(int i = 0; i < points.length;i++) {
			s.setA(s.getB());
			s.setB(points[(i+1)%points.length].copy().add(getCoord()));
			if(side) {
				if(s.isPointOnRight(point)) {
					return false;
				}
			} else if(s.isPointOnLeft(point)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double getHighestY() {
		Point res = points[0];
		for(int i = 1; i < points.length; i++) {
			if(res.getY() < points[i].getY()) {
				res = points[i];
			}
		}
		return getCoord().getY()+res.getY();
	}

	@Override
	public Point getCenter() {
		Point res = new Point(0, 0);
		for(Point k : points) {
			res.add(k);
		}
		return res.div(points.length).add(getCoord());
	}

	@Override
	public Point[] getPoints() {
		Point[] res = new Point[points.length];
		for(int i = 0; i < res.length; i++) {
			res[i] = getCoord().copy().add(points[i]);
		}
		return res;
	}

}
