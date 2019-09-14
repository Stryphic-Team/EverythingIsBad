package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.entity.EntityJesus;
import com.dna.everythingisbad.init.*;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.ModTeleporter;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class PlayerHandler {
    private static Random random = new Random();
    public static void playerDied(EntityPlayer player){
        if(ModConfig.BLOOD_SPAWNS_ON_DEATH) {
            player.getEntityWorld().setBlockState(
                    new BlockPos(player.posX, player.posY, player.posZ),
                    ModFluids.BLOOD.getBlockFluidBase().getDefaultState()
            );
        }
        //PotionEffectHandler.potionEffectFirstTimes.put(player,false);
    }
    public static void playerRespawn(EntityPlayer player){
        // sets to 0 when the player is not high
//        player.getEntityData().setInteger("highness_duration",0);
//        player.writeEntityToNBT(player.getEntityData());
        if(player instanceof EntityPlayerMP){
            blindnessHandler((EntityPlayerMP) player,true);
        }
    }
    public static void playerJoined(EntityPlayer entityPlayer) {
        //entity player needs to be casted to its respective type when writing nbt data

        if(entityPlayer instanceof EntityPlayerMP){

            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
            boolean hasBeenInitialized = entityPlayerMP.getEntityData().getBoolean("has_been_initialized");

            soulHandler(entityPlayerMP);
            blindnessHandler(entityPlayerMP,false);
            if(!hasBeenInitialized){
                commonColdInitializer(entityPlayerMP,true);
                //needs to be run at the end
                entityPlayerMP.getEntityData().setBoolean("has_been_initialized",true);
                entityPlayerMP.writeEntityToNBT(entityPlayerMP.getEntityData());
            }

        }
    }
    public static void commonColdInitializer(EntityPlayerMP player, boolean rollDice){
        if(random.nextInt(ModConfig.COMMON_COLD_CHANCE) == 1){
            player.getEntityData().setBoolean("common_cold_immunity",false);
        }else{
            player.getEntityData().setBoolean("common_cold_immunity",true);
        }
        player.writeEntityToNBT(player.getEntityData());
    }
    public static void blindnessHandler(EntityPlayerMP player,boolean rollDice){
        if(random.nextInt(ModConfig.BLINDNESS_CHANCE) == 1 && rollDice) {
            player.getEntityData().setBoolean("is_blind", true);

            player.writeEntityToNBT(player.getEntityData());
        }else{
            player.getEntityData().setBoolean("is_blind", false);
            player.writeEntityToNBT(player.getEntityData());
        }
        boolean isBlind = player.getEntityData().getBoolean("is_blind");
        if(isBlind){
            player.addSuffix(new TextComponentString(" [Blind]").setStyle(new Style().setColor(TextFormatting.DARK_RED)));
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(15),100000));
        }
    }

    public static void soulHandler(EntityPlayerMP player){

        boolean hasSoul = player.getEntityData().getBoolean("has_soul");
        if (!hasSoul){

            // Makes a new itemstack and gives it an NBT string with the name of the player
            ItemStack soulstack = new ItemStack(ModItems.SOUL_ITEM,1,0);
            NBTTagCompound hingydingy = new NBTTagCompound();
            hingydingy.setString("player_name",player.getDisplayNameString());
            soulstack.setTagCompound(hingydingy);

            //soulstack.addEnchantment(Enchantment.getEnchantmentByID(2),1);
            //TODO: Add soulbound enchantment to itemstack (or make one)

            // Give it to the player, or drop it if they cant fit it (for whatever reason)
            if (!player.inventory.addItemStackToInventory(soulstack)) {
                player.dropItem(soulstack, false);
            }else{
                player.inventory.addItemStackToInventory(soulstack);
            }

            // Makes sure the player only gets one, ever!!!
            // (Unless they use another account, lol)
            player.getEntityData().setBoolean("has_soul",true);
            player.writeEntityToNBT(player.getEntityData());
        }
    }

    public static void playerPooped(EntityPlayer entityPlayer, int amount) {

        if(entityPlayer instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
            int current = entityPlayerMP.getEntityData().getInteger("times_pooped");
            entityPlayerMP.getEntityData().setInteger("times_pooped",current + amount);
            entityPlayerMP.writeEntityToNBT(entityPlayerMP.getEntityData());
        }else{
            int current = entityPlayer.getEntityData().getInteger("times_pooped");
            entityPlayer.getEntityData().setInteger("times_pooped",current + amount);
            entityPlayer.writeEntityToNBT(entityPlayer.getEntityData());

        }

    }
    public static void playerTick(EntityPlayer player){
        questionMarkBlockHandler(player);
        playerDeathHandler(player);
        fluidHandler(player);
        heavenHandler(player);
        commonColdHandler(player);
    }
    public static void livingTick(EntityLivingBase entityLivingBase){
        poopHandler(entityLivingBase);
        potionEffectHandler(entityLivingBase);
        jesusBloodDropHandler(entityLivingBase);
    }
    public static void jesusBloodDropHandler(EntityLivingBase entity){
        if(entity instanceof EntityJesus && ModConfig.BLOOD_SPAWNS_ON_DEATH){
            if(entity.getHealth() == 0){
                World world = entity.getEntityWorld();
                world.setBlockState(entity.getPosition(),ModFluids.BLOOD.getBlockFluidBase().getDefaultState());
            }
        }
    }
    //Detects which effect is active and routes it to the PotionEffectHandler
    public static void potionEffectHandler(EntityLivingBase entity){
        boolean highness_active = entity.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion());
        if(highness_active){
            if (entity instanceof EntityPlayerMP) {
                // Casting to entityplayermp
                EntityPlayerMP mp = (EntityPlayerMP)entity;
                int highness_duration = mp.getEntityData().getInteger("highness_duration");
                //Main.logger.info("Highness duration in client handler: " + highness_duration);
                PotionEffectHandler.livingEntityHighnessActive(mp, highness_duration);
            }else{
                int highness_duration = entity.getEntityData().getInteger("highness_duration");
                PotionEffectHandler.livingEntityHighnessActive(entity, highness_duration);
            }
        }
    }

    public static void poopHandler(EntityLivingBase entity){
        if(entity instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entity;
            if (entityPlayerMP.ticksExisted % (ModConfig.AUTO_POOP_INTERVAL) == 0) {

                int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX - 1) + 1;
                ItemStack item = new ItemStack(ModItems.POOP_ITEM, random_amount, 3);
                entityPlayerMP.inventory.addItemStackToInventory(item);
                PlayerHandler.playerPooped(entityPlayerMP, random_amount);
            }
        }else if(entity instanceof EntityCreature){
            EntityCreature entityAmbientCreature = (EntityCreature)entity;
            if (entityAmbientCreature.ticksExisted % (ModConfig.AUTO_POOP_INTERVAL) == 0) {

                int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX - 1) + 1;
                ItemStack item = new ItemStack(ModItems.POOP_ITEM, random_amount, 3);
                EntityItem entityItem = new EntityItem(entityAmbientCreature.getEntityWorld());
                entityItem.setPosition(entityAmbientCreature.posX,entityAmbientCreature.posY,entityAmbientCreature.posZ);
                entityItem.setItem(item);
                entityAmbientCreature.getEntityWorld().spawnEntity(entityItem);
            }
        }
    }
    public static void playerDeathHandler(EntityPlayer player){
        if (player.isDead) {
            PlayerHandler.playerDied(player);
        }
    }

    public static void playerLeft(EntityPlayer player) {

    }
    public static void fluidHandler(EntityPlayer player){
        if(player instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
            BlockPos playerPos = entityPlayerMP.getPosition();
            Block blockAtPlayerPos = entityPlayerMP.getEntityWorld().getBlockState(playerPos).getBlock();
            if(blockAtPlayerPos.getUnlocalizedName().equals("tile."+ Reference.MOD_ID+":devils_pee")){
                FluidEventHandler.inDevilsPee(entityPlayerMP);
            }
        }

    }
    public static void commonColdHandler(EntityPlayer player){
        if(player instanceof EntityPlayerMP){
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            World world = playerMP.getEntityWorld();
            boolean Immune = playerMP.getEntityData().getBoolean("common_cold_immunity");
            if(world.getBiome(playerMP.getPosition()).getTempCategory() == Biome.TempCategory.COLD && !Immune){
                if(playerMP.ticksExisted + 1 % 100 == 0){
                    playerMP.addPotionEffect(new PotionEffect(ModPotions.POTION_COMMON_COLD.getPotion(),24000));
                }
            }
        }
    }
    public static void heavenHandler(EntityPlayer player){
        if (player instanceof EntityPlayerMP){
            EntityPlayerMP mp = (EntityPlayerMP)player;
            WorldServer server = mp.getServerWorld();

            // Get the biome the player is in
            BlockPos playerPos = mp.getPosition();
            Biome biyom = mp.getServerWorld().getBiome(playerPos);

            if (biyom.equals(ModBiomes.HEAVEN) && mp.posY <= 0){
                int dimension = DimensionType.OVERWORLD.getId();
                mp.changeDimension(dimension,new ModTeleporter(server,mp.posX,300,mp.posZ));
            }
        }
    }
    public static void questionMarkBlockHandler(EntityPlayer player){
        Random rand = new Random();
        World worldIn = player.getEntityWorld();

        BlockPos player_pos = player.getPosition();
        int player_x = player_pos.getX();
        int player_y = player_pos.getY();
        int player_z = player_pos.getZ();

        BlockPos pos_plus_2 = new BlockPos(player_x,player_y+2,player_z);
        Block blockabove = worldIn.getBlockState(pos_plus_2).getBlock();

        if (blockabove == ModBlocks.QUESTION_MARK_BLOCK && player.motionY > 0){
            //Main.logger.info("Under question mark block");
            //TODO: This should be a fucking loot table
            //TODO: PLEASE
            worldIn.setBlockToAir(pos_plus_2);
            worldIn.setBlockState(pos_plus_2,ModBlocks.EMPTY_BLOCK.getBlockState().getBaseState());
            ItemStack itemstack;
            if (rand.nextInt(101) > 98){
                itemstack = new ItemStack(ModItems.STUPID_CORE_ITEM,1);
                player.dropItem(itemstack, false);
            }else if (rand.nextInt(101) > 90){
                itemstack = new ItemStack(Items.GOLD_INGOT,1);
                player.dropItem(itemstack, false);
            }else if (rand.nextInt(101) > 50){
                if (rand.nextBoolean() == true){
                    itemstack = new ItemStack(Blocks.RED_MUSHROOM,1);
                    player.dropItem(itemstack, false);
                }else{
                    itemstack = new ItemStack(Blocks.BROWN_MUSHROOM,1);
                    player.dropItem(itemstack, false);
                }
            }else{
                itemstack = new ItemStack(Items.GOLD_NUGGET,1);
                player.dropItem(itemstack, false);
            }
        }
    }
}
