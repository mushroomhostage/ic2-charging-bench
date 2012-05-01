package ic2chargingbench.common;

import ic2.common.ContainerIC2;
import java.util.List;
import net.minecraft.src.*;

public class ContainerChargingBench extends ContainerIC2
{
    public TileEntityChargingBench tileentity;
    public int energy;
    public short maxInput;

    public ContainerChargingBench(InventoryPlayer inventoryplayer, TileEntityChargingBench tileentitychargingbench)
    {
        tileentity = tileentitychargingbench;
        energy = 0;
        maxInput = 0;
        addSlot(new Slot(tileentitychargingbench, 0, 61, 19));
        addSlot(new Slot(tileentitychargingbench, 1, 79, 19));
        addSlot(new Slot(tileentitychargingbench, 2, 97, 19));
        addSlot(new Slot(tileentitychargingbench, 3, 115, 19));
        addSlot(new Slot(tileentitychargingbench, 4, 61, 37));
        addSlot(new Slot(tileentitychargingbench, 5, 79, 37));
        addSlot(new Slot(tileentitychargingbench, 6, 97, 37));
        addSlot(new Slot(tileentitychargingbench, 7, 115, 37));
        addSlot(new Slot(tileentitychargingbench, 8, 61, 55));
        addSlot(new Slot(tileentitychargingbench, 9, 79, 55));
        addSlot(new Slot(tileentitychargingbench, 10, 97, 55));
        addSlot(new Slot(tileentitychargingbench, 11, 115, 55));
        addSlot(new Slot(tileentitychargingbench, 12, 24, 40));
        addSlot(new Slot(tileentitychargingbench, 13, 152, 8));
        addSlot(new Slot(tileentitychargingbench, 14, 152, 26));
        addSlot(new Slot(tileentitychargingbench, 15, 152, 44));
        addSlot(new Slot(tileentitychargingbench, 16, 152, 62));

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++)
        {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }

    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    public void updateCraftingResults()
    {
        super.updateCraftingResults();

        for (int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);

            if (energy != tileentity.energy)
            {
                icrafting.updateCraftingInventoryInfo(this, 0, tileentity.energy & 0xffff);
                icrafting.updateCraftingInventoryInfo(this, 1, tileentity.energy >>> 16);
            }

            if (maxInput != tileentity.maxInput)
            {
                icrafting.updateCraftingInventoryInfo(this, 2, tileentity.maxInput);
            }
        }

        energy = tileentity.energy;
        maxInput = (short)tileentity.maxInput;
    }

    public void updateProgressBar(int i, int j)
    {
        switch (i)
        {
            case 0:
                tileentity.energy = tileentity.energy & 0xffff0000 | j;
                break;

            case 1:
                tileentity.energy = tileentity.energy & 0xffff | j << 16;
                break;

            case 2:
                tileentity.maxInput = j;
                break;
        }
    }

    public int guiInventorySize()
    {
        return 17;
    }

    public int getInput()
    {
        return 0;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return tileentity.isUseableByPlayer(entityplayer);
    }
}
