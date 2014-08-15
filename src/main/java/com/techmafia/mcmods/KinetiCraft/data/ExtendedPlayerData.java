package com.techmafia.mcmods.KinetiCraft.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.techmafia.mcmods.KinetiCraft.items.EnderKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.GoldKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.IronKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.StoneKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.WoodenKineticEnergyCore;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class ExtendedPlayerData implements IMessage
{
	protected ItemStack energyCores[];
	
	public ExtendedPlayerData() { }
	
	public ExtendedPlayerData(ItemStack energyCores[])
	{
		this.energyCores = new ItemStack[9];
		
		System.arraycopy(energyCores, 0, this.energyCores, 0, energyCores.length);
	}
	
	public ItemStack [] getEnergyCores()
	{
		return this.energyCores;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		int itemType, itemDamage;
		
		for (int c = 0; c < this.energyCores.length; c++)
		{
			ItemStack itemStack;
			itemType = buf.readInt();
			itemDamage = buf.readInt();
			
			switch (itemType)
			{
			case 1:
				itemStack = new ItemStack(new WoodenKineticEnergyCore(), 1, itemDamage);
				break;
			case 2:
				itemStack = new ItemStack(new StoneKineticEnergyCore(), 1, itemDamage);
				break;
			case 3:
				itemStack = new ItemStack(new IronKineticEnergyCore(), 1, itemDamage);
				break;
			case 4:
				itemStack = new ItemStack(new GoldKineticEnergyCore(), 1, itemDamage);
				break;
			case 5:
				itemStack = new ItemStack(new EnderKineticEnergyCore(), 1, itemDamage);
				break;
			default:
				itemStack = new ItemStack(new WoodenKineticEnergyCore(), 1, 0);
				break;
			}
			
			this.energyCores[c] = itemStack;
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		for (int c = 0; c < this.energyCores.length; c++)
		{
			ItemStack itemStack;
			Item item;
			
			if (this.energyCores[c] != null)
			{
				itemStack = this.energyCores[c];
				item = itemStack.getItem();
				
				if (item instanceof WoodenKineticEnergyCore)
				{
					buf.writeInt(1);
				}
				else if (item instanceof StoneKineticEnergyCore)
				{
					buf.writeInt(2);					
				}
				else if (item instanceof IronKineticEnergyCore)
				{
					buf.writeInt(3);					
				}
				else if (item instanceof GoldKineticEnergyCore)
				{
					buf.writeInt(4);					
				}
				else if (item instanceof EnderKineticEnergyCore)
				{
					buf.writeInt(5);					
				}
				
				buf.writeInt(itemStack.getItemDamage());
			}			
		}
	}
}
