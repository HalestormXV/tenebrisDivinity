package halestormxv.eAngelus.items.cards;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class O_card_Fire extends Item 
{
	//public  double powerCost;

	public O_card_Fire()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(-1);
	}

	public boolean isDamageable()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack){
		return EnumRarity.UNCOMMON;
	}

	@Override
	public boolean hasEffect(ItemStack stack){
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("");
		tooltip.add("\u00A76" + "You fall into my arms.");
		tooltip.add("\u00A76" + "You are the good gift of destruction's path.");
		tooltip.add("\u00A76" + "When life sickens more than disease.");
		tooltip.add("\u00A76" + "And boldness is the root of beauty...");
		tooltip.add("");
		tooltip.add("\u00A7n" + "Calls down a storm of lightning on surrounding enemies.");
	}

	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		return EnumAction.BOW;
	}

	/*@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		// TODO Auto-generated method stub
		//return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}*/


	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		int chargeTime = 0;
		ArrowLooseEvent event = new ArrowLooseEvent((EntityPlayer) entityLiving, itemstack, worldIn, chargeTime, false);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		//int j = this.getMaxItemUseDuration(itemstack) - timeLeft;
		int j = event.getCharge();
		List<EntityLivingBase> targetList = entityLiving.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entityLiving.getEntityBoundingBox().expand(8.0F + j, 8.0F + j, 8.0F + j));	
		for (EntityLivingBase targets : targetList)
		{
			if ( targets != null)
			{
				if ( targets != (EntityPlayer)entityLiving)
				{
					((EntityPlayer)entityLiving).worldObj.spawnEntityInWorld(new EntityLightningBolt(worldIn, targets.posX, targets.posY, targets.posZ, false) );
					targets.clearActivePotions();
					worldIn.createExplosion(targets, targets.posX, targets.posY, targets.posZ, 3.2F + (j / 2), true);
					targets.setHealth(targets.getHealth() - j);
					//worldIn.playSoundAtEntity(targets, RefStrings.MODID + ":leo_gift_execute", 1.4F, 1.0F);
				}
			}
		}
	}
}


/*
@Override
public void addInformation(ItemStack istack, EntityPlayer player, List info, boolean par4) {

	NBTTagCompound tag = istack.stackTagCompound;

    if (tag == null)
    {
        tag = new NBTTagCompound();
        tag.setTag("display", new NBTTagCompound());
        istack.stackTagCompound = tag;
    }

    tag.getTag("display");
    tag.setTag("display", new NBTTagCompound());
	NBTTagCompound taglore = tag.getCompoundTag("display");
	NBTTagList lore = new NBTTagList();

	lore.appendTag(new NBTTagString("§r"+"This is a special item, for adding"));
	lore.appendTag(new NBTTagString("§r"+"guests"));
	lore.appendTag(new NBTTagString("§r"+"into your home."));

	taglore.setTag("Lore", lore);
}*/
