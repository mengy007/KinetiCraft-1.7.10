package com.techmafia.mcmods.KinetiCraft.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseKineticEnergyCore extends Item implements IEnergyHandler
{
	protected int energyFromJumping = 0;
	protected int energyFromMoving = 0;
	protected int energyFromUsing = 0;
	protected int overChargeBuffer = 0;
	protected int maxExtract = 0;
	protected float prevDistanceWalkedModified = 0;
	public int maxEnergy = 0;
	protected IIcon icons[] = new IIcon[6];
	protected boolean hasMultipleIcons = false;
	protected float damageFromOverChargeExplosion = 0;

	public BaseKineticEnergyCore()
	{
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabKC.KC_TAB);
		this.setNoRepair();
	}	
		
	public int getMaxExtract()
	{
		return this.maxExtract;
	}
	
	/**
     * Called when a entity tries to play the 'swing' animation.
     *  
     * @param entityLiving The entity swinging the item.
     * @param stack The Item stack
     * @return True to cancel any further processing by EntityLiving 
     */
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack)
    {
		if (NBTHelper.hasTag(itemStack, "kineticEnergyStored"))
		{
			// Get stored energy level from item stack
			int energyStored = NBTHelper.getInt(itemStack, "kineticEnergyStored");
			
			// Adds energy from using if max not hit yet
			if (energyStored < this.maxEnergy)
			{
				energyStored += this.energyFromUsing;
				
				// play sound if max is hit
				if (energyStored >= this.maxEnergy)
				{
					entityLiving.playSound("random.orb", 1, 1);
				}
			}
			else
			{
				// Handle over charging. Note: players can currently only over charge from swinging
				if ( ! NBTHelper.hasTag(itemStack, "overCharge"))
				{
					NBTHelper.setInteger(itemStack, "overCharge", 0);
				}
				
				int overCharge = NBTHelper.getInt(itemStack, "overCharge");
				
				overCharge++;
				
				if (overCharge >= this.overChargeBuffer)
				{
					// KaBOOM!
					((EntityPlayer)entityLiving).destroyCurrentEquippedItem();
					
					entityLiving.attackEntityFrom(DamageSource.generic, this.damageFromOverChargeExplosion);
					entityLiving.playSound("random.explode", 1, 1);
				}
				
				NBTHelper.setInteger(itemStack, "overCharge", overCharge);
			}
			
			if (energyStored > this.maxEnergy) { energyStored = this.maxEnergy; }
			
			// Save new energy level to item stack
			NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
			
			// Set item damage
			this.setDamage(itemStack, this.maxEnergy - energyStored);		
		}
		
        return false;
    }
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {		
        return itemStack;
    }
	
	/**
	 * Used to track player movement and jumping
	 */
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean inPlayerInv)
	{
		super.onUpdate(itemStack, world, entity, par4, inPlayerInv);
		
		EntityPlayer ep = (EntityPlayer)entity;
		
		if (prevDistanceWalkedModified == 0) {
			prevDistanceWalkedModified = ep.distanceWalkedModified;
		}
		
		boolean isMoving = ep.getAIMoveSpeed() > 0.11f ? true : false;
		boolean isJumping = (ep.fallDistance > 0.0f) ? true : false;
		boolean energyGained = false;
		int energyStored = NBTHelper.getInt(itemStack, "kineticEnergyStored");
		
		if ( ! world.isRemote)
		{
			if (energyStored <= maxEnergy)
			{
				if (isMoving)
				{
					energyStored += energyFromMoving;
					energyGained = true;
				}
				
				if (isJumping)
				{
					energyStored += energyFromJumping;
					energyGained = true;
				}
				
				if (energyGained)
				{
					if (energyStored > this.maxEnergy) { energyStored = this.maxEnergy; }

					NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
					
					// play sound if max is hit
					if (energyStored >= this.maxEnergy)
					{
						entity.playSound("random.orb", 1, 1);
					}
				}
			}
			else
			{
				if (energyStored > this.maxEnergy)
				{
					energyStored = this.maxEnergy;
					NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
				}
			}
		}
		
		prevDistanceWalkedModified = ep.distanceWalkedModified;
		
		this.setDamage(itemStack, this.maxEnergy - energyStored);
    }
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		if (this.hasMultipleIcons)
		{
			for (int i = 0; i < 6; i++)
			{
				this.icons[i] = iconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1) + i);
			}
			this.itemIcon = icons[0];
		}
		else
		{
			this.itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
		}
	}
	
	/**
     * Gets an icon index based on an item's damage value
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage)
    {
    	if (this.hasMultipleIcons)
    	{
        	int level = (this.maxEnergy - damage) / (this.maxEnergy / 5);
        	return level > 5 ? this.icons[5] : level < 0 ? this.icons[0] : this.icons[level];
    	}
    	else
    	{
    		return this.itemIcon;
    	}
    }

    @Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
    	super.addInformation(itemStack, player, list, par4);
    	
		if (itemStack.stackTagCompound != null)
		{
            int energyStored = itemStack.stackTagCompound.getInteger("kineticEnergyStored");
            list.add(EnumChatFormatting.GREEN + "" + energyStored + " / " + this.maxEnergy + " RF");
            
            Item item = itemStack.getItem();
            if (item instanceof EnderKineticEnergyCore && NBTHelper.hasTag(itemStack, "ownerName"))
            {
            	list.add(EnumChatFormatting.RED + "Owner: " + NBTHelper.getString(itemStack, "ownerName"));
            }
		}
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}	
	
	@Override
	public int getDamage(ItemStack itemStack)
	{
		return super.getDamage(itemStack);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection dir)
	{
		return false;
	}

	@Override
	public int extractEnergy(ForgeDirection dir, int maxExtract, boolean simulate)
	{
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
