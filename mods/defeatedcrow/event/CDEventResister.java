package mods.defeatedcrow.event;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraftforge.common.MinecraftForge;

public class CDEventResister {
	
	Set<IForgeEvent> events = Sets.newHashSet();
	
	public CDEventResister()
	{
		events.add(new PlateSWEvent());
		events.add(new AttackWithSwordEvent());
		events.add(new FallOnBedrockEvent());
	}
	
	public void registerEvent()
	{
		for (IForgeEvent event : events)
		{
			MinecraftForge.EVENT_BUS.register(event);
		}
	}

}
