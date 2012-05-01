package net.minecraft.src;

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
    public static Configuration config;
    public static int idBlockChargingBench;
    public static Block blockChargingBench;
    public static int guiIdChargingBench;

    public mod_IC2_ChargingBench()
    {
    }

    public void load()
    {
        blockChargingBench = new BlockChargingBench(idBlockChargingBench);
        ModLoader.registerBlock(blockChargingBench, ic2chargingbench.common.ItemChargingBench.class);
        ModLoader.registerTileEntity(ic2chargingbench.common.TileEntityChargingBench1.class, "Charging Bench Mk1");
        ModLoader.registerTileEntity(ic2chargingbench.common.TileEntityChargingBench2.class, "Charging Bench Mk2");
        ModLoader.registerTileEntity(ic2chargingbench.common.TileEntityChargingBench3.class, "Charging Bench Mk3");
        ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 0), new Object[]
                {
                    "UUU", "W W", "WWW", 'U', Items.getItem("copperCableItem"), 'W', Block.planks
                });
        ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 1), new Object[]
                {
                    "UUU", "WCW", "WWW", 'U', Items.getItem("goldCableItem"), 'W', Block.planks, 'C', Items.getItem("electronicCircuit")
                });
        ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 2), new Object[]
                {
                    "UUU", "WCW", "WWW", 'U', Items.getItem("ironCableItem"), 'W', Block.planks, 'C', Items.getItem("advancedCircuit")
                });
    }

    public String getVersion()
    {
        return "1.90-1";
    }

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
        catch (Exception exception)
        {
            System.out.println((new StringBuilder()).append("[ChargingBench] Error while trying to access configuration!").toString());
            throw new RuntimeException(exception);
        }
    }
}
