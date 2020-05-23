package abstraction;

import java.util.List;
import java.util.Observable;

import controle.CtrlWarrior;

public abstract class Equipment extends Observable {
	abstract void use(Warrior warrior, List<CtrlWarrior> enemy);
}
