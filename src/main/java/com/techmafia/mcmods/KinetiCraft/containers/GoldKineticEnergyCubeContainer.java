package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.InventoryPlayer;

import com.techmafia.mcmods.KinetiCraft.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft.tileentities.GoldKineticEnergyCubeTileEntity;

public class GoldKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected GoldKineticEnergyCubeTileEntity tileEntity;
	
	public GoldKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, GoldKineticEnergyCubeTileEntity te)
	{
		int currentSlot = 0;
		
		this.slotCount = 6;
        tileEntity = te;
        
        for (int y = 0; y < 2; y++)
        {
        	for (int x = 0; x < 3; x++)
        	{
        		addSlotToContainer(new KineticEnergyCoreSlot(te, currentSlot++, 62 + (x * 18), 23 + (y * 18)));
        	}
        }
        
        bindPlayerInventory(inventoryPlayer);
	}
}