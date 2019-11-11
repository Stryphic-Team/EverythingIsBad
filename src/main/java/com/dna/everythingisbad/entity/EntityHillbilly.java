package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.ai.EntityAIPoliceShootGun;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.init.ModSoundEvents;
import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.utils.handlers.MidiHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityHillbilly extends EntitySkeleton implements IRangedAttackMob {
    // KEY: Tick; what tick to play the note on within the cycle
    // VALUE: Note value; MIDI note number
    //HashMap<Integer,Integer> hillbillyNotes = new HashMap<>();
    //Set keys = hillbillyNotes.keySet();

    // The length of the array is being abused right now as a "Number of ticks before looping"
    SoundFrame[] banjoSong = new SoundFrame[120];
    SoundFrame[] bassSong = new SoundFrame[120];
    int songTick;

    public EntityHillbilly(World worldIn) {
        super(worldIn);

        banjoSong[0] = new SoundFrame(0,51, 63); // Eb Eb
        banjoSong[1] = new SoundFrame(15,58, 63, 67); //Eb major
        banjoSong[2] = new SoundFrame(30,53); // F
        banjoSong[3] = new SoundFrame(40,55); // G
        banjoSong[4] = new SoundFrame(45,58,63); // Bb Eb
        banjoSong[5] = new SoundFrame(60,53); // F
        banjoSong[6] = new SoundFrame(70,55); // G
        banjoSong[7] = new SoundFrame(75,58,63); // Bb Eb
        banjoSong[8] = new SoundFrame(85,53); // F
        banjoSong[9] = new SoundFrame(100,55); // G
        banjoSong[10] = new SoundFrame(105,48,60); // C C

        bassSong[0] = new SoundFrame(0,63); // Eb
        bassSong[1] = new SoundFrame(30, 58); // Bb
        bassSong[2] = new SoundFrame(60,63); // Eb
        bassSong[3] = new SoundFrame(75, 58); // Bb
        bassSong[4] = new SoundFrame(90,60); // C
        bassSong[5] = new SoundFrame(105,62); // D
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata =  super.onInitialSpawn(difficulty, livingdata);
        // Gives the hillbillies their gear
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.HILLBILLY_HAT_ITEM));
        int threeSidedDie = RandomUtils.fromRangeI(0,3);
        if (threeSidedDie == 0) {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.BANJO_ITEM));
        }else if (threeSidedDie == 1){
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.STRING_BASS_ITEM));
        }else{
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.HUNTING_RIFLE));
        }
        return livingdata;
    }
    @Override
    public ResourceLocation getLootTable()
    {
        return ModLootTables.ENTITY_HILLBILLY_LOOT;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        SoundFrame[] song = new SoundFrame[1];
        if (this.getHeldItemMainhand().getItem() == ModItems.BANJO_ITEM){
            song = banjoSong;
        }else if (this.getHeldItemMainhand().getItem() == ModItems.STRING_BASS_ITEM){
            song = bassSong;
        }
        songTick = (int)this.world.getWorldTime() % song.length;
        for (SoundFrame frame : song){
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

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityBullet bulet = new EntityBullet(this.world,this);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - bulet.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        bulet.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(bulet);
    }
    @Override
    public void setCombatTask() {
        super.setCombatTask();
        EntityAIPoliceShootGun doodly = new EntityAIPoliceShootGun(this, 1.0D, 20, 15.0F);
        this.tasks.addTask(4,doodly);
    }
    @Override
    protected void initEntityAI()
    {
        // normal stuff
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

        // Hunting the player is more prioritised than aminals
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

        // Hunting aminals (small aminals are less prioritised than big ones)
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCow.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySheep.class, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityChicken.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPig.class, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityRabbit.class, true));
    }

    @Override
    public boolean getCanSpawnHere() {
        if (this.rand.nextFloat() < 0.01){

            // All of this is so that we skip the EntityMob's light level requirement
            IBlockState iblockstate = this.world.getBlockState((new BlockPos(this)).down());
            return this.world.getDifficulty() != EnumDifficulty.PEACEFUL; //&&
            //this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F &&
            //iblockstate.canEntitySpawn(this);

        }else{
            return false;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSoundEvents.SOUND_EVENT_HILLBILLY_HURT[rand.nextInt(4)];
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.SOUND_EVENT_HILLBILLY_HURT[rand.nextInt(4)];
    }

    @Override
    protected float getSoundVolume() {
        return 1f;
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }
}

class SoundFrame {
    private int tick;
    private int note1;
    private int note2;
    private int note3;

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
