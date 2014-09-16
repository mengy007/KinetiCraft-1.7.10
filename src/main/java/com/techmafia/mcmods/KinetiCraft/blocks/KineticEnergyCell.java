package com.techmafia.mcmods.KinetiCraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyCellTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/* KineticEnergyCell
 * - Stores 4x amount of RF of core 
 * - Capacity depends on which core was used for creation
 * - Created by right clicking conduit with a core
 * - RF stored carries over from core
 * - 4x throughput of core
 */
public class KineticEnergyCell extends KCBlock
{	
	protected IIcon frontIcon;
	
	public KineticEnergyCell() 
	{
		super(Material.rock);
		
		this.setBlockName("kineticEnergyCell");
		this.setHardness(0.5f);
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
		this.blockIcon = iconRegister.registerIcon("KinetiCraft:kineticEnergyCell");
		this.frontIcon = iconRegister.registerIcon("KinetiCraft:kineticEnergyCellFront");;
	}
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
    	if (metadata > 0)
    	{
    		return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (side != metadata ? this.blockIcon : this.frontIcon));
    	}
    	else
    	{
    		return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (side != 3 ? this.blockIcon : this.frontIcon));
    	}
    }
	
    @Override
	/**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }
    
    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
    
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {	
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, metadata);
    }
	
	/*
	 * Tile Entity
	 */
	@Override
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new KineticEnergyCellTileEntity();
	}
}
