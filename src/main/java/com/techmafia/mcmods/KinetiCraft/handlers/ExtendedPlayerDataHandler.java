package com.techmafia.mcmods.KinetiCraft.handlers;

import net.minecraft.client.Minecraft;

import com.techmafia.mcmods.KinetiCraft.data.ExtendedPlayerData;
import com.techmafia.mcmods.KinetiCraft.player.ExtendedPlayer;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class ExtendedPlayerDataHandler implements IMessageHandler <ExtendedPlayerData, IMessage>
{
	public static SimpleNetworkWrapper INSTANCE;
	
	@Override
	public IMessage onMessage(ExtendedPlayerData message, MessageContext ctx)
	{
		ExtendedPlayer ep = ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
		
		ep.inventory.energyCores = message.getEnergyCores();
		
		LogHelper.info("Received cores from server!");
		
		return null;
	}
}
