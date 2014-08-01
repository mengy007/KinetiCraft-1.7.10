package com.techmafia.mcmods.KinetiCraft.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.containers.IronKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.containers.KineticFurnaceContainer;
import com.techmafia.mcmods.KinetiCraft.containers.StoneKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.containers.WoodenKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.gui.GuiIronKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.gui.GuiKineticFurnace;
import com.techmafia.mcmods.KinetiCraft.gui.GuiStoneKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.gui.GuiWoodenKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.tileentities.IronKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.KineticFurnaceTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler
{
	public GuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(KinetiCraft.instance, this);
	}
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z)
	{	
		TileEntity te = world.getTileEntity(x, y, z);
		
		if (te != null)
		{
			LogHelper.info("getServerGuiElement ID: " + ID);
			
			if (ID == 0 && te instanceof WoodenKineticEnergyCubeTileEntity)
			{
				return new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te);
			}
			if (ID == 1 && te instanceof StoneKineticEnergyCubeTileEntity)
			{
				return new StoneKineticEnergyCubeContainer(player.inventory, (StoneKineticEnergyCubeTileEntity)te);
			}
			if (ID == 2 && te instanceof IronKineticEnergyCubeTileEntity)
			{
				return new IronKineticEnergyCubeContainer(player.inventory, (IronKineticEnergyCubeTileEntity)te);
			}
			if (ID == 5 && te instanceof KineticFurnaceTileEntity)
			{
				return new KineticFurnaceContainer(player.inventory, (KineticFurnaceTileEntity)te);
			}
		}
		else
		{
			LogHelper.error("Server: No tile entity found at " + x + "," + y + "," + z + ".");
		}
		
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		LogHelper.info("getClientGuiElement ID: " + ID);
		
		if (te != null)
		{
			LogHelper.info("Tile Entity is there!");
			
			if (ID == 0 && te instanceof WoodenKineticEnergyCubeTileEntity)
			{
				return new GuiWoodenKineticEnergyCube(new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te));
			}
			if (ID == 1 && te instanceof StoneKineticEnergyCubeTileEntity)
			{
				return new GuiStoneKineticEnergyCube(new StoneKineticEnergyCubeContainer(player.inventory, (StoneKineticEnergyCubeTileEntity)te));
			}
			if (ID == 2 && te instanceof IronKineticEnergyCubeTileEntity)
			{
				return new GuiIronKineticEnergyCube(new IronKineticEnergyCubeContainer(player.inventory, (IronKineticEnergyCubeTileEntity)te));
			}
			if (ID == 5 && te instanceof KineticFurnaceTileEntity)
			{
				return new GuiKineticFurnace(new KineticFurnaceContainer(player.inventory, (KineticFurnaceTileEntity)te));
			}
		}
		else
		{
			LogHelper.error("Client: No tile entity found at " + x + "," + y + "," + z + ".");
		}
		
		return null;
	}
}
