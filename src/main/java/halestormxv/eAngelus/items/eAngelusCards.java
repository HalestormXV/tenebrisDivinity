package halestormxv.eAngelus.items;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import halestormxv.eAngelus.items.cards.O_card_Fire;
import halestormxv.eAngelus.items.cards.O_card_Lightning;
import halestormxv.eAngelus.items.cards.O_card_Strength;
import halestormxv.eAngelus.items.cards.O_card_Wind;
import halestormxv.eAngelus.items.cards.O_card_Wither;
import halestormxv.eAngelus.main.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class eAngelusCards extends Item
{
	public static Class<? extends Item>[] itemCardsClasses = new Class[]
			{
					O_card_Fire.class, 
					O_card_Wind.class, 
					O_card_Wither.class, 
					O_card_Strength.class,
					O_card_Lightning.class
			};

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
	
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
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

	@Override
	public EnumActionResult onItemUse(ItemStack itemstack, EntityPlayer playerIn, World world, BlockPos pos, 
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)	
	{
		if (!world.isRemote)
		{
			try 
			{
				Item card = itemCardsClasses[itemstack.getItemDamage()].getConstructor(World.class).newInstance(world);

				//int sF = CardCostFormula[itemstack.getItemDamage()];
				//int sB = CardCostBase[itemstack.getItemDamage()];
				//int sC = CardUsageCost = (int)(Math.ceil((player.experienceLevel) / sF));
				//return EnumActionResult.SUCCESS;
			} 
			catch (InstantiationException card) {
				card.printStackTrace();
			} 
			catch (IllegalAccessException card) {
				card.printStackTrace();
			} 
			catch (IllegalArgumentException card) {
				card.printStackTrace();
			} 
			catch (InvocationTargetException card) {
				card.printStackTrace();
			} 
			catch (NoSuchMethodException card) {
				card.printStackTrace();
			} 
			catch (SecurityException card) {
				card.printStackTrace();
			}
		}
		else if (itemstack.getItemDamage() > O_cardNames.length){};
		return EnumActionResult.FAIL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if (stack.getItemDamage() == 0)
		{
			tooltip.add("");
			tooltip.add("\u00A76" + "You fall into my arms.");
			tooltip.add("\u00A76" + "You are the good gift of destruction's path.");
			tooltip.add("\u00A76" + "When life sickens more than disease.");
			tooltip.add("\u00A76" + "And boldness is the root of beauty...");
			tooltip.add("");
			tooltip.add("\u00A7n" + "Calls down a storm of lightning on surrounding enemies.");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		if (stack.getItemDamage() == 0)
		{
			return EnumRarity.UNCOMMON;
		}else{
			return EnumRarity.COMMON;
		}
	}

}
