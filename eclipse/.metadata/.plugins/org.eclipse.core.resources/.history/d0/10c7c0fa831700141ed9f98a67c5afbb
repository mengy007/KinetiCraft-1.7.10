package com.techmafia.mcmods.KinetiCraft.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.creativetab.CreativeTabKC;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseKineticEnergyCore extends Item
{
	protected int energyFromJumping = 0;
	protected int energyFromMoving = 0;
	protected int energyFromUsing = 0;
	protected int overChargeBuffer = 0;
	protected float prevDistanceWalkedModified = 0;
	public int maxEnergy = 0;
	protected IIcon icons[] = new IIcon[6];

	public BaseKineticEnergyCore() {
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabKC.KC_TAB);
		this.setNoRepair();
	}

	protected boolean hasMultipleIcons()
	{
		return false;
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
		if (NBTHelper.hasTag(itemStack, "energyStored"))
		{
			// Get stored energy level from item stack
			int energyStored = NBTHelper.getInt(itemStack, "energyStored");
			
			// Adds energy from using if max not hit yet
			if (energyStored < this.maxEnergy)
			{
				energyStored += this.energyFromUsing;
			}
			
			if (energyStored > this.maxEnergy) { energyStored = this.maxEnergy; }
			
			// Save new energy level to item stack
			NBTHelper.setInteger(itemStack, "energyStored", energyStored);
			
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
		int energyStored = NBTHelper.getInt(itemStack, "energyStored");
		
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

					NBTHelper.setInteger(itemStack, "energyStored", energyStored);
				}
			}
			else
			{
				if (energyStored > this.maxEnergy)
				{
					energyStored = this.maxEnergy;
					NBTHelper.setInteger(itemStack, "energyStored", energyStored);
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
		if (this.hasMultipleIcons())
		{
			for (int i = 0; i < 6; i++)
			{
				this.icons[i] = iconRegister.registerIcon(Reference.MOD_NAME + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(":") + 1) + i);//"KinetiCraft:woodenKineticEnergyCore" + i);
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
    	if (this.hasMultipleIcons())
    	{
        	int level = (this.maxEnergy - damage) / (this.maxEnergy / 5);
        	
    		if (level > 5) { level = 5; }

    		return this.icons[level];
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
            int energyStored = itemStack.stackTagCompound.getInteger("energyStored");
            list.add(EnumChatFormatting.GREEN + "" + energyStored + " / " + this.maxEnergy + " RF");
		}
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
