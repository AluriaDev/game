package io.github.aluria.game.entity.combat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
@AllArgsConstructor
public enum DamageType {
  
  PHYSICAL("Dano Físico", ChatColor.GOLD),
  MAGIC("Dano Mágico", ChatColor.AQUA),
  TRUE_DAMAGE("Dano Verdadeiro", ChatColor.WHITE);
  
  
  private String name;
  private ChatColor color;
}
