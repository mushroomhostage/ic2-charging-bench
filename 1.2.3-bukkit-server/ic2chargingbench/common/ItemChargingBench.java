// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ItemChargingBench.java

package ic2chargingbench.common;

import ic2.platform.ItemBlockCommon;
import net.minecraft.server.ItemStack;

public class ItemChargingBench extends ItemBlockCommon
{

    public ItemChargingBench(int i)
    {
        super(i);
        setMaxDurability(0);
        a(true);
    }

    public int filterData(int i)
    {
        return i;
    }

    public String a(ItemStack itemstack)
    {
        int i = itemstack.getData();
        switch(i)
        {
        case 0: // '\0'
            return "blockChargingBench1";

        case 1: // '\001'
            return "blockChargingBench2";

        case 2: // '\002'
            return "blockChargingBench3";
        }
        return null;
    }
}
