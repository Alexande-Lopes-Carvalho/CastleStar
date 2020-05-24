
public class HeartItem extends Item{
	private int sustain ;
	
	public HeartItem() {
		super();
		sustain = 50;
	}
	public void pickUpBy(Player player) {
		player.heal(sustain);
		
	}

}
