package controle;

import PresItem.PresItem;
import abstraction.Item;

public class CtrlItem extends CtrlElementScene{
	private Item item;
	private PresItem presItem;
	
	public CtrlItem(Item item, PresItem presItem) {
		super(item,presItem);
		this.item = item;
		this.presItem = presItem;
		
	}
	public void playerMoved(Player player) {
		
		if(player.PointInside(this.item.getCoord())) {
			this.item.pickUpBy(player);
			
		}
		
	}
	
}
