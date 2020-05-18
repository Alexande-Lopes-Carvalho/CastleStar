package controle;

import java.util.Observable;
import java.util.Observer;

import abstraction.ColoredRectangle;
import presentation.PresColoredRectangle;

public class CtrlColoredRectangle implements Observer {
	private ColoredRectangle c;
	private PresColoredRectangle p;
	public CtrlColoredRectangle(ColoredRectangle _c) {
		c = _c;
		p = new PresColoredRectangle(this);
		p.setPaint(c.getPaint());
		p.setCoord(c.getCoord());
		p.setDimension(c.getDimension());
		c.addObserver(this);
	}
	
	public void newColor() {
		System.out.println("New Color");
		c.newPaint();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("pass Update");
		p.setPaint(c.getPaint());
	}
	
	public PresColoredRectangle getP() {
		return p;
	}
}
