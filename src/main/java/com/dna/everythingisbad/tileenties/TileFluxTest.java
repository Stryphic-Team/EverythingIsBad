package com.dna.everythingisbad.tileenties;
import cofh.redstoneflux.api.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;



public class TileFluxTest extends TileEntity implements IEnergyStorage, ITickable {
    private int energyStored = 10000;

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        energyStored -= maxExtract;
        if(energyStored > maxExtract){
            return maxExtract;
        }else{
            return 0;
        }

    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored() {
        return 1000000;
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY;
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        energyStored = nbt.getInteger("energyStored");
        super.readFromNBT(nbt);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        nbt.setInteger("energyStored",energyStored);

        return nbt;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, final EnumFacing from) {

        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new net.minecraftforge.energy.IEnergyStorage() {

                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {

                    return TileFluxTest.this.receiveEnergy(maxReceive, simulate);
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {

                    return TileFluxTest.this.extractEnergy(maxExtract, simulate);
                }

                @Override
                public int getEnergyStored() {

                    return TileFluxTest.this.getEnergyStored();
                }

                @Override
                public int getMaxEnergyStored() {

                    return TileFluxTest.this.getMaxEnergyStored();
                }

                @Override
                public boolean canExtract() {

                    return true;
                }

                @Override
                public boolean canReceive() {

                    return false;
                }
            });
        }
        return super.getCapability(capability, from);
    }
    class FaceData{
        public TileEntity tileEntity;
        public EnumFacing facing;
        public FaceData(BlockPos pos,EnumFacing facing){
            World world = getWorld();
            this.tileEntity = world.getTileEntity(pos);
            this.facing = facing;
        }
    }

    @Override
    public void update() {


        FaceData east = new FaceData(getPos().east(),EnumFacing.EAST);
        FaceData west = new FaceData(getPos().west(),EnumFacing.WEST);
        FaceData north = new FaceData(getPos().north(),EnumFacing.NORTH);
        FaceData south = new FaceData(getPos().south(),EnumFacing.SOUTH);
        FaceData up = new FaceData(getPos().up(),EnumFacing.UP);
        FaceData down = new FaceData(getPos().down(),EnumFacing.DOWN);


        FaceData[] faceData = new FaceData[]{
          east,west,north,south,up,down
        };
        for(FaceData face:faceData){
            if(face.tileEntity != null) {
                net.minecraftforge.energy.IEnergyStorage cap = (net.minecraftforge.energy.IEnergyStorage) face.tileEntity.getCapability(
                        CapabilityEnergy.ENERGY,
                        face.facing
                );
                if (cap != null) {
                    cap.receiveEnergy(1, false);
                    extractEnergy(1,false);
                }

            }
        }

    }
    static {
        register("flux_tes ", TileFluxTest.class);
    }
}
