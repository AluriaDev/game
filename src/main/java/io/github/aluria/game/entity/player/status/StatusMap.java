package io.github.aluria.game.entity.player.status;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class StatusMap {
  
  private final Map<StatusType, Integer> statusMap = new ConcurrentHashMap<>();
  
  public int getStatusValue(StatusType type) {
    return this.getStatusValue(type, 0);
  }
  
  public int getStatusValue(StatusType type, int def) {
    return statusMap.getOrDefault(type, def);
  }
  
  public void setStatusValue(StatusType type, int value) {
    statusMap.put(type, value);
  }
  
  public void incrementStatusValue(StatusType type, int value) {
    this.setStatusValue(type, getStatusValue(type) + value);
  }
  
  public Map<StatusType, Integer> getStatusMap() {
    return statusMap;
  }
}
