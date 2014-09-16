package com.techmafia.mcmods.KinetiCraft.items;

public class GoldKineticEnergyCore extends BaseKineticEnergyCore
{
	public GoldKineticEnergyCore()
	{
		super();
		
		this.setUnlocalizedName("goldKineticEnergyCore");
		
		this.energyFromJumping 				= 100;	//10;
		this.energyFromMoving 				= 200;	//50;
		this.energyFromUsing 				= 500;	//100;
		this.overChargeBuffer 				= 10;
		this.maxEnergy						= 100000;
		this.hasMultipleIcons				= true;
		this.damageFromOverChargeExplosion 	= 6.0f;
		this.maxExtract						= 500;	//80;
		
		this.setMaxDamage(this.maxEnergy);
	}
}
