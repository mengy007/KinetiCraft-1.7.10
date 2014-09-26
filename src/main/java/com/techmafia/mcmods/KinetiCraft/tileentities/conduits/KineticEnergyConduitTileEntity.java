package com.techmafia.mcmods.KinetiCraft.tileentities.conduits;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

import com.techmafia.mcmods.KinetiCraft.utility.BlockPos;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;
import com.techmafia.mcmods.KinetiCraft.utility.PowerTile;

public class KineticEnergyConduitTileEntity extends TileEntity implements IEnergyHandler
{
	/*
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	public ForgeDirection[] connections = new ForgeDirection[6];
	private ArrayList <PowerTile> powerSources = new ArrayList <PowerTile>();
	private ArrayList <PowerTile> powerDrains = new ArrayList <PowerTile>();
	private int maxThroughPut = 10000;
	private int energyStored = 0;
	private boolean firstTick = true;
		
	public KineticEnergyConduitTileEntity()	
	{
		super();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		if (nbt != null)
		{
			this.energyStored = nbt.getInteger("energyStored");
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		if (nbt != null)
		{
			nbt.setInteger("energyStored",	this.energyStored);
		}
	}
	
	public void updateEntity()
	{
		if (this.firstTick)
		{
			this.updateConduitNetwork();
			this.firstTick = false;
		}
		
		//if (this.powerSources.size() > 0 && this.powerDrains.size() > 0)
		if (this.powerDrains.size() > 0 && this.energyStored > 0 && this.energyStored > this.powerDrains.size())
		{
			this.distributePower();
		}
	}
	
	public void updateConduitNetwork()
	{
		this.updateConnections();
		//this.updatePowerSources();
		
		//if (this.powerSources.size() > 0)
		//{
			//ArrayList <BlockPos> checkedList = new ArrayList <BlockPos>();
			
			// Add connected power sources
			//for (Iterator <PowerTile> it = this.powerSources.iterator(); it.hasNext(); )
			//{
			//	TileEntity te = it.next().te;				
			//	checkedList.add(new BlockPos(te.xCoord, te.yCoord, te.zCoord));
			//}			
			this.powerDrains = this.updatePowerDrains(new BlockPos(this.xCoord, this.yCoord, this.zCoord), null);
		//}
	}
	
	public void distributePower()
	{
		if (this.powerDrains.size() > 0 && this.energyStored > 0 && this.energyStored > this.powerDrains.size())
		{
			int energyPerDrain = this.energyStored / this.powerDrains.size();
			
			for (Iterator <PowerTile> it = this.powerDrains.iterator(); it.hasNext(); )
			{
				PowerTile pt = it.next();
				TileEntity te = pt.te;
				
				if (te != null && te instanceof IEnergyHandler && ((IEnergyHandler)te).receiveEnergy(pt.connectedDir.getOpposite(), 1, true) > 0)
				{
					this.energyStored -= ((IEnergyHandler)te).receiveEnergy(pt.connectedDir.getOpposite(), energyPerDrain, false);
				}
			}			
			
			/*
			int hungryDrains = 0;
			int energyPerDrain = 0;
			
			for (Iterator <PowerTile> it = this.powerDrains.iterator(); it.hasNext(); )
			{
				PowerTile pt = it.next();
				TileEntity te = pt.te;
				
				if (((IEnergyHandler)te).receiveEnergy(pt.connectedDir.getOpposite(), 1, true) > 0) hungryDrains++;
			}
			
			energyPerDrain = this.energyStored / hungryDrains;
			*/
		}		
	}
	
	/*
	 * Updates power drains for current conduit tile.
	 * Should only get called if conduit is connected to power source
	 */
	public ArrayList <PowerTile> updatePowerDrains(BlockPos start, ArrayList <BlockPos> checkedTiles)
	{
		TileEntity tes[] = new TileEntity[6];
		ForgeDirection dirs[] = new ForgeDirection[6];
		ArrayList <PowerTile> returnList = new ArrayList <PowerTile>();
		boolean tileConnectedToPowerSource = false;
		
		tes[0] = this.worldObj.getTileEntity(start.x-1, start.y, 	start.z); // WEST
		tes[1] = this.worldObj.getTileEntity(start.x+1, start.y, 	start.z); // EAST
		tes[2] = this.worldObj.getTileEntity(start.x, 	start.y-1, 	start.z); // DOWN
		tes[3] = this.worldObj.getTileEntity(start.x, 	start.y+1, 	start.z); // UP
		tes[4] = this.worldObj.getTileEntity(start.x, 	start.y, 	start.z-1); // SOUTH
		tes[5] = this.worldObj.getTileEntity(start.x, 	start.y, 	start.z+1); // NORTH
		
		dirs[0] = ForgeDirection.WEST;
		dirs[1] = ForgeDirection.EAST;
		dirs[2] = ForgeDirection.DOWN;
		dirs[3] = ForgeDirection.UP;
		dirs[4] = ForgeDirection.SOUTH;
		dirs[5] = ForgeDirection.NORTH;
		
		if (checkedTiles == null)
		{
			checkedTiles = new ArrayList <BlockPos>();
		}
		
		checkedTiles.add(new BlockPos(start.x, start.y, start.z));
		
		//LogHelper.info("Checked Tiles Count: " + checkedTiles.size());
		
		for (int i=0; i<tes.length; i++)
		{
			// Not null and not in checkedList
			if (tes[i] != null && ! (new BlockPos(tes[i].xCoord, tes[i].yCoord, tes[i].zCoord).inArrayList(checkedTiles)))
			{
				if (returnList == null) returnList = new ArrayList <PowerTile>();
				
				// Tile is conduit
				if (tes[i] instanceof KineticEnergyConduitTileEntity)
				{
					//LogHelper.info("Connected conduit to the " + dirs[i].toString());
					
					// Check matching adjacent conduit for more connected conduits and return possible connected power drains
					ArrayList <PowerTile> newList = new ArrayList <PowerTile>();
					newList = this.updatePowerDrains(new BlockPos(tes[i].xCoord, tes[i].yCoord, tes[i].zCoord), checkedTiles);

					// Merge to return list
					if (newList.size() > 0)
					{
						for (Iterator <PowerTile> ni = newList.iterator(); ni.hasNext(); )
						{
							returnList.add(ni.next());
						}
					}
					
					//((KineticEnergyConduitTileEntity)tes[i]).updateConduitNetwork();
				}
				else if (tes[i] instanceof IEnergyHandler && ((IEnergyHandler)tes[i]).canConnectEnergy(dirs[i].getOpposite()))// && ((IEnergyHandler)tes[i]).receiveEnergy(dirs[i].getOpposite(), 1, true) > 0)
				{
					// Power drain
					if (((IEnergyHandler)tes[i]).receiveEnergy(dirs[i].getOpposite(), 1, true) > 0)
					{
						returnList.add(new PowerTile(dirs[i], tes[i]));
					}
					// Power source
					else if (((IEnergyHandler)tes[i]).extractEnergy(dirs[i].getOpposite(), 1, true) > 0)
					{
						//this.powerDrains = this.updatePowerDrains(new BlockPos(this.xCoord, this.yCoord, this.zCoord), null);
						
						//tileConnectedToPowerSource = true;
						
						// There must be a smarter way of doing this
						/*
						if (this.powerSources.size() > 0)
						{
							ArrayList <BlockPos> checkedList = new ArrayList <BlockPos>();
							
							// Add connected power sources
							for (Iterator <PowerTile> it = this.powerSources.iterator(); it.hasNext(); )
							{
								TileEntity te = it.next().te;				
								checkedList.add(new BlockPos(te.xCoord, te.yCoord, te.zCoord));
							}			
							this.powerDrains = this.updatePowerDrains(new BlockPos(this.xCoord, this.yCoord, this.zCoord), checkedList);
						}
						*/
					}
				}
				else
				{
					//Other type of block found
				}
			}
		}
		
		/*
		if (tileConnectedToPowerSource)
		{
			ArrayList <BlockPos> checkedList = new ArrayList <BlockPos>();
			
			// Add connected power sources
			for (Iterator <PowerTile> it = this.powerSources.iterator(); it.hasNext(); )
			{
				TileEntity te = it.next().te;				
				checkedList.add(new BlockPos(te.xCoord, te.yCoord, te.zCoord));
			}			
			this.powerDrains = this.updatePowerDrains(new BlockPos(this.xCoord, this.yCoord, this.zCoord), checkedList);
		}
		*/
		
		return returnList;
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
			if (tes[i] != null & tes[i] instanceof IEnergyHandler && ((IEnergyHandler)tes[i]).canConnectEnergy(dirs[i].getOpposite()) && ((IEnergyHandler)tes[i]).extractEnergy(dirs[i].getOpposite(), 1, true) > 0)
			{
				this.powerSources.add(new PowerTile(dirs[i], tes[i]));
			}
		}
	}
	
	public void updateConnections()
	{
		try
		{
			connections[0] = (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null && (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof KineticEnergyConduitTileEntity || (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IEnergyHandler && ((IEnergyHandler)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).canConnectEnergy(ForgeDirection.DOWN)))) ? ForgeDirection.UP : null;
			connections[1] = (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) != null && (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof KineticEnergyConduitTileEntity || (this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IEnergyHandler && ((IEnergyHandler)this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord)).canConnectEnergy(ForgeDirection.UP)))) ? ForgeDirection.DOWN : null;
			connections[2] = (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) != null && (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof KineticEnergyConduitTileEntity || (this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IEnergyHandler && ((IEnergyHandler)this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1)).canConnectEnergy(ForgeDirection.SOUTH)))) ? ForgeDirection.NORTH : null;
			connections[3] = (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) != null && (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity || (this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IEnergyHandler && ((IEnergyHandler)this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord)).canConnectEnergy(ForgeDirection.WEST)))) ? ForgeDirection.EAST : null;
			connections[4] = (this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) != null && (this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof KineticEnergyConduitTileEntity || (this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IEnergyHandler && ((IEnergyHandler)this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1)).canConnectEnergy(ForgeDirection.NORTH)))) ? ForgeDirection.SOUTH : null;
			connections[5] = (this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) != null && (this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof KineticEnergyConduitTileEntity || (this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IEnergyHandler && ((IEnergyHandler)this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord)).canConnectEnergy(ForgeDirection.EAST)))) ? ForgeDirection.WEST : null;
		}
		catch (Exception e)
		{
			LogHelper.error(e.toString());
		}
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

	@Override
	public boolean canConnectEnergy(ForgeDirection dir) 
	{
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection dir, int maxExtract, boolean simulate)
	{
		int actualExtract = Math.min(maxExtract, this.energyStored);
		
		if ( ! simulate)
		{
			this.energyStored -= actualExtract;
			this.energyStored = Math.min(this.energyStored, 0);
		}
		
		return actualExtract;
	}

	@Override
	public int getEnergyStored(ForgeDirection dir)
	{
		return this.energyStored;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection dir)
	{
		return this.maxThroughPut;
	}

	@Override
	public int receiveEnergy(ForgeDirection dir, int maxReceive, boolean simulate)
	{
		int actualReceive = 0;

		if (this.powerDrains.size() > 0)
		{
			actualReceive = Math.min(maxReceive, (this.maxThroughPut - this.energyStored));
			
			if ( ! simulate)
			{
				this.energyStored += actualReceive;
				this.energyStored = Math.max(this.energyStored, this.maxThroughPut);
			}
		}
		
		return actualReceive;
	}
}
