package abstraction;

public class ShieldItem extends Item{
	
	public ShieldItem(Point coord) {
		super(coord);
	}
	public void pickUpBy(Player player) {
		player.setHasShield(true);
	}

}
