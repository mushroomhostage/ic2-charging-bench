package ic2chargingbench.common;

import ic2.platform.ItemBlockCommon;
import net.minecraft.src.ItemStack;

public class ItemChargingBench extends ItemBlockCommon
{
    public ItemChargingBench(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int i)
    {
        return i;
    }

    public String getItemNameIS(ItemStack itemstack)
    {
        int i = itemstack.getItemDamage();

        switch (i)
        {
            case 0:
                return "blockChargingBench1";

            case 1:
                return "blockChargingBench2";

            case 2:
                return "blockChargingBench3";
        }

        return null;
    }
}
