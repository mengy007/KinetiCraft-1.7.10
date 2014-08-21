package com.techmafia.mcmods.KinetiCraft.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.techmafia.mcmods.KinetiCraft.tileentities.KineticEnergyConduitTileEntity;

public class KineticEnergyConduitTileEntityRenderer extends TileEntitySpecialRenderer
{
	private ResourceLocation texture = new ResourceLocation("kineticraft:textures/blocks/woodenPowerConduit.png");
	private float pixel = 1f/16f;
	private float texturePixel = 1f/32f;
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double translationX, double translationY, double translationZ, float f)
	{
		GL11.glTranslated(translationX, translationY, translationZ);
		this.bindTexture(this.texture);
		GL11.glDisable(GL11.GL_LIGHTING);
		{
			KineticEnergyConduitTileEntity conduit = (KineticEnergyConduitTileEntity) tileEntity;

			if ( ! conduit.onlyOneOpposite(conduit.connections))
			{
				this.drawCore(tileEntity);
				
				for (int i = 0; i < 6; i++)
				{
					if (conduit.connections[i] != null)
					{
						this.drawConnector(conduit.connections[i]);
					}
				}

			}
			else
			{
				for (int i = 0; i < conduit.connections.length; i++)
				{
					if (conduit.connections[i] != null)
					{
						this.drawStraight(conduit.connections[i]);
						break;
					}
				}
			}			
		}

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-translationX, -translationY, -translationZ);
	}
	
	public void drawStraight(ForgeDirection dir)
	{
		Tessellator tes = Tessellator.instance;
		
		tes.startDrawingQuads();
		{
			GL11.glTranslatef(0.5f, 0.5f, 0.5f);
			
			if (dir == ForgeDirection.SOUTH || dir == ForgeDirection.NORTH)
			{
				GL11.glRotatef(90, 1, 0, 0);
			}
			else if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST)
			{
				GL11.glRotatef(90, 0, 0, 1);
			}

			GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
			
			tes.addVertexWithUV(1-9*this.pixel/2, 	0, 					1-9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	0, 					1-9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);

			tes.addVertexWithUV(9*this.pixel/2, 	0, 					9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	0, 					9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
		
			tes.addVertexWithUV(1-9*this.pixel/2, 	0, 					9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	0, 					1-9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);		

			tes.addVertexWithUV(9*this.pixel/2, 	0, 					1-9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);		
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	0, 					9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
		}
		tes.draw();
		
		GL11.glTranslatef(0.5f, 0.5f, 0.5f);
		
		if (dir == ForgeDirection.SOUTH || dir == ForgeDirection.NORTH)
		{
			GL11.glRotatef(-90, 1, 0, 0);
		}
		else if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST)
		{
			GL11.glRotatef(-90, 0, 0, 1);
		}

		GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
	}
	
	public void drawConnector(ForgeDirection dir)
	{
		Tessellator tes = Tessellator.instance;
		
		tes.startDrawingQuads();
		{
			GL11.glTranslatef(0.5f, 0.5f, 0.5f);
			
			if (dir == ForgeDirection.UP)
			{
				// ROTATE
			}
			else if (dir == ForgeDirection.DOWN)
			{
				GL11.glRotatef(180, 1, 0, 0);
			}
			else if (dir == ForgeDirection.SOUTH)
			{
				GL11.glRotatef(90, 1, 0, 0);
			}
			else if (dir == ForgeDirection.NORTH)
			{
				GL11.glRotatef(270, 1, 0, 0);
			}
			else if (dir == ForgeDirection.WEST)
			{
				GL11.glRotatef(-90, 0, 0, 1);
			}
			else if (dir == ForgeDirection.EAST)
			{
				GL11.glRotatef(-270, 0, 0, 1);
			}

			GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
			
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);

			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
		
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);		

			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);		
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					1-9*this.pixel/2, 	10*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1, 					9*this.pixel/2, 	10*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
		}
		tes.draw();
		
		GL11.glTranslatef(0.5f, 0.5f, 0.5f);
		
		if (dir == ForgeDirection.UP)
		{
			// ROTATE
		}
		else if (dir == ForgeDirection.DOWN)
		{
			GL11.glRotatef(-180, 1, 0, 0);
		}
		else if (dir == ForgeDirection.SOUTH)
		{
			GL11.glRotatef(-90, 1, 0, 0);
		}
		else if (dir == ForgeDirection.NORTH)
		{
			GL11.glRotatef(-270, 1, 0, 0);
		}
		else if (dir == ForgeDirection.WEST)
		{
			GL11.glRotatef(90, 0, 0, 1);
		}
		else if (dir == ForgeDirection.EAST)
		{
			GL11.glRotatef(270, 0, 0, 1);
		}

		GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
	}
	
	public void drawCore(TileEntity tileEntity)
	{
		Tessellator tes = Tessellator.instance;
		
		tes.startDrawingQuads();
		{
			tes.addVertexWithUV(1-9*this.pixel/2, 	9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	7*this.texturePixel);

			tes.addVertexWithUV(1-9*this.pixel/2, 	9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	7*this.texturePixel);

			tes.addVertexWithUV(9*this.pixel/2, 	9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	0*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	0*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);

			tes.addVertexWithUV(9*this.pixel/2, 	9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);

			/* Top */
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	0*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	1-9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);

			/* Bottom */
			tes.addVertexWithUV(9*this.pixel/2, 	9*this.pixel/2, 	1-9*this.pixel/2, 	7*this.texturePixel, 	7*this.texturePixel);
			tes.addVertexWithUV(9*this.pixel/2, 	9*this.pixel/2, 	9*this.pixel/2, 	7*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	9*this.pixel/2, 	9*this.pixel/2, 	0*this.texturePixel, 	0*this.texturePixel);
			tes.addVertexWithUV(1-9*this.pixel/2, 	9*this.pixel/2, 	1-9*this.pixel/2, 	0*this.texturePixel, 	7*this.texturePixel);
		}
		tes.draw();
	}
}
