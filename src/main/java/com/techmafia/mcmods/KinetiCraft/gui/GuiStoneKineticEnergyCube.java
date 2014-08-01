package com.techmafia.mcmods.KinetiCraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.techmafia.mcmods.KinetiCraft.containers.StoneKineticEnergyCubeContainer;
import com.techmafia.mcmods.KinetiCraft.tileentities.StoneKineticEnergyCubeTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStoneKineticEnergyCube extends GuiBaseEnergyCube
{
	public GuiStoneKineticEnergyCube(Container container)
	{
		super(container);
		this.inventoryName		= "Stone Kinetic Energy Cube";
		this.inventoryTexture 	= "kineticraft:textures/gui/stoneKineticEnergyCubeGui.png";
	}
}