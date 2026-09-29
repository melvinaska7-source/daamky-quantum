package baritone.api;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;

public final class BaritoneSettings {
   public final BaritoneSettings.Setting<Boolean> blockFreeLook = new BaritoneSettings.Setting<>(true);
   public final BaritoneSettings.Setting<Boolean> allowBreak = new BaritoneSettings.Setting<>(true);
   public final BaritoneSettings.Setting<Boolean> allowPlace = new BaritoneSettings.Setting<>(false);
   public final BaritoneSettings.Setting<Boolean> avoidance = new BaritoneSettings.Setting<>(false);
   public final BaritoneSettings.Setting<Boolean> creative = new BaritoneSettings.Setting<>(false);
   public final BaritoneSettings.Setting<Float> turnSpeed = new BaritoneSettings.Setting<>(60.0F);
   public final BaritoneSettings.Setting<Double> randomLooking = new BaritoneSettings.Setting<>(1.0);
   public final BaritoneSettings.Setting<Double> randomLooking113 = new BaritoneSettings.Setting<>(1.0);
   public final BaritoneSettings.Setting<Integer> maxFallHeightNoWater = new BaritoneSettings.Setting<>(3);
   public final BaritoneSettings.Setting<List<Block>> blocksToAvoid = new BaritoneSettings.Setting<>(new ArrayList<>());

   public static final class Setting<T> {
      public T value;

      public Setting(T var1) {
         this.value = (T)var1;
      }
   }
}
