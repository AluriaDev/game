package io.github.aluria.game.entity.player.upgrade;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class UpgradeMap {
  
  private Map<UpgradeType, Integer> upgradeMap;
  private int upgradePoints;
  
  public UpgradeMap() {
    upgradeMap = new ConcurrentHashMap<>();
    upgradePoints = 0;
  }
  
  public final int getUpgradeLevel(UpgradeType upgradeType) {
    return upgradeMap.getOrDefault(upgradeType, 1);
  }
  
  public final boolean setUpgradeLevel(UpgradeType upgradeLevel, int level) {
    return upgradeMap.put(upgradeLevel, level) != null;
  }
  
  public final boolean incrementUpgradeLevel(UpgradeType type, int value) {
    return setUpgradeLevel(type, getUpgradeLevel(type) + value);
  }
  
  public final boolean decrementUpgradeLevel(UpgradeType type, int value) {
    return setUpgradeLevel(type, getUpgradeLevel(type) - value);
  }
  
  public Map<UpgradeType, Integer> getUpgradeMap() {
    return upgradeMap;
  }
  
}
