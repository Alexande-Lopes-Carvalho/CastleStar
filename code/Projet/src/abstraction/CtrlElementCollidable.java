package abstraction;


public class CtrlElementCollidable {
	private ElementCollidable elementCollidable;
	public CtrlElementCollidable(ElementCollidable _elementCollidable) {
		
		elementCollidable = _elementCollidable;
	}
	
	public ElementCollidable getElementCollidable() {
		return elementCollidable;
	}
}
