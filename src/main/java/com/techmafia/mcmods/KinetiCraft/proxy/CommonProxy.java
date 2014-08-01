package com.techmafia.mcmods.KinetiCraft.proxy;

import com.techmafia.mcmods.KinetiCraft.tileentities.KineticFurnaceTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(WoodenKineticEnergyCubeTileEntity.class, "WoodenKineticEnergyCubeTileEntity");
		GameRegistry.registerTileEntity(StoneKineticEnergyCubeTileEntity.class, "StoneKineticEnergyCubeTileEntity");
		GameRegistry.registerTileEntity(KineticFurnaceTileEntity.class, "KineticFurnaceTileEntity");
		LogHelper.info("Done registering tile entities!");
	}
}
