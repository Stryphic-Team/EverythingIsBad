package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.IHasModel;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.helpers.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This is going to be deprecated in the future for a new system of registering items, blocks, and entities
 */
@EventBusSubscriber
public class RegistryHandler {


    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    /**
     * Registers models for items and blocks
     * @param event
     */
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ModItems.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : ModBlocks.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

    }
    @SubscribeEvent
    public static void milked(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() instanceof EntityAnimal && !event.getEntityLiving().world.isRemote && event.getEntityLiving().ticksExisted % (ModConfig.AUTO_POOP_INTERVAL) == 0) {
//            EntityPig pig = (EntityPig) event.getEntityLiving();
//            pig.setVelocity(0f,1f,0f);
            //Main.logger.info("pig updated");
            EntityAnimal animal = (EntityAnimal) event.getEntity();

            EntityItem item = new EntityItem(event.getEntity().world,animal.posX,animal.posY,animal.posZ,new ItemStack(ModItems.POOP_ITEM,1,3));
            animal.world.spawnEntity(item);

        }
    }

    @SubscribeEvent
    public static void reallyMilked(PlayerInteractEvent.EntityInteract event){
        Entity target = event.getTarget();
        String target_name = target.getName();

        EntityPlayer player = event.getEntityPlayer();
        //EnumHand hand = player.getActiveHand();
        // I had trouble with this, since it spits an error every time I use it

        ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        // This works however, but it's ugly
        FluidStack fluidstack = new FluidStack(ModFluids.DIARIA.getFluid(),1);
        ItemStack bucket = FluidUtil.getFilledBucket(fluidstack);

        if (target_name.equals("Pig")) {
            //Main.logger.info("Pig milked");
            if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode) {
                player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
                itemstack.shrink(1);
                if (itemstack.isEmpty()) {

                    player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,bucket);

                } else if (!player.inventory.addItemStackToInventory(bucket)) {
                    player.dropItem(bucket, false);
                }
            }
        }
    }
    @SubscribeEvent
    public static void wolfFed(PlayerInteractEvent.EntityInteract event){
        Entity target = event.getTarget();

        String target_name = target.getName();

        EntityPlayer player = event.getEntityPlayer();

        ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        if (target_name.equals("Wolf")) {
            if (itemstack.getItem() == ModItems.JESUS_MEAT_COOKED || itemstack.getItem() == ModItems.JESUS_MEAT_RAW){
                EntityWolf wolf = (EntityWolf)target;
                wolf.addPotionEffect(new PotionEffect(Potion.getPotionById(25), TimeHelper.fromSeconds(30), 1));
            }
        }
    }
}
