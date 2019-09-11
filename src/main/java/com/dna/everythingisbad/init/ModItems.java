package com.dna.everythingisbad.init;


import com.dna.everythingisbad.item.ItemPoop;
import com.dna.everythingisbad.item.ItemPoopBrick;
import com.dna.everythingisbad.item.ItemSoul;
import com.dna.everythingisbad.item.ItemStupidCore;
import com.dna.everythingisbad.item.armor.ItemCamoChestplate;
import com.dna.everythingisbad.item.armor.ItemCowboyHat;
import com.dna.everythingisbad.item.armor.ItemSantaHat;
import com.dna.everythingisbad.item.food.*;
import com.dna.everythingisbad.item.instruments.ItemBanjo;
import com.dna.everythingisbad.item.instruments.ItemStringBass;
import com.dna.everythingisbad.item.plants.ItemTobaccoLeaf;
import com.dna.everythingisbad.item.plants.ItemTobaccoSeeds;
import com.dna.everythingisbad.item.records.*;
import com.dna.everythingisbad.item.weapons.ItemHairDryerGun;
import com.dna.everythingisbad.item.weapons.ItemStupidTNTCartridge;
import com.dna.everythingisbad.item.weapons.ItemStupidTNTGun;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final ItemPoop POOP_ITEM = new ItemPoop("poop");
    public static final ItemPoopBrick POOP_BRICK_ITEM = new ItemPoopBrick("poop_brick");
    public static final ItemWeed WEED_ITEM = new ItemWeed("weed");
    public static final ItemPaperBag PAPER_BAG_ITEM = new ItemPaperBag("paper_bag");
    public static final ItemSoul SOUL_ITEM = new ItemSoul("soul");
    public static final ItemTobaccoLeaf TOBACCO_LEAF_ITEM = new ItemTobaccoLeaf("tobacco_leaf");
    public static final ItemTobaccoSeeds TOBACCO_SEEDS_ITEM = new ItemTobaccoSeeds("tobacco_seeds");

    public static final ItemFungOs FUNG_OS_ITEM = new ItemFungOs("fung_os");
    public static final ItemHotCheaters HOT_CHEATERS_ITEM = new ItemHotCheaters("hot_cheaters");
    public static final ItemJesusMeatRaw JESUS_MEAT_RAW = new ItemJesusMeatRaw("jesus_meat_raw");
    public static final ItemJesusMeatCooked JESUS_MEAT_COOKED = new ItemJesusMeatCooked("jesus_meat_cooked");
    public static final ItemDevilsCabbage DEVILS_CABBAGE_ITEM = new ItemDevilsCabbage("devils_cabbage");
    public static final ItemChickenSoup CHICKEN_SOUP_ITEM = new ItemChickenSoup("chicken_soup");
    public static final ItemAloeLeaf ALOE_LEAF_ITEM = new ItemAloeLeaf("aloe_leaf");
    public static final ItemFriedDragonEgg FRIED_DRAGON_EGG_ITEM = new ItemFriedDragonEgg("fried_dragon_egg");

    public static final ItemRecordGasolineBabies RECORD_GASOLINE_BABIES_ITEM = new ItemRecordGasolineBabies("record_gasoline_babies");
    public static final ItemRecordGodsPee RECORD_GODS_PEE = new ItemRecordGodsPee("record_gods_pee");
    public static final ItemRecordSmartMan1 RECORD_SMART_MAN_1 = new ItemRecordSmartMan1("record_smart_man1");
    public static final ItemRecordSmartMan2 RECORD_SMART_MAN_2 = new ItemRecordSmartMan2("record_smart_man2");
    public static final ItemRecordSmartMan3 RECORD_SMART_MAN_3 = new ItemRecordSmartMan3("record_smart_man3");
    public static final ItemBanjo BANJO_ITEM = new ItemBanjo("banjo");
    public static final ItemStringBass STRING_BASS_ITEM = new ItemStringBass("string_bass");

    public static final ItemHairDryerGun HAIR_DRYER_GUN_ITEM = new ItemHairDryerGun("hair_dryer_gun");
    public static final ItemStupidTNTGun STUPID_TNT_GUN_ITEM = new ItemStupidTNTGun("stupid_tnt_gun");
    public static final ItemStupidTNTCartridge STUPID_TNT_CARTRIDGE_ITEM = new ItemStupidTNTCartridge("stupid_tnt_cartridge");
    public static final ItemStupidCore STUPID_CORE_ITEM = new ItemStupidCore("stupid_core");

    public static  final ItemCowboyHat COWBOY_HAT_ITEM = new ItemCowboyHat("cowboy_hat");
    public static  final ItemSantaHat SANTA_HAT_ITEM = new ItemSantaHat("santa_hat");
    public static final ItemCamoChestplate CAMO_CHESTPLATE_ITEM = new ItemCamoChestplate("camo_chestplate");

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Item item: ITEMS) {
            registerRender(item);
        }
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }

}
