// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockChargingBench.java

package ic2chargingbench.common;

import ic2.common.BlockMultiID;
import ic2.common.TileEntityBlock;
import ic2.platform.Platform;
import java.util.Random;
import net.minecraft.server.*;

// Referenced classes of package ic2chargingbench.common:
//            TileEntityChargingBench1, TileEntityChargingBench2, TileEntityChargingBench3

public class BlockChargingBench extends BlockMultiID
{

    public BlockChargingBench(int i)
    {
        super(i, Material.WOOD);
        c(1.0F);
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman)
    {
        TileEntity tileentity = world.getTileEntity(i, j, k);

        System.out.println("interact tileentity="+tileentity);

        if(tileentity == null || !(tileentity instanceof TileEntityChargingBench)) {
            return true;
        }

        entityhuman.openGui(mod_IC2_ChargingBench.instance, mod_IC2_ChargingBench.guiIdChargingBench, world, i, j, k);

        return true;
    }

    public TileEntityBlock getBlockEntity(int i)
    {
        switch(i)
        {
        case 0: // '\0'
            return new TileEntityChargingBench1();

        case 1: // '\001'
            return new TileEntityChargingBench2();

        case 2: // '\002'
            return new TileEntityChargingBench3();
        }
        return null;
    }

    public String getTextureFile()
    {
        return "/ic2/sprites/ChargingBench.png";
    }

    public int getDropType(int i, Random random, int j)
    {
        return id;
    }

    protected int getDropData(int i)
    {
        return i;
    }
}
