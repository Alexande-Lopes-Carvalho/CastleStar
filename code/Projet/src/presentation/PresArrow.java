package PresItem;

import controle.CtrlArrow;

public class PresArrow extends PresElementScene{
	private Image skin;
	private String path;
	private CtrlArrow ctrlArrow;
	
	public PresArrow(Image _skin,String _path) {
		super();
		skin = _skin;
		path =_path;
		
		
	}
	public void setCtrlArrow(CtrlArrow _ctrlArrow) {
		ctrlArrow = _ctrlArrow;
	}
	public String getPath() {
		return this.path;
	}

	public void calc(long timePassed) {
		super.calc(timePassed);
		//  a corriger 
		Point deplacement  =  new Point(ctrlArrow.getArrow().getCelerity()*timePassed,0);
		ctrlArrow.move(deplacement);
		
	}
	

}
