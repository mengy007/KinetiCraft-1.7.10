package com.techmafia.mcmods.KinetiCraft.blocks;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyConduitTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class KineticEnergyConduit extends BlockContainer
{
	private int type = 0;
	
	public KineticEnergyConduit(int type)
	{
		super(Material.wood);
		
		float pixel = 1f/16f;
		
		this.setHardness(0.1f);
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
