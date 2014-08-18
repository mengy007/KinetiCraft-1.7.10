package com.techmafia.mcmods.KinetiCraft.proxy;

import com.techmafia.mcmods.KinetiCraft.renderer.KineticEnergyConduitTileEntityRenderer;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyConduitTileEntity;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
	public void registerClientStuff() 
	{
		ClientRegistry.bindTileEntitySpecialRenderer(KineticEnergyConduitTileEntity.class, new KineticEnergyConduitTileEntityRenderer());
	}
}
