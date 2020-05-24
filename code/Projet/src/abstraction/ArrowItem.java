
public class ArrowItem extends Item{
	private int nbArrow ;
	public ArrowItem(int nbArrow) {
		super();
		this.nbArrow = nbArrow;
		
	}
	public void pickUpBy(Player player) {
		player.setNbArrow(player.getNbArrow()+nbArrow);
	}

}
