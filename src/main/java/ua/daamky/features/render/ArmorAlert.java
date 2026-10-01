package ua.daamky.features.extra;

import ua.daamky.events.RenderEvent;
import ua.daamky.settings.NumberSetting;
import ua.daamky.settings.Setting;
import ua.daamky.system.api.Category;
import ua.daamky.system.api.Module;
import ua.daamky.system.api.NewFunction;
import ua.daamky.system.events.EventHandler;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@NewFunction(
   I0 = "ArmorAlert",
   I00 = "Предупреждает, когда прочность брони или элитр упала ниже порога",
   I000 = Category.PLAYER
)
public class ArmorAlert extends Module {
   private final NumberSetting threshold = new NumberSetting("Порог, %", 20.0, 5.0, 60.0, 1.0);
   private final NumberSetting oy = new NumberSetting("Смещение Y", 0.0, -200.0, 400.0, 1.0);
   private final Ex.Clock clock = new Ex.Clock();
   private float shown;
   private float time;
   private final List<ItemStack> stacks = new ArrayList<>();
   private final List<Float> fracs = new ArrayList<>();
   private final List<String> names = new ArrayList<>();

   public ArmorAlert() {
      this.addSettings(new Setting[]{this.threshold, this.oy});
   }

   @EventHandler
   public void onRender(RenderEvent event) {
      DrawContext ctx = event.m583();
      if (!Ex.ready(ctx)) {
         return;
      }

      float dt = this.clock.tick();
      this.time += dt;
      this.stacks.clear();
      this.fracs.clear();
      this.names.clear();
      EquipmentSlot[] slots = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
      String[] labels = {"Шлем", "Нагрудник", "Штаны", "Ботинки"};
      for (int i = 0; i < slots.length; i++) {
         ItemStack s = this.mc.player.getEquippedStack(slots[i]);
         if (s.isEmpty() || !s.isDamageable() || s.getMaxDamage() <= 0) {
            continue;
         }

         float f = 1.0F - (float)s.getDamage() / (float)s.getMaxDamage();
         if (f * 100.0F <= (float)this.threshold.getValue()) {
            this.stacks.add(s);
            this.fracs.add(f);
            this.names.add(s.isOf(Items.ELYTRA) ? "Элитры" : labels[i]);
         }
      }

      this.shown = Ex.smooth(this.shown, this.stacks.isEmpty() ? 0.0F : 1.0F, 10.0F, dt);
      if (this.shown < 0.02F || this.stacks.isEmpty()) {
         return;
      }

      float a = this.shown;
      float rows = (float)this.stacks.size();
      float w = 150.0F;
      float h = 22.0F + rows * 15.0F;
      float x = Ex.w() / 2.0F - w / 2.0F;
      float y = 38.0F + (float)this.oy.getValue() - (1.0F - a) * 10.0F;
      float pulse = 0.6F + 0.4F * (float)Math.sin((double)(this.time * 6.0F));
      Color warn = new Color(255, 170, 70);

      Ex.panel(x, y, w, h, 8.0F, Ex.mix(warn, new Color(255, 70, 80), pulse), a);
      Ex.text(ctx, x + 9.0F, y + 6.0F, "Броня почти сломана", 6.5F, Ex.a(Color.WHITE, a));
      for (int i = 0; i < this.stacks.size(); i++) {
         float ry = y + 19.0F + (float)i * 15.0F;
         Ex.item(ctx, this.stacks.get(i), x + 8.0F, ry, 11.0F);
         Ex.text(ctx, x + 23.0F, ry + 1.0F, this.names.get(i), 6.0F, Ex.a(new Color(210, 216, 228), a));
         float f = this.fracs.get(i);
         Ex.bar(x + 78.0F, ry + 3.0F, 40.0F, 4.0F, f, Ex.mix(new Color(255, 70, 80), warn, f * 3.0F), a);
         Ex.rtext(ctx, x + w - 8.0F, ry + 1.0F, Math.round(f * 100.0F) + "%", 6.0F, Ex.a(Color.WHITE, a));
      }
   }
}
