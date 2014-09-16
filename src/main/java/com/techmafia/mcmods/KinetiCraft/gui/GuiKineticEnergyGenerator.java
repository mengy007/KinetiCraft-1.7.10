package com.techmafia.mcmods.KinetiCraft.gui;

import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiKineticEnergyGenerator extends GuiBaseEnergyCube
{
	public GuiKineticEnergyGenerator(Container container)
	{
		super(container);
		this.inventoryName		= "Kinetic Energy Generator";
		this.inventoryTexture 	= "kineticraft:textures/gui/woodenKineticEnergyCubeGui.png";
	}
}
