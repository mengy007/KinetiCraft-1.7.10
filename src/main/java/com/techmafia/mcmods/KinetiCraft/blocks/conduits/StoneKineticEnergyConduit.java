package com.techmafia.mcmods.KinetiCraft.blocks.conduits;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.tileentities.conduits.StoneKineticEnergyConduitTileEntity;

public class StoneKineticEnergyConduit extends KineticEnergyConduit
{
	public StoneKineticEnergyConduit()
	{
		super();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new StoneKineticEnergyConduitTileEntity();
	}
}
