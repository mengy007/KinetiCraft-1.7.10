package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.tileentities.IronKineticEnergyCubeTileEntity;

public class IronKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected IronKineticEnergyCubeTileEntity tileEntity;
	
	public IronKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, IronKineticEnergyCubeTileEntity te)
	{
        tileEntity = te;
        addSlotToContainer(new Slot(te, 0, 62, 35)); // Left
        addSlotToContainer(new Slot(te, 1, 80, 35)); // Middle
        addSlotToContainer(new Slot(te, 2, 98, 35)); // Right
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

            if (par2 < 3)
            {
                if (!this.mergeItemStack(itemstack1, 3, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 3, false))
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