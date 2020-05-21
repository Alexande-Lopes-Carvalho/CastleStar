package presentation;

import controle.CtrlColoredRectangle;
import javafx.scene.paint.Paint;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;
import shapeSceneFX.EventHandling.EventHandler;

public class PresColoredRectangle extends EventHandler {
	private CtrlColoredRectangle c;
	private Paint p;
	private Point coord, dim;

	public PresColoredRectangle(CtrlColoredRectangle _c) {
		c = _c;
		addEvent(new NewColorEvent().in(NewColorEvent.PERIOD));
	}

	public void setPaint(Paint _p) {
		p = _p;
	}

	/*
	 * Ici on ne fait rien dans notre methode calc, la sous classe de EventHandler
	 * ne nous demandant pas de traitement particulier
	 * 
	 * Par contre dans des sous classe d'EventHandler nous demandant par exemple le deplacement d'un personnage ou autres ...
	 * Le traitement de la requete a notre classe Controle se ferait ici
	 */
	public void calc(long timePassed) {
	}
	/*
	 * On souhaite afficher un rectangle avec sa couleur au coordonné et au dimension adéquat
	 */
	public void render() {
		/*
		 * on passe le fond des forme geometrique a afficher de la couleur p
		 */
		fill(p);
		/*
		 * on affiche notre rectangle
		 * coord.getX() : coordonné en X
		 * coord.getY() : coordonné en Y
		 * dim.getX() : longeur (width)
		 * dim.getY() : largeur (height)
		 */
		rect(coord, dim);
	}

	public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
	}

	public Point getDimension() {
		return dim;
	}

	public void setDimension(Point dim) {
		this.dim = dim;
	}
	/*
	 * si on clique alors on change la couleur du rectangle
	 */
	public void mousePressed() {
		c.newColor();
	}
	/*
	 * On crée un evenement qui va appeler toute les 1000 ms la methode newColor() de notre CtrlColoredRectangle c
	 */
	public class NewColorEvent implements Event {
		public static final int PERIOD = 1000;

		@Override
		public void handleEvent() {
			/*
			 * Ici on appel la fonction
			 */
			c.newColor();
			/*
			 * On ajoute un nouvel evenement pour changer de couleur dans 1000 ms
			 */
			addEvent(new NewColorEvent().in(PERIOD));
		}
	}
}
