package ic2chargingbench.common;

import ic2.common.BlockMultiID;
import ic2.common.TileEntityBlock;
import ic2.platform.Platform;
import ic2chargingbench.common.TileEntityChargingBench1;
import ic2chargingbench.common.TileEntityChargingBench2;
import ic2chargingbench.common.TileEntityChargingBench3;
import ic2chargingbench.platform.ChargingBenchMod;
import java.util.Random;

public class BlockChargingBench extends BlockMultiID {

   public BlockChargingBench(int id) {
      super(id, na.d);
      this.c(1.0F);
   }

   public boolean a(ge world, int i, int j, int k, ih entityplayer) {
      return Platform.isSimulating()?ChargingBenchMod.launchGUI(entityplayer, world.b(i, j, k)):true;
   }

   public TileEntityBlock getBlockEntity(int meta) {
      switch(meta) {
      case 0:
         return new TileEntityChargingBench1();
      case 1:
         return new TileEntityChargingBench2();
      case 2:
         return new TileEntityChargingBench3();
      default:
         return null;
      }
   }

   public String getTextureFile() {
      return "/ic2/sprites/ChargingBench.png";
   }

   public int a(int i, Random random, int j) {
      return this.bO;
   }

   protected int c(int i) {
      return i;
   }
}
