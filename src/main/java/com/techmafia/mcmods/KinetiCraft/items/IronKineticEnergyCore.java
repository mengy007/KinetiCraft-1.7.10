package com.techmafia.mcmods.KinetiCraft.items;

public class IronKineticEnergyCore extends BaseKineticEnergyCore
{
	public IronKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("ironKineticEnergyCore");
		
		this.energyFromJumping 				= 2;
		this.energyFromMoving 				= 4;
		this.energyFromUsing 				= 10;
		this.overChargeBuffer				= 4;
		this.maxEnergy						= 5000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion	= 2.0f;
		this.maxExtract						= 10;
		
		this.setMaxDamage(this.maxEnergy);
	}
}
