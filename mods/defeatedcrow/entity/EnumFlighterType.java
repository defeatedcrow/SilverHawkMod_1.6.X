package mods.defeatedcrow.entity;

/**
 * ごく単純に、モブの種類別に並べているだけ。
 * いちいちモブのidや名前で判断するのが面倒だったとかその程度の動機。
 * <br>ボタン押下時間のチェックなど、一応値だけ入れている。使うのはたぶん先の話。
 */
public enum EnumFlighterType {
	
	SILVER_HAWK(0),
	
	SHILVER_CHICKEN(0),
	
	KERBEROS(20),
	
	EARL_GRAY(0),
	
	FIRE_LEO(0),
	
	VIPER(0),
	
	UNKNOUN(0);
	
	public final int Time;
	
	/** 作ったけど多分あんまり意味ない */
	private EnumFlighterType(int checkTime) {
		Time = checkTime;
	}

}
