package shapeSceneFX.EventHandling;

/**
 * Evenement associé a un int, appelera la méthode handleFlag de l'EventHandler qui le lance
 * 
 * @author Alexandre Lopes Carvalho
 * 
 * @see EventHandler#handleFlag
 */

public class FlagEvent implements TransferableEvent {
	private int flagValue;
	public FlagEvent(int _flagValue) {
		flagValue = _flagValue;
	}

	@Override
	public void handleEvent(EventHandler current) {
		current.handleFlag(flagValue);
	}
}
