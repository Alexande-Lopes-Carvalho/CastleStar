package presentation;

import abstraction.Player;
import controle.CtrlLevel;
import controle.CtrlLevel_1;
import controle.CtrlLevel_2;
import controle.CtrlPlayer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;
import shapeSceneFX.EventHandling.EventHandler;
import shapeSceneFX.EventHandling.TransferableEvent;

public class MainEventHandler extends EventHandler {
	public static int pxSize = 3;
	private CtrlLevel ctrlLevel;
	private PresLevel presLevel;
	private Image background, logo, button, human, lock, detailSelection, hideDetail;
	private int state = 0;
	private ButtonImage[] btn;
	private MovingImage movingLogo, movingHuman, movingDetail;
	private int levelSelected;
	private int maxLevel;
	private Point[] levels;
	private CtrlPlayer ctrlPlayer;
	public void setup() {
		size(pxSize * 402, pxSize * 160);
		initImage();
		Items.initImage();
		Lootables.initImage();
		PresWarrior.initImage();
		PresPlayer.initImage();
		PresOrc.initImage();
		PresSkeleton.initImage();
		PresArrow.initImage();
		PresInventory.initImage();
		//System.out.println(width());
		btn = new ButtonImage[] { new StartButton(new Point(width() / 2., height() / 2. - 30)),
				new QuitButton(new Point(width() / 2., height() / 2. + 30)) };
		movingLogo = new MovingImage(575, logo, new Point(width() / 2. - logo.getWidth() / 2., -logo.getHeight()));
		levels = new Point[] {new Point(83, 92), new Point(230, 77)};
		for(Point k : levels) {
			k.mult(pxSize);
		}
		movingHuman = new MovingImage(575, human, levels[0].copy().sub(human.getWidth()/2.,human.getHeight()));
		movingDetail = new MovingImage(375, detailSelection, new Point(274*pxSize, 155*pxSize));
		levelSelected = 0;
		
		maxLevel = 1;
		
		ctrlPlayer = new CtrlPlayer(new Player(10, new Point(50, 0), 10, new Point(0, 0)), new PresPlayer());
		setState(0);
		/*
		 * ctrlLevel = new CtrlLevel_1(); presLevel = ctrlLevel.getPresLevel();
		 */
	}

	protected void calc(long timePassed) {
		if (presLevel != null) {
			presLevel.calcEvent(timePassed);
		} else {
			movingLogo.calc(timePassed);
			movingHuman.calc(timePassed);
			movingDetail.calc(timePassed);
		}
	}

	public void render() {
		if (presLevel != null) {
			presLevel.render();
		} else {
			image(background, 0, 0);
			movingLogo.render();
			movingDetail.render();
			image(hideDetail,274*pxSize, 149*pxSize);
			if (state == 0) {
				for (ButtonImage k : btn) {
					k.render();
				}
			} else if(state == 1) {
				movingHuman.render();
				if(maxLevel == 1) {
					Point coord = levels[1].copy().sub(lock.getWidth()/2, lock.getHeight()+5*pxSize);
					image(lock, Math.round(coord.getX()), Math.round(coord.getY()));
				}
			}
			
		}
		stroke(255);
		text(Math.round(frameRate()) + "", 15, 170);
		stroke(0);
	}

	public void mousePressed() {
		if(state == 0) {
			for (ButtonImage k : btn) {
				k.check();
			}
		}
	}
	
	public void keyPressed(javafx.scene.input.KeyEvent e) {
		keyPressed();
	}
	
	public void keyPressed() {
		if(state == 0) {
			if(keyCode().equals(KeyCode.ESCAPE)) {
				exit();
			}
		} else if(state == 1) {
			if(keyCode().equals(KeyCode.ENTER)) {
				putLevel();
			} else if(keyCode().equals(KeyCode.ESCAPE)) {
				setState(0);
			} else if(keyCode().equals(KeyCode.D)) {
				selectLevel(levelSelected+1);
			} else if(keyCode().equals(KeyCode.Q)) {
				selectLevel(levelSelected-1);
			}
		}
	}
	
	public void putLevel() {
		if(levelSelected == 0) {
			//System.out.println("pass");
			presLevel = new CtrlLevel_1(ctrlPlayer, this).getPresLevel();
		} else if(levelSelected == 1) {
			System.out.println("LEVEL 2");
			presLevel = new CtrlLevel_2(ctrlPlayer, this).getPresLevel();
		}
	}
	
	public void setMaxLevel(int k) {
		if(maxLevel >= 0) {
			maxLevel = k;
		}
	}
	
	public void selectLevel(int s) {
		if(s >= 0 && s < maxLevel) {
			levelSelected = s;
			movingHuman.setObjective(levels[levelSelected].copy().sub(human.getWidth()/2.,human.getHeight()));
		}
	}

	public void initImage() {
		background = loadPixelatedImage("./data/Menu/Fond.png", pxSize);
		logo = loadPixelatedImage("./data/Menu/Logo.png", 3);
		button = loadPixelatedImage("./data/Menu/ButtonIcon.png", 3);
		human = loadPixelatedImage("./data/Menu/HumanIcon.png", pxSize);
		lock = loadPixelatedImage("./data/Menu/Lock.png", pxSize);
		detailSelection = loadPixelatedImage("./data/Menu/SelectionDetail.png", pxSize);
		hideDetail = loadPixelatedImage("./data/Menu/HideDetail.png", pxSize);
	}

	public void setState(int _state) {
		state = _state;
		if (state == 0) {
			movingLogo.setObjective(new Point(width() / 2. - logo.getWidth() / 2., 0));
			movingDetail.setObjective(new Point(274*pxSize, 155*pxSize));
		} else if(state == 1) {
			movingLogo.setObjective(new Point(width() / 2. - logo.getWidth() / 2., -logo.getHeight()));
			movingDetail.setObjective(new Point(274*pxSize, 128*pxSize));
		}
	}

	public abstract class ButtonImage {
		private Point p;
		private String txt;
		private Image img;

		public ButtonImage(String _txt, Image _img, Point _p) {
			txt = _txt;
			img = _img;
			p = _p;
		}

		public void render() {
			imageMode(CENTER);
			setTextAlign(CENTER);
			image(img, p.getX(), p.getY());
			stroke(255);
			text(txt, p.getX(), p.getY());
			stroke(0);
			setTextAlign(LEFT);
			imageMode(CORNER);
		}

		public void check() {
			if (mouseX() >= p.getX() - img.getWidth() / 2. && mouseX() <= p.getX() + img.getWidth() / 2
					&& mouseY() >= p.getY() - img.getHeight() / 2 && mouseY() <= p.getY() + img.getHeight() / 2.) {
				press();
			}
		}

		public abstract void press();
	}

	public class StartButton extends ButtonImage {
		public StartButton(Point coord) {
			super("START", button, coord);
		}

		public void press() {
			setState(1);
		}
	}

	public class QuitButton extends ButtonImage {
		public QuitButton(Point coord) {
			super("QUIT", button, coord);
		}

		public void press() {
			exit();
		}
	}

	public class MovingImage {
		private Point p, obj;
		private int timeCount, time;
		private Image img;

		public MovingImage(int _time, Image _img, Point _p) {
			p = _p;
			obj = p.copy();
			img = _img;
			time = _time;
			timeCount = 0;
		}

		public void calc(long timePassed) {
			timeCount += timePassed;
		}

		public void render() {
			Point k = getCoord();
			//System.out.println(k);
			image(img, Math.round(k.getX()), Math.round(k.getY()));
		}

		public void setObjective(Point o) {
			p = getCoord();
			timeCount = 0;
			//System.out.println("OBJ " + o);
			obj = o;
		}

		public Point getCoord() {
			int tCount = Math.min(timeCount, time);
			//System.out.println(tCount + " " + ( ((Math.tanh(((tCount/ ((float)time) )*375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006))) / (((Math.tanh((375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006)))) ));
			return p.copy().add(p.getVector(obj).mult( ( ((Math.tanh(((tCount/ ((float)time) )*375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006))) / (((Math.tanh((375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006)))) )));
		}
	}

	public void transferEvent(TransferableEvent e) {
		// System.out.println("TransferEvent From MainEventHandler");
		if (presLevel != null) {
			presLevel.addEvent(e.transfer().in(0));
		} else {
			addEvent(e.in(0));
		}
	}
	
	public void endLevel() {
		ctrlPlayer.reset();
		presLevel = null;
	}
}
