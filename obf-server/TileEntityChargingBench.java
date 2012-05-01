// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntityChargingBench.java

package ic2chargingbench.common;

import ew;
import ge;
import ic2.api.*;
import ic2.common.*;
import ic2.platform.Platform;
import id;
import ko;
import kp;
import qj;

// Referenced classes of package ic2chargingbench.common:
//            ContainerChargingBench

public class TileEntityChargingBench extends TileEntityElecMachine
    implements IEnergySource
{

    public TileEntityChargingBench(int baseTier)
    {
        super(17, 0, 4 * (int)Math.pow(2D, baseTier * 2 + 3), 4 * (int)Math.pow(2D, baseTier * 2 + 3), baseTier);
        fuelslot = 12;
        this.baseTier = baseTier;
        baseMaxInput = (int)Math.pow(2D, baseTier * 2 + 3);
        operations = 1;
    }

    public String e()
    {
        return "Charging Bench";
    }

    public int gaugeEnergyScaled(int i)
    {
        if(energy <= 0)
            return 0;
        int r = (energy * i) / (maxEnergy - baseMaxInput);
        if(r > i)
            r = i;
        return r;
    }

    public void q_()
    {
        super.q_();
        if(Platform.isSimulating())
        {
            setOverclockRates();
            boolean needsInvUpdate = false;
            if(k.x(l, m, n))
            {
                if(energy < maxEnergy)
                {
                    needsInvUpdate = provideEnergy();
                    for(int i = 0; i < 12; i++)
                    {
                        for(int j = 0; j < operations; j++)
                        {
                            if(inventory[i] == null || !(inventory[i].a() instanceof IElectricItem) || !((IElectricItem)inventory[i].a()).canProvideEnergy())
                                continue;
                            int maxTaken = maxInput;
                            if(maxEnergy - energy < maxTaken)
                                maxTaken = maxEnergy - energy;
                            int taken = ElectricItem.discharge(inventory[i], maxTaken, tier, false, false);
                            energy += taken;
                            if(taken > 0)
                                needsInvUpdate = true;
                            if(taken == 0)
                                break;
                        }

                    }

                }
                if(energy > 0)
                {
                    int maxOutput = getMaxEnergyOutput();
                    int send = energy <= maxOutput ? energy : maxOutput;
                    energy -= send - EnergyNet.getForWorld(k).emitEnergyFrom(this, send);
                }
            } else
            {
                if(energy < maxEnergy)
                    needsInvUpdate = provideEnergy();
label0:
                for(int i = 0; i < 12 && energy > 0; i++)
                {
                    if(inventory[i] == null || !(inventory[i].a() instanceof IElectricItem))
                        continue;
                    int j;
                    if(inventory[i].a > 1)
                    {
                        j = 0;
                        do
                        {
                            if(j >= 12)
                                break;
                            if(inventory[j] == null)
                            {
                                inventory[j] = new kp(inventory[i].a().bP, 1, inventory[i].h());
                                inventory[i].a--;
                                needsInvUpdate = true;
                                break;
                            }
                            j++;
                        } while(true);
                    }
                    j = 0;
                    do
                    {
                        if(j >= operations)
                            continue label0;
                        int sent = ElectricItem.charge(inventory[i], energy, tier, false, false);
                        energy -= sent;
                        if(sent > 0)
                            needsInvUpdate = true;
                        if(sent == 0)
                            continue label0;
                        j++;
                    } while(true);
                }

            }
            if(needsInvUpdate)
                G_();
        }
    }

    public boolean demandsEnergy()
    {
        if(k.x(l, m, n))
            return false;
        else
            return energy <= maxEnergy - baseMaxInput;
    }

    public ew getGuiContainer(ko inventoryplayer)
    {
        return new ContainerChargingBench(inventoryplayer, this);
    }

    public boolean emitsEnergyTo(qj receiver, Direction direction)
    {
        return true;
    }

    public int getMaxEnergyOutput()
    {
        return (int)Math.pow(2D, 2 * baseTier + 3);
    }

    public void setOverclockRates()
    {
        int overclockerUpgradeCount = 0;
        int transformerUpgradeCount = 0;
        int energyStorageUpgradeCount = 0;
        for(int i = 13; i <= 16; i++)
        {
            kp itemStack = inventory[i];
            if(itemStack == null)
                continue;
            if(itemStack.a(Ic2Items.overclockerUpgrade))
            {
                overclockerUpgradeCount += itemStack.a;
                continue;
            }
            if(itemStack.a(Ic2Items.transformerUpgrade))
            {
                transformerUpgradeCount += itemStack.a;
                continue;
            }
            if(itemStack.a(Ic2Items.energyStorageUpgrade))
                energyStorageUpgradeCount += itemStack.a;
        }

        if(overclockerUpgradeCount > 16)
            overclockerUpgradeCount = 16;
        if(transformerUpgradeCount > 10)
            transformerUpgradeCount = 10;
        tier = transformerUpgradeCount + baseTier;
        operations = (int)Math.round(1.0D / Math.pow(0.69999999999999996D, overclockerUpgradeCount));
        maxInput = (int)Math.pow(2D, tier * 2 + 3);
        maxEnergy = energyStorageUpgradeCount * 10000 + 4 * getMaxEnergyOutput();
    }

    public int injectEnergy(Direction directionFrom, int amount)
    {
        setOverclockRates();
        return super.injectEnergy(directionFrom, amount);
    }

    public int baseTier;
    public int baseMaxInput;
    public int operations;
}
