package com.techmafia.mcmods.KinetiCraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

public class EnderKineticEnergyCubeInventory implements IInventory
{
	public ItemStack[] energyCores;
	
	public EnderKineticEnergyCubeInventory()
	{
		this.energyCores = new ItemStack[9];
	}
	
	@Override
	public int getSizeInventory() 
	{
		return this.energyCores.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		if (this.energyCores != null && slot < this.energyCores.length)
		{
			return this.energyCores[slot];
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (i < this.energyCores.length && this.energyCores[i] != null)
		{
			ItemStack itemstack = this.energyCores[i];
            this.energyCores[i] = null;
            return itemstack;
		}
		else
		{
			LogHelper.error("Invalid index: " + i + " or no core present");
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack)
	{
		if (this.energyCores != null && slot < this.energyCores.length)
		{
			this.energyCores[slot] = itemStack;
	
	        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemStack.stackSize = this.getInventoryStackLimit();
	        }
		}
		else
		{
			LogHelper.error("Invalid index: " + slot);
		}
	}

	@Override
	public String getInventoryName() 
	{
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public void markDirty()
	{

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return true;
	}

	@Override
	public void openInventory() 
	{
		
	}

	@Override
	public void closeInventory()
	{
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) 
	{
		return (itemStack.getItem() instanceof BaseKineticEnergyCore) ? true : false;
	}
}
