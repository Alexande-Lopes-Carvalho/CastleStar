package presentation;

import abstraction.ColoredRectangle;
import controle.CtrlColoredRectangle;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;
import shapeSceneFX.EventHandling.TransferableEvent;

public class MainEventHandler extends EventHandler {
	/*
	 * PresColoredRectangle est une sous classe de EventHandler
	 */
	private PresColoredRectangle r;

	/*
	 * setup est ici pour definir des information propre au lancement du canvas
	 * comme par exemple sa taille (avec size()) voir
	 * shapeSceneFX.ProcessingMethod#size(int, int)
	 * 
	 * Ce qui veut dire que le Canvas n'existe pas encore lorsque notre constructeur
	 * MainEventHandler est appel� Il ne faut donc jamais utilis� le contructeur
	 * dans la sous classe d'EventHandler donn� en argument dans le constructeur de
	 * notre FXCanvas sauf si l'on ne fait aucune operation graphique
	 * 
	 * Les instance de sous class d'EventHandler qui ne sont pas donn� en argument du
	 * constructeur FXCanvas peuvent par contre faire des operation graphique dans
	 * leur constructeur
	 */
	public void setup() {
		// ici on definit une fenetre de 250 pixel par 250
		size(250, 250);
		/*
		 * creation de notre Rectangle color� qui changera de couleur toute les seconde
		 * ou grace a un click de souris selon le pattern PAC
		 */
		CtrlColoredRectangle c = new CtrlColoredRectangle(new ColoredRectangle(new Point(50, 50), new Point(60, 60)));
		r = c.getP();
	}

	/*
	 * lorsque calcEvent(long timePassed) est appel�, il va execut� les evenement
	 * devant etre lanc� immediatement puis il va calcul� le temps avant un prochain
	 * evenement a lanc� et appel calc(long timePassed) avec le temps ad�quat Il
	 * lancera ensuite les evenement a lanc� et recommencera le meme processus
	 * jusqu'a avoir calcul� tout le temps qu'il lui avait �t� donner en argument
	 * 
	 */
	protected void calc(long timePassed) {
		/*
		 * Apres la prise en compte des evenement propre a notre instance de
		 * MainEventHandler notre phase de calcul vise a mettre a jour les EvenHandler
		 * que notre instance poss�de ici r
		 * 
		 * On appel donc r.calcEvent pour que PresColoredRectangle r se mette a jour et
		 * prenne en compte ses evenement et sa methode calc(long timePassed)
		 */
		r.calcEvent(timePassed);
	}

	/*
	 * Notre fonction de rendu, elle va afficher dans la fenetre ce que l'on
	 * souhaite
	 */
	public void render() {
		/*
		 * A chaque generation d'une image il est important de cacher la derniere image
		 * g�n�r�
		 * 
		 * Pour cela on passe tout les pixel de notre canvas en noir avec background(0)
		 * On ne doit faire ceci qu'au debut de la generation d'une image, donc seul
		 * notre MainEventHandler s'occupera de faire cela
		 * 
		 * Les autre instance/sous classe de EventHandler ne doivent pas s'occuper de
		 * cacher la derniere image g�n�r�
		 */
		background(0);
		/*
		 * on afficher notre PresColoredRectangle r en appelant sa methode de rendu
		 */
		r.render();
		/*
		 * on souhaite ecrire un text en blanc, on passe donc la couleur pour dessin�
		 * des contour g�ometrique en blanc avec stroke(255)
		 */
		stroke(255);
		/*
		 * On affiche notre text a la coordonn� x : 20 et y : 20,
		 * le text affich� correspond au nombre d'image calcul� depuis le lancement du programme
		 */
		text("" + frameCount(), 20, 20);
		/*
		 * on repasse la couleur pour dessiner des contour g�ometrique en noir
		 * (facultatif)
		 */
		stroke(0);
	}

	/*
	 * Definit comment on doit trait� les evenement transferable a d'autre
	 * instance/sous classe de EventHandler les evenement transferable son des
	 * Evenement Clavier, Souris ou des Flag (qui transporte comme information un
	 * int)
	 */
	public void transferEvent(TransferableEvent e) {
		/*
		 * Ici on dit que notre evenement transferable doit etre execut� dans notre
		 * instance de MainEventHandler
		 */
		addEvent(e.in(0));
		/*
		 * On ajoute l'evenement transferable a la Queue des evenement de notre
		 * PresColoredRectangle r
		 */
		r.addEvent(e.in(0));
	}
}
