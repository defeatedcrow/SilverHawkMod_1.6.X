package mods.defeatedcrow.entity.projectile;

public enum OrbitType {
	
	/** 初期方角に直進 */
	STRAIGHT(0, 0),
	
	/** 重力に従う放物線軌道*/
	PARABOLA(0, 0),
	
	/** 初期ターゲット方角に直進 */
	SNIPE(1, 0),
	
	/** ターゲットに向かって徐々に方向修正 */
	TRACKING(1, 1);
	
	public final int hasTarget;
	public final int changeForcus;
	
	/** 作ったけど多分あんまり意味ない */
	private OrbitType(int target, int changeable) {
		hasTarget = target;
		changeForcus = changeable;
	}
	
}
