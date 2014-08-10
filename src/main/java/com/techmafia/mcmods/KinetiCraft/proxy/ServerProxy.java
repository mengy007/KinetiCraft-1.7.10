package com.techmafia.mcmods.KinetiCraft.proxy;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.data.EnderKineticEnergyData;
import com.techmafia.mcmods.KinetiCraft.entities.EnderKineticEnergy;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;

public class ServerProxy extends CommonProxy
{
	@Override
	public void sendEnderEnergyUpdate(int energyStored, EntityPlayer ep)
	{
		LogHelper.info("sendEnderEnergyUpdate");
		
		//KinetiCraft.network.sendTo(new EnderKineticEnergyData(energyStored), (EntityPlayerMP)ep);
		
		EnderKineticEnergy eke = EnderKineticEnergy.get(ep);
		
		eke.setEnergy(energyStored);
	}
	
	@Override
	public void drainEnderEnergyUpdate(ItemStack itemStack, int energyStored) 
	{
		ArrayList pe = (ArrayList) Minecraft.getMinecraft().theWorld.playerEntities;
		
		for (Iterator it = pe.iterator(); it.hasNext(); )
		{
			EntityPlayerMP ep = (EntityPlayerMP) it.next();
			String ownerUUID = NBTHelper.getString(itemStack, "ownerUUID");
			
			if (ownerUUID == ep.getUniqueID().toString())
			{
				LogHelper.info("Found player! " + ep.getDisplayName());
				
				EnderKineticEnergy eke = EnderKineticEnergy.get(ep);
				
				eke.setEnergy(energyStored);
			}
		}
	}
}
