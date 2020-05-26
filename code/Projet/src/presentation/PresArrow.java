package presentation;

import controle.CtrlArrow;

import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public class PresArrow extends PresElementScene {
	private static OrientedImage arrow;
	private CtrlArrow ctrlArrow;
	private double celerity;
	private boolean facingLeft;

	public PresArrow(Point coord, double _celerity, boolean _facingLeft) {
		setCoord(coord);
		setCelerity(_celerity);
		facingLeft = _facingLeft;
	}

	public void setCtrlArrow(CtrlArrow _ctrlArrow) {
		ctrlArrow = _ctrlArrow;
	}

	public void setCelerity(double k) {
		celerity = k * MainEventHandler.pxSize;
	}

	public void calc(long timePassed) {
		ctrlArrow.move(new Point(celerity * timePassed, 0));
	}

	public void render() {
		System.out.println(getCoord());
		if (facingLeft) {
			image(arrow.getImage(facingLeft), getCoord().getX() - getWidth(), getCoord().getY());
		} else {
			image(arrow.getImage(facingLeft), getCoord());
		}

	}

	public static void initImage() {
		arrow = new OrientedImage(new Point(0, 0),
				EventHandler.loadPixelatedImage("./data/arrow.png", MainEventHandler.pxSize));
	}

	public double getWidth() {
		return arrow.getImage(facingLeft).getWidth();
	}

	public double getHeight() {
		return arrow.getImage(facingLeft).getHeight();
	}

	public boolean doRender(Point camera) {
		if (facingLeft) {
			return (getCoord().getX()-getWidth() <= camera.getX() && getCoord().getX() >= camera.getX() + width())
					|| between(getCoord().getX()-getWidth(), camera.getX(), camera.getX() + width())
					|| between(getCoord().getX(), camera.getX(), camera.getX() + width());

		} else {
			return (getCoord().getX() <= camera.getX() && getCoord().getX() + getWidth() >= camera.getX() + width())
					|| between(getCoord().getX(), camera.getX(), camera.getX() + width())
					|| between(getCoord().getX() + getWidth(), camera.getX(), camera.getX() + width());
		}
	}
}