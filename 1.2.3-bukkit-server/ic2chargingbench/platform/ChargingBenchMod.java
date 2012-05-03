package ic2chargingbench.platform;

import ic2chargingbench.common.TileEntityChargingBench;
import forge.*;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.ModLoader;
import net.minecraft.server.TileEntity;
import net.minecraft.server.mod_IC2_ChargingBench;

public abstract class ChargingBenchMod extends NetworkMod {

   public static boolean launchGUI(EntityHuman entityhuman, TileEntity tileentity) {
      ModLoader.openGUI(entityhuman, mod_IC2_ChargingBench.guiIdChargingBench, (IInventory)tileentity, ((TileEntityChargingBench)tileentity).getGuiContainer(entityhuman.inventory));
      return true;
   }
}
