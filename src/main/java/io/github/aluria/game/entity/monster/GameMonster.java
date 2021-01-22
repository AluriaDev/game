package io.github.aluria.game.entity.monster;

import io.github.aluria.game.entity.GameEntity;
import io.github.aluria.game.entity.combat.DamageType;
import io.github.aluria.game.entity.player.status.StatusMap;
import lombok.*;
import org.bukkit.entity.Entity;

@Data
@Builder
public final class GameMonster implements GameEntity {
  
  private int entityId;
  private int level;
  private int experience;
  private StatusMap statusMap;
  
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private Entity bukkitEntity;
  
  @Override
  public boolean receiveDamage(int damageAmount, DamageType damageType, GameEntity source) {
    
    
    
    return false;
  }
  
  @Override
  public boolean applyDamage(int damageDealt, DamageType damageType, GameEntity target) {
    return false;
  }
}
