package com.techmafia.mcmods.KinetiCraft.entities;

import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class EnderKineticEnergy implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = "EnderKineticEnergy";
	private final EntityPlayer player;
	private final int maxEnergyStored = 1000000;
	private int currentEnergyStored;
	
	public EnderKineticEnergy(EntityPlayer player)
	{
		this.player = player;
		this.currentEnergyStored = 0;
	}
	
	/**
	* Used to register these extended properties for the player during EntityConstructing event
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(EnderKineticEnergy.EXT_PROP_NAME, new EnderKineticEnergy(player));
	}
	
	/**
	* Returns EnderKineticEnergy properties for player
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final EnderKineticEnergy get(EntityPlayer player)
	{
		return (EnderKineticEnergy) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	public int getEnergy()
	{
		return this.currentEnergyStored;
	}
	
	public void setEnergy(int newEnergyLevel)
	{
		this.currentEnergyStored = newEnergyLevel;
	}
	
	public int extractEnergy(int maxExtract)
	{
		int energyOut = 0;
		
		if (this.currentEnergyStored != 0)
		{		
			energyOut = this.currentEnergyStored > maxExtract ? maxExtract : this.currentEnergyStored;
		}
		
		return energyOut;
	}
	
	public void receiveEnergy(int maxExtract)
	{
		int energyIn = 0;
		
		if (this.currentEnergyStored < this.maxEnergyStored)
		{
			int energyUntilFull = this.maxEnergyStored - this.currentEnergyStored;
			
			energyIn = maxExtract < energyUntilFull ? maxExtract : energyUntilFull;
		}
		this.currentEnergyStored += energyIn;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();

		properties.setInteger("currentEnergyStored", this.currentEnergyStored);
		
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

		this.currentEnergyStored = properties.getInteger("currentEnergyStored");
	}

	@Override
	public void init(Entity entity, World world) { }
}
