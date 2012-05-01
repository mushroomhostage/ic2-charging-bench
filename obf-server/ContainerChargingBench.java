// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContainerChargingBench.java

package ic2chargingbench.common;

import ff;
import ic2.common.ContainerIC2;
import ig;
import ih;
import java.util.List;
import ko;

// Referenced classes of package ic2chargingbench.common:
//            TileEntityChargingBench

public class ContainerChargingBench extends ContainerIC2
{

    public ContainerChargingBench(ko inventoryplayer, TileEntityChargingBench tileentity)
    {
        this.tileentity = tileentity;
        energy = 0;
        maxInput = 0;
        a(new ig(tileentity, 0, 61, 19));
        a(new ig(tileentity, 1, 79, 19));
        a(new ig(tileentity, 2, 97, 19));
        a(new ig(tileentity, 3, 115, 19));
        a(new ig(tileentity, 4, 61, 37));
        a(new ig(tileentity, 5, 79, 37));
        a(new ig(tileentity, 6, 97, 37));
        a(new ig(tileentity, 7, 115, 37));
        a(new ig(tileentity, 8, 61, 55));
        a(new ig(tileentity, 9, 79, 55));
        a(new ig(tileentity, 10, 97, 55));
        a(new ig(tileentity, 11, 115, 55));
        a(new ig(tileentity, 12, 24, 40));
        a(new ig(tileentity, 13, 152, 8));
        a(new ig(tileentity, 14, 152, 26));
        a(new ig(tileentity, 15, 152, 44));
        a(new ig(tileentity, 16, 152, 62));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
                a(new ig(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));

        }

        for(int j = 0; j < 9; j++)
            a(new ig(inventoryplayer, j, 8 + j * 18, 142));

    }

    public void a()
    {
        super.a();
        for(int i = 0; i < g.size(); i++)
        {
            ff icrafting = (ff)g.get(i);
            if(energy != tileentity.energy)
            {
                icrafting.a(this, 0, tileentity.energy & 0xffff);
                icrafting.a(this, 1, tileentity.energy >>> 16);
            }
            if(maxInput != tileentity.maxInput)
                icrafting.a(this, 2, tileentity.maxInput);
        }

        energy = tileentity.energy;
        maxInput = (short)tileentity.maxInput;
    }

    public void updateProgressBar(int i, int j)
    {
        switch(i)
        {
        case 0: // '\0'
            tileentity.energy = tileentity.energy & 0xffff0000 | j;
            break;

        case 1: // '\001'
            tileentity.energy = tileentity.energy & 0xffff | j << 16;
            break;

        case 2: // '\002'
            tileentity.maxInput = j;
            break;
        }
    }

    public int guiInventorySize()
    {
        return 17;
    }

    public int getInput()
    {
        return 0;
    }

    public boolean b(ih entityplayer)
    {
        return tileentity.a(entityplayer);
    }

    public TileEntityChargingBench tileentity;
    public int energy;
    public short maxInput;
}
