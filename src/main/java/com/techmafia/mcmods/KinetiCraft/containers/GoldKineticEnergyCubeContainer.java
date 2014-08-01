package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.tileentities.GoldKineticEnergyCubeTileEntity;

public class GoldKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected GoldKineticEnergyCubeTileEntity tileEntity;
	
	public GoldKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, GoldKineticEnergyCubeTileEntity te)
	{
        tileEntity = te;
        addSlotToContainer(new Slot(te, 0, 62, 23)); // Top Left
        addSlotToContainer(new Slot(te, 1, 80, 23)); // Top Middle
        addSlotToContainer(new Slot(te, 2, 98, 23)); // Top Right
        addSlotToContainer(new Slot(te, 3, 62, 41)); // Bottom Left
        addSlotToContainer(new Slot(te, 4, 80, 41)); // Bottom Middle
        addSlotToContainer(new Slot(te, 5, 98, 41)); // Bottom Right
        bindPlayerInventory(inventoryPlayer);
	}
		
	/**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
	@Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 6)
            {
                if (!this.mergeItemStack(itemstack1, 6, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 6, false))
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