package controle;

import abstraction.Orc;
import abstraction.Shield;
import presentation.PresOrc;
/**
 * Orc
 * @author Administrator
 * @see Orc
 * @see PresOrc
 */
public class CtrlOrc extends CtrlEnemy {
	private PresOrc presOrc;
	private Orc orc;
	public CtrlOrc(Orc _orc, boolean withShield) {
		super(_orc, new PresOrc());
		orc = _orc;
		presOrc = (PresOrc)getPresWarrior();
		equip(new CtrlSword());
		if(withShield) {
			equip(new CtrlShield(new Shield(1500)));
		}
	}
}
