package shapeSceneFX.EventHandling;

/**
 * Interface fonctionnel qui represente un événement, 
 * La méthode fonctionnel est handleEvent
 * 
 * @author Alexandre Lopes Carvalho
 * 
 * @see EventHandler#mousePressed
 */

@FunctionalInterface
public interface Event {
	/**
	 * méthode appelé lors du lancement de l'evenement
	 */
	void handleEvent();
	
	/**
	 * Crée un evenement programmé ScheduledEvent qui se declenchera dans un event handler dans un certain
	 * nombre de milliseconde
	 * 
	 * @param startCount nombre de milliseconde avant le lancement de l'evenement
	 * @return evenement programmé
	 */
	default ScheduledEvent in(long startCount) {
		return new ScheduledEvent(startCount, this);
	}
}
