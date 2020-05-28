package abstraction;

import java.util.Observable;

public class Inventory extends Observable {
	public static final Object INDEX_UPDATE = 12;
	private int index, length;
	public Inventory(int _index, int _length) {
		index = _index;
		length = _length;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void changeWeaponRight() {
		setIndex((index+1)%length);
	}
	
	public void changeWeaponLeft() {
		if(index-1 == -1) {
			setIndex(length-1);
		} else {
			setIndex(index-1);
		}
	}
	
	public void setIndex(int _index) {
		if(_index >= 0 && _index < length) {
			index = _index;
			setChanged();
			notifyObservers(INDEX_UPDATE);
		}
	}
}
