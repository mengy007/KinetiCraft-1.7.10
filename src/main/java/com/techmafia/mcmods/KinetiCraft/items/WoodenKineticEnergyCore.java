package com.techmafia.mcmods.KinetiCraft.items;

public class WoodenKineticEnergyCore extends BaseKineticEnergyCore
{
	public WoodenKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("woodenKineticEnergyCore");
		
		this.energyFromJumping				= 1;
		this.energyFromMoving				= 2;
		this.energyFromUsing				= 4;
		this.overChargeBuffer				= 2;
		this.maxEnergy						= 1000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion	= 1.0f;
		this.maxExtract						= 6;
		
		this.setMaxDamage(this.maxEnergy);
	}
}