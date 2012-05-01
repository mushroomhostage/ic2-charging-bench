package ic2chargingbench.common;

import ic2.api.*;
import ic2.common.*;
import ic2.platform.Platform;
import net.minecraft.src.*;

public class TileEntityChargingBench extends TileEntityElecMachine implements IEnergySource
{
    public int baseTier;
    public int baseMaxInput;
    public int operations;

    public TileEntityChargingBench(int i)
    {
        super(17, 0, 4 * (int)Math.pow(2D, i * 2 + 3), 4 * (int)Math.pow(2D, i * 2 + 3), i);
        fuelslot = 12;
        baseTier = i;
        baseMaxInput = (int)Math.pow(2D, i * 2 + 3);
        operations = 1;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Charging Bench";
    }

    public int gaugeEnergyScaled(int i)
    {
        if (energy <= 0)
        {
            return 0;
        }

        int j = (energy * i) / (maxEnergy - baseMaxInput);

        if (j > i)
        {
            j = i;
        }

        return j;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();

        if (Platform.isSimulating())
        {
            setOverclockRates();
            boolean flag = false;

            if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
            {
                if (energy < maxEnergy)
                {
                    flag = provideEnergy();

                    for (int i = 0; i < 12; i++)
                    {
                        for (int l = 0; l < operations; l++)
                        {
                            if (inventory[i] == null || !(inventory[i].getItem() instanceof IElectricItem) || !((IElectricItem)inventory[i].getItem()).canProvideEnergy())
                            {
                                continue;
                            }

                            int l1 = maxInput;

                            if (maxEnergy - energy < l1)
                            {
                                l1 = maxEnergy - energy;
                            }

                            int j2 = ElectricItem.discharge(inventory[i], l1, tier, false, false);
                            energy += j2;

                            if (j2 > 0)
                            {
                                flag = true;
                            }

                            if (j2 == 0)
                            {
                                break;
                            }
                        }
                    }
                }

                if (energy > 0)
                {
                    int j = getMaxEnergyOutput();
                    int i1 = energy <= j ? energy : j;
                    energy -= i1 - EnergyNet.getForWorld(worldObj).emitEnergyFrom(this, i1);
                }
            }
            else
            {
                if (energy < maxEnergy)
                {
                    flag = provideEnergy();
                }

                label0:

                for (int k = 0; k < 12 && energy > 0; k++)
                {
                    if (inventory[k] == null || !(inventory[k].getItem() instanceof IElectricItem))
                    {
                        continue;
                    }

                    if (inventory[k].stackSize > 1)
                    {
                        int j1 = 0;

                        do
                        {
                            if (j1 >= 12)
                            {
                                break;
                            }

                            if (inventory[j1] == null)
                            {
                                inventory[j1] = new ItemStack(inventory[k].getItem().shiftedIndex, 1, inventory[k].getItemDamage());
                                inventory[k].stackSize--;
                                flag = true;
                                break;
                            }

                            j1++;
                        }
                        while (true);
                    }

                    int k1 = 0;

                    do
                    {
                        if (k1 >= operations)
                        {
                            continue label0;
                        }

                        int i2 = ElectricItem.charge(inventory[k], energy, tier, false, false);
                        energy -= i2;

                        if (i2 > 0)
                        {
                            flag = true;
                        }

                        if (i2 == 0)
                        {
                            continue label0;
                        }

                        k1++;
                    }
                    while (true);
                }
            }

            if (flag)
            {
                onInventoryChanged();
            }
        }
    }

    public boolean demandsEnergy()
    {
        if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
        {
            return false;
        }
        else
        {
            return energy <= maxEnergy - baseMaxInput;
        }
    }

    public Container getGuiContainer(InventoryPlayer inventoryplayer)
    {
        return new ContainerChargingBench(inventoryplayer, this);
    }

    public boolean emitsEnergyTo(TileEntity tileentity, Direction direction)
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

        for (int l = 13; l <= 16; l++)
        {
            ItemStack itemstack = inventory[l];

            if (itemstack == null)
            {
                continue;
            }

            if (itemstack.isItemEqual(Ic2Items.overclockerUpgrade))
            {
                i += itemstack.stackSize;
                continue;
            }

            if (itemstack.isItemEqual(Ic2Items.transformerUpgrade))
            {
                j += itemstack.stackSize;
                continue;
            }

            if (itemstack.isItemEqual(Ic2Items.energyStorageUpgrade))
            {
                k += itemstack.stackSize;
            }
        }

        if (i > 16)
        {
            i = 16;
        }

        if (j > 10)
        {
            j = 10;
        }

        tier = j + baseTier;
        operations = (int)Math.round(1.0D / Math.pow(0.69999999999999996D, i));
        maxInput = (int)Math.pow(2D, tier * 2 + 3);
        maxEnergy = k * 10000 + 4 * getMaxEnergyOutput();
    }

    public int injectEnergy(Direction direction, int i)
    {
        setOverclockRates();
        return super.injectEnergy(direction, i);
    }
}
