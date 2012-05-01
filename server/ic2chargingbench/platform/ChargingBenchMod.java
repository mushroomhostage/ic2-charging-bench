package ic2chargingbench.platform;

import ic2chargingbench.common.TileEntityChargingBench;
import net.minecraft.src.*;

public abstract class ChargingBenchMod extends BaseModMp
{
    public ChargingBenchMod()
    {
    }

    public static boolean launchGUI(EntityPlayer entityplayer, TileEntity tileentity)
    {
        ModLoader.openGUI(entityplayer, mod_IC2_ChargingBench.guiIdChargingBench, (IInventory)tileentity, ((TileEntityChargingBench)tileentity).getGuiContainer(entityplayer.inventory));
        return true;
    }
}
