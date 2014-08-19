package com.techmafia.mcmods.KinetiCraft.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

public class KineticEnergyConduitTileEntity extends TileEntity
{
	private int type = 0;
	
	/*
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	public ForgeDirection[] connections = new ForgeDirection[6];
	
	public KineticEnergyConduitTileEntity(int type)
	{
		this.setType(type);
	}
	
	public void updateEntity()
	{
		this.updateConnections();
	}
	
	public void updateConnections()
	{
		if (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyHandler) connections[0] = ForgeDirection.UP;
		else connections[0] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyHandler) connections[1] = ForgeDirection.DOWN;
		else connections[1] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyHandler) connections[2] = ForgeDirection.NORTH;
		else connections[2] = null;
		if (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyHandler) connections[3] = ForgeDirection.EAST;
		else connections[3] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IEnergyHandler) connections[4] = ForgeDirection.SOUTH;
		else connections[4] = null;
		if (this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IEnergyHandler) connections[5] = ForgeDirection.WEST;
		else connections[5] = null;
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
}
