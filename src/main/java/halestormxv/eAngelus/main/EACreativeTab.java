package halestormxv.eAngelus.main;

import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EACreativeTab extends CreativeTabs 
{

	public EACreativeTab(String string) 
	{
		super(string);
	}
	
	public Item getTabIconItem()
	{
		return eAngelusItems.angelic_ingot;
	}

}
