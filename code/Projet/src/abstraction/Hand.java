package abstraction;

import java.util.List;

import controle.CtrlEntity;
/**
 * Represente une main, equipment qui ne fait rien lorsqu'il est utilis�
 * @author Administrator
 *
 */
public class Hand extends Equipment {
	@Override
	public void use(Warrior warrior, List<? extends CtrlEntity> enemy) {
	}
}
