package controle;

import java.util.Observable;
import java.util.Observer;

import abstraction.Inventory;
import abstraction.Player;
import presentation.PresInventory;
import shapeSceneFX.Point;
/**
 * Inventaire d'un joueur
 * @author Administrator
 * @see Inventory
 * @see PresInventory
 */
public class CtrlInventory implements Observer {
	private PresInventory presInventory;
	private Inventory inventory;
	/**
	 * Equipement present dans l'inventaire
	 */
	private CtrlInventoryEquipment[] ctrlInventoryEquipment;
	private CtrlPlayer ctrlPlayer;
	public CtrlInventory(Inventory _inventory, CtrlPlayer _ctrlPlayer) {
		inventory = _inventory;
		ctrlPlayer = _ctrlPlayer;
		presInventory = new PresInventory(new Point(15, 15), inventory.getIndex(), inventory.getLength(), ctrlPlayer.getPlayer().getLife(), ctrlPlayer.getPlayer().getMaxLife(), ctrlPlayer.getPlayer().getNbArrow(), this);
		ctrlInventoryEquipment = new CtrlInventoryEquipment[inventory.getLength()];
		inventory.addObserver(this);
	}
	/**
	 * Ajoute un equipement dans l'inventaire
	 * @param ct
	 */
	public void add(CtrlInventoryEquipment ct) {
		for(int i = 0; i < ctrlInventoryEquipment.length; i++) {
			if(ctrlInventoryEquipment[i] != null && ct.getClass() == ctrlInventoryEquipment[i].getClass()) {
				return;
			}
		}
		for(int i = 0; i < ctrlInventoryEquipment.length; i++) {
			if(ctrlInventoryEquipment[i] == null) {
				ctrlInventoryEquipment[i] = ct;
				presInventory.setInventoryImage(ct.getInventoryImage(), i);
				inventory.setIndex(inventory.getIndex());
				return;
			}
		}
	}
	
	public void changeToLeft() {
		inventory.changeWeaponLeft();
	}
	
	public void changeToRight() {
		inventory.changeWeaponRight();
	}
	
	public void setIndex(int index) {
		inventory.setIndex(index);
	}
	
	public PresInventory getPresInventory() {
		return presInventory;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1.equals(Inventory.INDEX_UPDATE)) {
			if(ctrlInventoryEquipment[inventory.getIndex()] != null) {
				ctrlPlayer.equip(ctrlInventoryEquipment[inventory.getIndex()]);
			} else {
				ctrlPlayer.equip(ctrlPlayer.getHandFront());
			}
			presInventory.setIndex(inventory.getIndex());
		} else if(arg1.equals(Player.HEAL) || arg1.equals(Player.DAMAGE)) {
			presInventory.setLife(ctrlPlayer.getPlayer().getLife());
		} else if(arg1.equals(Player.NBARROW_UPDATE)) {
			presInventory.setNbArrow(ctrlPlayer.getPlayer().getNbArrow());
		}
	}
}
