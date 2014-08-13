package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.InventoryPlayer;

import com.techmafia.mcmods.KinetiCraft.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;

public class StoneKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected StoneKineticEnergyCubeTileEntity tileEntity;
	
	public StoneKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, StoneKineticEnergyCubeTileEntity te)
	{
		this.slotCount = 2;
        tileEntity = te;
        addSlotToContainer(new KineticEnergyCoreSlot(te, 0, 62, 35));
        addSlotToContainer(new KineticEnergyCoreSlot(te, 1, 98, 35));
        bindPlayerInventory(inventoryPlayer);
	}
}