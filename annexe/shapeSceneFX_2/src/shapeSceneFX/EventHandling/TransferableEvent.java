package shapeSceneFX.EventHandling;

/**
 * Represente un evenement "transferable", qui modifiera la l'instance de classe d'ou elle a �t� appel�.
 * On passera par currentEventHandler pour determin� d'ou l'evenement a �t� lanc�.
 * 
 * Toute les sous classe doivent donc influenc� EventHandler.currentEventHandler.
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
