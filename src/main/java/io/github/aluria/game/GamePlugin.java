package io.github.aluria.game;


import io.github.aluria.common.database.provider.sql.SQLConnectionProvider;
import io.github.aluria.common.database.provider.sql.SQLiteConnectionProvider;
import io.github.aluria.common.scheduler.tick.TickableTask;
import io.github.aluria.common.utils.CommonPlugin;
import io.github.aluria.common.utils.PropertiesBuilder;
import io.github.aluria.game.entity.monster.GameMonsterHandler;
import io.github.aluria.game.entity.player.GamerHandler;
import io.github.aluria.game.menu.PlayerInventoryController;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Getter
public final class GamePlugin extends CommonPlugin {
  
  /**
   * World spawn location to use as default location provider.
   */
  public static final Location FALLBACK_LOCATION = Bukkit.getWorlds()
    .get(0)
    .getSpawnLocation();
  
  
  /**
   * Custom executor to use as default executor service.
   */
  public static final ExecutorService DEFAULT_EXECUTOR = new ThreadPoolExecutor(
    2,
    Integer.MAX_VALUE,
    60L,
    TimeUnit.SECONDS,
    new SynchronousQueue<>()
  );
  
  
  private PlayerInventoryController inventoryController;
  
  private TickableTask tickableTask;
  
  private GamerHandler gamerHandler;
  
  private GameMonsterHandler monsterHandler;
  
  @Override
  public void onEnable() {
    
    inventoryController = new PlayerInventoryController(this);
    inventoryController.init();
    
    tickableTask = new TickableTask(this);
    tickableTask.start();
    
    gamerHandler = new GamerHandler(this);
    
    monsterHandler = new GameMonsterHandler();
  }
  
  @Override
  public void onDisable() {
  
  }
  
  public SQLConnectionProvider loadProvider() {
    FileConfiguration config = this.getConfig();
    
    ConfigurationSection database = config.getConfigurationSection("banco-de-dados");
    if (database == null) {
      throw new NullPointerException("Falha ao carregar a configuração do banco de dados.");
    }
    
    // FIXME: 18/01/2021
    
    SQLiteConnectionProvider provider = new SQLiteConnectionProvider();
    provider.connect(new PropertiesBuilder()
      .with("dir", getDataFolder().getPath())
      .with("database", "game")
      .wrap());
    
    return provider;
  }
  
  public static GamePlugin getInstance() {
    return getPlugin(GamePlugin.class);
  }
  
  
}
