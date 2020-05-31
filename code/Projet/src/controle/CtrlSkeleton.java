package controle;

import abstraction.Skeleton;
import presentation.PresSkeleton;
/**
 * Squelette
 * @author Administrator
 * @see Skeleton
 * @see PresSkeleton
 */
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
