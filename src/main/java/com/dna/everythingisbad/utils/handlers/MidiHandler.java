//
// This code is from sjlevine29 on stack overflow, Thank you
// https://stackoverflow.com/questions/6937760/java-getting-input-from-midi-keyboard
//

package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModSoundEvents;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessagePlayNote;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.sound.midi.*;
import java.util.List;

public class MidiHandler {
    MidiDevice device;
    public MidiHandler() {

    }
    public void init() {
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
                            new MidiInputReceiver(device.getDeviceInfo().toString())
                    );
                }

                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

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

        public MidiInputReceiver(String name) {
            this.name = name;

        }
        public void send(MidiMessage msg, long timeStamp) {
            byte[] data = msg.getMessage();
            int note;

            int statusbyte = (data[0] & 0xFF); // Makes them unsinged
            int notebyte = (data[1] & 0xFF);


            if (statusbyte == 144){
                note = notebyte; // set the note value as an unsigned int
                //Sends a MessagePlayNote to the server and the server triggers a sound event
                PacketHandler.INSTANCE.sendToServer(new MessagePlayNote(note,0));
            }
        }
        public void close() {}
    }
    enum EnumInstrument{
        BANJO,
        BASS,
        NONE
    }
    //Is only used in the MessagePlayNoteHandler for triggering note events when the player sends a MessagePlayNote to the server
    public static void PlayNote(int notenumber, int instrumentId, EntityPlayer entityPlayer) {
        World worldIn = entityPlayer.getEntityWorld();
        float note_coefficient = (float) Math.pow(2, (((float) notenumber - 60) / 12));
        //gets the name of the item in the player's main hand
        String current_hand = entityPlayer.inventory.getCurrentItem().getUnlocalizedName();
        //stores the current enum of the instrument
        EnumInstrument currentInstrument = EnumInstrument.NONE;
        if (current_hand.equals("item.everythingbad:banjo")) {
            currentInstrument = EnumInstrument.BANJO;
        } else if (current_hand.equals("item.everythingbad:string_bass")) {
            currentInstrument = EnumInstrument.BASS;
        }
        //currentInstrument = current_hand.equals("item.everythingbad:banjo") ? EnumInstrument.BANJO : EnumInstrument.NONE;
        //this checks to see which instrument and if the player has a instrument in their hand
        //If so it will play a note with that instrument sound
        switch (currentInstrument){
            case BANJO:
                worldIn.playSound((EntityPlayer)null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, ModSoundEvents.SOUND_EVENT_BANJO, SoundCategory.PLAYERS, 2F, note_coefficient);
                worldIn.spawnParticle(EnumParticleTypes.NOTE, entityPlayer.posX, entityPlayer.posY + 2.2D, entityPlayer.posZ, 0.0D, 0.0D, 0.0D);
                break;
            case BASS:
                worldIn.playSound((EntityPlayer)null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, ModSoundEvents.SOUND_EVENT_STRING_BASS, SoundCategory.PLAYERS, 2F, note_coefficient);
                worldIn.spawnParticle(EnumParticleTypes.NOTE, entityPlayer.posX, entityPlayer.posY + 2.2D, entityPlayer.posZ, 0.0D, 0.0D, 0.0D);
                break;
            case NONE:
                break;
        }



    }
}
