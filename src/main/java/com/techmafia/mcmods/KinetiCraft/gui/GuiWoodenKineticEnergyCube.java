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

	/*
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString("Wooden Kinetic Energy Cube", 7, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 7, 71, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
    	
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("kineticraft:textures/gui/woodenKineticEnergyCubeGui.png"));
        
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 6 * 18 + 17);
        this.drawTexturedModalRect(k, l + 6 * 18 + 17, 0, 125, this.xSize, 96);
    }
    */
}
