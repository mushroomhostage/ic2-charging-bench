package ic2chargingbench.common;

import ic2.api.Direction;
import ic2.api.IEnergySource;
import ic2.common.TileEntityElecMachine;
import net.minecraft.server.Container;
import net.minecraft.server.PlayerInventory;
import net.minecraft.server.TileEntity;

public class TileEntityChargingBench extends TileEntityElecMachine implements IEnergySource {

   public int baseTier;
   public int baseMaxInput;
   public int operations;


   public TileEntityChargingBench(int param1) {
      // $FF: Couldn't be decompiled
   }

   public String getName() {
      // $FF: Couldn't be decompiled
   }

   public int gaugeEnergyScaled(int param1) {
      // $FF: Couldn't be decompiled
   }

   public void q_() {
      // $FF: Couldn't be decompiled
   }

   public boolean demandsEnergy() {
      // $FF: Couldn't be decompiled
   }

   public Container getGuiContainer(PlayerInventory param1) {
      // $FF: Couldn't be decompiled
   }

   public boolean emitsEnergyTo(TileEntity param1, Direction param2) {
      // $FF: Couldn't be decompiled
   }

   public int getMaxEnergyOutput() {
      // $FF: Couldn't be decompiled
   }

   public void setOverclockRates() {
      // $FF: Couldn't be decompiled
   }

   public int injectEnergy(Direction param1, int param2) {
      // $FF: Couldn't be decompiled
   }
}
