package shapeSceneFX;

import javafx.animation.AnimationTimer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;
import shapeSceneFX.EventHandling.EventHandler;

/**
 * L'idée est de crée une Scene contenant un canvas definit par un shapeSceneFX.EventHandling.EventHandler
 * (via setup()) et qui actualisera l'EventHandler par l'appel systematique de calcEvent(long timePassed) et render()
 * 
 * 
 * @author Alexandre Lopes Carvalho
 * @see shapeSceneFX.EventHandling.EventHandler#setup()
 * @see shapeSceneFX.EventHandling.EventHandler#calcEvent(long)
 * @see shapeSceneFX.EventHandling.EventHandler#render()
 */

public class FXCanvas extends Scene {
	private GraphicsContext g;
	private Group group;
	private int imageMode;
	private int rectMode;
	private boolean exit;
	private int frameCount;
	private Canvas canvas;
	private long oldTime;
	private double mouseX, mouseY;
	private KeyCode keyCode;
	private String key;
	private MouseButton mouseButton;
	private int mouseCount;
	private EventHandler eventHandler;
	private float frameRate;

	/**
	 * Lance un canvas, et execute la fonction setup de _eventHandler avant
	 * d'appeler _eventHandler.calcEvent(long timePassed) et _eventHandler.render()
	 * en continue jusqu'a la fermeture de la fenetre ou l'appel de la fonction exit() dans l'EventHandler
	 * @param _eventHandler
	 */
	public FXCanvas(EventHandler _eventHandler) {
		super(new Group());
		group = (Group) getRoot();
		EventHandler.fxCanvas = this;
		eventHandler = _eventHandler;
		eventHandler.imageMode(EventHandler.CORNER);
		exit = false;
		eventHandler.setup();
		oldTime = System.nanoTime();
		frameCount = 0;

		setOnMouseClicked((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnMouseDragged((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnMouseEntered((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnMouseExited((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnMouseMoved((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnMousePressed((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnMouseReleased((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnKeyPressed((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnKeyReleased((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});
		setOnKeyTyped((e) -> {
			eventHandler.addEvent(toMillis(System.nanoTime() - oldTime), e);
		});

		calcDraw();
	}
	/**
	 * Converti des nanoSeconde en milliseconde 
	 * @param currentNanoTime
	 * @return
	 */
	public static long toMillis(long currentNanoTime) {
		return currentNanoTime / 1000000;
	}

	private void calcDraw() {
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				if (exit) {
					getWindow().hide();
					stop();
				}
				frameRate = 1000.f / toMillis((currentNanoTime - oldTime));
				eventHandler.calcEvent(toMillis((currentNanoTime - oldTime)));
				eventHandler.render();
				oldTime = currentNanoTime;
				frameCount++;
			}
		}.start();
	}

	/**
	 * Definit la taille de la fenetre en fonction des argument donner
	 * 
	 * @param _width
	 * @param _height
	 */
	public void size(int _width, int _height) {
		/*
		 * if (getCanvas() != null) { throw new IllegalArgumentException(
		 * "size(int _width, int _height) or fullScreen() have already been called"); }
		 */
		setCanvas(new Canvas(_width, _height));
	}

	/**
	 * Lance la fenetre en mode fullScreen
	 * 
	 */
	public void fullScreen() {
		// if (getCanvas() != null) {
		throw new IllegalArgumentException("fullScreen() is in WIP");
		/*
		 * } Screen screen = Screen.getPrimary(); Rectangle2D bounds =
		 * screen.getVisualBounds(); setCanvas(new Canvas(bounds.getWidth(),
		 * bounds.getHeight()));
		 */
	}

	private void setGraphicsContext() {
		g = getCanvas().getGraphicsContext2D();
	}

	private void setCanvas(Canvas c) {
		canvas = c;
		setGraphicsContext();
		eventHandler.stroke(0);
		eventHandler.fill(255);
		if (group.getChildren().size() == 1) {
			group.getChildren().remove(0);
			group.getChildren().add(getCanvas());
			getWindow().sizeToScene();
		} else {
			group.getChildren().add(getCanvas());
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public int getImageMode() {
		return imageMode;
	}
	/**
	 * Definit comment l'on doit interpreté les coordonnée donné pour l'afifchage d'une image, 
	 * si l'on met 0, la coordonné correspondera au coin superieur gauche de l'image
	 * si l'on met 1, la coordonné correspondera au milieu de l'image
	 * 
	 * @param _imageMode
	 */
	public void setImageMode(int _imageMode) {
		imageMode = _imageMode;
	}

	public int getRectMode() {
		return rectMode;
	}
	/**
	 * Definit comment l'on doit interpreté les coordonnée donné pour l'affichage d'un rectangle, 
	 * si l'on met CORNER, la coordonné correspondera au coin superieur gauche du rectangle
	 * si l'on met CENTER, la coordonné correspondera au milieu du rectangle
	 * 
	 * @param _rectMode
	 */
	public void setRectMode(int _rectMode) {
		rectMode = _rectMode;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public GraphicsContext getGraphicsContext() {
		return g;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}

	public void setMouseY(double mouseY) {
		this.mouseY = mouseY;
	}

	public KeyCode getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(KeyCode keyCode) {
		this.keyCode = keyCode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String _key) {
		key = _key;
	}

	public MouseButton getMouseButton() {
		return mouseButton;
	}

	public void setMouseButton(MouseButton _mouseButton) {
		mouseButton = _mouseButton;
	}

	public void mousePressed() {
	}

	public int getMouseCount() {
		return mouseCount;
	}

	public void setMouseCount(int _mouseCount) {
		mouseCount = _mouseCount;
	}

	public float getFrameRate() {
		return frameRate;
	}
	
	public Font getFont() {
		return getGraphicsContext().getFont();
	}
}
