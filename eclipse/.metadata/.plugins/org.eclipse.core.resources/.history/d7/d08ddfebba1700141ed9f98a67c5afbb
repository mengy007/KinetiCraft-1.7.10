package com.techmafia.mcmods.KinetiCraft;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.techmafia.mcmods.KinetiCraft.handlers.ConfigurationHandler;
import com.techmafia.mcmods.KinetiCraft.handlers.EnergyCubeGuiHandler;
import com.techmafia.mcmods.KinetiCraft.init.ModBlocks;
import com.techmafia.mcmods.KinetiCraft.init.ModItems;
import com.techmafia.mcmods.KinetiCraft.proxy.IProxy;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTOR_CLASS)
//@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class KinetiCraft {
	
	@Instance(Reference.MOD_ID)
	public static KinetiCraft instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	// define items
	public static Item WoodenKineticEnergyCore;
	public static Item StoneKineticEnergyCore;

	// define blocks
	public static Block WoodenKineticEnergyCube;
	public static Block StoneKineticEnergyCube;
	public static Block IronKineticEnergyCube;
	public static Block GoldKineticEnergyCube;
	public static Block DiamondKineticEnergyCube;
	
	// define tile entities
	public static TileEntity BasicKineticEnergyCubeTileEntity;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent preInitEvent) {
		/* Config */
		ConfigurationHandler.init(preInitEvent.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		/* Init Items */
		ModItems.init();
		
		/*
		WoodenKineticEnergyCore = new WoodenKineticEnergyCore();
		WoodenKineticEnergyCore.setMaxStackSize(1).setUnlocalizedName("BasicKineticEnergyCubeItem").setCreativeTab(CreativeTabs.tabRedstone).setTextureName("KinetiCraft:woodenKineticEnergyCore");
		*/
		
		//StoneKineticEnergyCore = new StoneKineticEnergyCore()
		
		/* Register Items */
		//GameRegistry.registerItem(WoodenKineticEnergyCore, "WoodenKineticEnergyCore");

		/* Init blocks */
		ModBlocks.init();

		LogHelper.info("Pre Initialization Complete!");
		
		//WoodenKineticEnergyCube = new WoodenKineticEnergyCube( Material.wood).setBlockName("WoodenKineticEnergyCube").setCreativeTab(CreativeTabs.tabRedstone).setBlockTextureName("KinetiCraft:woodenKineticEnergyCube");
		//StoneKineticEnergyCube = new StoneKineticEnergyCube(Material.wood).setBlockName("StoneKineticEnergyCube").setCreativeTab(CreativeTabs.tabRedstone).setBlockTextureName("KinetiCraft:stoneKineticEnergyCube0");
		
		/* Register blocks */
		//GameRegistry.registerBlock(WoodenKineticEnergyCube, Reference.MOD_ID + WoodenKineticEnergyCube.getUnlocalizedName().substring(5));
		//GameRegistry.registerBlock(StoneKineticEnergyCube, Reference.MOD_ID + StoneKineticEnergyCube.getUnlocalizedName().substring(5));

		/* Register tile entities */
		proxy.registerTileEntities();
		//GameRegistry.registerTileEntity(WoodenKineticEnergyCubeTileEntity.class, "WoodenKineticEnergyCubeTileEntity");
		//GameRegistry.registerTileEntity(StoneKineticEnergyCubeTileEntity.class, "StoneKineticEnergyCubeTileEntity");
		
		/* Register GUI stuff */
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new EnergyCubeGuiHandler());
		
		/* Items Crafting Recipes */
		GameRegistry.addRecipe(new ItemStack(WoodenKineticEnergyCore, 1), new Object[]{
			" W ",
			"WRW",
			" W ",
			'W', Blocks.planks,
			'R', Items.redstone
		});

		/* Blocks Crafting Recipes */
		GameRegistry.addRecipe(new ItemStack(WoodenKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Blocks.planks,
			'L', Blocks.lever
		});
		/*
		GameRegistry.addRecipe(new ItemStack(StoneKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Block.planks,
			'L', Block.lever
		});
		GameRegistry.addRecipe(new ItemStack(IronKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Block.planks,
			'L', Block.lever
		});
		GameRegistry.addRecipe(new ItemStack(GoldKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Block.planks,
			'L', Block.lever
		});
		GameRegistry.addRecipe(new ItemStack(DiamondKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Block.planks,
			'L', Block.lever
		});
		*/
	}
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		// init blocks
		
		//NetworkRegistry.instance().registerGuiHandler(this.instance, new WoodenKineticEnergyCubeGuiHandler());
		
		//GameRegistry.registerTileEntity(WoodenKineticEnergyCubeTileEntity.class, "WoodenKineticEnergyCubeTileEntity");
		//GameRegistry.registerTileEntity(StoneKineticEnergyCubeTileEntity.class, "StoneKineticEnergyCubeTileEntity");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		LogHelper.info("Init event complete.");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Post Init event complete.");
	}
}