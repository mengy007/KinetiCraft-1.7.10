package com.techmafia.mcmods.KinetiCraft.utility;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class PowerTile
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
