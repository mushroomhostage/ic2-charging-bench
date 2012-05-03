package ic2chargingbench.common;

import ic2.platform.ItemBlockCommon;

public class ItemChargingBench extends ItemBlockCommon {

   public ItemChargingBench(int i) {
      super(i);
      this.g(0);
      this.a(true);
   }

   public int a(int i) {
      return i;
   }

   public String a(aan itemstack) {
      int meta = itemstack.i();
      switch(meta) {
      case 0:
         return "blockChargingBench1";
      case 1:
         return "blockChargingBench2";
      case 2:
         return "blockChargingBench3";
      default:
         return null;
      }
   }
}
