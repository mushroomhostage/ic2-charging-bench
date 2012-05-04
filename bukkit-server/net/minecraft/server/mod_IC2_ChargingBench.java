package net.minecraft.server;

import forge.*;
import ic2.api.Items;
import ic2.platform.Platform;
import ic2chargingbench.common.*;
import java.io.File;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class mod_IC2_ChargingBench extends NetworkMod implements IGuiHandler {

   public static Configuration config;
   public static int idBlockChargingBench;
   public static Block blockChargingBench;
   public static int guiIdChargingBench;

   public static mod_IC2_ChargingBench instance;


   static {
      try {
         config = new Configuration(new File(Platform.getMinecraftDir() + "/config/IC2ChargingBench.cfg"));
         config.load();
         idBlockChargingBench = Integer.valueOf(config.getOrCreateIntProperty("blockChargingBench", Configuration.CATEGORY_BLOCK, 189).value).intValue();
         guiIdChargingBench = Integer.valueOf(config.getOrCreateIntProperty("guiIdChargingBench", Configuration.CATEGORY_GENERAL, 110).value).intValue();
         config.save();
      } catch (Exception var1) {
         System.out.println("[ChargingBench] Error while trying to access configuration!");
         throw new RuntimeException(var1);
      }
   }

/*
* Returns a Container to be displayed to the user.
* On the client side, this needs to return a instance of GuiScreen
* On the server side, this needs to return a instance of Container <--
*/
   public Object getGuiElement(int ID, EntityHuman player, World world, int x, int y, int z) {
      TileEntity tileentity = world.getTileEntity(x, y, z);
      System.out.println("getGuiElement te="+tileentity);
      if(tileentity != null && (tileentity instanceof TileEntityChargingBench)) {
         return new ContainerChargingBench(player, ((TileEntityChargingBench)tileentity));
      } else {
         return null;
      }
   }

   public void load() {
      instance = this;

      blockChargingBench = new BlockChargingBench(idBlockChargingBench);
      ModLoader.registerBlock(blockChargingBench, ItemChargingBench.class);
      ModLoader.registerTileEntity(TileEntityChargingBench1.class, "Charging Bench Mk1");
      ModLoader.registerTileEntity(TileEntityChargingBench2.class, "Charging Bench Mk2");
      ModLoader.registerTileEntity(TileEntityChargingBench3.class, "Charging Bench Mk3");
      ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 0), new Object[]{"UUU", "W W", "WWW", Character.valueOf('U'), Items.getItem("copperCableItem"), Character.valueOf('W'), Block.WOOD});
      ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 1), new Object[]{"UUU", "WCW", "WWW", Character.valueOf('U'), Items.getItem("goldCableItem"), Character.valueOf('W'), Block.WOOD, Character.valueOf('C'), Items.getItem("electronicCircuit")});
      ModLoader.addRecipe(new ItemStack(blockChargingBench, 1, 2), new Object[]{"UUU", "WCW", "WWW", Character.valueOf('U'), Items.getItem("ironCableItem"), Character.valueOf('W'), Block.WOOD, Character.valueOf('C'), Items.getItem("advancedCircuit")});
   }

   public String getVersion() {
      return "1.90-1";
   }
}
