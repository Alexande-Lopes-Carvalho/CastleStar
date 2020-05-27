import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;

abstract class Enemy extends Warrior {

	private List<Point> itineraire = new ArrayList<Point>();


	public List<Point> getItineraire() {
		return this.itineraire;
	}

	public void setItineraire(List<Point> itineraire) {
		this.itineraire = itineraire;
	}

	public void refreshItinary();
}