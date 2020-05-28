package controle;


import abstraction.ElementCollidable;
import presentation.PresElementScene;

public class CtrlElementCollidable extends CtrlElementScene {
	private ElementCollidable elementCollidable;
	public CtrlElementCollidable(ElementCollidable _elementCollidable, PresElementScene _presElementScene) {
		super(_elementCollidable, _presElementScene);
		elementCollidable = _elementCollidable;
	}
	
	public ElementCollidable getElementCollidable() {
		return elementCollidable;
	}
}
