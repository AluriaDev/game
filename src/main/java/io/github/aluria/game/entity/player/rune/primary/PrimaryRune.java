package io.github.aluria.game.entity.player.rune.primary;

import io.github.aluria.common.utils.Lore;
import io.github.aluria.game.entity.GameEntity;
import io.github.aluria.game.entity.player.Gamer;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public abstract class PrimaryRune {
  
  private int id;
  private String name;
  private Material iconType;
  private Lore description;
  
  public PrimaryRune(int id, String name, Material iconType, Lore description) {
    this.id = id;
    this.name = name;
    this.iconType = iconType;
    this.description = description;
  }
  
  public boolean handleAttack(Gamer damager, GameEntity target, double damage) {
    return false;
  }
  
  public boolean handleReceivedDamage(GameEntity damager, Gamer target, double damage) {
    return false;
  }
  
  public boolean handleMove(Gamer damager , Location from , Location to) {
    return false;
  }
  
  public boolean handleHealing(Gamer healed, double healedAmount) {
    return false;
  }
  
  public abstract ItemStack createIcon(Gamer gamer);
}
