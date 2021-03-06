package com.techmafia.mcmods.KinetiCraft.handlers;

import net.minecraft.client.Minecraft;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.data.EnderKineticEnergyData;
import com.techmafia.mcmods.KinetiCraft.entities.EnderKineticEnergy;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EnderKineticEnergyServerDataHandler implements IMessageHandler <EnderKineticEnergyData, IMessage>
{
	@SideOnly(Side.SERVER)
	@Override
	public IMessage onMessage(EnderKineticEnergyData message, MessageContext ctx)
	{
		EnderKineticEnergy eke = EnderKineticEnergy.get(Minecraft.getMinecraft().thePlayer);
		
		//eke.setEnergy(message.getEnergyLevel());
		
		LogHelper.info("Server message: " + message.getEnergyLevel());
		
		return null;
	}
}