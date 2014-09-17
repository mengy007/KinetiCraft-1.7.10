package com.techmafia.mcmods.KinetiCraft.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderKineticEnergyCore extends BaseKineticEnergyCore
{	
	public EnderKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("enderKineticEnergyCore");
		
		this.energyFromJumping 				= 500;
		this.energyFromMoving 				= 1000;
		this.energyFromUsing 				= 5000;
		this.overChargeBuffer 				= 100;
		this.maxEnergy						= 1000000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion 	= 6.0f;
		this.maxExtract						= 5000;
		
		this.setMaxDamage(this.maxEnergy);
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
		if ( ! entityLiving.worldObj.isRemote)
		{
			super.onEntitySwing(entityLiving, itemStack);			
			this.teleportPlayer((EntityPlayer)entityLiving, 10, true);
		}		
		return false;
    }
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if ( ! world.isRemote)
    	{
    		return this.teleportPlayer(entityPlayer, 3, false);
    	}
        return false;
    }
	
	public boolean teleportPlayer(EntityPlayer entityPlayer, int radius, boolean random)
	{
		int x, y, z;
		
		if (random)
		{
			x = (int) ((Math.random() * (2 * radius)) - radius);
			y = (int) ((Math.random() * (2 * radius)) - radius);
			z = (int) ((Math.random() * (2 * radius)) - radius);
		}
		else
		{		
			x = (int) ((Math.random() * (2 * radius)) - radius);
			y = (int) ((Math.random() * (2 * radius)) - radius);
			z = (int) ((Math.random() * (2 * radius)) - radius);
		}
		
		if ( ! (entityPlayer.worldObj.blockExists(x, y, z) && entityPlayer.worldObj.blockExists(x, y-1, z)) && Math.random() <= 0.25)
		{
			entityPlayer.posX += x;
			entityPlayer.posY += y;
			entityPlayer.posZ += z;
			//entityLiving.playSound("random.teleport", 1, 1);
			
			return true;
		}
		
		return false;
	}
}