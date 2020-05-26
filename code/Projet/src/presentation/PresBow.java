package presentation;

import controle.CtrlBow;
import javafx.scene.input.MouseButton;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;

public class PresBow extends PresEquipment {
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
	
	public void activate() {
		isShooting = true;
		getAnimatedOrientedImage().setIndex(1);
	}
	
	public void desactivate() {
		isShooting = false;
		getAnimatedOrientedImage().setIndex(0);
	}
	
	public void shoot() {
		ctrlBow.use();
		desactivate();
		onCooldown = true;
		addEvent(new ShootEvent().in(cooldownTime));
	}
	
	public void mousePressed() {
		if(mouseButton().equals(MouseButton.PRIMARY) && canShoot && !onCooldown) {
			activate();
		}
	}
	
	public void mouseReleased() {
		if(mouseButton().equals(MouseButton.PRIMARY) && canShoot && isShooting) {
			shoot();
		}
	}
	
	public class ShootEvent implements Event{
		@Override
		public void handleEvent() {
			onCooldown = false;
		}
	}
}
