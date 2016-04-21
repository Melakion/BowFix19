package melakion.bowfix19;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
		modid = "BowFix19",
		version = "@VERSION@",
		acceptedMinecraftVersions = "[1.9,1.10)",
		dependencies = "required-after:Forge@[12.16.0.1859,);",
		clientSideOnly = false
)
public class BowFix19 {
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onArrowNock(ArrowNockEvent event) {
    EntityPlayer player = event.getEntityPlayer();
    ItemStack stack = event.getBow();
    if (player.capabilities.isCreativeMode || event.hasAmmo()
        || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0) {
      player.setItemInUse(stack, stack.getItem().getMaxItemUseDuration(stack));
    }

    event.result = stack;
    event.setCanceled(true);
  }
}
