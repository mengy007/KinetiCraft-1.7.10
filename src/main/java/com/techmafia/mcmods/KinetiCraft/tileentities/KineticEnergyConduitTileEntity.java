package com.techmafia.mcmods.KinetiCraft.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

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
		if (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof KineticEnergyConduitTileEntity) connections[0] = ForgeDirection.UP;
		else connections[0] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof KineticEnergyConduitTileEntity) connections[1] = ForgeDirection.DOWN;
		else connections[1] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof KineticEnergyConduitTileEntity) connections[2] = ForgeDirection.NORTH;
		else connections[2] = null;
		if (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity) connections[3] = ForgeDirection.EAST;
		else connections[3] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof KineticEnergyConduitTileEntity) connections[4] = ForgeDirection.SOUTH;
		else connections[4] = null;
		if (this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity) connections[5] = ForgeDirection.WEST;
		else connections[5] = null;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
