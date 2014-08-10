package com.techmafia.mcmods.KinetiCraft.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.entities.EnderKineticEnergy;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;

public class EnderKineticEnergyCore extends BaseKineticEnergyCore
{	
	public EnderKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("enderKineticEnergyCore");
		
		this.energyFromJumping 				= 50;
		this.energyFromMoving 				= 100;
		this.energyFromUsing 				= 500;
		this.overChargeBuffer 				= 100;
		this.maxEnergy						= 1000000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion 	= 6.0f;
		this.maxExtract						= 10000;
		
		this.setMaxDamage(this.maxEnergy);
	}
	
	public int getMaxExtract()
	{
		return this.maxExtract;
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack)
    {
		EnderKineticEnergy eke = EnderKineticEnergy.get((EntityPlayer)entityLiving);
		boolean isServer = false;
		int energyStored = 0;
		
		if (entityLiving instanceof EntityPlayerMP)
		{
			isServer = true;
		}
		
		if (isServer)
		{
			energyStored = eke.getEnergy();
			NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
			itemStack.setItemDamage(this.maxEnergy - energyStored);
		}
		else
		{
			energyStored = NBTHelper.getInt(itemStack, "kineticEnergyStored");
		}
		
		// Adds energy from using if max not hit yet
		if (energyStored < this.maxEnergy)
		{
			energyStored += this.energyFromUsing;
		}
			
		if (energyStored > this.maxEnergy)
		{
			entityLiving.playSound("random.orb", 1, 1);
			energyStored = this.maxEnergy;
		}
		
		// Save new energy level to item stack
		if (isServer)
		{
			eke.setEnergy(energyStored);
		}
		NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
		this.setDamage(itemStack, this.maxEnergy - energyStored);
		
		KinetiCraft.proxy.sendEnderEnergyUpdate(energyStored, (EntityPlayer) entityLiving);
		
		/* Set owner if not set */
		if ( ! NBTHelper.hasTag(itemStack, "ownerUUID"))
		{
			NBTHelper.setString(itemStack, "ownerUUID", Minecraft.getMinecraft().thePlayer.getUniqueID().toString());
			NBTHelper.setString(itemStack, "ownerName", Minecraft.getMinecraft().thePlayer.getDisplayName());
		}
		
        return false;
    }
	
	/**
	 * Used to track player movement and jumping
	 */
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean inPlayerInv)
	{
		super.onUpdate(itemStack, world, entity, par4, inPlayerInv);

		EntityPlayer ep = (EntityPlayer)entity;
		int energyStored;
		boolean isServer = false;
		EnderKineticEnergy eke = EnderKineticEnergy.get(ep);
		
		if (entity instanceof EntityPlayerMP)
		{
			isServer = true;
		}
		
		if (isServer)
		{
			energyStored = eke.getEnergy();			
			NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
			itemStack.setItemDamage(this.maxEnergy - energyStored);
		}
		else
		{
			energyStored = NBTHelper.getInt(itemStack, "kineticEnergyStored");
		}
		
		if (prevDistanceWalkedModified == 0)
		{
			prevDistanceWalkedModified = ep.distanceWalkedModified;
		}
		
		boolean isMoving = ep.getAIMoveSpeed() > 0.11f ? true : false;
		boolean isJumping = (ep.fallDistance > 0.0f) ? true : false;
		boolean energyGained = false;
		
		if ( ! world.isRemote)
		{
			if (energyStored <= this.maxEnergy)
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
					if (energyStored > this.maxEnergy)
					{
						entity.playSound("random.orb", 1, 1);
						energyStored = this.maxEnergy;
					}

					if (isServer)
					{
						eke.setEnergy(energyStored);
					}
					NBTHelper.setInteger(itemStack, "kineticEnergyStored", energyStored);
					itemStack.setItemDamage(this.maxEnergy - energyStored);
				}
			}
		}
		
		prevDistanceWalkedModified = ep.distanceWalkedModified;
		
		this.setDamage(itemStack, this.maxEnergy - energyStored);

		KinetiCraft.proxy.sendEnderEnergyUpdate(energyStored, ep);
    }
	
	/**
	 * Overridden to use ExtendedEntity data for player
	 */
	@Override
	public int getDamage(ItemStack itemStack)
	{
		EnderKineticEnergy eke = EnderKineticEnergy.get(Minecraft.getMinecraft().thePlayer);
		
		if (eke != null)
		{
			return this.maxEnergy - eke.getEnergy();
		}
		else
		{
			return this.getDamage(itemStack);
		}
		//return super.getDamage(itemStack);
	}
}
