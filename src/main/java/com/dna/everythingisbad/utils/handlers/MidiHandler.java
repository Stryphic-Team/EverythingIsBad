//
// This code is from sjlevine29 on stack overflow, Thank you
// https://stackoverflow.com/questions/6937760/java-getting-input-from-midi-keyboard
//

package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModSoundEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

public class MidiHandler {
    MidiDevice device;
    public MidiHandler() {

    }
    public void init(EntityPlayer player) {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                //does the device have any transmitters?
                //if it does, add it to the device list
                Main.logger.info(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter

                for(int j = 0; j<transmitters.size();j++) {
                    //create a new receiver
                    transmitters.get(j).setReceiver(
                            //using my own MidiInputReceiver
                            new MidiInputReceiver(device.getDeviceInfo().toString(),player)
                    );
                }

                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString(),player));

                //open each device
                device.open();
                //if code gets this far without throwing an exception
                //print a success message
                Main.logger.info(device.getDeviceInfo()+" Was Opened");


            } catch (MidiUnavailableException e) {}
        }
    }

    public class MidiInputReceiver implements Receiver {
        String name;
        EntityPlayer player;
        public MidiInputReceiver(String name,EntityPlayer player) {
            this.name = name;
            this.player = player;
        }
        public void send(MidiMessage msg, long timeStamp) {
            byte[] data = msg.getMessage();
            int note;

            byte statusbyte = (byte) (data[0] & 0xFF); // Makes them unsinged
            byte notebyte = (byte) (data[1] & 0xFF);
            //Main.logger.info(statusbyte);
            //Main.logger.info(notebyte);

            if (statusbyte == -112){
                note = notebyte; // set the note value as an unsigned int
                //Main.logger.info("note onset detected");

                ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                if (itemstack.getItem() == ModItems.BANJO_ITEM) {
                    PlayNote(note, player);
                }
            }
        }
        public void close() {}
    }
    public void PlayNote(int notenumber,EntityPlayer entityplayer){
        World worldIn = entityplayer.getEntityWorld();
        //Main.logger.info("sound played");

        float note_coefficient = (float)Math.pow(2,(((float)notenumber-60)/12));
        //Main.logger.info(note_coefficient);
        worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, ModSoundEvents.SOUND_EVENT_BANJO, SoundCategory.PLAYERS, 1F, note_coefficient);
    }
}
