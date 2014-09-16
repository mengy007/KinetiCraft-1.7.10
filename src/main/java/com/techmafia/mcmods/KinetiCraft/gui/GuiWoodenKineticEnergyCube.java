package com.techmafia.mcmods.KinetiCraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiWoodenKineticEnergyCube extends GuiBaseEnergyCube
{
	public GuiWoodenKineticEnergyCube(Container container)
	{
		super(container);
		this.inventoryName		= "Wooden Kinetic Energy Cube";
		this.inventoryTexture 	= "kineticraft:textures/gui/woodenKineticEnergyCubeGui.png";
	}
}
