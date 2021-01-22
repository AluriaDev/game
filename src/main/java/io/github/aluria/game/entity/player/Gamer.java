package io.github.aluria.game.entity.player;

import io.github.aluria.common.utils.Players;
import io.github.aluria.game.entity.GameEntity;
import io.github.aluria.game.entity.combat.DamageType;
import io.github.aluria.game.entity.player.skill.SkillMap;
import io.github.aluria.game.entity.player.status.StatusMap;
import io.github.aluria.game.entity.player.upgrade.UpgradeMap;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Gamer implements GameEntity {
  
  public static Gamer createFromPlayer(Player player) {
    return Gamer.builder()
      .name(player.getName())
      .level(1)
      .experience(0)
      .skillMap(new SkillMap())
      .statusMap(new StatusMap())
      .upgradeMap(new UpgradeMap())
      .lastLocation(player.getLocation())
      .inventory(player.getInventory().getContents())
      .build();
  }
  
  @Include
  private String name;
  
  private int level;
  private int experience;
  
  private SkillMap skillMap;
  private StatusMap statusMap;
  private UpgradeMap upgradeMap;
  
  private Location lastLocation;
  private ItemStack[] inventory;
  
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private Player bukkitPlayer;
  
  @Override
  public boolean receiveDamage(int damageAmount, DamageType damageType, @Nullable GameEntity source) {
    return false;
  }
  
  @Override
  public boolean applyDamage(int damageDealt, DamageType damageType, GameEntity target) {
    return false;
  }
  
  public Player getBukkitPlayer() {
    if (bukkitPlayer == null) {
      bukkitPlayer = Players.getPlayerFromName(name);
    }
    
    return bukkitPlayer;
  }
}
