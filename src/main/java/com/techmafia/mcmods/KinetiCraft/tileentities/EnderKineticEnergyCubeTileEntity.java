package com.techmafia.mcmods.KinetiCraft.tileentities;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

import com.techmafia.mcmods.KinetiCraft.items.BaseKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.player.ExtendedPlayer;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.NBTHelper;

public class EnderKineticEnergyCubeTileEntity extends BaseKineticEnergyCubeTileEntity
{
	protected String ownerName="";
	protected String ownerUUID="";
	protected EntityPlayer owner;
	
	public EnderKineticEnergyCubeTileEntity()
	{
		super();
		energyCores = new ItemStack[9];
	}
	
	public void setOwnerUUID(String uuid)
	{
		this.ownerUUID = uuid;
	}
	
	public void setOwnerName(String owner)
	{
		this.ownerName = owner;
	}
	
	public String getOwnerUUID()
	{
		return this.ownerUUID;
	}
	
	public String getOwnerName()
	{
		return this.ownerName;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setString("ownerUUID", this.ownerUUID);
		nbt.setString("ownerName", this.ownerName);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		this.ownerUUID = nbt.getString("ownerUUID");
		this.ownerName = nbt.getString("ownerName");
	}
	
	@Override
	public void updateEntity()
	{
		if (this.owner != null)
		{
			ArrayList <Integer> hungryTiles = new ArrayList <Integer>();
			ExtendedPlayer ep = ExtendedPlayer.get(this.owner);
			
			//LogHelper.info(this.owner.getDisplayName());
			
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
				if (tes[i] != null && tes[i] instanceof IEnergyHandler)
				{
					if (((IEnergyHandler)tes[i]).receiveEnergy(dirs[i].getOpposite(), 1, true) > 0)
					{
						hungryTiles.add(i);
					}
				}
			}
			
			for (int c = 0; c < ep.inventory.energyCores.length; c++)
			{
				int totalEnergySent = 0;
				ItemStack energyCore = ep.inventory.getStackInSlot(c);
				//ItemStack energyCore = ep.inventory.energyCores[c];
				
				if (energyCore != null && energyCore.getItem() instanceof BaseKineticEnergyCore && energyCore.getItemDamage() < energyCore.getMaxDamage())
				{			
					if (hungryTiles.size() > 0)
					{
						int energyLeft = energyCore.getMaxDamage() - energyCore.getItemDamage();
						int maxExtract = ((BaseKineticEnergyCore)energyCore.getItem()).getMaxExtract();
						int energyToExtract = energyLeft < maxExtract ? energyLeft : maxExtract;
						int energyPerTile = energyToExtract / hungryTiles.size();
						
						for (Iterator <Integer> te = hungryTiles.iterator(); te.hasNext(); )
						{	
							int index = te.next();
							
							if (energyToExtract > 0)
							{
								if (totalEnergySent < maxExtract)
								{
									int energyOut = totalEnergySent + energyPerTile > maxExtract ? maxExtract - totalEnergySent : ((IEnergyHandler)tes[index]).receiveEnergy(dirs[index].getOpposite(), energyPerTile, false);
									
									if (energyOut > 0)
									{
										this.extractEnergy(dirs[index], energyOut, false);
										energyCore.setItemDamage(energyCore.getItemDamage() + energyOut);
										NBTHelper.setInteger(energyCore, "kineticEnergyStored", energyCore.getMaxDamage() - energyCore.getItemDamage());
										this.markDirty();
										
										Item item = energyCore.getItem();
										
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
		}
		else
		{
			//LogHelper.info("No owner");
			
			// set owner entity player if not set
			if (this.worldObj != null && this.worldObj.playerEntities != null)
			{
				LogHelper.info("players: " + this.worldObj.playerEntities.size());
				
				for (EntityPlayer ep : (ArrayList <EntityPlayer>)this.worldObj.playerEntities)
				{			
					if (((EntityPlayer)ep).getUniqueID().toString().equals(this.ownerUUID))
					{
						this.owner = ep;
					}
				}
			}
		}
	}
}