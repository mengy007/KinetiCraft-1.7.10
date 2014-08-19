package com.techmafia.mcmods.KinetiCraft.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyConduitTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

public class KineticEnergyConduit extends BlockContainer
{
	private int type = 0;
	
	public KineticEnergyConduit(int type)
	{
		super(Material.wood);
		
		float pixel = 1f/16f;
		
		this.setHardness(0.1f);
		this.useNeighborBrightness = true;
		this.setCreativeTab(CreativeTabKC.KC_TAB);
		this.setBlockBounds(9*pixel/2, 9*pixel/2, 9*pixel/2, 1-9*pixel/2, 1-9*pixel/2, 1-9*pixel/2);
		this.type = type;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i)
	{
		return new KineticEnergyConduitTileEntity(this.type);
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
}
