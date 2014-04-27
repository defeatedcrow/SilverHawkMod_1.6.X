package mods.defeatedcrow.common;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SHTickHandler implements ITickHandler{
	
	private final EnumSet<TickType> tickSet;
	
	public SHTickHandler(EnumSet<TickType> tickType)
	{
		this.tickSet = tickType;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return this.tickSet;
	}

	@Override
	public String getLabel() {
		return "SilverHawkServer";
	}

}
