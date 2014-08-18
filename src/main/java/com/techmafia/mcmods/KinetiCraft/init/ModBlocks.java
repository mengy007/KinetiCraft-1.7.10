package com.techmafia.mcmods.KinetiCraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.techmafia.mcmods.KinetiCraft.blocks.KineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.blocks.KineticEnergyConduit;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static final KineticEnergyCube woodenKineticEnergyCube 		= new KineticEnergyCube(1);
	public static final KineticEnergyCube stoneKineticEnergyCube 		= new KineticEnergyCube(2);
	public static final KineticEnergyCube ironKineticEnergyCube 		= new KineticEnergyCube(3);
	public static final KineticEnergyCube goldKineticEnergyCube 		= new KineticEnergyCube(4);
	public static final KineticEnergyCube enderKineticEnergyCube 		= new KineticEnergyCube(5);
	
	public static final KineticEnergyConduit woodenKineticEnergyConduit	= new KineticEnergyConduit(1);
	
	//public static final KineticFurnace kineticFurnace 				= new KineticFurnace();
	
	public static void init()
	{
		GameRegistry.registerBlock(woodenKineticEnergyCube, "woodenKineticEnergyCube");
		GameRegistry.registerBlock(stoneKineticEnergyCube, "StoneKineticEnergyCube");
		GameRegistry.registerBlock(ironKineticEnergyCube, "IronKineticEnergyCube");
		GameRegistry.registerBlock(goldKineticEnergyCube, "GoldKineticEnergyCube");
		GameRegistry.registerBlock(enderKineticEnergyCube, "EnderKineticEnergyCube");
		
		GameRegistry.registerBlock(woodenKineticEnergyConduit, "woodenKineticEnergyConduit");
		
		//GameRegistry.registerBlock(kineticFurnace, "kineticFurnace");
		
		/* Blocks Crafting Recipes */
		GameRegistry.addRecipe(new ItemStack(woodenKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Blocks.planks,
			'L', Blocks.lever
		});
		
		GameRegistry.addRecipe(new ItemStack(stoneKineticEnergyCube, 1), new Object[]{
			"AAA",
			"ABA",
			"AAA",
			'A', Blocks.stone,
			'B', new ItemStack(woodenKineticEnergyCube, 1, OreDictionary.WILDCARD_VALUE)
		});
		
		GameRegistry.addRecipe(new ItemStack(ironKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Items.iron_ingot,
			'L', new ItemStack(stoneKineticEnergyCube, 1, OreDictionary.WILDCARD_VALUE)
		});
		
		GameRegistry.addRecipe(new ItemStack(goldKineticEnergyCube, 1), new Object[]{
			"WWW",
			"WLW",
			"WWW",
			'W', Items.gold_ingot,
			'L', new ItemStack(ironKineticEnergyCube, 1, OreDictionary.WILDCARD_VALUE)
		});
		
		GameRegistry.addRecipe(new ItemStack(enderKineticEnergyCube, 1), new Object[]{
			"OWO",
			"WRW",
			"OWO",
			'W', Items.ender_pearl,
			'O', Blocks.obsidian,
			'R', Items.redstone
		});
		
		/* Energy Conduits */
		GameRegistry.addRecipe(new ItemStack(woodenKineticEnergyConduit, 3), new Object[]{
			"WBW",
			'W', Blocks.planks,
			'B', Blocks.stone_button 
		});
	}
}
