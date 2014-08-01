package com.techmafia.mcmods.KinetiCraft.items;

import cofh.api.energy.EnergyStorage;

public class WoodenKineticEnergyCore extends BaseKineticEnergyCore
{
	public WoodenKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("woodenKineticEnergyCore");
		
		this.energyFromJumping				= 1;
		this.energyFromMoving				= 2;
		this.energyFromUsing				= 5;
		this.overChargeBuffer				= 2;
		this.maxEnergy						= 500;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion	= 1.0f;
		this.maxExtract						= 2;
		
		this.setMaxDamage(this.maxEnergy);
	}
}