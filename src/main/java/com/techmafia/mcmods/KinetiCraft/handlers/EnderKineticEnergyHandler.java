package com.techmafia.mcmods.KinetiCraft.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

import com.techmafia.mcmods.KinetiCraft.entities.EnderKineticEnergy;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EnderKineticEnergyHandler
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayerMP && EnderKineticEnergy.get((EntityPlayer) event.entity) == null)
		{
			EnderKineticEnergy.register((EntityPlayer) event.entity);
			LogHelper.info("Registered EnderKineticEnergy");
		}
	}
}
