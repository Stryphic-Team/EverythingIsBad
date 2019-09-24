package com.dna.everythingisbad.block.plants;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class BlockLogHappy extends BlockLogBase {

    //public static final PropertyEnum<EnumAxis> LOG_HAPPY_AXIS = PropertyEnum.<BlockLog.EnumAxis>create("axis", BlockLogHappy.EnumAxis.class);

    public BlockLogHappy(String name){
        super(name);
        setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = this.getDefaultState();

        switch(meta & 6)
        {
            case 0:
                state = state.withProperty(LOG_AXIS, EnumAxis.Y);
                break;

            case 2:
                state = state.withProperty(LOG_AXIS, EnumAxis.X);
                break;

            case 4:
                state = state.withProperty(LOG_AXIS, EnumAxis.Z);
                break;

            default:
                state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
        }

        return state;
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
        {
            case X:
                i |= 2;
                break;

            case Y:
                i |= 4;
                break;

            case Z:
                i |= 6;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
    }
}
