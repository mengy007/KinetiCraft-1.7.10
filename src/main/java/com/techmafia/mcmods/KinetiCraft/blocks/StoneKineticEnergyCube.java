package com.techmafia.mcmods.KinetiCraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StoneKineticEnergyCube extends KCBlock
{
	public StoneKineticEnergyCube()
	{
		super(Material.rock);
		this.setBlockName("stoneKineticEnergyCube");
		this.setHardness(0.2f);
		this.maxCores = 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("KinetiCraft:stoneKineticEnergyCube");
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		ItemStack cube, core;
		EntityItem cubeEI, coreEI;
		StoneKineticEnergyCubeTileEntity te = (StoneKineticEnergyCubeTileEntity)world.getTileEntity(x, y, z);
		
		// Check tile entity and drop cores if there were any
		if (te != null)
		{
			for (int c = 0; c < this.maxCores; c++)
			{
				core = te.decrStackSize(c, 1);
	
				if (core != null)
				{
					world.spawnEntityInWorld(new EntityItem(world, x, y, z, core));
				}
			}
		}
		else
		{
			System.out.println("No tile entity found!");
		}

		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{		
    	TileEntity te = world.getTileEntity(x, y, z);
        	
    	player.openGui(KinetiCraft.instance, 1, world, x, y, z);
        	
    	return true;
	}
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
	{
        return new StoneKineticEnergyCubeTileEntity();
	}    
    
    @Override
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}
}
