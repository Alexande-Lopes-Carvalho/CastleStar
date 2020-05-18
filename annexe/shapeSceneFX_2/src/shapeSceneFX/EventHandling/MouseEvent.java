package shapeSceneFX.EventHandling;

import javafx.event.EventType;

/**
 * Evenement souris, appelera la méthode correspondant a l'event (mousePressed,
 * ...) de l'EventHandler qui le lancera
 * 
 * @author Alexandre Lopes Carvalho
 * 
 * @see EventHandler#mousePressed
 * @see EventHandler#mouseReleased
 * @see EventHandler#mouseClicked
 * @see EventHandler#mouseDragged
 * @see EventHandler#mouseMoved
 * @see EventHandler#mouseEntered
 * @see EventHandler#mouseExited
 */

public class MouseEvent implements TransferableEvent {
	private javafx.scene.input.MouseEvent mouseEvent;

	public MouseEvent(javafx.scene.input.MouseEvent e) {
		mouseEvent = e;
	}
	
	@Override
	public void handleEvent(EventHandler current) {
		setState();
		EventType<? extends javafx.scene.input.MouseEvent> e = mouseEvent.getEventType();
		if(e.equals(javafx.scene.input.MouseEvent.MOUSE_PRESSED)) {
			current.mousePressed(mouseEvent);
		} else if(e.equals(javafx.scene.input.MouseEvent.MOUSE_RELEASED)) {
			current.mouseReleased(mouseEvent);
		} else if(e.equals(javafx.scene.input.MouseEvent.MOUSE_CLICKED)) {
			current.mouseClicked(mouseEvent);
		} else if(e.equals(javafx.scene.input.MouseEvent.MOUSE_DRAGGED)) {
			current.mouseDragged(mouseEvent);
		} else if(e.equals(javafx.scene.input.MouseEvent.MOUSE_MOVED)) {
			current.mouseMoved(mouseEvent);
		} else if(e.equals(javafx.scene.input.MouseEvent.MOUSE_ENTERED)) {
			current.mouseEntered(mouseEvent);
		} else if(e.equals(javafx.scene.input.MouseEvent.MOUSE_EXITED)) {
			current.mouseExited(mouseEvent);
		} 
	}
	
	private void setState() {
		EventHandler.fxCanvas.setMouseX(mouseEvent.getX());
		EventHandler.fxCanvas.setMouseY(mouseEvent.getY());
		EventHandler.fxCanvas.setMouseButton(mouseEvent.getButton());
		EventHandler.fxCanvas.setMouseCount(mouseEvent.getClickCount());
	}
}