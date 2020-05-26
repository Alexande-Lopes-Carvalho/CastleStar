package abstraction;
import java.util.ArrayList;

public class WeaponItem extends Item{
	private Weapon weapon;
	private String messAddWeapon;
	public WeaponItem(Point coord){
		super(coord);
		messAddWeapon = "Weapon has been add to you inventory";
	}
	public void pickUpBy(CtrlPlayer ctrlPlayer) {
		 
		CtrlInventory ctrlInventory = ctrlplayer.getCtrlInventory();	
		try {
			ctrlInventory.getInventory().addWeapon(weapon);
		}
		catch(InventoryOutOfBound e) {
			messAddWeapon = "your Inventory is full";
			
		}
	}
	public String toString() {
		return messAddWeapon;
	}
	

}
