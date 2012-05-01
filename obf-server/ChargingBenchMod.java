// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChargingBenchMod.java

package ic2chargingbench.platform;

import BaseModMp;
import ModLoader;
import ic2chargingbench.common.TileEntityChargingBench;
import ih;
import mod_IC2_ChargingBench;
import ni;
import qj;

public abstract class ChargingBenchMod extends BaseModMp
{

    public ChargingBenchMod()
    {
    }

    public static boolean launchGUI(ih entityplayer, qj te)
    {
        ModLoader.openGUI(entityplayer, mod_IC2_ChargingBench.guiIdChargingBench, (ni)te, ((TileEntityChargingBench)te).getGuiContainer(entityplayer.k));
        return true;
    }
}
