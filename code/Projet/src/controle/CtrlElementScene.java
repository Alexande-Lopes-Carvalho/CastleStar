package controle;

import java.util.Observable;
import java.util.Observer;

import abstraction.ElementScene;
import presentation.PresElementScene;

public class CtrlElementScene implements Observer {
	public static CtrlLevel currentLevel;
	private PresElementScene presElementScene;
	private ElementScene elementScene;
	public CtrlElementScene(ElementScene _elementScene, PresElementScene _presElementScene) {
		elementScene = _elementScene;
		presElementScene = _presElementScene;
		presElementScene.setCoord(elementScene.getCoord());
		presElementScene.setRenderPriority(elementScene.getRenderPriority());
		elementScene.addObserver(this);
	}
	
	public CtrlElementScene() {
	}
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals(ElementScene.RENDER_PRIORITY_UPDATE)) {
			presElementScene.setRenderPriority(elementScene.getRenderPriority());
		} else if(arg.equals(ElementScene.COORD_UPDATE)) {
			//System.out.println("UPDATE SET COORD");
			presElementScene.setCoord(elementScene.getCoord());
		}
	}
	
	public PresElementScene getPresElementScene() {
		return presElementScene;
	}
	
	public ElementScene getElementScene() {
		return elementScene;
	}
	
	public void setRenderPriority(double value) {
		elementScene.setRenderPriority(value);
	}
}
