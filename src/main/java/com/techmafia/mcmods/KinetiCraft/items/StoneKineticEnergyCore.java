package com.techmafia.mcmods.KinetiCraft.items;

public class StoneKineticEnergyCore extends BaseKineticEnergyCore
{
	public StoneKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("stoneKineticEnergyCore");
		
		this.energyFromJumping				= 5; 	//1;
		this.energyFromMoving				= 10; 	//2;
		this.energyFromUsing				= 20; 	//5;
		this.overChargeBuffer				= 4;
		this.maxEnergy						= 10000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion	= 1.5f;
		this.maxExtract						= 20; 	//10;
		
		this.setMaxDamage(this.maxEnergy);
	}
}
