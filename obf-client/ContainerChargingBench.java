
import ic2.common.*;

public class ContainerChargingBench extends ContainerIC2 {

   public TileEntityChargingBench tileentity;
   public int energy;
   public short maxInput;


   public ContainerChargingBench(aak inventoryplayer, TileEntityChargingBench tileentity) {
      this.tileentity = tileentity;
      this.energy = 0;
      this.maxInput = 0;
      this.a(new yu(tileentity, 0, 61, 19));
      this.a(new yu(tileentity, 1, 79, 19));
      this.a(new yu(tileentity, 2, 97, 19));
      this.a(new yu(tileentity, 3, 115, 19));
      this.a(new yu(tileentity, 4, 61, 37));
      this.a(new yu(tileentity, 5, 79, 37));
      this.a(new yu(tileentity, 6, 97, 37));
      this.a(new yu(tileentity, 7, 115, 37));
      this.a(new yu(tileentity, 8, 61, 55));
      this.a(new yu(tileentity, 9, 79, 55));
      this.a(new yu(tileentity, 10, 97, 55));
      this.a(new yu(tileentity, 11, 115, 55));
      this.a(new yu(tileentity, 12, 24, 40));
      this.a(new yu(tileentity, 13, 152, 8));
      this.a(new yu(tileentity, 14, 152, 26));
      this.a(new yu(tileentity, 15, 152, 44));
      this.a(new yu(tileentity, 16, 152, 62));

      int j;
      for(j = 0; j < 3; ++j) {
         for(int k = 0; k < 9; ++k) {
            this.a(new yu(inventoryplayer, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
         }
      }

      for(j = 0; j < 9; ++j) {
         this.a(new yu(inventoryplayer, j, 8 + j * 18, 142));
      }

   }

   public void a() {
      super.a();

      for(int i = 0; i < this.g.size(); ++i) {
         wm icrafting = (wm)this.g.get(i);
         if(this.energy != this.tileentity.energy) {
            icrafting.a(this, 0, this.tileentity.energy & '\uffff');
            icrafting.a(this, 1, this.tileentity.energy >>> 16);
         }

         if(this.maxInput != this.tileentity.maxInput) {
            icrafting.a(this, 2, this.tileentity.maxInput);
         }
      }

      this.energy = this.tileentity.energy;
      this.maxInput = (short)this.tileentity.maxInput;
   }

   public void a(int i, int j) {
      switch(i) {
      case 0:
         this.tileentity.energy = this.tileentity.energy & -65536 | j;
         break;
      case 1:
         this.tileentity.energy = this.tileentity.energy & '\uffff' | j << 16;
         break;
      case 2:
         this.tileentity.maxInput = j;
      }

   }

   public int guiInventorySize() {
      return 17;
   }

   public int getInput() {
      return 0;
   }

   public boolean b(yw entityplayer) {
      return this.tileentity.a_(entityplayer);
   }
}
