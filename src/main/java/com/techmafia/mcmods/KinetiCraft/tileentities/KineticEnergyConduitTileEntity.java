package com.techmafia.mcmods.KinetiCraft.tileentities;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

public class KineticEnergyConduitTileEntity extends TileEntity implements IEnergyHandler
{
	private int type = 0;
	private boolean powerSourceConnected = false;
	private ArrayList <PowerSource> powerSources = new ArrayList <PowerSource>();
	
	class PowerSource
	{
		public ForgeDirection connectedDir;
		public TileEntity te;
		
		public PowerSource(ForgeDirection dir, TileEntity te)
		{
			this.connectedDir = dir;
			this.te = te;
		}
	}
	
	/*
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	public ForgeDirection[] connections = new ForgeDirection[6];
	
	public KineticEnergyConduitTileEntity(int type)
	{
		this.setType(type);
	}
	
	public void distributePower()
	{
		
	}
	
	public void updateEntity()
	{
		this.updateConnections();
		if (this.powerSourceConnected)
		{
			this.distributePower();
		}
	}
	
	public void updateConnections()
	{
		connections[0] = (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null && (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyHandler)) ? ForgeDirection.UP : null;
		if (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerSource(ForgeDirection.UP, this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)));
		}

		connections[1] = (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) != null && (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyHandler)) ? ForgeDirection.DOWN : null;
		if (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerSource(ForgeDirection.DOWN, this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord)));
		}

		connections[2] = (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) != null && (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyHandler)) ? ForgeDirection.NORTH : null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerSource(ForgeDirection.NORTH, this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1)));
		}

		connections[3] = (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) != null && (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyHandler)) ? ForgeDirection.EAST : null;
		if (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerSource(ForgeDirection.EAST, this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord)));
		}

		connections[4] = (this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) != null && (this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IEnergyHandler)) ? ForgeDirection.SOUTH : null;
		this.powerSourceConnected = this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IEnergyHandler ? true : false;

		connections[5] = (this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) != null && (this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IEnergyHandler)) ? ForgeDirection.WEST : null;
		this.powerSourceConnected = this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IEnergyHandler ? true : false;
	}
	
	public boolean onlyOneOpposite(ForgeDirection[] dirs)
	{
		ForgeDirection mainDirection = null;
		boolean isOpposite = false;
		
		for (int i=0; i < dirs.length; i++)
		{
			
			if (mainDirection == null && dirs[i] != null) mainDirection = dirs[i];
			
			if (dirs[i] != null && mainDirection != dirs[i])
			{
				if ( ! isOpposite(mainDirection, dirs[i])) return false;
				else isOpposite = true;
			}
		}
		
		return isOpposite;
	}
	
	public boolean isOpposite(ForgeDirection firstDir, ForgeDirection secondDir)
	{
		if ((firstDir == ForgeDirection.NORTH && secondDir == ForgeDirection.SOUTH) || (firstDir == ForgeDirection.SOUTH && secondDir == ForgeDirection.NORTH)) return true;
		if ((firstDir == ForgeDirection.UP && secondDir == ForgeDirection.DOWN) || (firstDir == ForgeDirection.DOWN && secondDir == ForgeDirection.UP)) return true;
		if ((firstDir == ForgeDirection.WEST && secondDir == ForgeDirection.EAST) || (firstDir == ForgeDirection.EAST && secondDir == ForgeDirection.WEST)) return true;
		
		return false;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection arg0)
	{
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
