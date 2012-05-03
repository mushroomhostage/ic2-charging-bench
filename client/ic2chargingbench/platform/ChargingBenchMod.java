package ic2chargingbench.platform;

import forge.MinecraftForgeClient;
import ic2chargingbench.common.TileEntityChargingBench;
import ic2chargingbench.platform.GuiChargingBench;

public abstract class ChargingBenchMod extends BaseModMp {

   public ChargingBenchMod() {
      ModLoader.addLocalization("blockChargingBench1.name", "Charging Bench Mk1");
      ModLoader.addLocalization("blockChargingBench2.name", "Charging Bench Mk2");
      ModLoader.addLocalization("blockChargingBench3.name", "Charging Bench Mk3");
      MinecraftForgeClient.preloadTexture("/ic2/sprites/ChargingBench.png");
      ModLoaderMp.registerGUI(this, mod_IC2_ChargingBench.guiIdChargingBench);
   }

   public static boolean launchGUI(yw entityplayer, kw te) {
      ModLoader.openGUI(entityplayer, new GuiChargingBench(entityplayer.ap, (TileEntityChargingBench)te));
      return true;
   }

   public vp handleGUI(int screenID) {
      vq player = ModLoader.getMinecraftInstance().h;
      return player == null?null:new GuiChargingBench(player.ap, new TileEntityChargingBench(0));
   }
}
