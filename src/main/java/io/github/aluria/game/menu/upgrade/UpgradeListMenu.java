package io.github.aluria.game.menu.upgrade;

import io.github.aluria.common.menu.Menu;
import io.github.aluria.common.menu.MenuHolder;
import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.entity.player.upgrade.UpgradeType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class UpgradeListMenu extends Menu {
  private static final Menu MENU = new UpgradeListMenu();
  
  private static final int[] SLOTS = {
    10, 11, 12, 13, 14, 15, 16,
    19, 20, 21, 22, 23, 24, 25,
    28, 29, 30, 31, 32, 33, 34,
    37, 38, 39, 40, 41, 42, 43
  };
  
  public static void open(Player player) {
    MENU.show(player);
  }
  
  private final GamePlugin plugin = GamePlugin.getInstance();
  
  public UpgradeListMenu() {
    super("Melhorias", 9 * 6);
  }
  
  @Override
  protected void render(Player player, MenuHolder holder) {
    Gamer gamer = plugin.getGamerLocalCache().getGamer(player);
    if (gamer == null) {
      return;
    }
    
    UpgradeType[] values = UpgradeType.values();
    for (int i = 0, valuesLength = values.length; i < valuesLength; i++) {
      UpgradeType type = values[i];
      
      holder.slot(SLOTS[i], type.asItem(gamer))
        .soundOnClick(Sound.ENTITY_ITEM_PICKUP, 10, 10)
        .withAction((h, e) -> new UpgradeMenu(type).show(player));
    }
    
    
    super.render(player, holder);
  }
  
  
}
