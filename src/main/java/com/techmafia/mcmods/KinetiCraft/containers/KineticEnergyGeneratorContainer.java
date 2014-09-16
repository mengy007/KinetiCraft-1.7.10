package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import com.techmafia.mcmods.KinetiCraft.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyGeneratorTileEntity;

public class KineticEnergyGeneratorContainer extends BaseKineticEnergyContainer
{
	protected KineticEnergyGeneratorTileEntity tileEntity;
	
	public KineticEnergyGeneratorContainer(InventoryPlayer inventoryPlayer, KineticEnergyGeneratorTileEntity te)
	{
		this.slotCount = 1;
        this.tileEntity = te;
        
        Slot coreSlot = new KineticEnergyCoreSlot(te, 0, 80, 35);

        addSlotToContainer(coreSlot);

        bindPlayerInventory(inventoryPlayer);
	}
}