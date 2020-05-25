package abstraction;
public class ArrowItem extends Item{
	private int nbArrow ;
	public ArrowItem(Point coord,int nbArrow) {
		super(coord);
		this.nbArrow = nbArrow;
		
	}
	public void pickUpBy(Player player) {
		player.setNbArrow(player.getNbArrow()+nbArrow);
	}

}
