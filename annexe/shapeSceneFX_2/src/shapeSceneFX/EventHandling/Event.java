package shapeSceneFX.EventHandling;

/**
 * Interface fonctionnel qui represente un �v�nement, 
 * La m�thode fonctionnel est handleEvent
 * 
 * @author Alexandre Lopes Carvalho
 * 
 * @see EventHandler#mousePressed
 */

@FunctionalInterface
public interface Event {
	/**
	 * m�thode appel� lors du lancement de l'evenement
	 */
	void handleEvent();
	
	/**
	 * Cr�e un evenement programm� ScheduledEvent qui se declenchera dans un event handler dans un certain
	 * nombre de milliseconde
	 * 
	 * @param startCount nombre de milliseconde avant le lancement de l'evenement
	 * @return evenement programm�
	 */
	default ScheduledEvent in(long startCount) {
		return new ScheduledEvent(startCount, this);
	}
}
