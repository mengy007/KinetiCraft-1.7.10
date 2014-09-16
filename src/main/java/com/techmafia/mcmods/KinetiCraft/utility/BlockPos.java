package com.techmafia.mcmods.KinetiCraft.utility;

import java.util.ArrayList;
import java.util.Iterator;

public class BlockPos
{
	public int x, y, z;
	
	public BlockPos(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean inArrayList(ArrayList <BlockPos> tes)
	{
		if (tes != null && tes.size() > 0)
		{
			for (Iterator <BlockPos> i = tes.iterator(); i.hasNext(); )
			{
				BlockPos bp = i.next();
				
				if (bp != null && bp.x == this.x && bp.y == this.y && bp.z == this.z)
				{
					return true;
				}
			}
		}
		else
		{
			//LogHelper.info("inArrayList list is either null or size is 0");
		}
		
		return false;
	}
}