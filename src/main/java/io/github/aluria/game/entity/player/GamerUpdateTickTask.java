package io.github.aluria.game.entity.player;

import io.github.aluria.common.scheduler.tick.Tickable;
import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.entity.player.status.StatusMap;
import io.github.aluria.game.entity.player.status.StatusType;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class GamerUpdateTickTask implements Tickable {
  
  private final GamePlugin plugin;
  
  private int syncCounter = 0;
  private final AtomicInteger asyncCounter = new AtomicInteger();
  
  public GamerUpdateTickTask(GamePlugin plugin) {
    this.plugin = plugin;
  }
  
  @Override
  public void onTickAsync() {
    if (asyncCounter.getAndIncrement() < 20) {
      return;
    }
    
    asyncCounter.set(0);
    
    for (Gamer gamer : plugin.getGamerHandler().getGamerMap().values()) {
      this.processGamerAsyncTick(gamer);
    }
  }
  
  @Override
  public void onTick() {
    if (syncCounter++ < 20) {
      return;
    }
    
    syncCounter = 0;
    
    for (Gamer gamer : plugin.getGamerHandler().getGamerMap().values()) {
      this.processGamerTick(gamer);
    }
  }
  
  
  private void processGamerAsyncTick(Gamer gamer) {
    Player player = gamer.getBukkitPlayer();
    if (player == null) {
      return;
    }
    
    StatusMap statusMap = gamer.getStatusMap();
    
    int health = statusMap.getStatusValue(StatusType.HEALTH, 1);
    int healthRegeneration = statusMap.getStatusValue(StatusType.HEALTH_REGENERATION, 1);
    int maxHealth = statusMap.getStatusValue(StatusType.MAX_HEALTH, 100 + (gamer.getLevel() * 5));
    
    int mana = statusMap.getStatusValue(StatusType.MANA, 1);
    int manaRegeneration = statusMap.getStatusValue(StatusType.MANA_REGENERATION, 1);
    int maxMana = statusMap.getStatusValue(StatusType.MAX_MANA, 100 + (gamer.getLevel() * 5));
    
    health += healthRegeneration;
    if (health >= maxHealth) {
      health = maxHealth;
    }
    
    mana += manaRegeneration;
    if (mana >= maxMana) {
      mana = maxMana;
    }
    
    statusMap.setStatusValue(StatusType.HEALTH, health);
    statusMap.setStatusValue(StatusType.MANA, mana);
    
    
    String healthBar = "§cVida: " + health + "/" + maxHealth;
    String manaBar = "§bMana:" + health + "/" + maxMana;
    
    player.sendActionBar('&', healthBar + "     " + manaBar);
  }
  
  private void processGamerTick(Gamer gamer) {
    Player player = gamer.getBukkitPlayer();
    if (player == null) {
      return;
    }
    
    StatusMap statusMap = gamer.getStatusMap();
    int health = statusMap.getStatusValue(StatusType.HEALTH, 1);
    int maxHealth = statusMap.getStatusValue(StatusType.MAX_HEALTH, 100);
    
    double healthPercentage = ((health * 100) / maxHealth);
    player.setHealth((healthPercentage / 10) * 2);
  }
}
