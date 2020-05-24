package abstraction;

import java.util.List;
import java.util.Observable;

import controle.CtrlEntity;

public abstract class Equipment extends Observable {
	public abstract void use(Warrior warrior, List<? extends CtrlEntity> enemy);
}
