package com.techmafia.mcmods.KinetiCraft.items;

public class GoldKineticEnergyCore extends BaseKineticEnergyCore
{
	public GoldKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("goldKineticEnergyCore");
		
		this.energyFromJumping 				= 10;
		this.energyFromMoving 				= 50;
		this.energyFromUsing 				= 100;
		this.overChargeBuffer 				= 10;
		this.maxEnergy						= 100000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion 	= 6.0f;
		this.maxExtract						= 80;
		
		this.setMaxDamage(this.maxEnergy);
	}
}
