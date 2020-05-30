package presentation;

import controle.CtrlBow;
import javafx.scene.input.MouseButton;
import shapeSceneFX.EventHandling.Event;
/**
 * Represente la main tenant l'arc
 * @author Administrator
 *
 */
public class PresBow extends PresEquipment {
	/**
	 * temps en ms que le personnage devrait attendre avant de pouvoir tiré a nouveau 
	 */
	private int cooldownTime;
	private CtrlBow ctrlBow;
	private boolean canShoot, isShooting, onCooldown;
	private AnimatedProjectileLauncher animatedProjectileLauncher;
	public PresBow(int _cooldownTime, CtrlBow _ctrlBow) {
		cooldownTime = _cooldownTime;
		ctrlBow = _ctrlBow;
		setLockArm(true);
	}
	
	public void set(AnimatedProjectileLauncher _img) {
		set((AnimatedOrientedImage)_img);
		animatedProjectileLauncher = _img;
		ctrlBow.setProjectileCoord(animatedProjectileLauncher.getCoord().copy().add(animatedProjectileLauncher.getProjectileCoord()));
	}
	
	public void setCanShoot(boolean value) {
		canShoot = value;
		if(!canShoot) {
			desactivate();
		}
	}
	/**
	 * prepare au lancement d'une fleche
	 */
	public void activate() {
		if(canShoot && !onCooldown) {
			isShooting = true;
			getAnimatedOrientedImage().setIndex(1);
		}
	}
	/**
	 * annule la preparation au lancement de la fleche
	 */
	public void desactivate() {
		isShooting = false;
		getAnimatedOrientedImage().setIndex(0);
	}
	
	/**
	 * tire la fleche
	 */
	public void shoot() {
		if(canShoot && isShooting) {
			ctrlBow.use();
			desactivate();
			onCooldown = true;
			addEvent(new ShootEvent().in(cooldownTime));
		}
	}
	/**
	 * si l'instance reçois un evenement bouton gauche de la souris enfoncé (méthode lancé s'il s'agit du bras d'un joueur)
	 * prepare le lancement d'une fleche (si possible, depend du nombre de fleche)
	 */
	public void mousePressed() {
		if(mouseButton().equals(MouseButton.PRIMARY)) {
			activate();
		}
	}
	/**
	 * si l'instance reçois un evenement bouton droit de la souris relaché (méthode lancé s'il s'agit du bras d'un joueur)
	 * lance la fleche
	 */
	public void mouseReleased() {
		if(mouseButton().equals(MouseButton.PRIMARY)) {
			shoot();
		}
	}
	/**
	 * Evenement qui lorsqu'il est lancé symbolise la fin du cooldown que le personnage devait attendre pour tiré a nouveau
	 * @author Administrator
	 * @see PresBow#cooldownTime
	 */
	public class ShootEvent implements Event{
		@Override
		public void handleEvent() {
			onCooldown = false;
		}
	}
	
	public void reset() {
		desactivate();
	}
}
