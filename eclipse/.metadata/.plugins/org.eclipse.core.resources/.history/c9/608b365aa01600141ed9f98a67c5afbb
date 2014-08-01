package com.techmafia.mcmods.KinetiCraft.blocks;

import java.util.ArrayList;

import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BaseKineticEnergyCube extends BlockContainer {
	
	public int energyStored;
	protected int maxCores=0;

	protected BaseKineticEnergyCube(int id, Material material) {
		super(id, material);
		
		this.setHardness(0.1f);
		energyStored = 0;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return par9;
    }
}
