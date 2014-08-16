package com.techmafia.mcmods.KinetiCraft.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.entities.EnderKineticEnergy;
import com.techmafia.mcmods.KinetiCraft.player.ExtendedPlayer;
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
	}
	
	public int getMaxExtract()
	{
		return this.maxExtract;
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack)
    {
		ExtendedPlayer ep = ExtendedPlayer.get((EntityPlayer)entityLiving);
		
		LogHelper.info("Current EE: " + ep.enderEnergy);
		
		ep.enderEnergy += this.energyFromUsing;

        return false;
    }
	
	/**
	 * Used to track player movement and jumping
	 */
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean inPlayerInv)
	{
		super.onUpdate(itemStack, world, entity, par4, inPlayerInv);

		ExtendedPlayer ep = ExtendedPlayer.get((EntityPlayer)entity);
		EntityPlayer entityPlayer = (EntityPlayer)entity;
		
		if (prevDistanceWalkedModified == 0)
		{
			prevDistanceWalkedModified = entityPlayer.distanceWalkedModified;
		}
		
		boolean isMoving = entityPlayer.getAIMoveSpeed() > 0.11f ? true : false;
		boolean isJumping = entityPlayer.fallDistance > 0.0f ? true : false;
		boolean energyGained = false;
		
		if (isMoving)
		{
			ep.enderEnergy += energyFromMoving;
			energyGained = true;
		}
		
		if (isJumping)
		{
			ep.enderEnergy += energyFromJumping;
			energyGained = true;
		}
		
		prevDistanceWalkedModified = entityPlayer.distanceWalkedModified;
    }
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		ExtendedPlayer ep = ExtendedPlayer.get(player);
		
       	list.add(EnumChatFormatting.GREEN + "" + ep.enderEnergy + " RF");
	}
}
