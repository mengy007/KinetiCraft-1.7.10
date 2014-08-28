package com.techmafia.mcmods.KinetiCraft.tileentities;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.EnderKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.tileentities.conduits.KineticEnergyConduitTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;

public class BaseKineticEnergyCubeTileEntity extends TileEntity implements IInventory, IEnergyHandler
{
	protected ItemStack[] energyCores;
	protected EnergyStorage energy = new EnergyStorage(0);
	protected int tickDirection = 0;
	public boolean canEmitPower = true;
	
	class HungryTile
	{
		public ForgeDirection feedDir;
		public TileEntity te;
	}
	
	public BaseKineticEnergyCubeTileEntity()
	{
		super();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		energy.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	   super.readFromNBT(nbt);
	   energy.readFromNBT(nbt);
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.energyCores.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		if (i < this.energyCores.length)
		{
			return this.energyCores[i];
		}
		else
		{
			LogHelper.error("Invalid index: " + i);
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (i < this.energyCores.length && this.energyCores[i] != null)
		{
			ItemStack itemstack = this.energyCores[i];
            this.energyCores[i] = null;
            return itemstack;
		}
		else
		{
			LogHelper.error("Invalid index: " + i + " or no core present");
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
    {
		return null;
    }

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		if (i < this.energyCores.length)
		{
			this.energyCores[i] = itemstack;
	
	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemstack.stackSize = this.getInventoryStackLimit();
	        }
		}
		else
		{
			LogHelper.error("Invalid index: " + i);
		}
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack)
	{
		return (itemStack.getItem() instanceof BaseKineticEnergyCore) ? true : false;
	}

	@Override
	public String getInventoryName() { return null; }

	@Override
	public boolean hasCustomInventoryName() { return false;	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }
	
	public ArrayList <HungryTile> getConnectedHungryTiles(int x, int y, int z)
	{
		TileEntity tes[] = new TileEntity[6];
		ForgeDirection dirs[] = new ForgeDirection[6];
		ArrayList <TileEntity> conduits = new ArrayList <TileEntity>();
		
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
		
		/* Gather all connected conduit tiles */
		for (int i=0; i<6; i++)
		{
			if (tes[i] != null && tes[i] instanceof KineticEnergyConduitTileEntity)
			{
				
			}
		}
		
		return null;
	}
	
	public int getMaxAvailableEnergyPerTick()
	{
		int maxEnergyPerTick = 0;
		
		for (int c = 0; c < this.energyCores.length; c++)
		{
			ItemStack energyCore = this.getStackInSlot(c);
			
			if (energyCore != null && energyCore.getItem() instanceof BaseKineticEnergyCore && energyCore.getItemDamage() < energyCore.getMaxDamage())
			{
				int coreMaxThroughPut = ((BaseKineticEnergyCore)energyCore.getItem()).getMaxExtract();
				int coreAvailable = NBTHelper.getInt(energyCore, "kineticEnergyStored");
				maxEnergyPerTick += coreMaxThroughPut > coreAvailable ? coreAvailable : coreMaxThroughPut;
			}
		}
		
		return maxEnergyPerTick;
	}
	
	@Override
	public void updateEntity()
	{
		if (this.canEmitPower)
		{
			ArrayList <HungryTile> hungryTiles = new ArrayList <HungryTile>();
			int maxAvailable = this.getMaxAvailableEnergyPerTick();
			int energyPerTile = 0;
			
			TileEntity tes[] = new TileEntity[6];
			ForgeDirection dirs[] = new ForgeDirection[6];
			
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
			
			/* Gather all tiles that want energy */
			for (int i=0; i<6; i++)
			{
				if (tes[i] != null)
				{
					if (tes[i] instanceof IEnergyHandler)
					{
						if (((IEnergyHandler)tes[i]).receiveEnergy(dirs[i].getOpposite(), 1, true) > 0)
						{
							HungryTile tileToAdd = new HungryTile();
							tileToAdd.feedDir = dirs[i];
							tileToAdd.te = tes[i];
							hungryTiles.add(tileToAdd);
						}
					}
				}		
			}
			
			if (hungryTiles.size() > 0 && maxAvailable >= hungryTiles.size())
			{
				energyPerTile = maxAvailable / hungryTiles.size();				
				
				for (Iterator <HungryTile> te = hungryTiles.iterator(); te.hasNext(); )
				{	
					HungryTile hungryTile = te.next();
					int tileEnergyTook = 0;
					int energyExtracted = 0;
					
					energyExtracted = this.extractEnergy(hungryTile.feedDir, energyPerTile, false);
					tileEnergyTook = ((IEnergyHandler)hungryTile.te).receiveEnergy(hungryTile.feedDir.getOpposite(), energyExtracted, false);
					
					if (tileEnergyTook < energyExtracted)
					{
						// Implement buffer system or redistribute power back to cores somehow
					}
				}
			}
			
			/*
			for (int c = 0; c < this.energyCores.length; c++)
			{
				int totalEnergySent = 0;
				ItemStack energyCore = this.getStackInSlot(c);
				
				if (energyCore != null && energyCore.getItem() instanceof BaseKineticEnergyCore && energyCore.getItemDamage() < energyCore.getMaxDamage())
				{			
					if (hungryTiles.size() > 0)
					{
						int energyLeft = energyCore.getMaxDamage() - energyCore.getItemDamage();
						int maxExtract = ((BaseKineticEnergyCore)energyCore.getItem()).getMaxExtract();
						int energyToExtract = energyLeft < maxExtract ? energyLeft : maxExtract;
						int energyPerTile = energyToExtract / hungryTiles.size();
						
						for (Iterator <HungryTile> te = hungryTiles.iterator(); te.hasNext(); )
						{	
							HungryTile hungryTile = te.next();
							
							if (energyToExtract > 0)
							{
								if (totalEnergySent < maxExtract)
								{
									int energyOut = totalEnergySent + energyPerTile > maxExtract ? maxExtract - totalEnergySent : ((IEnergyHandler)hungryTile.te).receiveEnergy(hungryTile.feedDir.getOpposite(), energyPerTile, false);
									
									if (energyOut > 0)
									{
										this.extractEnergy(hungryTile.feedDir, energyOut, false);
										energyCore.setItemDamage(energyCore.getItemDamage() + energyOut);
										NBTHelper.setInteger(energyCore, "kineticEnergyStored", energyCore.getMaxDamage() - energyCore.getItemDamage());
										this.markDirty();
										
										Item item = energyCore.getItem();
										
										if (item instanceof EnderKineticEnergyCore && NBTHelper.hasTag(energyCore, "ownerUUID"))
										{
											KinetiCraft.proxy.drainEnderEnergyUpdate(energyCore, energyLeft - energyOut);
										}
										
										totalEnergySent += energyOut;
										
										// Reset overcharge on item
										NBTHelper.setInteger(energyCore, "overCharge", 0);
									}
								}
							}
						}
					}
				}
			}
			*/
		}
	}
	
	/*
	@Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    */

	/**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net The NetworkManager the packet originated from
     * @param pkt The data packet
     */
	/*
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
    }
    */
	
	/**
	 * CoFH interface
	 */
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		// Only connect if there are cores available
		for (int c = 0; c < this.energyCores.length; c++)
		{
			ItemStack energyCore = this.getStackInSlot(c);
		
			if (energyCore != null && energyCore.getItem() instanceof BaseKineticEnergyCore)
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int max, boolean simulate)
	{
		int totalEnergyToSend = 0;
		int energyLeftUntilMax = max;
		
		for (int c = 0; c < this.energyCores.length; c++)
		{
			ItemStack energyCore = this.getStackInSlot(c);
			
			if (energyLeftUntilMax <= 0) break;
			
			if (energyCore != null && energyCore.getItem() instanceof BaseKineticEnergyCore && energyCore.getItemDamage() < energyCore.getMaxDamage())
			{			
				int energyFromCore = 0;
				int maxCoreExtract = ((BaseKineticEnergyCore)energyCore.getItem()).getMaxExtract();
				int coreEnergyLeft = NBTHelper.getInt(energyCore, "kineticEnergyStored");
				
				LogHelper.info("MaxCoreExtract: " + maxCoreExtract + ", coreEnergyLeft: " + coreEnergyLeft);
				
				if (maxCoreExtract <= coreEnergyLeft && maxCoreExtract <= energyLeftUntilMax)
				{
					LogHelper.info("ee: 1st: " + maxCoreExtract);
					totalEnergyToSend += maxCoreExtract;
					energyFromCore = maxCoreExtract;					
				}
				else if (maxCoreExtract > coreEnergyLeft)
				{
					if (coreEnergyLeft > energyLeftUntilMax)
					{
						LogHelper.info("ee: 2nd-1: " + energyLeftUntilMax);
						totalEnergyToSend += energyLeftUntilMax;
						energyFromCore = energyLeftUntilMax;
					}
					else
					{
						LogHelper.info("ee: 2nd-2: " + coreEnergyLeft);
						totalEnergyToSend += coreEnergyLeft;
						energyFromCore = coreEnergyLeft;
					}
				}
				else if (maxCoreExtract > energyLeftUntilMax)
				{
					LogHelper.info("ee: 3rd: " + energyLeftUntilMax);
					totalEnergyToSend += energyLeftUntilMax;
					energyFromCore = energyLeftUntilMax;
				}

				if (energyFromCore > 0)
				{
					energyLeftUntilMax -= energyFromCore;
					
					if (!simulate)
					{
						energyCore.setItemDamage(energyCore.getItemDamage()+energyFromCore);
						NBTHelper.setInteger(energyCore, "kineticEnergyStored", energyCore.getMaxDamage() - energyCore.getItemDamage());
						NBTHelper.setInteger(energyCore, "overCharge", 0);
						this.markDirty();
					}
				}
			}
		}
		
		return totalEnergyToSend;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		int totalEnergyStored = 0;
		
		for (int c = 0; c < this.energyCores.length; c++)
		{
			ItemStack energyCore = this.getStackInSlot(c);
			
			if (energyCore != null)
			{
				totalEnergyStored += NBTHelper.getInt(energyCore, "kineticEnergyStored");
			}
		}
		
		return totalEnergyStored;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		int totalMaxEnergy = 0;
		
		for (int c = 0; c < this.energyCores.length; c++)
		{
			ItemStack energyCore = this.getStackInSlot(c);
	
			if (energyCore != null)
			{
				totalMaxEnergy += energyCore.getMaxDamage();
			}
		}
		
		return totalMaxEnergy;
	}
}
