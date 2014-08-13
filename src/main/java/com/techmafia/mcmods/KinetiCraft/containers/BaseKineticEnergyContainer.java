package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;

public class BaseKineticEnergyContainer extends Container
{
	protected TileEntity tileEntity;
	protected int slotCount = 0;
	
	public BaseKineticEnergyContainer() { }
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
	{
        for (int i = 0; i < 3; i++)
        {
                for (int j = 0; j < 9; j++)
                {
                        addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
        }

        for (int i = 0; i < 9; i++)
        {
                addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
	}
	
	public void detectAndSendChanges()
	{
        super.detectAndSendChanges();
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
