package com.dna.everythingisbad.utils.handlers;

import cofh.core.init.CoreEnchantments;
import com.dna.everythingisbad.entity.EntityJesus;
import com.dna.everythingisbad.init.*;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.ModTeleporter;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
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
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

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
    public static void blindnessHandler(EntityPlayerMP player,boolean rollDice) {
        if (rollDice){
            if (random.nextInt(ModConfig.BLINDNESS_CHANCE) == 1) {
                player.getEntityData().setBoolean("is_blind", true);
                player.writeEntityToNBT(player.getEntityData());
            } else {
                player.getEntityData().setBoolean("is_blind", false);
                player.writeEntityToNBT(player.getEntityData());
            }
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
            ItemStack soulStack = new ItemStack(ModItems.SOUL_ITEM,1,0);
            NBTTagCompound soulCompound = new NBTTagCompound();
            soulCompound.setString("player_name",player.getDisplayNameString());
            soulStack.setTagCompound(soulCompound);
            soulStack.addEnchantment(CoreEnchantments.soulbound,1);

            //soulstack.addEnchantment(Enchantment.getEnchantmentByID(2),1);
            //TODO: Add soulbound enchantment to itemstack (or make one)

            // Give it to the player, or drop it if they cant fit it (for whatever reason)
            if (!player.inventory.addItemStackToInventory(soulStack)) {
                player.dropItem(soulStack, false);
            }else{
                player.inventory.addItemStackToInventory(soulStack);
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
        //fluidHandler(player);
        heavenHandler(player);
        commonColdHandler(player);
        babyHandler(player);
    }
    public static void livingTick(EntityLivingBase entityLivingBase){
        poopHandler(entityLivingBase);
        potionEffectHandler(entityLivingBase);
        jesusBloodDropHandler(entityLivingBase);
        fluidHandler(entityLivingBase);
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
            }else if (entity instanceof EntityCreature){
                int highness_duration = entity.getEntityData().getInteger("highness_duration");
                PotionEffectHandler.livingEntityHighnessActive(entity, highness_duration);
            }
        }
    }

    public static void babyHandler(EntityLivingBase entity){
        if (entity instanceof EntityPlayerMP){
            EntityPlayerMP mp = (EntityPlayerMP)entity;
            InventoryPlayer deeta = mp.inventory;
        }
    }

    public static void poopHandler(EntityLivingBase entity){
        if(entity instanceof EntityPlayerMP) {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entity;
            int intervalAlter = random.nextInt(500);
            if (entityPlayerMP.ticksExisted % Math.abs(ModConfig.AUTO_POOP_INTERVAL + intervalAlter) == 0) {

                int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX - 1) + 1;
                ItemStack item = new ItemStack(ModItems.POOP_ITEM, random_amount, 3);
                entityPlayerMP.inventory.addItemStackToInventory(item);
                PlayerHandler.playerPooped(entityPlayerMP, random_amount);
            }
        }else if(entity instanceof EntityAnimal){
            EntityAnimal entityAnimal = (EntityAnimal)entity;
            int intervalAlter = random.nextInt(500);
            if (entityAnimal.ticksExisted % Math.abs(ModConfig.AUTO_POOP_INTERVAL + intervalAlter) == 0) {
                if(entityAnimal.getServer() != null) {
                    WorldServer worldServer = (WorldServer) entityAnimal.getServer().getEntityWorld();
                    int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX - 1) + 1;
                    ItemStack item = new ItemStack(ModItems.POOP_ITEM, random_amount, 3);
                    EntityItem entityItem = new EntityItem(worldServer);
                    entityItem.setPosition(entityAnimal.posX, entityAnimal.posY, entityAnimal.posZ);
                    entityItem.setItem(item);
                    worldServer.spawnEntity(entityItem);
                }
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
    public static void fluidHandler(EntityLivingBase elb){
        if(!(elb instanceof EntityPlayerSP)){
            BlockPos playerPos = elb.getPosition();
            Block blockAtPlayerPos = elb.getEntityWorld().getBlockState(playerPos).getBlock();
            if(blockAtPlayerPos.getUnlocalizedName().equals("tile."+ Reference.MOD_ID+":devils_pee")){
                FluidEventHandler.inDevilsPee(elb);
            }
        }

    }
    public static void commonColdHandler(EntityPlayer player){
        if(player instanceof EntityPlayerMP){
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            World world = playerMP.getEntityWorld();
            boolean Immune = playerMP.getEntityData().getBoolean("common_cold_immunity");
            Biome bedBiome = world.getBiome(playerMP.getPosition());
            Immune = Immune || bedBiome.getTempCategory() == Biome.TempCategory.COLD;
            if(world.getBiome(playerMP.getPosition()).getTempCategory() == Biome.TempCategory.COLD && !Immune){
                if(playerMP.ticksExisted + 1 % (ModConfig.IS_DEBUG ? 100 : 10000) == 0){
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
        if (player instanceof EntityPlayerMP){
            Random rand = new Random();
            World worldIn = player.getEntityWorld();

            BlockPos player_pos = player.getPosition();
            int player_x = player_pos.getX();
            int player_y = player_pos.getY();
            int player_z = player_pos.getZ();

            BlockPos pos_plus_2 = new BlockPos(player_x,player_y+2,player_z);
            Block blockabove = worldIn.getBlockState(pos_plus_2).getBlock();

            if (blockabove == ModBlocks.QUESTION_MARK_BLOCK && player.motionY > 0){
                // Sets it to the empty block
                worldIn.setBlockToAir(pos_plus_2);
                worldIn.setBlockState(pos_plus_2,ModBlocks.EMPTY_BLOCK.getBlockState().getBaseState());

                // Gives you the item(s) and shit
                LootTable loottable = worldIn.getLootTableManager().getLootTableFromLocation(ModLootTables.QUESTION_MARK_BLOCK_LOOT);
                LootContext.Builder lootcontext$builder = (new LootContext.Builder((WorldServer)worldIn));
                for (ItemStack lutestack : loottable.generateLootForPools(rand,lootcontext$builder.build())){
                    player.dropItem(lutestack,false);
                }
            }
        }
    }
}
