package controle;

import java.util.Observable;

import abstraction.ElementScene;
import abstraction.Item;
import presentation.MainEventHandler;
import presentation.PresImage;

public class CtrlItem extends CtrlElementScene{
	private Item item;
	private PresImage presItem;
	
	public CtrlItem(Item item, PresImage presItem) {
		super(item,presItem);
		this.item = item;
		this.presItem = presItem;
		presItem.setCoord(item.getCoord().copy().sub((presItem.getWidth()/2.)/MainEventHandler.pxSize, (presItem.getHeight()/2.)/MainEventHandler.pxSize));
	}
	
	public boolean playerMoved(CtrlPlayer player) {
		return item.playerMoved(player);
	}
	
	public void update(Observable o, Object arg) {
		if(arg.equals(ElementScene.COORD_UPDATE)) {
			presItem.setCoord(item.getCoord().copy().sub((presItem.getWidth()/2.)/MainEventHandler.pxSize, (presItem.getHeight()/2.)/MainEventHandler.pxSize));
			return;
		}
		super.update(o, arg);
	}
	
	public Item getItem() {
		return item;
	}
}
