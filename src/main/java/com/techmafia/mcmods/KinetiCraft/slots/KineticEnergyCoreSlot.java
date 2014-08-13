package com.techmafia.mcmods.KinetiCraft.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;

public class KineticEnergyCoreSlot extends Slot
{

	public KineticEnergyCoreSlot(IInventory inventory, int par2, int par3, int par4)
	{
		super(inventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return itemStack.getItem() instanceof BaseKineticEnergyCore;
	}
}
