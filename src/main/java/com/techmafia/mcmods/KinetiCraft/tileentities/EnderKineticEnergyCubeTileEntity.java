package com.techmafia.mcmods.KinetiCraft.tileentities;

import java.util.ArrayList;
import java.util.Iterator;

import cofh.api.energy.IEnergyHandler;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.EnderKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class EnderKineticEnergyCubeTileEntity  extends BaseKineticEnergyCubeTileEntity implements IInventory
{
	public EnderKineticEnergyCubeTileEntity()
	{
		super();
		energyCores = new ItemStack[9];
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.energyCores.length; ++i)
		{
			if (this.energyCores[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.energyCores[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
			else
			{
				System.out.println("slot null!");
			}
		}
	
		nbt.setTag("Cores", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	   
		NBTTagList nbttaglist = nbt.getTagList("Cores", 10);
       
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.energyCores.length)
			{
				this.energyCores[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
}