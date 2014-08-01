package com.techmafia.mcmods.KinetiCraft.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.blocks.StoneKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.blocks.WoodenKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.gui.GuiStoneKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.gui.GuiWoodenKineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class EnergyCubeGuiHandler implements IGuiHandler {

	public EnergyCubeGuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(KinetiCraft.instance, this);
	}
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		
		TileEntity te = world.getTileEntity(x, y, z);
		
		if (te != null) {
			if (ID == 0 && te instanceof WoodenKineticEnergyCubeTileEntity) {
				return new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te);
			}
			if (ID == 1 && te instanceof StoneKineticEnergyCubeTileEntity) {
				return new StoneKineticEnergyCubeContainer(player.inventory, (StoneKineticEnergyCubeTileEntity)te);
			}
		} else {
			//player.addChatMessage(new ChatComponentText("Error: no tile entity found at that location."));
			LogHelper.error("Server: No tile entity found at " + x + "," + y + "," + z + ".");
		}
		
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		//TileEntity te = world.getTileEntity(x, y, z);
		WoodenKineticEnergyCubeTileEntity te = (WoodenKineticEnergyCubeTileEntity)world.getTileEntity(x, y, z);

		if (te != null) {// && te instanceof TileEntity) {
			if (ID == 0) {
				return new GuiWoodenKineticEnergyCube(new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te));
			}

			/*
			if (ID == 1) {
				player.addChatMessage(new ChatComponentText("Open"));
				return new GuiStoneKineticEnergyCube(player.inventory, (StoneKineticEnergyCubeTileEntity)te);
			}
			*/
		} else {
			//player.addChatMessage(new ChatComponentText("Error: no tile entity found at that location."));
			LogHelper.error("Client: No tile entity found at " + x + "," + y + "," + z + ".");
		}
		
		return null;
	}

}
