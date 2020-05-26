package abstraction;

public class ElementCollidable {
	private Point coord;
	
	public ElementCollidable(Point _coord){
		coord = _coord;
	}

	public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
	}

}
