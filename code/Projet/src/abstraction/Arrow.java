package abstraction;

public class Arrow extends ElementScene{
	private int celerity;
	private int dommage;

	public Arrow(int _celerity,int _dommage,Point _coord) {
		super(_coord);
		celerity = _celerity;
		dommage = _dommage;
		
	}
	
	public void move(Point deplacement, List<CtrlElementCollidable> list) {
		// a corriger pour prendre en compte les collison 
		setCoord(getCoord().copy().add(deplacement));
	}
	public int getCelerity() {
		return celerity;
	}
	public int getDommage() {
		return dommage;
	}

	
}
