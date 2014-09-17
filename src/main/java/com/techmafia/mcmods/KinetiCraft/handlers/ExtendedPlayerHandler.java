package com.techmafia.mcmods.KinetiCraft.handlers;

import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExtendedPlayerHandler
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		KinetiCraft.proxy.registerExtendedPlayer(event);

		/*
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		{
			//ExtendedPlayer ep = ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
			
			ExtendedPlayer.register((EntityPlayer) event.entity);
			
			//ExtendedPlayerDataHandler.INSTANCE.sendToDimension(new ExtendedPlayerData(ep.energyCores), Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId);
		}
		*/
	}
}
