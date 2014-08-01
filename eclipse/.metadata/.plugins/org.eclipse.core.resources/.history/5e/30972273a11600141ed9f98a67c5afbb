package com.techmafia.mcmods.KinetiCraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.techmafia.mcmods.KinetiCraft.KinetiCraft;
import com.techmafia.mcmods.KinetiCraft.blocks.StoneKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.blocks.WoodenKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;
import com.techmafia.mcmods.KinetiCraft.tileentities.WoodenKineticEnergyCubeTileEntity;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class EnergyCubeGuiHandler implements IGuiHandler {

	public EnergyCubeGuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(KinetiCraft.instance, this);
	}
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if (te != null) {
			if (ID == 0 && te instanceof WoodenKineticEnergyCubeTileEntity) {
				return new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te);
			}
			if (ID == 1 && te instanceof StoneKineticEnergyCubeTileEntity) {
				return new StoneKineticEnergyCubeContainer(player.inventory, (StoneKineticEnergyCubeTileEntity)te);
			}
		} else {
			player.addChatMessage("Error: no tile entity found at that location.");
		}
		
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if (te != null) {
			if (ID == 0 && te instanceof WoodenKineticEnergyCubeTileEntity) {
				return new GuiWoodenKineticEnergyCube(new WoodenKineticEnergyCubeContainer(player.inventory, (WoodenKineticEnergyCubeTileEntity)te));
			}

			if (ID == 1 && te instanceof StoneKineticEnergyCubeTileEntity) {
				player.addChatMessage("Open");
				return new GuiStoneKineticEnergyCube(player.inventory, (StoneKineticEnergyCubeTileEntity)te);
			}
		} else {
			player.addChatMessage("Error: no tile entity found at that location.");
		}
		
		return null;
	}

}
