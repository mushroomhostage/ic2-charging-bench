package ic2chargingbench.common;

import ic2.common.BlockMultiID;
import ic2.common.TileEntityBlock;
import ic2.platform.Platform;
import ic2chargingbench.platform.ChargingBenchMod;
import java.util.Random;
import net.minecraft.src.*;

public class BlockChargingBench extends BlockMultiID
{
    public BlockChargingBench(int i)
    {
        super(i, Material.wood);
        setHardness(1.0F);
    }

    /**
     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
     * block.
     */
    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if (Platform.isSimulating())
        {
            return ChargingBenchMod.launchGUI(entityplayer, world.getBlockTileEntity(i, j, k));
        }
        else
        {
            return true;
        }
    }

    public TileEntityBlock getBlockEntity(int i)
    {
        switch (i)
        {
            case 0:
                return new TileEntityChargingBench1();

            case 1:
                return new TileEntityChargingBench2();

            case 2:
                return new TileEntityChargingBench3();
        }

        return null;
    }

    public String getTextureFile()
    {
        return "/ic2/sprites/ChargingBench.png";
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int i, Random random, int j)
    {
        return blockID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    protected int damageDropped(int i)
    {
        return i;
    }
}
