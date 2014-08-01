package com.techmafia.mcmods.KinetiCraft.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

public class WoodenKineticEnergyCubeTileEntity extends BaseKineticEnergyCubeTileEntity implements IInventory {
	protected ItemStack[] energyCores = new ItemStack[1];

	public WoodenKineticEnergyCubeTileEntity()
	{
		super();
		LogHelper.info("Wooden Kinetic Energy Cube tile entity created!");
	}
	
	@Override
	public void openInventory()
	{
		
	}
	
	@Override
	public void closeInventory()
	{
		
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
			} else {
				System.out.println("slot null!");
			}
		}
	
		par1NBTTagCompound.setTag("Cores", nbttaglist);
		
		LogHelper.info("end of wooden tile entity write to nbt");
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
	   
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Cores", 10);
		this.energyCores = new ItemStack[1];
       
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);// .tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.energyCores.length)
			{
				this.energyCores[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		
		LogHelper.info("end of wooden tile entity read from nbt");
	}
	
	@Override
	public int getSizeInventory() {
		return this.energyCores.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.energyCores[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.energyCores[i] != null) {
			ItemStack itemstack = this.energyCores[i];
            this.energyCores[i] = null;
            return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
    {
        if (this.energyCores[i] != null)
        {
            ItemStack itemstack = this.energyCores[i];
            this.energyCores[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.energyCores[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
        }
		
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}
}
