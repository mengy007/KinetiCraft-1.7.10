package com.techmafia.mcmods.KinetiCraft.items;

public class IronKineticEnergyCore extends BaseKineticEnergyCore
{
	public IronKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("ironKineticEnergyCore");
		
		this.energyFromJumping 				= 25;
		this.energyFromMoving 				= 50;
		this.energyFromUsing 				= 100;
		this.overChargeBuffer				= 8;
		this.maxEnergy						= 50000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion	= 2.0f;
		this.maxExtract						= 100; //40;
		
		this.setMaxDamage(this.maxEnergy);
	}
}
