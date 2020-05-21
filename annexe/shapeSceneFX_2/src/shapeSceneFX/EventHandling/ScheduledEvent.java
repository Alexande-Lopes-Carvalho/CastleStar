package shapeSceneFX.EventHandling;

/**
 * Evenement a produire dans quelques milliseconde
 * 
 * @author Alexandre Lopes Carvalho
 *
 */

public class ScheduledEvent implements Comparable<ScheduledEvent> {
	/**
	 * Exprime en milliseconde le temps restant avant que l'événement ai lieu
	 */
	private long startCount;
	/**
	 * Evenement a lancer
	 */
	private Event event;
	public ScheduledEvent(long _startCount, Event _event) {
		startCount = _startCount;
		event = _event;
	}
	
	public void handleEvent() {
		event.handleEvent();
	}
	
	@Override
	public int compareTo(ScheduledEvent o) {
		return (int)(startCount-o.startCount);
	}

	public long getStartCount() {
		return startCount;
	}

	public void setStartCount(long startCount) {
		this.startCount = startCount;
	}
}
