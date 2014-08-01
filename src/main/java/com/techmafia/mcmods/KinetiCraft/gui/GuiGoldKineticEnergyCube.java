package com.techmafia.mcmods.KinetiCraft.gui;

import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGoldKineticEnergyCube extends GuiBaseEnergyCube
{
	public GuiGoldKineticEnergyCube(Container container)
	{
		super(container);
		this.inventoryName		= "Gold Kinetic Energy Cube";
		this.inventoryTexture 	= "kineticraft:textures/gui/goldKineticEnergyCubeGui.png";
	}
}