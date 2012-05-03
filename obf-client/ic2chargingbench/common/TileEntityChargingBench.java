package ic2chargingbench.common;

import ic2.api.Direction;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import ic2.api.IEnergySource;
import ic2.common.EnergyNet;
import ic2.common.Ic2Items;
import ic2.common.TileEntityElecMachine;
import ic2.platform.Platform;
import ic2chargingbench.common.ContainerChargingBench;

public class TileEntityChargingBench extends TileEntityElecMachine implements IEnergySource {

   public int baseTier;
   public int baseMaxInput;
   public int operations;


   public TileEntityChargingBench(int baseTier) {
      super(17, 0, 4 * (int)Math.pow(2.0D, (double)(baseTier * 2 + 3)), 4 * (int)Math.pow(2.0D, (double)(baseTier * 2 + 3)), baseTier);
      this.fuelslot = 12;
      this.baseTier = baseTier;
      this.baseMaxInput = (int)Math.pow(2.0D, (double)(baseTier * 2 + 3));
      this.operations = 1;
   }

   public String c() {
      return "Charging Bench";
   }

   public int gaugeEnergyScaled(int i) {
      if(this.energy <= 0) {
         return 0;
      } else {
         int r = this.energy * i / (this.maxEnergy - this.baseMaxInput);
         if(r > i) {
            r = i;
         }

         return r;
      }
   }

   public void n_() {
      super.n_();
      if(Platform.isSimulating()) {
         this.setOverclockRates();
         boolean needsInvUpdate = false;
         int i;
         int j;
         int sent;
         if(this.i.x(this.j, this.k, this.l)) {
            if(this.energy < this.maxEnergy) {
               needsInvUpdate = this.provideEnergy();

               for(i = 0; i < 12; ++i) {
                  for(j = 0; j < this.operations; ++j) {
                     if(this.inventory[i] != null && this.inventory[i].a() instanceof IElectricItem && ((IElectricItem)this.inventory[i].a()).canProvideEnergy()) {
                        sent = this.maxInput;
                        if(this.maxEnergy - this.energy < sent) {
                           sent = this.maxEnergy - this.energy;
                        }

                        int taken = ElectricItem.discharge(this.inventory[i], sent, this.tier, false, false);
                        this.energy += taken;
                        if(taken > 0) {
                           needsInvUpdate = true;
                        }

                        if(taken == 0) {
                           break;
                        }
                     }
                  }
               }
            }

            if(this.energy > 0) {
               i = this.getMaxEnergyOutput();
               j = this.energy > i?i:this.energy;
               this.energy -= j - EnergyNet.getForWorld(this.i).emitEnergyFrom(this, j);
            }
         } else {
            if(this.energy < this.maxEnergy) {
               needsInvUpdate = this.provideEnergy();
            }

            for(i = 0; i < 12 && this.energy > 0; ++i) {
               if(this.inventory[i] != null && this.inventory[i].a() instanceof IElectricItem) {
                  if(this.inventory[i].a > 1) {
                     for(j = 0; j < 12; ++j) {
                        if(this.inventory[j] == null) {
                           this.inventory[j] = new aan(this.inventory[i].a().bQ, 1, this.inventory[i].i());
                           --this.inventory[i].a;
                           needsInvUpdate = true;
                           break;
                        }
                     }
                  }

                  for(j = 0; j < this.operations; ++j) {
                     sent = ElectricItem.charge(this.inventory[i], this.energy, this.tier, false, false);
                     this.energy -= sent;
                     if(sent > 0) {
                        needsInvUpdate = true;
                     }

                     if(sent == 0) {
                        break;
                     }
                  }
               }
            }
         }

         if(needsInvUpdate) {
            this.j();
         }
      }

   }

   public boolean demandsEnergy() {
      return this.i.x(this.j, this.k, this.l)?false:this.energy <= this.maxEnergy - this.baseMaxInput;
   }

   public dd getGuiContainer(aak inventoryplayer) {
      return new ContainerChargingBench(inventoryplayer, this);
   }

   public boolean emitsEnergyTo(kw receiver, Direction direction) {
      return true;
   }

   public int getMaxEnergyOutput() {
      return (int)Math.pow(2.0D, (double)(2 * this.baseTier + 3));
   }

   public void setOverclockRates() {
      int overclockerUpgradeCount = 0;
      int transformerUpgradeCount = 0;
      int energyStorageUpgradeCount = 0;

      for(int i = 13; i <= 16; ++i) {
         aan itemStack = this.inventory[i];
         if(itemStack != null) {
            if(itemStack.a(Ic2Items.overclockerUpgrade)) {
               overclockerUpgradeCount += itemStack.a;
            } else if(itemStack.a(Ic2Items.transformerUpgrade)) {
               transformerUpgradeCount += itemStack.a;
            } else if(itemStack.a(Ic2Items.energyStorageUpgrade)) {
               energyStorageUpgradeCount += itemStack.a;
            }
         }
      }

      if(overclockerUpgradeCount > 16) {
         overclockerUpgradeCount = 16;
      }

      if(transformerUpgradeCount > 10) {
         transformerUpgradeCount = 10;
      }

      this.tier = transformerUpgradeCount + this.baseTier;
      this.operations = (int)Math.round(1.0D / Math.pow(0.7D, (double)overclockerUpgradeCount));
      this.maxInput = (int)Math.pow(2.0D, (double)(this.tier * 2 + 3));
      this.maxEnergy = energyStorageUpgradeCount * 10000 + 4 * this.getMaxEnergyOutput();
   }

   public int injectEnergy(Direction directionFrom, int amount) {
      this.setOverclockRates();
      return super.injectEnergy(directionFrom, amount);
   }
}
