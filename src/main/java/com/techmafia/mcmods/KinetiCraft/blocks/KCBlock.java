package com.techmafia.mcmods.KinetiCraft.blocks;

import java.awt.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KCBlock extends BlockContainer
{	
	protected int maxCores=1;
	
	public KCBlock(Material material)
	{
		super(material);
		
		this.setHardness(0.1f);
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
		blockIcon = iconRegister.registerIcon(String.format("$s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {	return null; }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, metadata);
    }
	
	/**
     * Called when a tile entity on a side of this block changes is created or is destroyed.
     * @param world The world
     * @param x The x position of this block instance
     * @param y The y position of this block instance
     * @param z The z position of this block instance
     * @param tileX The x position of the tile that changed
     * @param tileY The y position of the tile that changed
     * @param tileZ The z position of the tile that changed
     */
	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
		// LogHelper.info("Neighbor changed!");
    }
}
