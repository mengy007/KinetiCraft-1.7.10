package com.techmafia.mcmods.KinetiCraft.blocks.conduits;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.tileentities.conduits.WoodenKineticEnergyConduitTileEntity;

public class WoodenKineticEnergyConduit extends KineticEnergyConduit
{
	public WoodenKineticEnergyConduit()
	{
		super();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new WoodenKineticEnergyConduitTileEntity();
	}
}
