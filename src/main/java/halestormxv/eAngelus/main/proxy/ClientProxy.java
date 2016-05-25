package halestormxv.eAngelus.main.proxy;

import halestormxv.eAngelus.achievements.EA_Achievements;
import halestormxv.eAngelus.crafting.EARecipes;
import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		eAngelusItems.initItems();
		eAngelusBlocks.initBlocks();
		EARecipes.initRecipes();
		EA_Achievements.AchievementRegistry();
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		eAngelusItems.registerRenders();
		eAngelusBlocks.registerRenders();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);	
	}
		
}
