package com.dna.everythingisbad.utils.handlers;

import cofh.core.init.CoreEnchantments;
import com.dna.everythingisbad.entity.EntityJesus;
import com.dna.everythingisbad.entity.EntityPoliceOfficer;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.init.*;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.ModTeleporter;
import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.utils.SpawnUtils;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.Random;
@Deprecated
public class PlayerHandler {
    private static Random random = new Random();
    @Deprecated
    public static void playerDied(EntityPlayer player){
        if(ModConfig.BLOOD_SPAWNS_ON_DEATH) {
            player.getEntityWorld().setBlockState(
                    new BlockPos(player.posX, player.posY, player.posZ),
                    ModFluids.BLOOD.getBlockFluidBase().getDefaultState()
            );
        }
        //PotionEffectHandler.potionEffectFirstTimes.put(player,false);
    }
    @Deprecated
    public static void playerRespawn(EntityPlayer player){

            blindnessHandler(player,true);
            religionHandler(player);
            //soulHandler(player);

    }
    @Deprecated
    public static void playerJoined(EntityPlayer entityPlayer) {
        //entity player needs to be casted to its respective type when writing nbt data

        //EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
        PlayerProperties playerProperties = entityPlayer.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);

        if(playerProperties != null && !entityPlayer.world.isRemote) {
            boolean hasBeenInitialized = playerProperties.hasBeenInitialized();
            ///////////////////////////////////////////////////////
            //// All initializers need to be run in this block ////
            ///////////////////////////////////////////////////////
            religionHandler(entityPlayer);
            soulHandler(entityPlayer);
            blindnessHandler(entityPlayer, false);
            ///////////////////////////////////////////////////////
            //// End of Initializer Block /////////////////////////
            ///////////////////////////////////////////////////////
            if (!hasBeenInitialized) {
                commonColdInitializer(entityPlayer, true);
                playerProperties.setHasBeenInitialized(true);
            }

            playerProperties.saveNBTData(entityPlayer.getEntityData());
        }

    }
    @Deprecated
    public static void commonColdInitializer(EntityPlayer player, boolean rollDice){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            if (random.nextInt(ModConfig.COMMON_COLD_CHANCE) == 1) {
                playerProperties.setHasCommonColdImmunity(false);
            } else {
                playerProperties.setHasCommonColdImmunity(true);
            }
            playerProperties.saveNBTData(player.getEntityData());
        }
    }
    @Deprecated
    public static void blindnessHandler(EntityPlayer player,boolean rollDice) {
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            if (rollDice) {
                if (random.nextInt(ModConfig.BLINDNESS_CHANCE) == 1) {
                    playerProperties.setBlind(true);

                } else {
                    playerProperties.setBlind(false);
                }
                playerProperties.saveNBTData(player.getEntityData());
            }
            boolean isBlind = playerProperties.isBlind();
            if (isBlind) {
                //player.addSuffix(new TextComponentString(" [Blind]").setStyle(new Style().setColor(TextFormatting.DARK_RED)));
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 100000));
            }
        }
    }
    @Deprecated
    public static void soulHandler(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            boolean hasSoul = playerProperties.hasSoul();
            if (!hasSoul) {

                // Makes a new itemstack and gives it an NBT string with the name of the player
                ItemStack soulStack = new ItemStack(ModItems.SOUL_ITEM, 1, 0);
                NBTTagCompound soulCompound = new NBTTagCompound();
                soulCompound.setString("player_name", player.getDisplayNameString());
                soulStack.setTagCompound(soulCompound);

                // Enchants it with soulbound
                soulStack.addEnchantment(CoreEnchantments.soulbound, 1);

                // Give it to the player, or drop it if they cant fit it (for whatever reason)
                if (!player.inventory.addItemStackToInventory(soulStack)) {
                    player.dropItem(soulStack, false);
                } else {
                    player.inventory.addItemStackToInventory(soulStack);
                }

                // Makes sure the player only gets one, ever!!!
                // (Unless they use another account, lol)
                playerProperties.setHasSoul(true);
                playerProperties.saveNBTData(player.getEntityData());
            }else{
                int inventorySize = player.inventory.getSizeInventory();
                for(int i = 0;i<inventorySize;i++){
                    ItemStack itemStack = player.inventory.getStackInSlot(i);
                    if(itemStack.getItem() == ModItems.SOUL_ITEM){
                        NBTTagList enchantments = itemStack.getEnchantmentTagList();
                        if(enchantments.tagCount() == 0){
                            itemStack.addEnchantment(CoreEnchantments.soulbound,1);
                        }

                    }
                }
            }
        }
    }
    @Deprecated
    public static void religionHandler(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            boolean playerInitialized = playerProperties.hasBeenInitialized();

            // On first join, you get a religion assigned
            if (!playerInitialized) {
                int rnadomnum = random.nextInt(Religion.values().length);
                playerProperties.setReligion(rnadomnum);
                playerProperties.saveNBTData(player.getEntityData());
            }
            // This part is run on every join
            int playerReligion = playerProperties.getReligion();

            Religion rel[] = Religion.values();
            for (Religion religion : rel) {
                if (religion.ordinal() == playerReligion) {
                    player.addSuffix(new TextComponentString(" [" +
                            religion.getDisplayName() + "]").setStyle(new Style().setColor(religion.getTextFormatting())));
                }
            }
        }
    }
    @Deprecated
    public static void playerPooped(EntityPlayer entityPlayer, int amount) {



        PlayerProperties playerProperties = entityPlayer.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            int current = playerProperties.getTimesPooped();
            playerProperties.setTimesPooped(current+amount);
            playerProperties.saveNBTData(entityPlayer.getEntityData());
        }

    }
    @Deprecated
    public static void playerTick(EntityPlayer player){
        if(!player.world.isRemote) {
            questionMarkBlockHandler(player);
            playerDeathHandler(player);
            //fluidHandler(player);
            heavenHandler(player);
            commonColdHandler(player);
            babyHandler(player);
        }

    }
    @Deprecated
    public static void livingTick(EntityLivingBase entityLivingBase){
        poopHandler(entityLivingBase);
        potionEffectHandler(entityLivingBase);
        jesusBloodDropHandler(entityLivingBase);
        fluidHandler(entityLivingBase);
        villagerBabyHandler(entityLivingBase);
    }
    @Deprecated
    public static void livingDamage(LivingDamageEvent event, EntityLivingBase elb){
        healBlindnessHandler(event, elb);
    }
    @Deprecated
    private static void healBlindnessHandler(LivingDamageEvent event, EntityLivingBase elb) {
        if (elb instanceof EntityPlayerMP && !elb.getEntityWorld().isRemote){

            PlayerProperties playerProperties = elb.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
            // Is it a Jesus that attacked you? if so then...
            if (event.getSource().getTrueSource() instanceof EntityJesus){
                // are you blind? currently? uhhh
                if (elb.isPotionActive(Potion.getPotionById(15))){
                    elb.removePotionEffect(Potion.getPotionById(15));
                    playerProperties.setBlind(false);
                    playerProperties.saveNBTData(elb.getEntityData());
                }
            }
        }
    }
    @Deprecated
    private static void villagerBabyHandler(EntityLivingBase entityLivingBase) {
        if (entityLivingBase instanceof EntityVillager){
            EntityVillager villager = (EntityVillager)entityLivingBase;
            if (!villager.isChild() && !villager.getWorld().isRemote){
                if (villager.ticksExisted % ModConfig.BABY_DROP_INTERVAL == random.nextInt(ModConfig.BABY_DROP_INTERVAL)){
                    // Makes a new itemstack and gives it an NBT string with the age of the baby in ticks
                    ItemStack babyStack = new ItemStack(ModItems.BABY_ITEM, 1, 0);
                    NBTTagCompound babyCmpound = new NBTTagCompound();
                    babyCmpound.setInteger("age",1);
                    babyStack.setTagCompound(babyCmpound);
                    villager.entityDropItem(babyStack,0f);
                    villager.getWorld().playSound((EntityPlayer)null,villager.getPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.AMBIENT,2f,0.5f);
                }
            }
        }
    }
    @Deprecated
    public static void jesusBloodDropHandler(EntityLivingBase entity){
        if(entity instanceof EntityJesus && ModConfig.BLOOD_SPAWNS_ON_DEATH && !entity.world.isRemote){
            if(entity.getHealth() < 0.1f){
                World world = entity.getEntityWorld();
                world.setBlockState(entity.getPosition(),ModFluids.BLOOD.getBlockFluidBase().getDefaultState());
            }
        }
    }
    //Detects which effect is active and routes it to the PotionEffectHandler
    @Deprecated
    public static void potionEffectHandler(EntityLivingBase entity){
        boolean highness_active = entity.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion());
        boolean common_cold_active = entity.isPotionActive(ModPotions.POTION_COMMON_COLD.getPotion());
        boolean adrenaline_active = entity.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion());

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

        if (common_cold_active && !(entity instanceof EntityPlayerSP)){
            int common_cold_duration = entity.getEntityData().getInteger("common_cold_duration");
            PotionEffectHandler.livingEntityCommonColdActive(entity,common_cold_duration);
        }

        if (adrenaline_active && !(entity instanceof EntityPlayerSP)){
            int adrenaline_duration = entity.getEntityData().getInteger("adrenaline_duration");
            PotionEffectHandler.livingEntityAdrenalineActive(entity,adrenaline_duration);
        }
    }
    @Deprecated
    public static void playerSmelted(PlayerEvent.ItemSmeltedEvent event, EntityPlayer player){
        if (player instanceof EntityPlayerMP){
            EntityPlayerMP mp = (EntityPlayerMP)player;
            if (event.smelting.getItem() == ModItems.COOKED_BABY_ITEM){
                int randomamount = random.nextInt(3)+1;
                for (int i=0;i<randomamount;i++){
                    SpawnUtils.spawnMobInRadius(mp.getServerWorld(),mp,new EntityPoliceOfficer(mp.getServerWorld()),5,10);
                }
            }
        }
    }
    @Deprecated
    public static void babyHandler(EntityPlayer entity){
        if (entity instanceof EntityPlayerMP){
            EntityPlayerMP mp = (EntityPlayerMP)entity;
            InventoryPlayer deeta = mp.inventory;
            if(mp.ticksExisted % 20 == 19) {
                for (ItemStack stack : deeta.mainInventory) {
                    if (stack.getItem() == ModItems.BABY_ITEM) {
                        NBTTagCompound compound = stack.getTagCompound();
                        if (compound != null) {
                            int age = compound.getInteger("age");
                            age++;
                            compound.setInteger("age", age);
                            stack.setTagCompound(compound);

                            if (age >= ModConfig.BABY_AGE_UP_TIME) {
                                String name = stack.getDisplayName();
                                stack.shrink(1);
                                EntityVillager entityVillager = new EntityVillager(mp.getServerWorld());
                                entityVillager.setGrowingAge(-24000);
                                entityVillager.setPosition(mp.posX, mp.posY, mp.posZ);
                                if (!name.equals("Baby")) {
                                    entityVillager.setCustomNameTag(name);
                                }
                                mp.getServerWorld().spawnEntity(entityVillager);
                            }
                        }
                    }
                }
            }
        }
    }
    @Deprecated
    public static void poopHandler(EntityLivingBase entity){
        if(entity instanceof EntityPlayer && !entity.world.isRemote) {
            EntityPlayer entityPlayerMP = (EntityPlayer) entity;
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
    @Deprecated
    public static void playerDeathHandler(EntityPlayer player){
        if (player.isDead) {
            PlayerHandler.playerDied(player);
        }
    }
    @Deprecated
    public static void playerLeft(EntityPlayer player) {

    }
    @Deprecated
    public static void fluidHandler(EntityLivingBase elb){
        if(elb instanceof EntityPlayerMP || elb instanceof EntityCreature){
            BlockPos playerPos = elb.getPosition();
            Block blockAtPlayerPos = elb.getEntityWorld().getBlockState(playerPos).getBlock();
            if(blockAtPlayerPos.getUnlocalizedName().equals("tile."+ Reference.MOD_ID+":devils_pee")){
                FluidEventHandler.inDevilsPee(elb);
            }
        }

    }
    @Deprecated
    public static void commonColdHandler(EntityPlayer player){


        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            World world = player.getEntityWorld();
            boolean Immune = playerProperties.hasCommonColdImmunity();
            Biome bedBiome = world.getBiome(player.getPosition());
            Immune = Immune || bedBiome.getTempCategory() == Biome.TempCategory.COLD;
            if (world.getBiome(player.getPosition()).getTempCategory() == Biome.TempCategory.COLD && !Immune) {
                if (player.ticksExisted + 1 % (ModConfig.IS_DEBUG ? 100 : 10000) == 0) {
                    player.addPotionEffect(new PotionEffect(ModPotions.POTION_COMMON_COLD.getPotion(), 24000));
                }
            }
        }

    }
    @Deprecated
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
            }else if (!(biyom.equals(ModBiomes.HEAVEN) && !(biyom.equals(Biomes.SKY))) && mp.posY > 320 &&
            mp.isPotionActive(Potion.getPotionById(25))){
                int dimension = ModDimensions.HEAVEN.getId();
                mp.changeDimension(dimension,new ModTeleporter(server,mp.posX,35,mp.posZ));
            }
        }
    }
    @Deprecated
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
    @Deprecated
    public static void playerBrokeBlock(BlockEvent.BreakEvent event) {
        if (!event.getPlayer().getEntityWorld().isRemote && !event.getPlayer().isCreative())
        if (event.getState().getBlock() == Blocks.TALLGRASS){
            // 9% drop chance from tall grass
            if (RandomUtils.percentChance(9)){
                event.getPlayer().dropItem(ModItems.TOBACCO_SEEDS_ITEM,1);
            }
        }
    }
}
