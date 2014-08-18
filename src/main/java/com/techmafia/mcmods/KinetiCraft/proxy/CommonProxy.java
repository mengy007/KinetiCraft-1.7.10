package com.techmafia.mcmods.KinetiCraft.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.tileentities.*;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy
{
	public void registerTileEntities()
	{
		GameRegistry.registerTileEntity(WoodenKineticEnergyCubeTileEntity.class, "WoodenKineticEnergyCubeTileEntity");
		GameRegistry.registerTileEntity(StoneKineticEnergyCubeTileEntity.class, "StoneKineticEnergyCubeTileEntity");
		GameRegistry.registerTileEntity(IronKineticEnergyCubeTileEntity.class, "IronKineticEnergyCubeTileEntity");
		GameRegistry.registerTileEntity(GoldKineticEnergyCubeTileEntity.class, "GoldKineticEnergyCubeTileEntity");
		GameRegistry.registerTileEntity(EnderKineticEnergyCubeTileEntity.class, "EnderKineticEnergyCubeTileEntity");
		GameRegistry.registerTileEntity(KineticFurnaceTileEntity.class, "KineticFurnaceTileEntity");
		
		LogHelper.info("Done registering tile entities!");
	}
	
	public void registerClientStuff() { }
	
	public void sendEnderEnergyUpdate(int energyStored, EntityPlayer ep) { }
	
	public void drainEnderEnergyUpdate(ItemStack itemStack, int energyStored) { }
}
