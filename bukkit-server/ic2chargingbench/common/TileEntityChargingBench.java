// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TileEntityChargingBench.java

package ic2chargingbench.common;

import ic2.api.*;
import ic2.common.Ic2Items;
import ic2.common.TileEntityElecMachine;
import ic2.platform.Platform;
import net.minecraft.server.*;

// Referenced classes of package ic2chargingbench.common:
//            ContainerChargingBench

public class TileEntityChargingBench extends TileEntityElecMachine
    implements IEnergySource
{

    public TileEntityChargingBench(int i)
    {
        super(17, 0, 4 * (int)Math.pow(2D, i * 2 + 3), 4 * (int)Math.pow(2D, i * 2 + 3), i);
        fuelslot = 12;
        baseTier = i;
        baseMaxInput = (int)Math.pow(2D, i * 2 + 3);
        operations = 1;
    }

    public String getName()
    {
        return "Charging Bench";
    }

    public int gaugeEnergyScaled(int i)
    {
        if(energy <= 0)
            return 0;
        int j = (energy * i) / (maxEnergy - baseMaxInput);
        if(j > i)
            j = i;
        return j;
    }

    public void q_()
    {
        super.q_();
        if(Platform.isSimulating())
        {
            setOverclockRates();
            boolean flag = false;
            if(world.isBlockIndirectlyPowered(x, y, z))
            {
                if(energy < maxEnergy)
                {
                    flag = provideEnergy();
                    for(int i = 0; i < 12; i++)
                    {
                        for(int l = 0; l < operations; l++)
                        {
                            if(inventory[i] == null || !(inventory[i].getItem() instanceof IElectricItem) || !((IElectricItem)inventory[i].getItem()).canProvideEnergy())
                                continue;
                            int l1 = maxInput;
                            if(maxEnergy - energy < l1)
                                l1 = maxEnergy - energy;
                            int j2 = ElectricItem.discharge(inventory[i], l1, tier, false, false);
                            energy += j2;
                            if(j2 > 0)
                                flag = true;
                            if(j2 == 0)
                                break;
                        }

                    }

                }
                if(energy > 0)
                {
                    int j = getMaxEnergyOutput();
                    int i1 = energy > j ? j : energy;
                    energy -= i1 - EnergyNet.getForWorld(world).emitEnergyFrom(this, i1);
                }
            } else
            {
                if(energy < maxEnergy)
                    flag = provideEnergy();
                for(int k = 0; k < 12 && energy > 0; k++)
                    if(inventory[k] != null && (inventory[k].getItem() instanceof IElectricItem))
                    {
                        if(inventory[k].count > 1)
                        {
                            int j1 = 0;
                            do
                            {
                                if(j1 >= 12)
                                    break;
                                if(inventory[j1] == null)
                                {
                                    inventory[j1] = new ItemStack(inventory[k].getItem().id, 1, inventory[k].getData());
                                    inventory[k].count--;
                                    flag = true;
                                    break;
                                }
                                j1++;
                            } while(true);
                        }
                        int k1 = 0;
                        do
                        {
                            if(k1 >= operations)
                                break;
                            int i2 = ElectricItem.charge(inventory[k], energy, tier, false, false);
                            energy -= i2;
                            if(i2 > 0)
                                flag = true;
                            if(i2 == 0)
                                break;
                            k1++;
                        } while(true);
                    }

            }
            if(flag)
                update();
        }
    }

    public boolean demandsEnergy()
    {
        if(world.isBlockIndirectlyPowered(x, y, z))
            return false;
        return energy <= maxEnergy - baseMaxInput;
    }

/*
    public Container getGuiContainer(PlayerInventory playerinventory)
    {
        return new ContainerChargingBench(playerinventory, this);
    }
    */

    public boolean emitsEnergyTo(TileEntity tileentity, ic2.api.Direction direction)
    {
        return true;
    }

    public int getMaxEnergyOutput()
    {
        return (int)Math.pow(2D, 2 * baseTier + 3);
    }

    public void setOverclockRates()
    {
        int i = 0;
        int j = 0;
        int k = 0;
        for(int l = 13; l <= 16; l++)
        {
            ItemStack itemstack = inventory[l];
            if(itemstack != null)
                if(itemstack.doMaterialsMatch(Ic2Items.overclockerUpgrade))
                    i += itemstack.count;
                else
                if(itemstack.doMaterialsMatch(Ic2Items.transformerUpgrade))
                    j += itemstack.count;
                else
                if(itemstack.doMaterialsMatch(Ic2Items.energyStorageUpgrade))
                    k += itemstack.count;
        }

        if(i > 16)
            i = 16;
        if(j > 10)
            j = 10;
        tier = j + baseTier;
        operations = (int)Math.round(1.0D / Math.pow(0.69999999999999996D, i));
        maxInput = (int)Math.pow(2D, tier * 2 + 3);
        maxEnergy = k * 10000 + 4 * getMaxEnergyOutput();
    }

    public int injectEnergy(ic2.api.Direction direction, int i)
    {
        setOverclockRates();
        return super.injectEnergy(direction, i);
    }

    public int baseTier;
    public int baseMaxInput;
    public int operations;
}
