package controle;


import abstraction.Lootable;
import presentation.PresElementScene;
/**
 * Conteneur d'item
 * @author Administrator
 * @see CtrlItem
 * @see Lootable
 */
public class CtrlLootable extends CtrlEntity {
	private CtrlItem[] items;
	private Lootable lootable;
	public CtrlLootable(Lootable _lootable, PresElementScene e, CtrlItem ... _items) {
		super(_lootable, e);
		items = _items;
		lootable = _lootable;
	}
	/**
	 * On tue le conteneur et fait apparaitre les item
	 */
	public void kill() {
		for(CtrlItem k : items) {
			double dist = Math.random()*lootable.getDistSpawn(), ang = Math.random()*2*Math.PI;
			k.getItem().setCoord(lootable.getCenterHitbox().copy().add(Math.cos(ang)*dist, Math.sin(ang)*dist));
			currentLevel.add(k);
		}
		currentLevel.remove(this);
	}
}
