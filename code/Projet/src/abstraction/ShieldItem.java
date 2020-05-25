package abstraction;

public class ShieldItem extends Item{
	
	public ShieldItem(Point coord) {
		super(coord);
	}
	public void pickUpBy(CtrlPlayer ctrlPlayer) {
		ctrlPlayer().equip(new CtrlShield());
	}

}
