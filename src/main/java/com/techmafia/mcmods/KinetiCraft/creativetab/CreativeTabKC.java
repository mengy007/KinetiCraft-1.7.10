package com.techmafia.mcmods.KinetiCraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.techmafia.mcmods.KinetiCraft.init.ModItems;
import com.techmafia.mcmods.KinetiCraft.reference.Reference;

public class CreativeTabKC
{
	public static final CreativeTabs KC_TAB = new CreativeTabs(Reference.MOD_ID)
	{
		@Override
		public Item getTabIconItem()
		{
			return ModItems.woodenKineticEnergyCore;
		}
		
		@Override
		public String getTranslatedTabLabel()
		{
			return "KinetiCraft";
		}
	};
}
