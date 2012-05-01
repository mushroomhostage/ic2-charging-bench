package ic2chargingbench.platform;

import ic2chargingbench.common.TileEntityChargingBench;

public abstract class ChargingBenchMod extends BaseModMp {

   public static boolean launchGUI(ih entityplayer, qj te) {
      ModLoader.openGUI(entityplayer, mod_IC2_ChargingBench.guiIdChargingBench, (ni)te, ((TileEntityChargingBench)te).getGuiContainer(entityplayer.k));
      return true;
   }
}
