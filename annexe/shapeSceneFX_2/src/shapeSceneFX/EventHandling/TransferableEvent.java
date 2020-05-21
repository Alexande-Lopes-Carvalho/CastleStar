package shapeSceneFX.EventHandling;

/**
 * Represente un evenement "transferable", qui modifiera la l'instance de classe d'ou elle a été appelé.
 * On passera par currentEventHandler pour determiné d'ou l'evenement a été lancé.
 * 
 * Toute les sous classe doivent donc influencé EventHandler.currentEventHandler.
 * 
 * @author Alexandre
 *
 */
public interface TransferableEvent extends Event {
	default void handleEvent() {
		handleEvent(EventHandler.getCurrentEventHandler());
	}
	
	void handleEvent(EventHandler current);
	
	default Event transfer() {
		return () -> {EventHandler.getCurrentEventHandler().transferEvent(this);};
	}
}
