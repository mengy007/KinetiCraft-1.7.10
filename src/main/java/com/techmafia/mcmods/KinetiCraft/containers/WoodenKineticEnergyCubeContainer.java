package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.techmafia.mcmods.KinetiCraft.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;

public class WoodenKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected WoodenKineticEnergyCubeTileEntity tileEntity;
	
	public WoodenKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, WoodenKineticEnergyCubeTileEntity te)
	{
		this.slotCount = 1;
        tileEntity = te;
        Slot coreSlot = new KineticEnergyCoreSlot(te, 0, 80, 35);

        addSlotToContainer(coreSlot);

        bindPlayerInventory(inventoryPlayer);
	}	
}
