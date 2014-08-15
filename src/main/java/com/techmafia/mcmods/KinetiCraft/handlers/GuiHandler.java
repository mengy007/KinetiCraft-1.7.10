package com.techmafia.mcmods.KinetiCraft.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.containers.EnderKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.containers.GoldKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.containers.IronKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.containers.KineticFurnaceContainer;
import com.techmafia.mcmods.KinetiCraft.containers.StoneKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.containers.WoodenKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.gui.GuiEnderKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.gui.GuiGoldKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.gui.GuiIronKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.gui.GuiKineticFurnace;
import com.techmafia.mcmods.KinetiCraft.gui.GuiStoneKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.gui.GuiWoodenKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.player.ExtendedPlayer;
import com.techmafia.mcmods.KinetiCraft.tileentities.EnderKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.GoldKineticEnergyCubeTileEntity;
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
		if (ID == 4)
		{
			ExtendedPlayer ep = ExtendedPlayer.get(player);

			return new EnderKineticEnergyCubeContainer(player.inventory, ep.inventory);
		}
		else
		{
			TileEntity te = world.getTileEntity(x, y, z);
			
			if (te != null)
			{
				LogHelper.info("getServerGuiElement ID: " + ID);
				
				switch (ID)
				{
				case 0:
					return new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te);
				case 1:
					return new StoneKineticEnergyCubeContainer(player.inventory, (StoneKineticEnergyCubeTileEntity)te);
				case 2:
					return new IronKineticEnergyCubeContainer(player.inventory, (IronKineticEnergyCubeTileEntity)te);
				case 3:
					return new GoldKineticEnergyCubeContainer(player.inventory, (GoldKineticEnergyCubeTileEntity)te);
				case 5:
					return new KineticFurnaceContainer(player.inventory, (KineticFurnaceTileEntity)te);
				}
			}
			else
			{
				LogHelper.error("Server: No tile entity found at " + x + "," + y + "," + z + ".");
			}
		}
		
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == 4)
		{
			ExtendedPlayer ep = ExtendedPlayer.get(player);
			
			return new GuiEnderKineticEnergyCube(new EnderKineticEnergyCubeContainer(player.inventory, ep.inventory));
		}
		else
		{
			TileEntity te = world.getTileEntity(x, y, z);
	
			LogHelper.info("getClientGuiElement ID: " + ID);
			
			if (te != null)
			{
				LogHelper.info("Tile Entity is there!");
				
				switch (ID)
				{
				case 0:
					return new GuiWoodenKineticEnergyCube(new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te));
				case 1:
					return new GuiStoneKineticEnergyCube(new StoneKineticEnergyCubeContainer(player.inventory, (StoneKineticEnergyCubeTileEntity)te));
				case 2:
					return new GuiIronKineticEnergyCube(new IronKineticEnergyCubeContainer(player.inventory, (IronKineticEnergyCubeTileEntity)te));
				case 3:
					return new GuiGoldKineticEnergyCube(new GoldKineticEnergyCubeContainer(player.inventory, (GoldKineticEnergyCubeTileEntity)te));
				case 5:
					return new GuiKineticFurnace(new KineticFurnaceContainer(player.inventory, (KineticFurnaceTileEntity)te));
				}
			}
			else
			{
				LogHelper.error("Client: No tile entity found at " + x + "," + y + "," + z + ".");
			}
		}
		
		return null;
	}
}
