package com.techmafia.mcmods.KinetiCraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyGeneratorTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*
 * KineticEnergyGenerator
 *
 * - Holds 1 of any type of Kinetic Energy Core
 * - Walking on top generates 4 times the amount of RF gotten from running with core
 * - Jumping on top generates 4 times the amount of RF gotten from jumping with core
 */
public class KineticEnergyGenerator extends KCBlock
{
	String blockName="";
	IIcon frontIcon, frontPoweredIcon, frontNotPoweredIcon;
	int walkedOnEnergy = 10;
	int fellOnEnergy = 50;
	
	public KineticEnergyGenerator() 
	{
		super(Material.rock);
		
		this.blockName = "kineticEnergyGenerator";
		
		this.setBlockName(this.blockName);
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
		this.blockIcon = iconRegister.registerIcon("KinetiCraft:kineticEnergyGenerator");
		this.frontNotPoweredIcon = iconRegister.registerIcon("KinetiCraft:kineticEnergyGeneratorFront");
		this.frontPoweredIcon = iconRegister.registerIcon("KinetiCraft:kineticEnergyGeneratorFrontPowered");
		this.frontIcon = frontNotPoweredIcon;
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
		return new KineticEnergyGeneratorTileEntity();
	}
	
	/*
	 * GUI
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{		
    	TileEntity te = world.getTileEntity(x, y, z);

   		player.openGui(KinetiCraft.instance, 6, world, x, y, z);
        	
    	return true;
	}
	
	/**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) 
    {
    	TileEntity te = world.getTileEntity(x, y, z);
    	
    	if (te != null)
    	{
    		ItemStack energyCore = ((KineticEnergyGeneratorTileEntity)te).getStackInSlot(0);
    		
    		if (energyCore != null && energyCore.getItem() instanceof BaseKineticEnergyCore)
    		{
	    		int energyToAdd = ((BaseKineticEnergyCore)energyCore.getItem()).getEnergyFromMoving() * 4;
	    		
	    		this.addEnergyToTile((KineticEnergyGeneratorTileEntity)te, energyToAdd);
    		}
    	}
    }
    
    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float speed)
    {
    	TileEntity te = world.getTileEntity(x, y, z);
    	
    	if (te != null)
    	{
    		ItemStack energyCore = ((KineticEnergyGeneratorTileEntity)te).getStackInSlot(0);
    		
    		if (energyCore != null && energyCore.getItem() instanceof BaseKineticEnergyCore)
    		{
	    		int energyToAdd = ((BaseKineticEnergyCore)energyCore.getItem()).getEnergyFromJumping() * 4;
	
	    		this.addEnergyToTile((KineticEnergyGeneratorTileEntity)te, energyToAdd);
    		}
    	}
    }
    
    public void addEnergyToTile(KineticEnergyGeneratorTileEntity te, int energyToAdd)
    {
    	int energyReceived = te.receiveKineticEnergy(ForgeDirection.UP, energyToAdd, false);
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
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
    	TileEntity te = ((World)world).getTileEntity(x, y, z);
    	
    	if (te != null && ! ((World)world).isRemote)
    	{
    		((KineticEnergyGeneratorTileEntity)te).updateHungryTiles();
    	}
    }
    
    /**
     * Called after a block is placed
     */
    public void onPostBlockPlaced(World world, int x, int y, int z, int metadata)
    {
    	TileEntity te = world.getTileEntity(x, y, z);
    	
    	if (te != null && ! world.isRemote)
    	{
    		((KineticEnergyGeneratorTileEntity)te).updateHungryTiles();
    	}
    }
}
