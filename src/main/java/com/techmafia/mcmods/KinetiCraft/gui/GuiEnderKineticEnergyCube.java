package com.techmafia.mcmods.KinetiCraft.gui;

import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnderKineticEnergyCube extends GuiBaseEnergyCube
{
	public GuiEnderKineticEnergyCube(Container container)
	{
		super(container);
		this.inventoryName		= "Ender Kinetic Energy Cube";
		this.inventoryTexture 	= "kineticraft:textures/gui/enderKineticEnergyCubeGui.png";
	}
}
