package io.github.aluria.game.entity.player;

import io.github.aluria.common.event.EventSubscription;
import io.github.aluria.game.GamePlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public final class GamerHandler {
  
  private final ExecutorService service = GamePlugin.DEFAULT_EXECUTOR;
  
  private final GamePlugin gamePlugin;
  private final Map<String, Gamer> gamerMap;
  private final GamerStorage storage;
  
  public GamerHandler(GamePlugin plugin) {
    gamePlugin = plugin;
    gamerMap = new ConcurrentHashMap<>();
    storage = new GamerStorage(plugin.loadProvider());
    
    EventSubscription.of(PlayerJoinEvent.class)
      .priority(EventPriority.LOWEST)
      .handler(this::handleJoinEvent)
      .listen(plugin);
    
    EventSubscription.of(PlayerQuitEvent.class)
      .priority(EventPriority.LOWEST)
      .handler(this::handleQuitEvent)
      .listen(plugin);
    
  }
  
  public boolean put(Gamer gamer) {
    return gamerMap.put(gamer.getName().toLowerCase(), gamer) != null;
  }
  
  public boolean clear(Gamer gamer) {
    return gamerMap.remove(gamer.getName().toLowerCase(), gamer);
  }
  
  public Gamer getGamer(String playerName) {
    return gamerMap.get(playerName.toLowerCase());
  }
  
  public Gamer getGamer(OfflinePlayer player) {
    return this.getGamer(player.getName());
  }
  
  public Collection<Gamer> getOnlineGamers() {
    return gamerMap.values();
  }
  
  public Map<String, Gamer> getGamerMap() {
    return gamerMap;
  }
  
  private void handleJoinEvent(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    
    service.execute(() -> {
      Gamer gamer = this.getGamer(player);
      if (gamer != null) {
        return;
      }
      
      gamer = storage.selectGamer(player.getName());
      if (gamer == null) {
        gamer = Gamer.createFromPlayer(player);
        storage.updateGamer(gamer);
      }
      
      gamerMap.put(player.getName().toLowerCase(), gamer);
    });
    
  }
  
  private void handleQuitEvent(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    service.execute(() -> {
      Gamer gamer = this.getGamer(player);
      if (gamer != null) {
        storage.updateGamer(gamer);
      }
    });
  }
}
