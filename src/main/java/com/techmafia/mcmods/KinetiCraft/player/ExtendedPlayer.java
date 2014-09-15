package com.techmafia.mcmods.KinetiCraft.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cofh.api.energy.EnergyStorage;

import com.techmafia.mcmods.KinetiCraft.inventory.EnderKineticEnergyCubeInventory;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

public class ExtendedPlayer implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = Reference.MOD_ID + "_ExtenedPlayer";
	private final EntityPlayer player;
	public EnderKineticEnergyCubeInventory inventory = new EnderKineticEnergyCubeInventory();
	public int enderEnergy;
	
	public ExtendedPlayer()
	{
		this.player = Minecraft.getMinecraft().thePlayer;
	}
	
	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}
	
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound nbt)
	{
		/*
		 * Save Ender Inventory
		 */
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.energyCores.length; ++i)
		{
			if (this.inventory.energyCores[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.inventory.energyCores[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
	
		nbt.setTag(this.EXT_PROP_NAME, nbttaglist);
		
		/*
		 * Save Ender Energy
		 */
		nbt.setInteger("enderEnergy", this.enderEnergy);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt)
	{
		/*
		 * Load Ender Inventory
		 */
		NBTTagList nbttaglist = nbt.getTagList(this.EXT_PROP_NAME, 10);
	       
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inventory.energyCores.length)
			{
				this.inventory.energyCores[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		
		/*
		 * Load Ender Energy
		 */
		this.enderEnergy = nbt.getInteger("enderEnergy");
	}

	@Override
	public void init(Entity entity, World world)
	{	
	}
}
