package com.techmafia.mcmods.KinetiCraft.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

import com.techmafia.mcmods.KinetiCraft.init.ModBlocks;
import com.techmafia.mcmods.KinetiCraft.player.ExtendedPlayer;
import com.techmafia.mcmods.KinetiCraft.renderer.*;
import com.techmafia.mcmods.KinetiCraft.tileentities.conduits.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	public void registerClientStuff() 
	{
		TileEntitySpecialRenderer conduitSpecialRenderer = new KineticEnergyConduitTileEntityRenderer();
		
		ClientRegistry.bindTileEntitySpecialRenderer(KineticEnergyConduitTileEntity.class, conduitSpecialRenderer);		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.kineticEnergyConduit), new KineticEnergyConduitTileEntityItemRenderer(conduitSpecialRenderer, new KineticEnergyConduitTileEntity()));
	}
	
	@Override
	public void registerExtendedPlayer(EntityConstructing event) 
	{ 
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		{
			ExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}
}
