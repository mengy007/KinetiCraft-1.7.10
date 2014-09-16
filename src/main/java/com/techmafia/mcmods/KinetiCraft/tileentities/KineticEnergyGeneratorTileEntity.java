package com.techmafia.mcmods.KinetiCraft.tileentities;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;
import com.techmafia.mcmods.KinetiCraft.utility.PowerTile;

public class KineticEnergyGeneratorTileEntity extends TileEntity implements IInventory, IEnergyHandler
{
	protected int maxTransfer = 1000;
	protected ItemStack energyCore;
	protected boolean canFunction = true;
	protected ArrayList <PowerTile> hungryTiles;
	
	public KineticEnergyGeneratorTileEntity()
	{
		super();
	}
	
	public void canFunction(boolean canFunction)
	{
		this.canFunction = canFunction;
	}
	
	public void updateHungryTiles()
	{
		TileEntity tes[] = new TileEntity[6];
		ForgeDirection dirs[] = new ForgeDirection[6];
		
		this.hungryTiles = new ArrayList <PowerTile>();

		tes[0] = this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord); // WEST
		tes[1] = this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord); // EAST
		tes[2] = this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord); // DOWN
		tes[3] = this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord); // UP
		tes[4] = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1); // SOUTH
		tes[5] = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1); // NORTH
		
		dirs[0] = ForgeDirection.WEST;
		dirs[1] = ForgeDirection.EAST;
		dirs[2] = ForgeDirection.DOWN;
		dirs[3] = ForgeDirection.UP;
		dirs[4] = ForgeDirection.SOUTH;
		dirs[5] = ForgeDirection.NORTH;
		
		// Get connected hungry tiles
		for (int i = 0; i < tes.length; i++)
		{
			if (dirs[i] != ForgeDirection.UP && dirs[i] != ForgeDirection.DOWN && tes[i] != null && tes[i] instanceof IEnergyHandler && ((IEnergyHandler)tes[i]).canConnectEnergy(dirs[i].getOpposite()))
			{
				this.hungryTiles.add(new PowerTile(dirs[i], tes[i]));
			}
		}
		
		LogHelper.info("update hungry tiles. found " + this.hungryTiles.size());
	}
	
	@Override
	public void updateEntity()
	{
		if (this.canFunction && ! this.worldObj.isRemote && this.hungryTiles != null && this.hungryTiles.size() > 0)
		{
			TileEntity tes[] = new TileEntity[6];
			ForgeDirection dirs[] = new ForgeDirection[6];
			int energyOut = 0;
			int energyLeftUntilMax = this.maxTransfer;
			
			tes[0] = this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord); // WEST
			tes[1] = this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord); // EAST
			tes[2] = this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord); // DOWN
			tes[3] = this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord); // UP
			tes[4] = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1); // SOUTH
			tes[5] = this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1); // NORTH
			
			dirs[0] = ForgeDirection.WEST;
			dirs[1] = ForgeDirection.EAST;
			dirs[2] = ForgeDirection.DOWN;
			dirs[3] = ForgeDirection.UP;
			dirs[4] = ForgeDirection.SOUTH;
			dirs[5] = ForgeDirection.NORTH;
						
			LogHelper.info("Hungry tiles: " + this.hungryTiles.size());
			
			for (Iterator <PowerTile> i = this.hungryTiles.iterator(); i.hasNext(); )
			{
				PowerTile pt = i.next();
				
				if (pt.te != null && pt.te instanceof IEnergyHandler)
				{
					int energyCanSend = 0;
					int energySent;
					
					if (this.maxTransfer < energyLeftUntilMax && this.maxTransfer < this.getEnergyStored(null))
					{
						energyCanSend = this.maxTransfer;
					}
					else
					{
						if (this.maxTransfer > energyLeftUntilMax)
						{
							energyCanSend = this.maxTransfer > this.getEnergyStored(null) ? this.getEnergyStored(null) : energyLeftUntilMax;
						}
						else if (this.maxTransfer > this.getEnergyStored(null))
						{
							energyCanSend = this.maxTransfer > energyLeftUntilMax ? energyLeftUntilMax : this.getEnergyStored(null);
						}
					}
					
					energySent = ((IEnergyHandler)pt.te).receiveEnergy(pt.connectedDir.getOpposite(), energyCanSend, false);
					
					pt.te.markDirty();
					
					energyOut += energySent;
					energyLeftUntilMax -= energySent;
				}

				if (energyOut > 0)
				{
					LogHelper.info("extracting: " + energyOut);
					this.extractEnergy(pt.connectedDir, energyOut, false);
				}
			}
			
			LogHelper.info("Update: energy out = " + energyOut);
		}
		
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.markDirty();
	}
	
	/*
	 * CoFH
	 */
	@Override
	public boolean canConnectEnergy(ForgeDirection dir)
	{
		return (dir == ForgeDirection.UP || this.energyCore == null) ? false : true;
	}

	@Override
	public int extractEnergy(ForgeDirection dir, int maxExtract, boolean simulate)
	{
		int energyExtracted = 0;
		
		this.energyCore = this.getStackInSlot(0);
		
		if (this.energyCore != null && dir != ForgeDirection.UP && this.getEnergyStored(null) > 0)
		{
			int currentEnergy = this.energyCore.getMaxDamage() - this.energyCore.getItemDamage();
			int coreMaxThroughput = ((BaseKineticEnergyCore)this.energyCore.getItem()).getMaxExtract();
			
			/*
			 * energyExtracted cannot exceed the following items:
			 * 
			 * 	1. core max throughput per tick
			 * 	2. generator max throughput per tick
			 * 	3. currentEnergy
			 */
			energyExtracted = maxExtract > coreMaxThroughput ? coreMaxThroughput : maxExtract;
			energyExtracted = energyExtracted > this.maxTransfer ? this.maxTransfer : (energyExtracted > currentEnergy ? currentEnergy : energyExtracted);
		}
		
		if ( ! simulate)
		{
			this.energyCore.setItemDamage(this.energyCore.getItemDamage() + energyExtracted);
			NBTHelper.setInteger(this.energyCore, "kineticEnergyStored", this.energyCore.getMaxDamage() - this.energyCore.getItemDamage());
		}
		
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ForgeDirection dir)
	{
		return this.energyCore != null ? (this.energyCore.getMaxDamage() - this.energyCore.getItemDamage()) : 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection dir) 
	{
		return this.energyCore != null ? this.energyCore.getMaxDamage() : 0;
	}

	/*
	 * Receive kinetic energy - by passes preset throughput limit
	 */
	public int receiveKineticEnergy(ForgeDirection dir, int maxReceive, boolean simulate)
	{
		int energyAccepted = 0;
		
		this.energyCore = this.getStackInSlot(0);
		
		if (this.energyCore != null)
		{
			int energyCanAccept = this.energyCore.getItemDamage();

			energyAccepted = maxReceive > energyCanAccept ? energyCanAccept : maxReceive;
			
			if ( ! simulate && energyAccepted > 0)
			{
				try
				{
					this.energyCore.setItemDamage(this.energyCore.getItemDamage() - energyAccepted);
					NBTHelper.setInteger(this.energyCore, "kineticEnergyStored", this.energyCore.getMaxDamage() - this.energyCore.getItemDamage());
				}
				catch (Exception e) 
				{
					LogHelper.info(e.toString());
				}
			}
		}
		
		return energyAccepted;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection dir, int maxReceive, boolean simulate) 
	{
		return 0;
		
		/*
		int energyAccepted = 0;
		
		this.energyCore = this.getStackInSlot(0);

		if (this.energyCore != null)
		{
			int energyCanAccept = this.energyCore.getItemDamage();
			int coreMaxThroughput = ((BaseKineticEnergyCore)this.energyCore.getItem()).getMaxExtract();
			
			energyAccepted = maxReceive > coreMaxThroughput ? coreMaxThroughput : maxReceive;
			energyAccepted = energyAccepted > this.maxTransfer ? this.maxTransfer : (energyAccepted > energyCanAccept ? energyCanAccept : energyAccepted);
			
			if ( ! simulate && energyAccepted > 0)
			{
				try
				{
					this.energyCore.setItemDamage(this.energyCore.getItemDamage() - energyAccepted);
					NBTHelper.setInteger(this.energyCore, "kineticEnergyStored", this.energyCore.getMaxDamage() - this.energyCore.getItemDamage());
				}
				catch (Exception e) 
				{
					LogHelper.info(e.toString());
				}
			}
		}
		
		return energyAccepted;
		*/
	}

	@Override
	public int getSizeInventory() 
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		if (slot == 0)
		{
			return this.energyCore;
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (i == 0)
		{
			ItemStack returnStack = this.energyCore;
			this.energyCore = null;
			return returnStack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack)
	{
		if (i == 0)
		{
			this.energyCore = itemStack;
			
			if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
	        {
				itemStack.stackSize = this.getInventoryStackLimit();
	        }
		}
	}

	@Override
	public String getInventoryName() 
	{
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return true;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) 
	{
		if (itemStack.getItem() instanceof BaseKineticEnergyCore)
		{
			return true;
		}
		
		return false;
	}

	/*
	 * NBT
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		
		NBTTagList nbttaglist = new NBTTagList();

		if (this.energyCore != null)
		{
			
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setByte("Slot", (byte)0);
			this.energyCore.writeToNBT(nbttagcompound1);
			nbttaglist.appendTag(nbttagcompound1);
		}
	
		par1NBTTagCompound.setTag("Cores", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Cores", 10);
       
		NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(0);
		int j = nbttagcompound1.getByte("Slot") & 255;

		this.energyCore = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	}
}
