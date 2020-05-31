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
	/**
	 * Taille d'un pixel pour les images pixelisées qui seront redimensionnées au démarage
	 */
	public static int pxSize = 3;
	private CtrlLevel ctrlLevel;
	private PresLevel presLevel;
	/**
	 * reference d'image a afficher dans le menu du jeu
	 */
	private Image background, logo, button, human, lock, detailSelection, hideDetail;
	/**
	 * Etat du menu du jeu :
	 *  - 0 menu de démarage avec les bouton Start et Quit
	 *  - 1 selection de niveau
	 */
	private int state = 0;
	/**
	 * Bouton (Start Quit)
	 */
	private ButtonImage[] btn;
	private MovingImage movingLogo, movingHuman, movingDetail;
	/**
	 * Le niveau selectionné
	 */
	private int levelSelected;
	/**
	 * Le nombre de niveau accessible 
	 */
	private int maxLevel;
	/**
	 * coordonnée pour localisé un niveau dans la selection (utilisé par le verrou et l'icone de joueur)
	 */
	private Point[] levels;
	private CtrlPlayer ctrlPlayer;
	/**
	 * Demarage du canvas, specifiquation de la taille de la fenetre (depend de pxSize)
	 * Initialisation des images (item, conteneur d'item, Joueur, Enemy, Inventaire)
	 * Preparation du Menu
	 */
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
	
	/**
	 * procedure de calcul, 
	 * Mise a jour du menu ou du niveau si present
	 */
	protected void calc(long timePassed) {
		if (presLevel != null) {
			presLevel.calcEvent(timePassed);
		} else {
			movingLogo.calc(timePassed);
			movingHuman.calc(timePassed);
			movingDetail.calc(timePassed);
		}
	}
	/**
	 * procedure de rendu
	 * affichage du menu ou du niveau si present 
	 */
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
	
	/**
	 * On verifie si lors de la pression d'un bouton de la souris, elle se trouvait au dessus d'un bouton, 
	 * le bouton s'actione si c'est le cas
	 */
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
	/**
	 * Lorsqu'une touche du clavier est enfoncé, 
	 * si l'on se trouve dans la selection du niveau
	 * 	on ferme la fenetre s'il s'agit de la touche ECHAP
	 * 
	 * si l'on se trouve dans le menu de selection du niveau
	 *  on lance le niveau selection sur pression de ENTRE
	 *  on navigue entre les niveau sur pression de Q ou D
	 */
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
	
	/**
	 * procedure qui lance le niveau selectionné
	 */
	public void putLevel() {
		if(levelSelected == 0) {
			//System.out.println("pass");
			presLevel = new CtrlLevel_1(ctrlPlayer, this).getPresLevel();
		} else if(levelSelected == 1) {
			//System.out.println("LEVEL 2");
			presLevel = new CtrlLevel_2(ctrlPlayer, this).getPresLevel();
		}
	}
	/**
	 * change le nombre de niveau accessible
	 * @param k
	 */
	public void setMaxLevel(int k) {
		if(maxLevel >= 0) {
			maxLevel = k;
		}
	}
	/**
	 * selectionne un niveau et anime l'icone en conséquence
	 * @param s
	 */
	public void selectLevel(int s) {
		if(s >= 0 && s < maxLevel) {
			levelSelected = s;
			movingHuman.setObjective(levels[levelSelected].copy().sub(human.getWidth()/2.,human.getHeight()));
		}
	}
	/**
	 * charge toute les image requise au fonctionnement du menu
	 */
	public void initImage() {
		background = loadPixelatedImage("./data/Menu/Fond.png", pxSize);
		logo = loadPixelatedImage("./data/Menu/Logo.png", 3);
		button = loadPixelatedImage("./data/Menu/ButtonIcon.png", 3);
		human = loadPixelatedImage("./data/Menu/HumanIcon.png", pxSize);
		lock = loadPixelatedImage("./data/Menu/Lock.png", pxSize);
		detailSelection = loadPixelatedImage("./data/Menu/SelectionDetail.png", pxSize);
		hideDetail = loadPixelatedImage("./data/Menu/HideDetail.png", pxSize);
	}
	
	/**
	 * change l'etat du menu du jeu :
	 *  - 0 menu de demarrage
	 *  - 1 selection de niveau
	 * @param _state
	 * @see MainEventHandler#state
	 */
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
	
	/**
	 * Image cliquable, lance la procedure press, si l'on clique dessus
	 * @author Administrator
	 *
	 */
	public abstract class ButtonImage {
		/**
		 * coordonnée pour l'affichage de l'image
		 */
		private Point p;
		/**
		 * texte a afficher au dessus de l'image
		 */
		private String txt;
		/**
		 * l'image
		 */
		private Image img;

		public ButtonImage(String _txt, Image _img, Point _p) {
			txt = _txt;
			img = _img;
			p = _p;
		}
		/**
		 * procedure de rendu
		 */
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
		/**
		 * procedure lancé en cas de pression d'un bouton de la souris, verifie si la souris est au dessus de l'image et lance la méthode press() si c'est le cas
		 * @see ButtonImage#press()
		 */
		public void check() {
			if (mouseX() >= p.getX() - img.getWidth() / 2. && mouseX() <= p.getX() + img.getWidth() / 2
					&& mouseY() >= p.getY() - img.getHeight() / 2 && mouseY() <= p.getY() + img.getHeight() / 2.) {
				press();
			}
		}
		/**
		 * méthode appeler lorsque l'utilisateur clique sur l'image 
		 */
		public abstract void press();
	}
	/**
	 * Bouton dont l'action est de passer a la selection de niveau
	 * @author Administrator
	 *
	 */
	public class StartButton extends ButtonImage {
		public StartButton(Point coord) {
			super("START", button, coord);
		}

		public void press() {
			setState(1);
		}
	}
	/**
	 * boutons dont l'action est de passer a la fermuture de la fenetre et la terminaison du programme
	 * @author Administrator
	 *
	 */
	public class QuitButton extends ButtonImage {
		public QuitButton(Point coord) {
			super("QUIT", button, coord);
		}

		public void press() {
			exit();
		}
	}
	/**
	 * Image qui se deplace vers la coordonnée precisé progressivement dans un temps donné
	 * @author Administrator
	 *
	 */
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
		/**
		 * procedure de calcul
		 * @param timePassed
		 */
		public void calc(long timePassed) {
			timeCount += timePassed;
		}
		/**
		 * procedure de rendu
		 */
		public void render() {
			Point k = getCoord();
			//System.out.println(k);
			image(img, Math.round(k.getX()), Math.round(k.getY()));
		}
		/**
		 * definit une coordonné a atteindre
		 * @param o
		 */
		public void setObjective(Point o) {
			p = getCoord();
			timeCount = 0;
			//System.out.println("OBJ " + o);
			obj = o;
		}
		/**
		 * calcul la coordonnée 
		 * @return
		 */
		public Point getCoord() {
			int tCount = Math.min(timeCount, time);
			//System.out.println(tCount + " " + ( ((Math.tanh(((tCount/ ((float)time) )*375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006))) / (((Math.tanh((375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006)))) ));
			return p.copy().add(p.getVector(obj).mult( ( ((Math.tanh(((tCount/ ((float)time) )*375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006))) / (((Math.tanh((375+86)*0.006)-Math.tanh(86*0.006))/(1-Math.tanh(86*0.006)))) )));
		}
	}
	/**
	 * Methode qui transfère les evenement transferrable (clavier, souris, flag)
	 * au niveau si present
	 * 
	 * @see EventHandler#transferEvent(TransferableEvent)
	 */
	public void transferEvent(TransferableEvent e) {
		// System.out.println("TransferEvent From MainEventHandler");
		if (presLevel != null) {
			presLevel.addEvent(e.transfer().in(0));
		} else {
			addEvent(e.in(0));
		}
	}
	/**
	 * méthode appeler a la fin d'un niveau, reset le joueur (remise de la vie au max)
	 */
	public void endLevel() {
		ctrlPlayer.reset();
		presLevel = null;
	}
}
