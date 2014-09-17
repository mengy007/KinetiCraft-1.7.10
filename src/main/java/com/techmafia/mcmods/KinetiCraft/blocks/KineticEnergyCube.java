package com.techmafia.mcmods.KinetiCraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.tileentities.EnderKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.GoldKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.IronKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyGeneratorTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KineticEnergyCube extends BlockContainer
{
	protected int maxCores=1;
	protected String blockName="";
	protected float blockHardness=0.1f;
	protected int metadata=0;
	protected IIcon sideIcon;
	protected IIcon frontIcon;
	protected IIcon frontPoweredIcon;
	protected IIcon frontNotPoweredIcon;
	
	public KineticEnergyCube(int metadata)
	{
		super(Material.rock);
		
		this.damageDropped(metadata);
		this.metadata = metadata;
		
		switch (metadata)
		{
		case 1:
			this.blockName = "woodenKineticEnergyCube";
			this.blockHardness = 0.1f;
			this.maxCores = 1;
			break;
		case 2:
			this.blockName = "stoneKineticEnergyCube";
			this.blockHardness = 0.3f;
			this.maxCores = 2;
			break;
		case 3:
			this.blockName = "ironKineticEnergyCube";
			this.blockHardness = 0.5f;
			this.maxCores = 3;
			break;
		case 4:
			this.blockName = "goldKineticEnergyCube";
			this.blockHardness = 0.3f;
			this.maxCores = 6;
			break;
		case 5:
			this.blockName = "enderKineticEnergyCube";
			this.blockHardness = 0.5f;
			this.maxCores = 9;
			break;
		}
		
		this.setBlockName(this.blockName);
		this.setHardness(this.blockHardness);
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
		switch (this.metadata)
		{
		case 1:
			this.blockIcon = iconRegister.registerIcon("KinetiCraft:woodenKineticEnergyCube");
			this.frontNotPoweredIcon = iconRegister.registerIcon("KinetiCraft:woodenKineticEnergyCubeFront");
			this.frontPoweredIcon = iconRegister.registerIcon("KinetiCraft:woodenKineticEnergyCubeFrontPowered");
			this.frontIcon = frontNotPoweredIcon;
			break;
		case 2:
			this.blockIcon = iconRegister.registerIcon("KinetiCraft:stoneKineticEnergyCube");
			this.frontNotPoweredIcon = iconRegister.registerIcon("KinetiCraft:stoneKineticEnergyCubeFront");
			this.frontPoweredIcon = iconRegister.registerIcon("KinetiCraft:stoneKineticEnergyCubeFrontPowered");
			this.frontIcon = frontNotPoweredIcon;
			break;
		case 3:
			this.blockIcon = iconRegister.registerIcon("KinetiCraft:ironKineticEnergyCube");
			this.frontNotPoweredIcon = iconRegister.registerIcon("KinetiCraft:ironKineticEnergyCubeFront");
			this.frontPoweredIcon = iconRegister.registerIcon("KinetiCraft:ironKineticEnergyCubeFrontPowered");
			this.frontIcon = frontNotPoweredIcon;
			break;
		case 4:
			this.blockIcon = iconRegister.registerIcon("KinetiCraft:goldKineticEnergyCube");
			this.frontNotPoweredIcon = iconRegister.registerIcon("KinetiCraft:goldKineticEnergyCubeFront");
			this.frontPoweredIcon = iconRegister.registerIcon("KinetiCraft:goldKineticEnergyCubeFrontPowered");
			this.frontIcon = frontNotPoweredIcon;
			break;
		case 5:
			this.blockIcon = iconRegister.registerIcon("KinetiCraft:enderKineticEnergyCube");
			this.frontNotPoweredIcon = iconRegister.registerIcon("KinetiCraft:enderKineticEnergyCubeFront");
			this.frontPoweredIcon = iconRegister.registerIcon("KinetiCraft:enderKineticEnergyCubeFrontPowered");
			this.frontIcon = frontNotPoweredIcon;
			break;
		}
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
        
        if (this.metadata == 5)
        {
        	TileEntity te = world.getTileEntity(x, y, z);
        	
        	if (te != null && te instanceof EnderKineticEnergyCubeTileEntity)
        	{
        		((EnderKineticEnergyCubeTileEntity)te).setOwnerUUID(entity.getUniqueID().toString());
    			((EnderKineticEnergyCubeTileEntity)te).setOwnerName(((EntityPlayer)entity).getDisplayName());
        	}
        }
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
    	super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);
    	
    	/*
    	 * We want to stop emmiting power if block is being powered by redstone
    	 */
    	
    	if (((World)world).isBlockIndirectlyGettingPowered(x, y-1 ,z))
    	{
    		this.frontIcon = this.frontPoweredIcon;
    	}
    	else
    	{
    		this.frontIcon = this.frontNotPoweredIcon;
    	}
    }
    
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		switch (this.metadata)
		{
		case 1:
			return new WoodenKineticEnergyCubeTileEntity();
		case 2:
			return new StoneKineticEnergyCubeTileEntity();
		case 3:
			return new IronKineticEnergyCubeTileEntity();
		case 4:
			return new GoldKineticEnergyCubeTileEntity();
		case 5:
			return new EnderKineticEnergyCubeTileEntity();
		}
		
		return null;		
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		ItemStack cube, core;
		EntityItem cubeEI, coreEI;
		TileEntity te = world.getTileEntity(x, y, z);
		
		// Check tile entity and drop cores if there were any
		if (te != null)
		{
			for (int c = 0; c < this.maxCores; c++)
			{
				core = ((IInventory)te).decrStackSize(c, 1);
	
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

    	if (te != null && te instanceof EnderKineticEnergyCubeTileEntity)
   		{
    		if ( ! ((EnderKineticEnergyCubeTileEntity)te).getOwnerUUID().toString().equals(player.getUniqueID().toString()))
    		{
    			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This ender energy cube belongs to " + ((EnderKineticEnergyCubeTileEntity)te).getOwnerName() + "!"));
    			return false;
    		}
    	}
    	
    	switch (this.metadata)
    	{
    	case 1:
    		player.openGui(KinetiCraft.instance, 0, world, x, y, z);
    		break;
    	case 2:
    		player.openGui(KinetiCraft.instance, 1, world, x, y, z);
    		break;
    	case 3:
    		player.openGui(KinetiCraft.instance, 2, world, x, y, z);
    		break;
    	case 4:
    		player.openGui(KinetiCraft.instance, 3, world, x, y, z);
    		break;
    	case 5:
    		player.openGui(KinetiCraft.instance, 4, world, x, y, z);
    		break;
    	}
        	
    	return true;
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
	    return true;
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
}
