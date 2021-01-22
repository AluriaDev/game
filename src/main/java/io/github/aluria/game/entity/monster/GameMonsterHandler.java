package io.github.aluria.game.entity.monster;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.entity.monster.spawn.DefaultMonsterSpawningStrategy;
import io.github.aluria.game.entity.monster.spawn.MonsterSpawningStrategy;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class GameMonsterHandler {
  
  private final Int2ObjectMap<GameMonster> monsterMap = new Int2ObjectLinkedOpenHashMap<>();
  
  public GameMonster getMonster(Entity entity) {
    return monsterMap.get(entity.getEntityId());
  }
  
  public void register(GameMonster monster) {
    monsterMap.put(monster.getEntityId(), monster);
  }
  
  public void unregister(int entityId) {
    monsterMap.remove(entityId);
  }
  
  public Int2ObjectMap<GameMonster> getMonsterMap() {
    return monsterMap;
  }
  
  private void handlePreSpawnEvent(PreCreatureSpawnEvent event) {
    Location spawnLocation = event.getSpawnLocation();
    
    MonsterSpawningStrategy strategy = null;
    if (spawnLocation.getWorld().equals(GamePlugin.FALLBACK_LOCATION.getWorld())) {
      strategy = new DefaultMonsterSpawningStrategy();
    }
    
    if (strategy != null) {
      strategy.preSpawn(event);
    }
  }
}
