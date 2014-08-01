package com.techmafia.mcmods.KinetiCraft.init;

import com.techmafia.mcmods.KinetiCraft.blocks.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static final WoodenKineticEnergyCube woodenKineticEnergyCube 	= new WoodenKineticEnergyCube();
	public static final StoneKineticEnergyCube stoneKineticEnergyCube 		= new StoneKineticEnergyCube();
	public static final IronKineticEnergyCube ironKineticEnergyCube 		= new IronKineticEnergyCube();
	public static final GoldKineticEnergyCube goldKineticEnergyCube 		= new GoldKineticEnergyCube();
	//public static final KineticFurnace kineticFurnace 						= new KineticFurnace();
	
	public static void init()
	{
		GameRegistry.registerBlock(woodenKineticEnergyCube, "woodenKineticEnergyCube");
		GameRegistry.registerBlock(stoneKineticEnergyCube, "StoneKineticEnergyCube");
		GameRegistry.registerBlock(ironKineticEnergyCube, "IronKineticEnergyCube");
		GameRegistry.registerBlock(goldKineticEnergyCube, "GoldKineticEnergyCube");
		//GameRegistry.registerBlock(kineticFurnace, "kineticFurnace");
	}
}
