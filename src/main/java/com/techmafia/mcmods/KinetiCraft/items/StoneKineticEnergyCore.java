package com.techmafia.mcmods.KinetiCraft.items;

public class StoneKineticEnergyCore extends BaseKineticEnergyCore
{
	public StoneKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("stoneKineticEnergyCore");
		
		this.energyFromJumping				= 1;
		this.energyFromMoving				= 2;
		this.energyFromUsing				= 5;
		this.overChargeBuffer				= 4;
		this.maxEnergy						= 1000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion	= 1.5f;
		this.maxExtract						= 5;
		
		this.setMaxDamage(this.maxEnergy);
	}
}
