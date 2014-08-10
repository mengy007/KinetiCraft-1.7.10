package com.techmafia.mcmods.KinetiCraft.init;

import net.minecraft.entity.DataWatcher;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.techmafia.mcmods.KinetiCraft.items.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
	public static final WoodenKineticEnergyCore woodenKineticEnergyCore = new WoodenKineticEnergyCore();
	public static final StoneKineticEnergyCore stoneKineticEnergyCore = new StoneKineticEnergyCore();
	public static final IronKineticEnergyCore ironKineticEnergyCore = new IronKineticEnergyCore();
	public static final GoldKineticEnergyCore goldKineticEnergyCore = new GoldKineticEnergyCore();
	//public static final EnderKineticEnergyCore enderKineticEnergyCore = new EnderKineticEnergyCore();
	
	public static void init()
	{
		/* Register Items */
		GameRegistry.registerItem(woodenKineticEnergyCore, "WoodenKineticEnergyCore");
		GameRegistry.registerItem(stoneKineticEnergyCore, "StoneKineticEnergyCore");
		GameRegistry.registerItem(ironKineticEnergyCore, "IronKineticEnergyCore");
		GameRegistry.registerItem(goldKineticEnergyCore, "GoldKineticEnergyCore");
		//GameRegistry.registerItem(enderKineticEnergyCore, "EnderKineticEnergyCore");
		
		ItemStack woodenCoreEmpty = new ItemStack(woodenKineticEnergyCore, 1);
		woodenCoreEmpty.setItemDamage(woodenCoreEmpty.getMaxDamage());
		
		ItemStack stoneCoreEmpty = new ItemStack(stoneKineticEnergyCore, 1);
		stoneCoreEmpty.setItemDamage(stoneCoreEmpty.getMaxDamage());
		
		ItemStack ironCoreEmpty = new ItemStack(ironKineticEnergyCore, 1);
		ironCoreEmpty.setItemDamage(ironCoreEmpty.getMaxDamage());
		
		ItemStack goldCoreEmpty = new ItemStack(goldKineticEnergyCore, 1);
		goldCoreEmpty.setItemDamage(goldCoreEmpty.getMaxDamage());
		
		//ItemStack enderCoreEmpty = new ItemStack()
		
		/* Register Item Recipes */
		GameRegistry.addRecipe(woodenCoreEmpty, new Object[]{
			" W ",
			"WRW",
			" W ",
			'W', Blocks.planks,
			'R', Blocks.stone_button
		});
		GameRegistry.addRecipe(stoneCoreEmpty, new Object[]{
			" S ",
			"SCS",
			" S ",
			'S', Blocks.stone,
			'C', new ItemStack(woodenKineticEnergyCore, 1 ,OreDictionary.WILDCARD_VALUE)
		});
		GameRegistry.addRecipe(ironCoreEmpty, new Object[]{
			" I ",
			"ICI",
			" I ",
			'I', Items.iron_ingot,
			'C', Items.redstone
		});
		GameRegistry.addRecipe(goldCoreEmpty, new Object[]{
			" C ",
			"GRG",
			" G ",
			'G', Items.gold_ingot,
			'R', Items.redstone,
			'C', new ItemStack(ironKineticEnergyCore, 1 ,OreDictionary.WILDCARD_VALUE)
		});
		/*
		// Disabled due to huge bug
		GameRegistry.addRecipe(new ItemStack(enderKineticEnergyCore, 1), new Object[]{
			" G ",
			"GEG",
			" G ",
			'E', Items.ender_pearl,
			'G', new ItemStack(goldKineticEnergyCore, 1 ,OreDictionary.WILDCARD_VALUE)
		});
		*/
	}
}
