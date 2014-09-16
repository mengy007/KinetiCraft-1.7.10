package com.techmafia.mcmods.KinetiCraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.init.ModItems;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KineticBlock extends KCBlock
{
	public KineticBlock() 
	{
		super(Material.sand);
		
		this.setHardness(0.5f);
		this.setBlockName("kineticBlock");
		this.setCreativeTab(CreativeTabKC.KC_TAB);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon("KinetiCraft:kineticBlock");
	}
	
	/**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) 
    {
    	if ( ! world.isRemote && entity instanceof EntityPlayerMP)
    	{
    		this.makeKineticIngot(world, x, y, z, entity);
    	}
    }
    
    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float speed)
    {
    	if ( ! world.isRemote && entity instanceof EntityPlayerMP)
    	{
    		this.makeKineticIngot(world, x, y, z, entity);
    	}
    }
    
    public void makeKineticIngot(World world, int x, int y, int z, Entity entity)
    {
    	world.func_147480_a(x, y, z, false); // Destroy block
    	world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ModItems.kineticIngot, 2)));
    }
}
