package io.github.aluria.game.menu.upgrade;

import io.github.aluria.common.menu.Menu;
import io.github.aluria.common.menu.MenuHolder;
import io.github.aluria.common.utils.ItemBuilder;
import io.github.aluria.game.entity.player.upgrade.UpgradeMap;
import io.github.aluria.game.entity.player.upgrade.UpgradeType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;


public final class UpgradeMenu extends Menu {
  
  private final UpgradeType upgradeType;
  
  public UpgradeMenu(UpgradeType upgradeType) {
    super("Aprimorar " + upgradeType.getName(), 9 * 3);
    this.upgradeType = upgradeType;
  }
  
  @Override
  protected void render(Player player, MenuHolder holder) {
    holder.slot(12)
      .withItem(ItemBuilder.of(Material.STAINED_GLASS_PANE, 5)
        .name("§aAplicar melhoria")
        .lore(
          "",
          " §7Clique para aplicar esta",
          " §7melhoria no seu personagem.",
          ""
        )
        .build()
      )
      .soundOnClick(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 10)
      .withAction((h, e) -> {
      });
    
    holder.slot(14)
      .withItem(ItemBuilder.of(Material.STAINED_GLASS_PANE, 1)
        .name("§cCancelar Melhoria")
        .lore(
          "",
          " §7Clique se você deseja",
          " §7cancelar esta melhoria.",
          ""
        )
        .build()
      )
      .soundOnClick(Sound.ENTITY_PLAYER_LEVELUP, 10, 10)
      .withAction((h, e) -> e.getWhoClicked().closeInventory());
    
    super.render(player, holder);
  }
  
  
  private void upgrade(Gamer gamer) {
    UpgradeMap upgradeMap = gamer.getUpgradeMap();
    
    int upgradePoints = upgradeMap.getUpgradePoints();
    int upgradeLevel = upgradeMap.getUpgradeLevel(upgradeType);
    int cost = upgradeType.getUpgradePointsCost(upgradeLevel);
    
    if (upgradePoints <= cost) {
      gamer.sendMessage("§cVocê precisa de " + cost + " pontos para ter esta melhoria.");
      return;
    }
    
    upgradeMap.setUpgradePoints(upgradePoints - cost);
    
    gamer.sendMessage("§a§LGG! §aMelhoria " + upgradeType.getName() + " ");
    
    Player player = gamer.getBukkitEntity();
    if (player != null) {
      UpgradeListMenu.open(player);
    }
  }
}
