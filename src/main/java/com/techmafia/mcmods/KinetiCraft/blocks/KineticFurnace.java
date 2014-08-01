package com.techmafia.mcmods.KinetiCraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticFurnaceTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KineticFurnace extends KCBlock
{
	@SideOnly(Side.CLIENT)
	protected IIcon frontIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon topIcon;
	protected boolean isCooking=false;

	
	public KineticFurnace()
	{
		super(Material.rock);
		
		this.setHardness(0.5f);
		this.setBlockName("kineticFurnace");
	}

	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side == 1 ? this.topIcon : (side == 0 ? this.topIcon : (side != metadata ? this.blockIcon : this.frontIcon));
    }
 
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon 		= iconRegister.registerIcon("furnace_side");
		this.frontIcon	= iconRegister.registerIcon(this.isCooking ? "furnace_front_on" : "furnace_front_off");
		this.topIcon 	= iconRegister.registerIcon("furnace_top");
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

        if (itemStack.hasDisplayName())
        {
            ((KineticFurnaceTileEntity)world.getTileEntity(x, y, z)).setDisplayName(itemStack.getDisplayName());
        }
    }
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
        	KineticFurnaceTileEntity te = (KineticFurnaceTileEntity)world.getTileEntity(x, y, z);

            if (te != null)
            {
            	entityPlayer.openGui(KinetiCraft.instance, 5, world, x, y, z);
            }

            return true;
        }
    }
	
	/**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
	@Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new KineticFurnaceTileEntity();
    }
    
    @Override
	public boolean hasTileEntity(int metadata)
	{
	    return true;
	}

}
