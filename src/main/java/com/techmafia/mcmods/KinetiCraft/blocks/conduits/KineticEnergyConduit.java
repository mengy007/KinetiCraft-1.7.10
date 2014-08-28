package com.techmafia.mcmods.KinetiCraft.blocks.conduits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.tileentities.conduits.KineticEnergyConduitTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

public class KineticEnergyConduit extends BlockContainer
{
	public KineticEnergyConduit()
	{
		super(Material.rock);
		
		float pixel = 1f/16f;
		
		this.setHardness(0.4f);
		this.useNeighborBrightness = true;
		this.setCreativeTab(CreativeTabKC.KC_TAB);
		this.setBlockBounds(9*pixel/2, 9*pixel/2, 9*pixel/2, 1-9*pixel/2, 1-9*pixel/2, 1-9*pixel/2);
		this.setBlockName("kineticEnergyConduit");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new KineticEnergyConduitTileEntity();
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		KineticEnergyConduitTileEntity conduit = (KineticEnergyConduitTileEntity)world.getTileEntity(x, y, z);
		
		float pixel = 1f/16f;
		
		if (conduit != null)
		{
			float minX = 9*pixel/2 - (conduit.connections[3] != null ? (9*pixel/2) : 0);
			float minY = 9*pixel/2 - (conduit.connections[1] != null ? (9*pixel/2) : 0);
			float minZ = 9*pixel/2 - (conduit.connections[2] != null ? (9*pixel/2) : 0);
			
			float maxX = 1-9*pixel/2 + (conduit.connections[5] != null ? (9*pixel/2) : 0);
			float maxY = 1-9*pixel/2 + (conduit.connections[0] != null ? (9*pixel/2) : 0);
			float maxZ = 1-9*pixel/2 + (conduit.connections[4] != null ? (9*pixel/2) : 0);
			
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		else
		{
			LogHelper.info("null!");
		}
			
		return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
	}
	
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		KineticEnergyConduitTileEntity conduit = (KineticEnergyConduitTileEntity)world.getTileEntity(x, y, z);
		
		float pixel = 1f/16f;
		
		if (conduit != null)
		{
			float minX = 9*pixel/2 - (conduit.connections[3] != null ? (9*pixel/2) : 0);
			float minY = 9*pixel/2 - (conduit.connections[1] != null ? (9*pixel/2) : 0);
			float minZ = 9*pixel/2 - (conduit.connections[2] != null ? (9*pixel/2) : 0);
			
			float maxX = 1-9*pixel/2 + (conduit.connections[5] != null ? (9*pixel/2) : 0);
			float maxY = 1-9*pixel/2 + (conduit.connections[0] != null ? (9*pixel/2) : 0);
			float maxZ = 1-9*pixel/2 + (conduit.connections[4] != null ? (9*pixel/2) : 0);
			
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		else
		{
			LogHelper.info("null!");
		}
			
		return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
	}
	
	public int getRenderType()
	{
		return -1;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
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
		TileEntity te = ((World)world).getTileEntity(x, y, z);
		
		if (te != null && te instanceof KineticEnergyConduitTileEntity)
		{
			((KineticEnergyConduitTileEntity)te).updateConduitNetwork();
		}
    }
	
	/**
     * Called after a block is placed
     */
	@Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int p_149714_5_)
    {
		TileEntity te = ((World)world).getTileEntity(x, y, z);
		
		if (te != null && te instanceof KineticEnergyConduitTileEntity)
		{
			((KineticEnergyConduitTileEntity)te).updateConduitNetwork();
		}
    }
	
	/**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public void onBlockAdded(World world, int x, int y, int z)
	{
		TileEntity te = ((World)world).getTileEntity(x, y, z);
		
		if (te != null && te instanceof KineticEnergyConduitTileEntity)
		{
			((KineticEnergyConduitTileEntity)te).updateConduitNetwork();
		}
	}
}
