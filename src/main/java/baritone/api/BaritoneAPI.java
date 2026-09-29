package baritone.api;

import baritone.api.pathing.goals.Goal;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

public final class BaritoneAPI {
   private static IBaritoneProvider provider;
   private static final BaritoneSettings settings = new BaritoneSettings();
   private static final IBaritone noop = new BaritoneAPI.NoopBaritone();

   private BaritoneAPI() {
   }

   public static IBaritoneProvider getProvider() {
      return provider == null ? () -> noop : provider;
   }

   public static void setProvider(IBaritoneProvider var0) {
      provider = var0;
   }

   public static BaritoneSettings getSettings() {
      return settings;
   }

   private static final class NoopBaritone implements IBaritone {
      private final BaritoneAPI.NoopBaritone.PathingBehavior pathing = new BaritoneAPI.NoopBaritone.PathingBehavior();
      private final BaritoneAPI.NoopBaritone.CustomGoalProcess goals = new BaritoneAPI.NoopBaritone.CustomGoalProcess();
      private final BaritoneAPI.NoopBaritone.MineProcess mine = new BaritoneAPI.NoopBaritone.MineProcess();

      public BaritoneAPI.NoopBaritone.PathingBehavior getPathingBehavior() {
         return this.pathing;
      }

      public BaritoneAPI.NoopBaritone.CustomGoalProcess getCustomGoalProcess() {
         return this.goals;
      }

      public BaritoneAPI.NoopBaritone.MineProcess getMineProcess() {
         return this.mine;
      }

      private static final class CustomGoalProcess implements IBaritone.CustomGoalProcess {
         @Override
         public void setGoalAndPath(Goal var1) {
         }
      }

      private static final class MineProcess implements IBaritone.MineProcess {
         private final Set<BlockPos> blacklist = new HashSet<>();

         @Override
         public boolean isActive() {
            return false;
         }

         @Override
         public void minePositions(Item var1, Iterable<BlockPos> var2) {
         }

         @Override
         public Set<BlockPos> getBlacklist() {
            return this.blacklist;
         }
      }

      private static final class PathingBehavior implements IBaritone.PathingBehavior {
         @Override
         public boolean hasPath() {
            return false;
         }

         @Override
         public void cancelEverything() {
         }

         @Override
         public void requestPause() {
         }
      }
   }
}
