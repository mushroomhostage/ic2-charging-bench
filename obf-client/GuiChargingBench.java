import org.lwjgl.opengl.GL11;

public class GuiChargingBench extends gb {

   public TileEntityChargingBench tileentity;


   public GuiChargingBench(aak inventoryplayer, TileEntityChargingBench te) {
      super(te.getGuiContainer(inventoryplayer));
      this.tileentity = te;
   }

   protected void d() {
      this.u.b("Charging Bench", 54, 6, 4210752);
      this.u.b("Inventory", 8, this.c - 96 + 2, 4210752);
   }

   protected void a(float f, int l, int m) {
      int i = this.p.p.b("/ic2/sprites/GUIChargingBench.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.p.p.b(i);
      int j = (this.q - this.b) / 2;
      int k = (this.r - this.c) / 2;
      this.b(j, k, 0, 0, this.b, this.c);
      if(this.tileentity.energy > 0) {
         int i1 = this.tileentity.gaugeEnergyScaled(14);
         this.b(j + 24, k + 23 + 14 - i1, 176, 14 - i1, 14, i1);
      }

   }
}
