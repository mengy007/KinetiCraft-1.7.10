package com.techmafia.mcmods.KinetiCraft.data;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class EnderKineticEnergyData implements IMessage
{
	private int energyLevel;
	
	public EnderKineticEnergyData() { }
	
	public EnderKineticEnergyData(int energyLevel)
	{
		this.energyLevel = energyLevel;
	}
	
	public void setEnergyLevel(int energyLevel)
	{
		this.energyLevel = energyLevel;
	}
	
	public int getEnergyLevel()
	{
		return this.energyLevel;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.energyLevel = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.energyLevel);
	}
}
