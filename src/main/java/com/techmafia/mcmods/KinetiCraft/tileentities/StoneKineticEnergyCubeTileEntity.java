package com.techmafia.mcmods.KinetiCraft.tileentities;

import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class StoneKineticEnergyCubeTileEntity extends BaseKineticEnergyCubeTileEntity implements IInventory
{
	public StoneKineticEnergyCubeTileEntity()
	{
		super();
		energyCores = new ItemStack[2];
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		
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
	
		par1NBTTagCompound.setTag("Cores", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
	   
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Cores", 10);
       
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