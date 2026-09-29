package baritone.api.pathing.goals;

import net.minecraft.util.math.BlockPos;

public class GoalRunAway implements Goal  {
   private final double distance;
   private final BlockPos[] positions;

   public GoalRunAway(double var1, BlockPos[] var3) {
      this.distance = var1;
      this.positions = var3;
   }
}
