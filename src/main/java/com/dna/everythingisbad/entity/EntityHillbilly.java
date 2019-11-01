package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.utils.handlers.MidiHandler;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class EntityHillbilly extends EntityZombie {
    // KEY: Tick; what tick to play the note on within the cycle
    // VALUE: Note value; MIDI note number
    //HashMap<Integer,Integer> hillbillyNotes = new HashMap<>();
    //Set keys = hillbillyNotes.keySet();

    SoundFrame[] hillbillySong = new SoundFrame[120];
    int songTick;

    public EntityHillbilly(World worldIn) {
        super(worldIn);
        hillbillySong[0] = new SoundFrame(0,51); // Eb
        hillbillySong[1] = new SoundFrame(15,58, 63, 67); //Eb major
        hillbillySong[2] = new SoundFrame(30,53); // F
        hillbillySong[3] = new SoundFrame(40,55); // G
        hillbillySong[4] = new SoundFrame(45,58,63); // Bb Eb
        hillbillySong[5] = new SoundFrame(60,53); // F
        hillbillySong[6] = new SoundFrame(70,55); // G
        hillbillySong[7] = new SoundFrame(75,58,63); // Bb Eb
        hillbillySong[8] = new SoundFrame(85,53); // F
        hillbillySong[9] = new SoundFrame(100,55); // G
        hillbillySong[10] = new SoundFrame(105,48,60); // C C
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata =  super.onInitialSpawn(difficulty, livingdata);
        // Gives the hillbillies their gear
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.HILLBILLY_HAT_ITEM));
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.BANJO_ITEM));

        return livingdata;
    }

    @Override
    public boolean isArmsRaised() {
        return false;
    }
    @Override
    public ResourceLocation getLootTable()
    {
        return ModLootTables.ENTITY_HILLBILLY_LOOT;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        songTick = this.ticksExisted % hillbillySong.length;
        for (SoundFrame frame : hillbillySong){
            if (frame != null){
                if (frame.getTick() + 1 == songTick){
                    int[] notes = frame.getNotes();
                    if (notes[0] != -1){
                        MidiHandler.PlayNote(notes[0],0,this);
                    }
                    if (notes[1] != -1){
                        MidiHandler.PlayNote(notes[1],0,this);
                    }
                    if (notes[2] != -1){
                        MidiHandler.PlayNote(notes[2],0,this);
                    }
                }
            }
        }
    }
}

class SoundFrame {
    int tick;
    int note1;
    int note2;
    int note3;

    SoundFrame(int tick, int note1, int note2, int note3){
        this.tick = tick;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
    }

    SoundFrame(int tick, int note1, int note2){
        this(tick, note1, note2,-1);
    }

    SoundFrame(int tick, int note1){
        this(tick, note1,-1,-1);
    }

    public int getTick(){
        return this.tick;
    }

    public int[] getNotes(){
        int[] notes = new int[3];
        notes[0] = note1;
        notes[1] = note2;
        notes[2] = note3;
        return notes;
    }
}
