package abstraction;
public class HeartItem extends Item{
	private int sustain ;
	
	public HeartItem(Point coord) {
		super(coord);
		sustain = 50;
	}
	public void pickUpBy(Player player) {
		player.heal(sustain);
		
	}

}
