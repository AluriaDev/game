package io.github.aluria.game.entity.monster.spawn;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;

public interface MonsterSpawningStrategy {
  
  boolean preSpawn(PreCreatureSpawnEvent event);
  
}
