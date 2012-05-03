// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChargingBenchMod.java

//package ic2chargingbench.platform;

import forge.*;
import ic2chargingbench.common.TileEntityChargingBench;
import net.minecraft.client.Minecraft;

// Referenced classes of package ic2chargingbench.platform:
//            GuiChargingBench

public abstract class ChargingBenchMod extends NetworkMod implements IGuiHandler
{

    public ChargingBenchMod()
    {
        ModLoader.addLocalization("blockChargingBench1.name", "Charging Bench Mk1");
        ModLoader.addLocalization("blockChargingBench2.name", "Charging Bench Mk2");
        ModLoader.addLocalization("blockChargingBench3.name", "Charging Bench Mk3");
        MinecraftForgeClient.preloadTexture("/ic2/sprites/ChargingBench.png");
        MinecraftForge.setGuiHandler(this, this);
    }

    public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    }

    public static boolean launchGUI(yw entityplayer, kw te)
    {
        ModLoader.openGUI(entityplayer, new GuiChargingBench(entityplayer.ap, (TileEntityChargingBench)te));
        return true;
    }

    public vp handleGUI(int screenID)
    {
        yw player = ModLoader.getMinecraftInstance().h;
        if(player == null)
            return null;
        else
            return new GuiChargingBench(player.ap, new TileEntityChargingBench(0));
    }
}
