package io.github.aluria.game.entity.player.rune.combat;

import io.github.aluria.common.utils.Lore;
import io.github.aluria.game.entity.player.Gamer;
import io.github.aluria.game.entity.player.rune.primary.PrimaryRune;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class HarmonicStrike extends PrimaryRune {
  
  public HarmonicStrike() {
    super(2, "Golpe Harmônico", Material.PRISMARINE_SHARD, Lore.of(
      "",
      "§7Seu quinto ataque consecutivo",
      "§7cura você e causa §b<scalingDamage> §7de ",
      "§7dano mágico no alvo.",
      ""
    ));
  }
  
  @Override
  public ItemStack createIcon(Gamer gamer) {
    return null;
  }
}
