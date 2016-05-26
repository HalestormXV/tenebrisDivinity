package halestormxv.eAngelus.items;

import java.util.List;

import halestormxv.eAngelus.main.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class eAngelusCards extends Item
{
//Offense Card Names
public static final String[] O_cardNames = new String[] {"cIgnis", "cFortitudo", "cVentus", "cArescet", "cLightning"};

	
	public eAngelusCards(String unlocalizedName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(Reference.eaCreativeTab);
		this.setHasSubtypes(true);
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> items) 
	{
		for (int i = 0; i < O_cardNames.length; ++i)
		{
			items.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
		for (int i = 0; i < O_cardNames .length; ++i)
		if(stack.getItemDamage() == i)
		{
			return this.getUnlocalizedName() + "." + O_cardNames[i];
		}
		return "Invalid";
	}

}
