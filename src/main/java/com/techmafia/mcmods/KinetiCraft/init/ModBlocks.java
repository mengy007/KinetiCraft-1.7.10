package com.techmafia.mcmods.KinetiCraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.techmafia.mcmods.KinetiCraft.blocks.KineticBlock;
import com.techmafia.mcmods.KinetiCraft.blocks.KineticEnergyCube;
import com.techmafia.mcmods.KinetiCraft.blocks.KineticEnergyGenerator;
import com.techmafia.mcmods.KinetiCraft.blocks.conduits.KineticEnergyConduit;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks
{
	public static final KineticBlock kineticBlock						= new KineticBlock();
	public static final KineticEnergyCube woodenKineticEnergyCube 		= new KineticEnergyCube(1);
	public static final KineticEnergyCube stoneKineticEnergyCube 		= new KineticEnergyCube(2);
	public static final KineticEnergyCube ironKineticEnergyCube 		= new KineticEnergyCube(3);
	public static final KineticEnergyCube goldKineticEnergyCube 		= new KineticEnergyCube(4);
	public static final KineticEnergyCube enderKineticEnergyCube 		= new KineticEnergyCube(5);
	
	public static final KineticEnergyConduit kineticEnergyConduit		= new KineticEnergyConduit();
	
	public static final KineticEnergyGenerator kineticEnergyGenerator	= new KineticEnergyGenerator();
	
	public static void init()
	{
		// Blocks
		GameRegistry.registerBlock(kineticBlock, "kineticBlock");
		GameRegistry.registerBlock(woodenKineticEnergyCube, "woodenKineticEnergyCube");
		GameRegistry.registerBlock(stoneKineticEnergyCube, "StoneKineticEnergyCube");
		GameRegistry.registerBlock(ironKineticEnergyCube, "IronKineticEnergyCube");
		GameRegistry.registerBlock(goldKineticEnergyCube, "GoldKineticEnergyCube");
		GameRegistry.registerBlock(enderKineticEnergyCube, "EnderKineticEnergyCube");
		
		// Conduits
		GameRegistry.registerBlock(kineticEnergyConduit, "kineticEnergyConduit");
		
		// Generators
		GameRegistry.registerBlock(kineticEnergyGenerator, "kineticEnergyGenerator");
		
		/* Blocks Crafting Recipes */
		GameRegistry.addShapelessRecipe(new ItemStack(kineticBlock, 2), new Object[]{
			Blocks.sand,
			Blocks.dirt
		});
		
		GameRegistry.addRecipe(new ItemStack(woodenKineticEnergyCube, 1), new Object[]{
			"WKW",
			"KLK",
			"WKW",
			'W', Blocks.planks,
			'L', Blocks.lever,
			'K', ModItems.kineticIngot
		});
		
		GameRegistry.addRecipe(new ItemStack(stoneKineticEnergyCube, 1), new Object[]{
			"AKA",
			"KBK",
			"AKA",
			'A', Blocks.stone,
			'B', new ItemStack(woodenKineticEnergyCube, 1, OreDictionary.WILDCARD_VALUE),
			'K', ModItems.kineticIngot
		});
		
		GameRegistry.addRecipe(new ItemStack(ironKineticEnergyCube, 1), new Object[]{
			"WKW",
			"KLK",
			"WKW",
			'W', Items.iron_ingot,
			'L', new ItemStack(stoneKineticEnergyCube, 1, OreDictionary.WILDCARD_VALUE),
			'K', ModItems.kineticIngot
		});
		
		GameRegistry.addRecipe(new ItemStack(goldKineticEnergyCube, 1), new Object[]{
			"WKW",
			"KLK",
			"WKW",
			'W', Items.gold_ingot,
			'L', new ItemStack(ironKineticEnergyCube, 1, OreDictionary.WILDCARD_VALUE),
			'K', ModItems.kineticIngot
		});
		
		GameRegistry.addRecipe(new ItemStack(enderKineticEnergyCube, 1), new Object[]{
			"WKW",
			"KRK",
			"WKW",
			'W', Items.ender_pearl,
			'K', ModItems.kineticIngot,
			'R', Items.redstone
		});
		
		/* Energy Conduits - NEED TO FIX */
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
