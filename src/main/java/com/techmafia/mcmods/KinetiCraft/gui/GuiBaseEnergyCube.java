package com.techmafia.mcmods.KinetiCraft.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiBaseEnergyCube extends GuiContainer
{
	protected String inventoryName = "";
	protected String inventoryTexture = "";
	
	public GuiBaseEnergyCube(Container container)
	{
		super(container);
	}

	/**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRendererObj.drawString(this.inventoryName, 7, 6, 4210752);
        this.fontRendererObj.drawString("Inventory", 7, 71, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
    	
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(this.inventoryTexture));
        
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 6 * 18 + 17);
        this.drawTexturedModalRect(k, l + 6 * 18 + 17, 0, 125, this.xSize, 96);
    }
}
