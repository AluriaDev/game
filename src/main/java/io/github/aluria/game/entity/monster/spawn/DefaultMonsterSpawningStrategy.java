package io.github.aluria.game.entity.monster.spawn;

import io.github.aluria.game.entity.monster.GameMonster;
import io.github.aluria.game.entity.player.status.StatusMap;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class DefaultMonsterSpawningStrategy implements MonsterSpawningStrategy {
  
  private static final String MOB_DISPLAY_NAME = "&e[Nível <level>] &f<name>";
  
  public static String mobDisplayNameFormat() {
    return MOB_DISPLAY_NAME;
  }
  
  @Override
  public boolean preSpawn(Location location, Entity entity) {
    int distance = (int) location.distance(location.getWorld().getSpawnLocation());
    if (distance <= 0) {
      distance = 1;
    }
    
    distance = distance / 100;
    if (distance <= 0) {
      distance = 1;
    }
    
    entity.setCustomNameVisible(true);
    entity.setCustomName(mobDisplayNameFormat()
      .replace("<level>", String.valueOf(distance))
      .replace("name", entity.getType().getName()));
    
  
    
    
    return false;
  }
}
