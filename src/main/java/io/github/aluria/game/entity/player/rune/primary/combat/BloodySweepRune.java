package io.github.aluria.game.entity.player.rune.combat;

import io.github.aluria.common.utils.Lore;
import io.github.aluria.game.entity.GameEntity;
import io.github.aluria.game.entity.player.Gamer;
import io.github.aluria.game.entity.player.rune.PrimaryRune;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.WeakHashMap;

public final class BloodySweepRune extends PrimaryRune {
  
  private final BloodySweepCounterMap counterMap = new BloodySweepCounterMap();
  
  public BloodySweepRune() {
    super(0, "Varredura Sangrenta", Material.RED_GLAZED_TERRACOTTA , Lore.of(
      "",
      "§7No seu quinto ataque consecutivo",
      "§7sem receber dano você causa §b<scalingValue>§7",
      "§7de dano adaptivo em todas as entidades",
      "§7que estiverem §b<scalingRadius> §7ao redor do alvo.",
      ""
    ));
  }
  
  @Override
  public boolean handleAttack(Gamer damager, GameEntity target, double damage) {
    int level = damager.getLevel();
    int count = counterMap.getCurrentHitCount(damager);
    if (count < 4) {
      counterMap.incrementPlayerCount(damager);
    } else {
      
      double areaDamage = damage * (0.02 * level);
      int radius = (damager.getLevel() / 3) * 3;
      
      this.blowDamage(damager, areaDamage, radius);
      counterMap.resetCounter(damager);
    }
    
    return super.handleAttack(damager, target, damage);
  }
  
  
  
  private void blowDamage(Gamer damager, double damage, double radius) {
  
  }
  
  @Override
  public ItemStack createIcon(Gamer gamer) {
    return null;
  }
  
  public static class BloodySweepCounterMap {
    private final Map<String, Integer> counterMap = new WeakHashMap<>();
    
    public void incrementPlayerCount(Gamer player) {
      counterMap.put(player.getName(), getCurrentHitCount(player) + 1);
    }
    
    public void resetCounter(Gamer player) {
      counterMap.remove(player.getName());
    }
    
    public int getCurrentHitCount(Gamer player) {
      return counterMap.getOrDefault(player.getName(), 0);
    }
  }
}
