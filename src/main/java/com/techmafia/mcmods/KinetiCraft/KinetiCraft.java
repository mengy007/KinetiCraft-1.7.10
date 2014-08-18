package com.techmafia.mcmods.KinetiCraft;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

import com.techmafia.mcmods.KinetiCraft.data.EnderKineticEnergyData;
import com.techmafia.mcmods.KinetiCraft.data.ExtendedPlayerData;
import com.techmafia.mcmods.KinetiCraft.handlers.ConfigurationHandler;
import com.techmafia.mcmods.KinetiCraft.handlers.EnderKineticEnergyDataHandler;
import com.techmafia.mcmods.KinetiCraft.handlers.ExtendedPlayerDataHandler;
import com.techmafia.mcmods.KinetiCraft.handlers.ExtendedPlayerHandler;
import com.techmafia.mcmods.KinetiCraft.handlers.GuiHandler;
import com.techmafia.mcmods.KinetiCraft.init.ModBlocks;
import com.techmafia.mcmods.KinetiCraft.init.ModItems;
import com.techmafia.mcmods.KinetiCraft.proxy.CommonProxy;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;
import com.techmafia.mcmods.KinetiCraft.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTOR_CLASS)
//@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class KinetiCraft
{	
	@Instance(Reference.MOD_ID)
	public static KinetiCraft instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	// define tile entities
	public static TileEntity BasicKineticEnergyCubeTileEntity;
	
	public static SimpleNetworkWrapper network;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent preInitEvent)
	{
		/* Extended Plauer */
		MinecraftForge.EVENT_BUS.register(new ExtendedPlayerHandler());
		
		/* Config */
		ConfigurationHandler.init(preInitEvent.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		/* Init Items */
		ModItems.init();

		/* Init blocks */
		ModBlocks.init();
		
		/* Register tile entities */
		proxy.registerTileEntities();
		
		/* Regiister client stuff */
		proxy.registerClientStuff();
		
		/* Register GUI stuff */
		NetworkRegistry.INSTANCE.registerGuiHandler(KinetiCraft.instance, new GuiHandler());

		LogHelper.info("Pre Initialization Complete!");
	}
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		//MinecraftForge.EVENT_BUS.register(new ExtendedPlayer());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		/* Register network packet stuff */
		KinetiCraft.network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		KinetiCraft.network.registerMessage(EnderKineticEnergyDataHandler.class, EnderKineticEnergyData.class, 0, Side.CLIENT);
		KinetiCraft.network.registerMessage(ExtendedPlayerDataHandler.class, ExtendedPlayerData.class, 0, Side.CLIENT);

		LogHelper.info("Init event complete.");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Post Init event complete.");
	}
}