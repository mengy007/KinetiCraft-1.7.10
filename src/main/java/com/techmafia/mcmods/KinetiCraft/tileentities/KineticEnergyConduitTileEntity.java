package com.techmafia.mcmods.KinetiCraft.tileentities;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

public class KineticEnergyConduitTileEntity extends TileEntity
{
	private int type = 0;
	private boolean powerSourceConnected = false;
	private ArrayList <PowerTile> powerSources = new ArrayList <PowerTile>();
	private ArrayList <PowerTile> powerDrains = new ArrayList <PowerTile>();
	private int maxThroughPut = 10;
	
	class PowerTile
	{
		public ForgeDirection connectedDir;
		public TileEntity te;
		public boolean source = false;
		
		public PowerTile(ForgeDirection dir, TileEntity te)
		{
			this.connectedDir = dir;
			this.te = te;
		}
		
		public PowerTile(ForgeDirection dir, TileEntity te, boolean source)
		{
			this.connectedDir = dir;
			this.te = te;
			this.source = source;
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
	
	public boolean inArrayList(ArrayList <TileEntity> tes)
	{
		if (tes != null && tes.size() > 0)
		{
			for (Iterator <TileEntity> i = tes.iterator(); i.hasNext(); )
			{
				TileEntity te = i.next();
				
				if (te != null && te.xCoord == this.xCoord && te.yCoord == this.yCoord && te.zCoord == this.zCoord)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void updateEntity()
	{
		this.updateConnections();
		
		this.updatePowerSources();
		
		if (this.powerSourceConnected)
		{
			ArrayList <TileEntity> checkedList = new ArrayList <TileEntity>();
			
			for (Iterator <PowerTile> it = this.powerSources.iterator(); it.hasNext(); )
			{
				checkedList.add(it.next().te);
			}
			
			this.powerDrains = this.updatePowerDrains(checkedList);
			this.distributePower();
		}
	}
	
	public void distributePower()
	{
		//LogHelper.debug("Giving power! " + this.powerDrains.size() + " found!");
		
		if (this.powerSources != null && this.powerSources.size() > 0)
		{
			for (Iterator <PowerTile> i = this.powerSources.iterator(); i.hasNext(); )
			{
				PowerTile powerSource = i.next();
				int maxEnergyDrainPossible = ((IEnergyHandler)powerSource.te).extractEnergy(powerSource.connectedDir, this.maxThroughPut, true);
				
				if (maxEnergyDrainPossible > 0 && this.powerDrains != null && this.powerDrains.size() > 0)
				{
					int energyPerDrainTile = maxEnergyDrainPossible / this.powerDrains.size();
					int actualEnergyDrained = 0;

					for (Iterator <PowerTile> i1 = this.powerDrains.iterator(); i1.hasNext(); )
					{
						PowerTile pt = i1.next();
						
						actualEnergyDrained += ((IEnergyHandler)pt.te).receiveEnergy(pt.connectedDir, energyPerDrainTile, false);
					}
					
					if (actualEnergyDrained > 0)
					{
						((IEnergyHandler)powerSource.te).extractEnergy(powerSource.connectedDir, actualEnergyDrained, false);
					}
				}
			}
		}
		
	}
	
	/*
	 * Updates power drains for current conduit tile.
	 * Should only get called if conduit is connected to power source
	 */
	public ArrayList <PowerTile> updatePowerDrains(ArrayList <TileEntity> checkedTiles)
	{
		TileEntity tes[] = new TileEntity[6];
		ForgeDirection dirs[] = new ForgeDirection[6];
		ArrayList <PowerTile> returnList = null;
		
		// Clear power sources
		this.powerSources = new ArrayList <PowerTile>(); 
		
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
		
		checkedTiles.add(this);
		
		for (int i=0; i<tes.length; i++)
		{
			if (tes[i] != null)
			{
				if (returnList == null) returnList = new ArrayList <PowerTile>();
				
				if (tes[i] instanceof KineticEnergyConduitTileEntity && ! ((KineticEnergyConduitTileEntity)tes[i]).inArrayList(checkedTiles))	
				{
					ArrayList <PowerTile> newList;
					
					newList = ((KineticEnergyConduitTileEntity)tes[i]).updatePowerDrains(checkedTiles);
					
					if (newList != null && newList.size() > 0)
					{
						for (Iterator <PowerTile> ni = newList.iterator(); ni.hasNext(); )
						{
							returnList.add(ni.next());
						}
					}
				}
				else if (tes[i] instanceof IEnergyHandler && ((IEnergyHandler)tes[i]).canConnectEnergy(dirs[i].getOpposite()) && ((IEnergyHandler)tes[i]).extractEnergy(dirs[i].getOpposite(), 1, true) > 0)
				{
					returnList.add(new PowerTile(dirs[i], tes[i]));
				}
			}
		}
		
		return returnList != null ? returnList : null;
	}
	
	public void updatePowerSources()
	{
		TileEntity tes[] = new TileEntity[6];
		ForgeDirection dirs[] = new ForgeDirection[6];
		
		// Clear power sources
		this.powerSources = new ArrayList <PowerTile>(); 
		
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
		
		for (int i=0; i<tes.length; i++)
		{
			if (tes[i] != null & tes[i] instanceof IEnergyHandler && ((IEnergyHandler)tes[i]).canConnectEnergy(dirs[i].getOpposite()))
			{
				this.powerSources.add(new PowerTile(dirs[i], tes[i]));
			}
		}
		
		LogHelper.info("Found " + this.powerSources.size() + " power sources connected to this conduit!");
	}
	
	public void updateConnections()
	{
		connections[0] = (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null && (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyHandler)) ? ForgeDirection.UP : null;
		if (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerTile(ForgeDirection.UP, this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)));
		}

		connections[1] = (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) != null && (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyHandler)) ? ForgeDirection.DOWN : null;
		if (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerTile(ForgeDirection.DOWN, this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord)));
		}

		connections[2] = (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) != null && (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyHandler)) ? ForgeDirection.NORTH : null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerTile(ForgeDirection.NORTH, this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1)));
		}

		connections[3] = (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) != null && (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity || this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyHandler)) ? ForgeDirection.EAST : null;
		if (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyHandler)
		{
			this.powerSourceConnected = true;
			this.powerSources.add(new PowerTile(ForgeDirection.EAST, this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord)));
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
}
