package com.dna.everythingisbad.init;


import com.dna.everythingisbad.item.*;
import com.dna.everythingisbad.item.armor.ItemCamoChestplate;
import com.dna.everythingisbad.item.armor.ItemCowboyHat;
import com.dna.everythingisbad.item.armor.ItemHillbillyHat;
import com.dna.everythingisbad.item.armor.ItemSantaHat;
import com.dna.everythingisbad.item.food.*;
import com.dna.everythingisbad.item.instruments.ItemBanjo;
import com.dna.everythingisbad.item.instruments.ItemStringBass;
import com.dna.everythingisbad.item.machineitems.ItemIronRod;
import com.dna.everythingisbad.item.machineitems.ItemSolenoid;
import com.dna.everythingisbad.item.plants.ItemTobaccoLeaf;
import com.dna.everythingisbad.item.plants.ItemTobaccoSeeds;
import com.dna.everythingisbad.item.records.*;
import com.dna.everythingisbad.item.weapons.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();
    /**
     * Misc Crafting items
     */
    public static final ItemPoop POOP_ITEM = new ItemPoop("poop");
    public static final ItemPoopBrick POOP_BRICK_ITEM = new ItemPoopBrick("poop_brick");
    public static final ItemPaperBag PAPER_BAG_ITEM = new ItemPaperBag("paper_bag");
    public static final ItemSoul SOUL_ITEM = new ItemSoul("soul");
    public static final ItemTobaccoLeaf TOBACCO_LEAF_ITEM = new ItemTobaccoLeaf("tobacco_leaf");
    public static final ItemTobaccoSeeds TOBACCO_SEEDS_ITEM = new ItemTobaccoSeeds("tobacco_seeds");
    public static final ItemUrineBrick URINE_BRICK_ITEM = new ItemUrineBrick("urine_brick");
    public static final ItemUrineCrystal URINE_CRYSTAL_ITEM = new ItemUrineCrystal("urine_crystal");
    public static final ItemBaby BABY_ITEM = new ItemBaby("baby");
    public static final ItemUltraStupidCore ULTRA_STUPID_CORE = new ItemUltraStupidCore("ultra_stupid_core");
    public static final ItemHeartRateMonitor HEART_RATE_MONITOR_ITEM = new ItemHeartRateMonitor("heart_rate_monitor");

    /**
     * Machine parts
     */
    public static final ItemCopperIngot COPPER_INGOT_ITEM = new ItemCopperIngot("copper_ingot");
    public static final ItemSolenoid ITEM_SOLENOID = new ItemSolenoid("solenoid");
    public static final ItemIronRod ITEM_IRON_ROD = new ItemIronRod("iron_rod");


    /**
     * Food items
     */
    public static final ItemFungOs FUNG_OS_ITEM = new ItemFungOs("fung_os");
    public static final ItemHotCheaters HOT_CHEATERS_ITEM = new ItemHotCheaters("hot_cheaters");
    public static final ItemJesusMeatRaw JESUS_MEAT_RAW = new ItemJesusMeatRaw("jesus_meat_raw");
    public static final ItemJesusMeatCooked JESUS_MEAT_COOKED = new ItemJesusMeatCooked("jesus_meat_cooked");
    public static final ItemDevilsCabbage DEVILS_CABBAGE_ITEM = new ItemDevilsCabbage("devils_cabbage");
    public static final ItemChickenSoup CHICKEN_SOUP_ITEM = new ItemChickenSoup("chicken_soup");
    public static final ItemAloeLeaf ALOE_LEAF_ITEM = new ItemAloeLeaf("aloe_leaf");
    public static final ItemFriedDragonEgg FRIED_DRAGON_EGG_ITEM = new ItemFriedDragonEgg("fried_dragon_egg");
    public static final ItemMedicineBottle MEDICINE_BOTTLE_ITEM = new ItemMedicineBottle("medicine_bottle");
    public static final ItemLaxative LAXATIVE_ITEM = new ItemLaxative("laxative");
    public static final ItemCookedBaby COOKED_BABY_ITEM = new ItemCookedBaby("cooked_baby");
    public static final ItemAngelDust ANGEL_DUST = new ItemAngelDust("angel_dust");
    public static final ItemCigarette CIGARETTE_ITEM = new ItemCigarette("cigarette");
    public static final ItemCigar CIGAR_ITEM = new ItemCigar("cigar");
    public static final ItemBabySoup BABY_SOUP_ITEM = new ItemBabySoup("baby_soup");
    /**
     * Music
     */
    public static final ItemRecordGasolineBabies RECORD_GASOLINE_BABIES_ITEM = new ItemRecordGasolineBabies("record_gasoline_babies");
    public static final ItemRecordGodsPee RECORD_GODS_PEE = new ItemRecordGodsPee("record_gods_pee");
    public static final ItemRecordSmartMan1 RECORD_SMART_MAN_1 = new ItemRecordSmartMan1("record_smart_man1");
    public static final ItemRecordSmartMan2 RECORD_SMART_MAN_2 = new ItemRecordSmartMan2("record_smart_man2");
    public static final ItemRecordSmartMan3 RECORD_SMART_MAN_3 = new ItemRecordSmartMan3("record_smart_man3");
    public static final ItemBanjo BANJO_ITEM = new ItemBanjo("banjo");
    public static final ItemStringBass STRING_BASS_ITEM = new ItemStringBass("string_bass");

    /**
     * Weapons
     */
    public static final ItemHairDryerGun HAIR_DRYER_GUN_ITEM = new ItemHairDryerGun("hair_dryer_gun");
    public static final ItemStupidTNTGun STUPID_TNT_GUN_ITEM = new ItemStupidTNTGun("stupid_tnt_gun");
    public static final ItemStupidTNTCartridge STUPID_TNT_CARTRIDGE_ITEM = new ItemStupidTNTCartridge("stupid_tnt_cartridge");
    public static final ItemHuntingRifle HUNTING_RIFLE = new ItemHuntingRifle("hunting_rifle");
    public static final ItemRifleCartridge RIFLE_CARTRIDGE = new ItemRifleCartridge("rifle_cartridge");
    public static final ItemStupidCore STUPID_CORE_ITEM = new ItemStupidCore("stupid_core");
    public static final ItemPoliceGun POLICE_GUN_ITEM = new ItemPoliceGun("police_gun");
    public static final ItemBullet BULLET_ITEM = new ItemBullet("bullet");

    /**
     * Apparel
     */
    public static  final ItemCowboyHat COWBOY_HAT_ITEM = new ItemCowboyHat("cowboy_hat");
    public static  final ItemSantaHat SANTA_HAT_ITEM = new ItemSantaHat("santa_hat");
    public static final ItemCamoChestplate CAMO_CHESTPLATE_ITEM = new ItemCamoChestplate("camo_chestplate");
    public static final ItemHillbillyHat HILLBILLY_HAT_ITEM = new ItemHillbillyHat("hillbilly_hat");

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Item item: ITEMS) {
            registerRender(item);
        }
    }
    public static void init(){
        for(Item item:ITEMS){
            ForgeRegistries.ITEMS.register(item);
        }
    }
    public static void initOreDictionary(){
        for(Item item:ITEMS){
            if(item instanceof IOreDictItem) {
                ((IOreDictItem)item).initOreDict();
            }
        }
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }

}
