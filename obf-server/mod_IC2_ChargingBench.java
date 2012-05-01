// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   mod_IC2_ChargingBench.java

import forge.Configuration;
import forge.Property;
import ic2.api.Items;
import ic2.platform.Platform;
import ic2chargingbench.common.*;
import ic2chargingbench.platform.ChargingBenchMod;
import java.io.File;
import java.io.PrintStream;

public class mod_IC2_ChargingBench extends ChargingBenchMod
{

    public mod_IC2_ChargingBench()
    {
    }

    public void load()
    {
        blockChargingBench = new BlockChargingBench(idBlockChargingBench);
        ModLoader.registerBlock(blockChargingBench, ic2chargingbench/common/ItemChargingBench);
        ModLoader.registerTileEntity(ic2chargingbench/common/TileEntityChargingBench1, "Charging Bench Mk1");
        ModLoader.registerTileEntity(ic2chargingbench/common/TileEntityChargingBench2, "Charging Bench Mk2");
        ModLoader.registerTileEntity(ic2chargingbench/common/TileEntityChargingBench3, "Charging Bench Mk3");
        ModLoader.addRecipe(new kp(blockChargingBench, 1, 0), new Object[] {
            "UUU", "W W", "WWW", Character.valueOf('U'), Items.getItem("copperCableItem"), Character.valueOf('W'), vz.x
        });
        ModLoader.addRecipe(new kp(blockChargingBench, 1, 1), new Object[] {
            "UUU", "WCW", "WWW", Character.valueOf('U'), Items.getItem("goldCableItem"), Character.valueOf('W'), vz.x, Character.valueOf('C'), Items.getItem("electronicCircuit")
        });
        ModLoader.addRecipe(new kp(blockChargingBench, 1, 2), new Object[] {
            "UUU", "WCW", "WWW", Character.valueOf('U'), Items.getItem("ironCableItem"), Character.valueOf('W'), vz.x, Character.valueOf('C'), Items.getItem("advancedCircuit")
        });
    }

    public String getVersion()
    {
        return "1.90-1";
    }

    public static Configuration config;
    public static int idBlockChargingBench;
    public static vz blockChargingBench;
    public static int guiIdChargingBench;

    static 
    {
        try
        {
            config = new Configuration(new File((new StringBuilder()).append(Platform.getMinecraftDir()).append("/config/IC2ChargingBench.cfg").toString()));
            config.load();
            idBlockChargingBench = Integer.valueOf(config.getOrCreateIntProperty("blockChargingBench", "block", 189).value).intValue();
            guiIdChargingBench = Integer.valueOf(config.getOrCreateIntProperty("guiIdChargingBench", "general", 110).value).intValue();
            config.save();
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("[ChargingBench] Error while trying to access configuration!").toString());
            throw new RuntimeException(e);
        }
    }
}
