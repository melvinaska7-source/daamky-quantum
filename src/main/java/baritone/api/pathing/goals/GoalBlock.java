package baritone.api.pathing.goals;

import net.minecraft.util.math.BlockPos;

public class GoalBlock implements Goal  {
   private final BlockPos pos;

   public GoalBlock(BlockPos var1) {
      this.pos = var1;
   }
}
