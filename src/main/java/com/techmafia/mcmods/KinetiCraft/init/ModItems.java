package com.techmafia.mcmods.KinetiCraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.techmafia.mcmods.KinetiCraft.items.GoldKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.IronKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.StoneKineticEnergyCore;
import com.techmafia.mcmods.KinetiCraft.items.WoodenKineticEnergyCore;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems
{
	public static final WoodenKineticEnergyCore woodenKineticEnergyCore = new WoodenKineticEnergyCore();
	public static final StoneKineticEnergyCore stoneKineticEnergyCore = new StoneKineticEnergyCore();
	public static final IronKineticEnergyCore ironKineticEnergyCore = new IronKineticEnergyCore();
	public static final GoldKineticEnergyCore goldKineticEnergyCore = new GoldKineticEnergyCore();
	
	public static void init()
	{
		/* Register Items */
		GameRegistry.registerItem(woodenKineticEnergyCore, "WoodenKineticEnergyCore");
		GameRegistry.registerItem(stoneKineticEnergyCore, "StoneKineticEnergyCore");
		GameRegistry.registerItem(ironKineticEnergyCore, "IronKineticEnergyCore");
		GameRegistry.registerItem(goldKineticEnergyCore, "GoldKineticEnergyCore");
		
		/* Register Item Recipes */
		GameRegistry.addRecipe(new ItemStack(woodenKineticEnergyCore, 1), new Object[]{
			" W ",
			"WRW",
			" W ",
			'W', Blocks.planks,
			'R', Items.redstone
		});
		GameRegistry.addRecipe(new ItemStack(stoneKineticEnergyCore, 1), new Object[]{
			" S ",
			"SCS",
			" S ",
			'S', Blocks.stone,
			'C', new ItemStack(woodenKineticEnergyCore, 1 ,OreDictionary.WILDCARD_VALUE)
		});
		GameRegistry.addRecipe(new ItemStack(ironKineticEnergyCore, 1), new Object[]{
			" I ",
			"ICI",
			" I ",
			'I', Items.iron_ingot,
			'C', new ItemStack(stoneKineticEnergyCore, 1 ,OreDictionary.WILDCARD_VALUE)
		});
		GameRegistry.addRecipe(new ItemStack(goldKineticEnergyCore, 1), new Object[]{
			" G ",
			"GCG",
			" G ",
			'G', Items.gold_ingot,
			'C', new ItemStack(ironKineticEnergyCore, 1 ,OreDictionary.WILDCARD_VALUE)
		});
	}
}
