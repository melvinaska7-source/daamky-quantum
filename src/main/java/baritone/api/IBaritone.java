package baritone.api;

import baritone.api.pathing.goals.Goal;
import java.util.Set;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

public interface IBaritone {
   IBaritone.PathingBehavior getPathingBehavior();

   IBaritone.CustomGoalProcess getCustomGoalProcess();

   IBaritone.MineProcess getMineProcess();

   public interface CustomGoalProcess {
      void setGoalAndPath(Goal var1);
   }

   public interface MineProcess {
      boolean isActive();

      void minePositions(Item var1, Iterable<BlockPos> var2);

      Set<BlockPos> getBlacklist();
   }

   public interface PathingBehavior {
      boolean hasPath();

      void cancelEverything();

      void requestPause();
   }
}
