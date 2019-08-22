package com.dna.everythingisbad.init;


import com.dna.everythingisbad.item.*;
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

    public static final ItemFungOs FUNG_OS_ITEM = new ItemFungOs("fung_os");
    public static final ItemHotCheaters HOT_CHEATERS_ITEM = new ItemHotCheaters("hot_cheaters");
    public static final ItemJesusMeatRaw JESUS_MEAT_RAW = new ItemJesusMeatRaw("jesus_meat_raw");
    public static final ItemJesusMeatCooked JESUS_MEAT_COOKED = new ItemJesusMeatCooked("jesus_meat_cooked");
    public static final ItemDevilsCabbage DEVILS_CABBAGE_ITEM = new ItemDevilsCabbage("devils_cabbage");
    public static final ItemChickenSoup CHICKEN_SOUP_ITEM = new ItemChickenSoup("chicken_soup");

    public static final ItemRecordGasolineBabies RECORD_GASOLINE_BABIES_ITEM = new ItemRecordGasolineBabies("record_gasoline_babies");
    public static final ItemRecordGodsPee RECORD_GODS_PEE = new ItemRecordGodsPee("record_gods_pee");
    public static final ItemRecordSmartMan1 RECORD_SMART_MAN_1 = new ItemRecordSmartMan1("record_smart_man1");
    public static final ItemRecordSmartMan2 RECORD_SMART_MAN_2 = new ItemRecordSmartMan2("record_smart_man2");
    public static final ItemRecordSmartMan3 RECORD_SMART_MAN_3 = new ItemRecordSmartMan3("record_smart_man3");

    public static final ItemHairDryerGun HAIR_DRYER_GUN_ITEM = new ItemHairDryerGun("hair_dryer_gun");
    public static final ItemStupidTNTGun STUPID_TNT_GUN_ITEM = new ItemStupidTNTGun("stupid_tnt_gun");
    public static final ItemStupidTNTCartridge STUPID_TNT_CARTRIDGE_ITEM = new ItemStupidTNTCartridge("stupid_tnt_cartridge");
    public static final ItemStupidCore STUPID_CORE = new ItemStupidCore("stupid_core");

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
