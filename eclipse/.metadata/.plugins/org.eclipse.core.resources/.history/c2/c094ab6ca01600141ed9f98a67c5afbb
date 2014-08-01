package com.techmafia.mcmods.KinetiCraft.blocks;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class StoneKineticEnergyCube extends BaseKineticEnergyCube {

	public StoneKineticEnergyCube(int id, Material material) {
		super(id, material);
		
		this.setHardness(0.2f);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{		
    	TileEntity te = world.getBlockTileEntity(x, y, z);
    
    	player.addChatMessage("click");
    
    	player.openGui(KinetiCraft.instance, 1, world, x, y, z);
    	
    	return true;
	}
	
	@Override
    public TileEntity createNewTileEntity(World world)
	{
        return new WoodenKineticEnergyCubeTileEntity();
	}
	
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}

}
