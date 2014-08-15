package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft.tileentities.EnderKineticEnergyCubeTileEntity;

public class EnderKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected IInventory inventory;
	
	public EnderKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, IInventory te)
	{
		int currentSlot = 0;
		
		this.slotCount = 9;
		inventory = te;
        
        for (int y = 0; y < 3; y++)
        {
        	for (int x = 0; x < 3; x++)
        	{
        		addSlotToContainer(new KineticEnergyCoreSlot(inventory, currentSlot++, 62 + (x * 18), 16 + (y * 18)));
        	}
        }
        
        bindPlayerInventory(inventoryPlayer);
	}
	
	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotClicked)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotClicked);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotClicked < this.slotCount) // If anything in the energy cube is clicked
            {
                if ( ! this.mergeItemStack(itemstack1, this.slotCount, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if ( ! (itemstack.getItem() instanceof BaseKineticEnergyCore) || ! this.mergeItemStack(itemstack1, 0, this.slotCount, false))
        	{
        		return null;
        	}            		

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
