package com.techmafia.mcmods.KinetiCraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.techmafia.mcmods.KinetiCraft.blocks.KineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.blocks.conduits.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static final KineticEnergyCube woodenKineticEnergyCube 		= new KineticEnergyCube(1);
	public static final KineticEnergyCube stoneKineticEnergyCube 		= new KineticEnergyCube(2);
	public static final KineticEnergyCube ironKineticEnergyCube 		= new KineticEnergyCube(3);
	public static final KineticEnergyCube goldKineticEnergyCube 		= new KineticEnergyCube(4);
	public static final KineticEnergyCube enderKineticEnergyCube 		= new KineticEnergyCube(5);
	
	public static final KineticEnergyConduit kineticEnergyConduit		= new KineticEnergyConduit();
	
	//public static final KineticFurnace kineticFurnace 				= new KineticFurnace();
	
	public static void init()
	{
		// Blocks
		GameRegistry.registerBlock(woodenKineticEnergyCube, "woodenKineticEnergyCube");
		GameRegistry.registerBlock(stoneKineticEnergyCube, "StoneKineticEnergyCube");
		GameRegistry.registerBlock(ironKineticEnergyCube, "IronKineticEnergyCube");
		GameRegistry.registerBlock(goldKineticEnergyCube, "GoldKineticEnergyCube");
		GameRegistry.registerBlock(enderKineticEnergyCube, "EnderKineticEnergyCube");
		
		// Conduits
		GameRegistry.registerBlock(kineticEnergyConduit, "kineticEnergyConduit");
		
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
		GameRegistry.addRecipe(new ItemStack(kineticEnergyConduit, 1), new Object[]{
			"CBC",
			'C', new ItemStack(ModItems.woodenKineticEnergyCore, 1),
			'B', Blocks.planks
		});
		GameRegistry.addRecipe(new ItemStack(kineticEnergyConduit, 2), new Object[]{
			"CBC",
			'C', new ItemStack(ModItems.stoneKineticEnergyCore, 1),
			'B', Blocks.stone
		});
		GameRegistry.addRecipe(new ItemStack(kineticEnergyConduit, 4), new Object[]{
			"CBC",
			'C', new ItemStack(ModItems.ironKineticEnergyCore, 1),
			'B', Blocks.iron_block
		});
		GameRegistry.addRecipe(new ItemStack(kineticEnergyConduit, 8), new Object[]{
			"CBC",
			'C', new ItemStack(ModItems.goldKineticEnergyCore, 1),
			'B', Blocks.gold_block
		});
		GameRegistry.addRecipe(new ItemStack(kineticEnergyConduit, 16), new Object[]{
			"CBC",
			'C', new ItemStack(ModItems.goldKineticEnergyCore, 1),
			'B', Items.ender_pearl
		});
	}
}
