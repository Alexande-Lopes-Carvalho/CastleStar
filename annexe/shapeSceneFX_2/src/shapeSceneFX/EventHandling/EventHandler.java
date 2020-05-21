package shapeSceneFX.EventHandling;

import java.io.File;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import shapeSceneFX.FXCanvas;
import shapeSceneFX.Point;

/**
 * Permet une phase de calcul et une gestion des événement a la milliseconde près
 *  
 * @author Alexandre Lopes Carvalho
 *
 */

public class EventHandler {
	/**
	 * utilisé pour les Event "transferable" TransferableEvent,
	 * 
	 * @see TransferableEvent
	 */
	private static Stack<EventHandler> eventHandlerStack = new Stack<>();
	/**
	 * permet de timé correctement les evenement quand il sont crée en pleine
	 * execution de calcEvent
	 * 
	 *  @see EventHandler#calcEvent
	 */
	private long timePresent;
	/**
	 * la queue des événements, une PriorityQueue
	 */
	private Queue<ScheduledEvent> eventQueue;
	public static FXCanvas fxCanvas;
	public static final int CORNER = 0,  CENTER = 1, JUSTIFY = 2, LEFT = 3, RIGHT = 4;
	public EventHandler() {
		eventQueue = new PriorityQueue<ScheduledEvent>();
		timePresent = 0;
		addEvent(new TickEvent().in(0));
	}
	
	/**
	 * Ne doit pas être appeler a l'exterieur de la classe, calcEvent() s'occupera de l'appeler,
	 * méthode public car doit être Overridable pour les classes filles
	 * 
	 * @param timePassed le temps ecoulé
	 * 
	 * @see EventHandler#calcEvent
	 */
	protected void calc(long timePassed) {
		//System.out.println("calc(" + timePassed + ")");
	}

	public void render() {
	}
	
	/**
	 * Methode appelé a la création de notre canvas, on on definira la taille du
	 * canvas
	 * 
	 * @see FXCanvas#size(int, int)
	 * @see FXCanvas
	 */
	public void setup() {
	}
	
	/**
	 * Va calculé pas a pas en lancant les événement au bon moment et en appelant la méthode calc(timePassed)
	 * 
	 * @param timePassed le temps ecoulé depuis la derniere mise a jour
	 */
	public void calcEvent(long timePassed) {
		if(timePassed > 0) {
			getEventHandlerStack().push(this);
			addEvent(new TickEvent().in(timePassed));
			Queue<ScheduledEvent> queue = getEventQueue();
			for(ScheduledEvent e = queue.peek(); e.getStartCount() != timePassed;) {
				long time = e.getStartCount();
				setTimePresent(time);
				for(; e.getStartCount() == time; e = queue.peek()) {
					e.handleEvent();
					queue.remove(e);
				}
				time = e.getStartCount()-time;
				calc(time);
			}
			setTimePresent(0);
			for(ScheduledEvent k : queue) {
				k.setStartCount(k.getStartCount()-timePassed);
			}
			getEventHandlerStack().pop();
		}
	}

	/**
	 * Ajoute un événement a lancer lors de la phase de calcul
	 * 
	 * @param e évènement
	 */
	public void addEvent(ScheduledEvent e) {
		e.setStartCount(((e.getStartCount() >= 0)? e.getStartCount() : 0)+getTimePresent());
		getEventQueue().add(e);
	}
	
	public void addEvent(ScheduledEvent ... arEvent) {
		for(ScheduledEvent e : arEvent) {
			addEvent(e);
		}
	}
	
	/**
	 * Ajoute un événement souris a lancer lors de la phase de calcul
	 * 
	 * @param _startCount temps restant en milliseconde avant l'execution de l'evenement
	 * @param e événement souris
	 * 
	 * @see MouseEvent
	 * @see EventHandler#mousePressed
	 * @see EventHandler#mouseReleased
	 * @see EventHandler#mouseClicked
	 * @see EventHandler#mouseDragged
	 * @see EventHandler#mouseMoved
	 * @see EventHandler#mouseEntered
	 * @see EventHandler#mouseExited
	 */
	public void addEvent(long _startCount, javafx.scene.input.MouseEvent e) {
		addEvent(new MouseEvent(e).transfer().in(_startCount));
	}
	
	/**
	 * Ajoute un événement clavier a lancer lors de la phase de calcul
	 * 
	 * @param _startCount temps restant en milliseconde avant l'execution de l'événement
	 * @param e événement souris
	 * 
	 * @see KeyEvent
	 * @see EventHandler#keyPressed
	 * @see EventHandler#keyTyped
	 * @see EventHandler#keyReleased
	 */
	public void addEvent(long _startCount, javafx.scene.input.KeyEvent e) {
		addEvent(new KeyEvent(e).transfer().in(_startCount));
	}
	
	/**
	 * Ajoute un événement de type Flag, a lancer lors de la phase de calcul
	 * 
	 * @param _startCount temps restant en milliseconde avant l'execution de l'événement
	 * @param _flagValue valeur du flag
	 * 
	 * @see FlagEvent
	 * @see EventHandler#handleFlag(int)
	 */
	public void addEvent(long _startCount, int _flagValue) {
		addEvent(new FlagEvent(_flagValue).in(_startCount));
	}
	
	public void removeEvent(ScheduledEvent e) {
		getEventQueue().remove(e);
	}

	public static EventHandler getCurrentEventHandler() {
		return eventHandlerStack.peek();
	}

	protected static Stack<EventHandler> getEventHandlerStack() {
		return eventHandlerStack;
	}
	
	protected Queue<ScheduledEvent> getEventQueue() {
		return eventQueue;
	}
	
	protected long getTimePresent() {
		return timePresent;
	}

	protected void setTimePresent(long timePresent) {
		this.timePresent = timePresent;
	}
	
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void keyPressed(javafx.scene.input.KeyEvent e) {
		if(keyCode().equals(KeyCode.ESCAPE)) {
			exit();
		}
		keyPressed();
	}
	
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void keyPressed() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void keyTyped(javafx.scene.input.KeyEvent e) {
		keyTyped();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void keyTyped() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void keyReleased(javafx.scene.input.KeyEvent e) {
		keyReleased();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void keyReleased() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void mousePressed(javafx.scene.input.MouseEvent e) {
		mousePressed();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void mousePressed() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void mouseReleased(javafx.scene.input.MouseEvent e) {
		mouseReleased();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void mouseReleased() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void mouseClicked(javafx.scene.input.MouseEvent e) {
		mouseClicked();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void mouseClicked() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void mouseDragged(javafx.scene.input.MouseEvent e) {
		mouseDragged();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void mouseDragged() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void mouseMoved(javafx.scene.input.MouseEvent e) {
		mouseMoved();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void mouseMoved() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void mouseEntered(javafx.scene.input.MouseEvent e) {
		mouseEntered();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void mouseEntered() {
	}
	/**
	 * Si l'on a besoin de plus de detail concernant l'evenement on override cette méthode au lieu de sa version sans paramètre
	 * 
	 * @param e
	 */
	public void mouseExited(javafx.scene.input.MouseEvent e) {
		mouseExited();
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void mouseExited() {
	}
	/**
	 * méthode appelé quand l'évenement est réalisé
	 */
	public void handleFlag(int flagValue) {
	}
	
	/**
	 * Méthode qui transfère des evenement vers d'autre EventHandler, par default 
	 * lancera l'evenement sur l'instance elle meme
	 * 
	 * @param e
	 */
	public void transferEvent(TransferableEvent e) {
		addEvent(e.in(0));
	}
	
	/**
	 * Class qui represente des tick, handleEvent ne fait donc rien de particulier
	 * 
	 * @author Alexandre Lopes Carvalho
	 *
	 */
	public class TickEvent implements Event {
		public void handleEvent() {
		}
	}

	public FXCanvas getFXCanvas() {
		return fxCanvas;
	}
	
	/**
	 * Definit comment l'on doit interpreté les coordonnée donné pour l'afifchage d'une image, 
	 * si l'on met CORNER, la coordonné correspondera au coin superieur gauche de l'image
	 * si l'on met CENTER, la coordonné correspondera au milieu de l'image
	 * 
	 * @param _const
	 */
	public void imageMode(int _const) {
		if(_const >= CORNER && _const <= CENTER) {
			getFXCanvas().setImageMode(_const);
		} else {
			throw new IllegalArgumentException("IllegalArgument at imageMode(int _const)");
		}
	}
	/**
	 * Definit comment l'on doit interpreté les coordonnée donné pour l'affichage d'un rectangle, 
	 * si l'on met CORNER, la coordonné correspondera au coin superieur gauche du rectangle
	 * si l'on met CENTER, la coordonné correspondera au milieu du rectangle
	 * 
	 * @param _const
	 */
	public void rectMode(int _const) {
		if(_const >= CORNER && _const <= CENTER) {
			getFXCanvas().setRectMode(_const);
		} else {
			throw new IllegalArgumentException("IllegalArgument at rectMode(int _const)");
		}
	}
	/**
	 * Definit la taille de la fenetre graphique (doit etre utilisé uniquement dans setup())
	 * 
	 * @param w longeur fenetre (en pixel)
	 * @param h largeur fenetre (en pixel)
	 */
	public void size(int w, int h) {
		getFXCanvas().size(w, h);
	}
	/**
	 * Work in progress
	 */
	public void fullScreen() {
		getFXCanvas().fullScreen();
	}
	/**
	 * retourne la longueur de la fenetre
	 * @return
	 */
	public double width() {
		return getFXCanvas().getCanvas().getWidth();
	}
	/**
	 * retourne la largeur de la fenetre
	 * @return
	 */
	public double height() {
		return getFXCanvas().getCanvas().getHeight();
	}
	/**
	 * retourne la position de la souris sur l'axe X
	 * @return
	 */
	public double mouseX() {
		return getFXCanvas().getMouseX();
	}
	/**
	 * retourne la position de la souris sur l'axe Y
	 * @return
	 */
	public double mouseY() {
		return getFXCanvas().getMouseY();
	}
	/**
	 * retourne le boutons de la souris associé au dernier l'evenement souris lancé
	 * @return
	 */
	public MouseButton mouseButton() {
		return getFXCanvas().getMouseButton();
	}
	/**
	 * retourne le nombre de click souris associé au dernier évenement souris lancé
	 * @return
	 */
	public int mouseCount() {
		return getFXCanvas().getMouseCount();
	}
	/**
	 * retourne le KeyCode associé au dernier evenement clavier lancé
	 * @return
	 */
	public KeyCode keyCode() {
		return getFXCanvas().getKeyCode();
	}
	/**
	 * retourne un string decrivant le character ou la sequence de character associé au dernier evenement clavier lancé
	 * @return
	 */
	public String key() {
		return getFXCanvas().getKey();
	}
	/**
	 * retourne le frameRate de notre canvas
	 * @return
	 */
	public float frameRate() {
		return getFXCanvas().getFrameRate();
	}
	 
	/**
	 * Retourne une couleur ayant les trois composante R G B a la meme valeur
	 * 
	 * @param rgb
	 * @return
	 * 
	 * @see Color#grayRgb(int)
	 */
	public Paint color(int rgb) {
		return Color.grayRgb(rgb);
	}
	
	/**
	 * Retourne une couleur 
	 *  
	 * @param r composante Rouge (0-255)
	 * @param g composante Verte (0-255)
	 * @param b composante Bleu (0-255)
	 * @return
	 * 
	 * @see Color#rgb(int, int, int)
	 */
	public Paint color(int r, int g, int b) {
		return Color.rgb(r, g, b);
	}
	
	/**
	 * Retourne une couleur
	 * 
	 * @param r composante Rouge (0-255)
	 * @param g composante Verte (0-255)
	 * @param b composante Bleu (0-255)
	 * @param a composante transparente (0-255)
	 * @return
	 */
	public Paint color(int r, int g, int b, int a) {
		return Color.rgb(r, g, b, a/255.);
	}
	
	/**
	 * Definit la couleur des contour/trait
	 * 
	 * @param a
	 */
	public void stroke(Paint a) {
		getFXCanvas().getGraphicsContext().setStroke(a);
	}
	
	/**
	 * Definit la couleur des contour/trait en passant par la methode color(int)
	 * 
	 * @param rgb
	 * @see color(int)
	 */
	public void stroke(int rgb) {
		getFXCanvas().getGraphicsContext().setStroke(color(rgb));
	}
	
	/**
	 * Definit la couleur des contour/trait en passant par la methode color(int, int, int)
	 * 
	 * @param r composante Rouge (0-255)
	 * @param g composante Verte (0-255)
	 * @param b composante Bleu (0-255)
	 * @see color(int, int, int)
	 */
	public void stroke(int r, int g, int b) {
		getFXCanvas().getGraphicsContext().setStroke(color(r, g, b));
	}
	
	/**
	 * Definit la couleur des contour/trait en passant par la methode color(int, int, int, int)
	 * 
	 * @param r composante Rouge (0-255)
	 * @param g composante Vert (0-255)
	 * @param b composante Bleu (0-255)
	 * @param a composante Transparente (0-255)
	 * @see color(int, int, int, int)
	 */
	public void stroke(int r, int g, int b, int a) {
		getFXCanvas().getGraphicsContext().setStroke(color(r, g, b, a));
	}
	
	/**
	 * Definit une couleur transparente pour ne pas afficher le contour des forme géometrique
	 * 
	 */
	public void noStroke() {
		getFXCanvas().getGraphicsContext().setStroke(color(0, 0, 0, 0));
	}
	
	/**
	 * Retounre l'objet Paint assigné a stroke
	 * @return
	 */
	public Paint getStroke() {
		return getFXCanvas().getGraphicsContext().getStroke();
	}
	
	/**
	 * retourne la police d'ecriture associé au canvas
	 * @return
	 */
	public Font getFont() {
		return getFXCanvas().getFont();
	}
	/*
	default void strokeWeight(double weight) {
		getFXCanvas().getGraphicsContext().setLineWidth(weight);
	}*/
	/**
	 * Definit comment l'on doit interpreté les coordonnée donné pour l'afifchage d'une text, 
	 * si l'on met CENTER, la coordonné y correspondera au milieu du texte 
	 * LEFT
	 * JUSTIFY
	 * RIGHT
	 * 
	 * @param _const
	 */
	public void setTextAlign(int _const) {
		switch(_const){
			case CENTER:
				getFXCanvas().getGraphicsContext().setTextAlign(TextAlignment.CENTER);
				break;
			case JUSTIFY :
				getFXCanvas().getGraphicsContext().setTextAlign(TextAlignment.JUSTIFY);
				break;
			case LEFT :
				getFXCanvas().getGraphicsContext().setTextAlign(TextAlignment.LEFT);
				break;
			case RIGHT :
				getFXCanvas().getGraphicsContext().setTextAlign(TextAlignment.RIGHT);
				break;
		}
	}
	
	/**
	 * Definit la couleur a afficher lors du dessin de forme géometrique
	 * 
	 * @param p
	 */
	public void fill(Paint p) {
		getFXCanvas().getGraphicsContext().setFill(p);
	}
	
	/**
	 * Definit la couleur a afficher lors du dessin de forme géometrique
	 * 
	 * @param rgb
	 * @see color(int)
	 */
	public void fill(int rgb) {
		getFXCanvas().getGraphicsContext().setFill(color(rgb));
	}
	
	/**
	 * Definit la couleur a afficher lors du dessin de forme géometrique
	 * 
	 * @param r composante Rouge (0-255)
	 * @param g composante Verte (0-255)
	 * @param b composante Bleu (0-255)
	 * @see color(int, int, int)
	 */
	public void fill(int r, int g, int b) {
		getFXCanvas().getGraphicsContext().setFill(color(r, g, b));
	}
	
	/**
	 * Definit une couleur transparente pour ne pas afficher l'interieur des forme géometrique 
	 */
	public void noFill() {
		getFXCanvas().getGraphicsContext().setFill(color(0, 0, 0, 0));
	}
	
	/**
	 * Definit la couleur a afficher lors du dessin de forme géometrique
	 * 
	 * @param r composante Rouge (0-255)
	 * @param g composante Vert (0-255)
	 * @param b composante Bleu (0-255)
	 * @param a composante Transparente (0-255)
	 * @see color(int, int, int, int)
	 */
	public void fill(int r, int g, int b, int a) {
		getFXCanvas().getGraphicsContext().setFill(color(r, g, b, a));
	}
	
	/**
	 * Retounre l'objet Paint assigné a fill
	 * @return
	 */
	public Paint getFill() {
		return getFXCanvas().getGraphicsContext().getFill();
	}

	public Point getCoord(double x, double y, double w, double h, int coordMode) {
		if(coordMode == CORNER) {
			return new Point(x, y);
		} else if(coordMode == CENTER) {
			return new Point(x-w/2., y-h/2.);
		} else {
			throw new IllegalArgumentException("Invalid coordMode at ProcessingMethod.getCoord");
		}
	}
	
	/**
	 * Dessine un rectangle
	 * @param x coord en x
	 * @param y coord en y
	 * @param w longueur
	 * @param h hauteur
	 */
	public void rect(double x, double y, double w, double h) {
		Point coord = getCoord(x, y, w, h, getFXCanvas().getRectMode());
		getFXCanvas().getGraphicsContext().fillRect(coord.getX(), coord.getY(), w, h);
		getFXCanvas().getGraphicsContext().strokeRect(coord.getX(), coord.getY(), w, h);
	}
	/**
	 * Dessine un rectangle
	 * @param coord coordonné en x, y
	 * @param dim dimension w, h
	 */
	public void rect(Point coord, Point dim) {
		rect(coord.getX(), coord.getY(), dim.getX(), dim.getY());
	}
	
	/**
	 * dessine une ligne
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void line(double x1, double y1, double x2, double y2) {
		getFXCanvas().getGraphicsContext().strokeLine(x1, y1, x2, y2);
	}
	/**
	 * dessine une ligne
	 * @param a
	 * @param b
	 */
	public void line(Point a, Point b) {
		getFXCanvas().getGraphicsContext().strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
	}
	
	/**
	 * dessine un text a la coordonné souhaité
	 * 
	 * @param txt
	 * @param x
	 * @param y
	 */
	public void text(String txt, double x, double y) {
		getFXCanvas().getGraphicsContext().strokeText(txt, x, y);
	}
	/**
	 * color le fond de notre canvas avec la couleur donnée
	 * @param rgb
	 * @see color(int)
	 */
	public void background(int rgb) {
		Paint f = getFill(), s = getStroke();
		int a = getFXCanvas().getRectMode();
		fill(rgb);
		noStroke();
		rectMode(CORNER);
		rect(0, 0, width(), height());
		rectMode(a);
		stroke(s);
		fill(f);
	}
	
	/**
	 * Retourne l'image situé au chemin definit
	 * @param path
	 * @return
	 */
	public static Image loadImage(String path) {
		return new Image(new File(path).toURI().toString());
	}
	/**
	 * Charge une image pixelisé avec le facteur de grandissement definit
	 * @param path
	 * @param scale
	 * @return
	 */
	public static Image loadPixelatedImage(String path, int scale) {
		Image im = loadImage(path);
		int w = (int)im.getWidth(), h = (int)im.getHeight();
		WritableImage res = new WritableImage(w*scale, h*scale);
		PixelReader reader = im.getPixelReader();
		PixelWriter writer = res.getPixelWriter();
		
		for(int j = 0; j < h; j++) {
			for(int i = 0; i < w; i++) {
				int argb = reader.getArgb(i,  j);
				for(int dj = 0; dj < scale; dj++) {
					for(int di = 0; di < scale; di++) {
						writer.setArgb(i*scale+di, j*scale+dj, argb);
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * Desisne l'image a la coordonné souhaité
	 * 
	 * @param img
	 * @param x
	 * @param y
	 */
	public void image(Image img, double x, double y) {
		Point coord = getCoord(x, y, img.getWidth(), img.getHeight(), getFXCanvas().getImageMode());
		getFXCanvas().getGraphicsContext().drawImage(img, coord.getX(), coord.getY());
	}
	/**
	 * Dessine l'image a la coordonné souhaité et avec les dimensions souhaités
	 * @param img
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void image(Image img, double x, double y, double w, double h) {
		Point coord = getCoord(x, y, w, h, getFXCanvas().getImageMode());
		getFXCanvas().getGraphicsContext().drawImage(img, coord.getX(), coord.getY(), w, h);
	}
	/**
	 * Dessine l'image a la coordonné souhaité 
	 * @param img
	 * @param coord
	 */
	public void image(Image img, Point coord) {
		image(img, coord.getX(), coord.getY());
	}
	
	/**
	 * arrete la fenetre
	 */
	public void exit() {
		getFXCanvas().setExit(true);
	}
	
	/**
	 * retourne le nombre total d'image généré
	 * @return
	 */
	public int frameCount() {
		return getFXCanvas().getFrameCount();
	}
	/**
	 * Deplace l'origine a la coordonné souhaité
	 * @param x
	 * @param y
	 */
	public void translate(double x, double y) {
		getFXCanvas().getGraphicsContext().translate(x, y);
	}
	/**
	 * Deplace l'origine a la coordonné souhaité
	 * @param p
	 */
	public void translate(Point p) {
		getFXCanvas().getGraphicsContext().translate(p.getX(), p.getY());
	}
	/**
	 * Utilisé après un tanslate pour effectué l'operation inverse et retouné a l'origine de base
	 * @param x
	 * @param y
	 */
	public void translateBack(double x, double y) {
		getFXCanvas().getGraphicsContext().translate(-x, -y);
	}
	/**
	 *  Utilisé après un tanslate pour effectué l'operation inverse et retouné a l'origine de base
	 * @param p
	 */
	public void translateBack(Point p) {
		getFXCanvas().getGraphicsContext().translate(-p.getX(), -p.getY());
	}
	/**
	 * Effectue une rotation de l'origine sur l'axe Z 
	 * @param r unité en radian
	 */
	public void rotate(double r) {
		getFXCanvas().getGraphicsContext().rotate(r*(180./Math.PI));
	}
	
	/**
	 * Utilisé après une rotation de l'origine sur l'axe Z pour effectué l'operation inverse et retouné a l'origine de base
	 * @param r unité en radian
	 */
	public void rotateBack(double r) {
		getFXCanvas().getGraphicsContext().rotate(-r*(180./Math.PI));
	}
	/**
	 * Effectue une rotation de l'origine sur l'axe Z (unité en Radian)
	 * @param p
	 */
	public void rotate(Point p) {
		getFXCanvas().getGraphicsContext().rotate(p.getZ()*(180./Math.PI));
	}
	/**
	 * Utilisé après une rotation de l'origine sur l'axe Z pour effectué l'operation inverse et retouné a l'origine de base
	 * @param p
	 */
	public void rotateBack(Point p) {
		getFXCanvas().getGraphicsContext().rotate(-p.getZ()*(180./Math.PI));
	}
}