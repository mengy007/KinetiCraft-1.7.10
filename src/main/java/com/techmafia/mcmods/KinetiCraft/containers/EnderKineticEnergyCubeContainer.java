package com.techmafia.mcmods.KinetiCraft.containers;

import net.minecraft.entity.player.InventoryPlayer;

import com.techmafia.mcmods.KinetiCraft.slots.KineticEnergyCoreSlot;
import com.techmafia.mcmods.KinetiCraft.tileentities.EnderKineticEnergyCubeTileEntity;

public class EnderKineticEnergyCubeContainer extends BaseKineticEnergyContainer
{
	protected EnderKineticEnergyCubeTileEntity tileEntity;
	
	public EnderKineticEnergyCubeContainer(InventoryPlayer inventoryPlayer, EnderKineticEnergyCubeTileEntity te)
	{
		int currentSlot = 0;
		
		this.slotCount = 9;
        tileEntity = te;
        
        for (int y = 0; y < 3; y++)
        {
        	for (int x = 0; x < 3; x++)
        	{
        		addSlotToContainer(new KineticEnergyCoreSlot(te, currentSlot++, 62 + (x * 18), 16 + (y * 18)));
        	}
        }
        
        bindPlayerInventory(inventoryPlayer);
	}
}
