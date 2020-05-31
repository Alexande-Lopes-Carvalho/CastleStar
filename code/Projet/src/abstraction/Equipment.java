package abstraction;

import java.util.List;
import java.util.Observable;

import controle.CtrlEntity;
/**
 * Equipement utilisé par un personnage, (épée, arc, bouclier ...)
 * @author Administrator
 *
 */
public abstract class Equipment extends Observable {
	/**
	 * Action a faire lors de l'utilisation de l'equipment
	 * @param warrior personnage qui possede l'equipment
	 * @param enemy liste des enemi du personnage
	 */
	public abstract void use(Warrior warrior, List<? extends CtrlEntity> enemy);
}
