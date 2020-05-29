package controle;

import abstraction.Bow;
import abstraction.Enemy;
import abstraction.Skeleton;
import presentation.PresEnemy;
import presentation.PresSkeleton;

public class CtrlSkeleton  extends CtrlEnemy{
	private Skeleton skeleton;
	private PresSkeleton presSkeleton;

	public CtrlSkeleton(Skeleton skeleton) {
		super(skeleton, new PresSkeleton());
		this.skeleton = skeleton;
		this.presSkeleton = (PresSkeleton)getPresWarrior();
		equip(new CtrlBow());
		
	}
	

}
