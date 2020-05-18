package shapeSceneFX.EventHandling;

import javafx.event.EventType;

/**
 * Evenement clavier, appelera la méthode correspondant a l'event (keyPressed
 * ...) de l'EventHandler qui le lance
 * 
 * @author Alexandre Lopes Carvalho
 * 
 * @see EventHandler#keyPressed
 * @see EventHandler#keyTyped
 * @see EventHandler#keyReleased
 */

public class KeyEvent implements TransferableEvent {
	private javafx.scene.input.KeyEvent keyEvent;
	
	public KeyEvent(javafx.scene.input.KeyEvent e) {
		keyEvent = e;
	}
	
	@Override
	public void handleEvent(EventHandler current) {
		setState();
		EventType<javafx.scene.input.KeyEvent> e = keyEvent.getEventType();
		if(e.equals(javafx.scene.input.KeyEvent.KEY_PRESSED)) {
			current.keyPressed(keyEvent);
		} else if(e.equals(javafx.scene.input.KeyEvent.KEY_RELEASED)) {
			current.keyReleased(keyEvent);
		} else if(e.equals(javafx.scene.input.KeyEvent.KEY_TYPED)) {
			current.keyTyped(keyEvent);
		}
	}
	
	private void setState() {
		EventHandler.fxCanvas.setKeyCode(keyEvent.getCode());
		EventHandler.fxCanvas.setKey(keyEvent.getCharacter());
	}
}
