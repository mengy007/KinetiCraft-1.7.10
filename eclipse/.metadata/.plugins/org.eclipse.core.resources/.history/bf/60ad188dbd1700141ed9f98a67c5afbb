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
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WoodenKineticEnergyCube extends KCBlock {

	public WoodenKineticEnergyCube()
	{
		super(Material.wood);
	
		this.setBlockName("woodenKineticEnergyCube");
		
		this.maxCores = 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("KinetiCraft:woodenKineticEnergyCube");
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		ItemStack cube, core;
		EntityItem cubeEI, coreEI;
		WoodenKineticEnergyCubeTileEntity te = (WoodenKineticEnergyCubeTileEntity)world.getTileEntity(x, y, z);
		
		// Check tile entity and drop cores if there were any
		if (te != null) {
			core = te.decrStackSize(0, 1);

			if (core != null) {
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, core));
			}
		} else {
			System.out.println("No tile entity found!");
		}

		// Drops the block
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{		
    	TileEntity te = world.getTileEntity(x, y, z);
        	
    	player.openGui(KinetiCraft.instance, 0, world, x, y, z);
        	
    	return true;
	}
	
	/*
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
	    itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setInteger("energyStored", 0);
	}
	*/
		
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        return metadata;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
	{
    	LogHelper.info("Creating tile entity!");
        return new WoodenKineticEnergyCubeTileEntity();
	}    
    
    @Override
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}
}
