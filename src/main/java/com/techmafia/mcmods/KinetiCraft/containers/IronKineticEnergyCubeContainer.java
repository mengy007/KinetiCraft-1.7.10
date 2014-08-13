package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.InventoryPlayer;

import com.techmafia.mcmods.KinetiCraft.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft.tileentities.IronKineticEnergyCubeTileEntity;

public class IronKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected IronKineticEnergyCubeTileEntity tileEntity;
	
	public IronKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, IronKineticEnergyCubeTileEntity te)
	{
		this.slotCount = 3;
        tileEntity = te;
        addSlotToContainer(new KineticEnergyCoreSlot(te, 0, 62, 35)); // Left
        addSlotToContainer(new KineticEnergyCoreSlot(te, 1, 80, 35)); // Middle
        addSlotToContainer(new KineticEnergyCoreSlot(te, 2, 98, 35)); // Right
        bindPlayerInventory(inventoryPlayer);
	}
}