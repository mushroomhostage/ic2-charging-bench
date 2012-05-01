// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockChargingBench.java

package ic2chargingbench.common;

import ge;
import ic2.common.BlockMultiID;
import ic2.common.TileEntityBlock;
import ic2.platform.Platform;
import ic2chargingbench.platform.ChargingBenchMod;
import ih;
import java.util.Random;
import na;
import qj;

// Referenced classes of package ic2chargingbench.common:
//            TileEntityChargingBench1, TileEntityChargingBench2, TileEntityChargingBench3

public class BlockChargingBench extends BlockMultiID
{

    public BlockChargingBench(int id)
    {
        super(id, na.d);
        c(1.0F);
    }

    public boolean a(ge world, int i, int j, int k, ih entityplayer)
    {
        if(Platform.isSimulating())
            return ChargingBenchMod.launchGUI(entityplayer, world.b(i, j, k));
        else
            return true;
    }

    public TileEntityBlock getBlockEntity(int meta)
    {
        switch(meta)
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

    public int a(int i, Random random, int j)
    {
        return bO;
    }

    protected int c(int i)
    {
        return i;
    }

    public volatile qj getBlockEntity(int x0)
    {
        return getBlockEntity(x0);
    }
}
