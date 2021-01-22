package io.github.aluria.game.menu;

import io.github.aluria.common.event.EventSubscription;
import io.github.aluria.common.scheduler.Scheduler;
import io.github.aluria.common.utils.ItemBuilder;
import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.menu.skill.SkillCategoryListMenu;
import io.github.aluria.game.menu.upgrade.UpgradeListMenu;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetSlot;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public final class PlayerInventoryController {
  
  private static final ItemStack AIR_ITEM = new ItemStack(Material.AIR);
  
  private static final ItemStack UPGRADES_ICON = ItemBuilder.of(Material.SKULL, 1, 3)
    .name("§6Melhorias")
    .lore(
      " §7Clique para consultar",
      " §7suas melhorias."
    )
    .build();
  
  private static final ItemStack SKILLS_ICON = ItemBuilder.of(Material.BOOK)
    .name("§6Proficiências")
    .lore(
      " §7Clique para consultar",
      " §7suas habilidades."
    )
    .build();
  
  private final GamePlugin plugin;
  
  public PlayerInventoryController(GamePlugin plugin) {
    this.plugin = plugin;
  }
  
  public void init() {
    Scheduler.async().runTaskTimer(() -> {
      for (Gamer gamer : plugin.getGamerLocalCache().getGamerMap().values()) {
        Player player = gamer.getBukkitEntity();
        if (player != null && isPlayerCraftingInv(player.getOpenInventory())) {
          setItems(player);
        }
      }
    }, 20, 20);
    
    
    EventSubscription.of(InventoryCloseEvent.class)
      .priority(EventPriority.HIGHEST)
      .handler(this::handleInventoryCloseEvent)
      .listen(plugin);
    
    EventSubscription.of(InventoryClickEvent.class)
      .priority(EventPriority.HIGHEST)
      .handler(this::handleInventoryClickEvent)
      .listen(plugin);
  }
  
  public void handleInventoryClickEvent(InventoryClickEvent event) {
    Player player = (Player) event.getWhoClicked();
    
    ItemStack currentItem = event.getCurrentItem();
    if (currentItem == null) {
      return;
    }
    
    
    if (currentItem.isSimilar(SKILLS_ICON)) {
      event.setCancelled(true);
      SkillCategoryListMenu.open(player);
    }
    
    if (currentItem.isSimilar(UPGRADES_ICON)) {
      event.setCancelled(true);
      UpgradeListMenu upgradeListMenu = new UpgradeListMenu();
    }
  }
  
  private void handleInventoryCloseEvent(InventoryCloseEvent event) {
    Player player = (Player) event.getPlayer();
    if (!isPlayerCraftingInv(player.getOpenInventory())) {
      return;
    }
    
    this.clearSlots(player);
  }
  
  private void setItems(Player player) {
    PacketPlayOutSetSlot skillsItemPacket = new PacketPlayOutSetSlot(0, 1, CraftItemStack.asNMSCopy(SKILLS_ICON));
    PacketPlayOutSetSlot upgradeItemsPacket = new PacketPlayOutSetSlot(0, 2, CraftItemStack.asNMSCopy(UPGRADES_ICON));
    
    PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
    playerConnection.sendPacket(skillsItemPacket);
    playerConnection.sendPacket(upgradeItemsPacket);
  }
  
  
  private void clearSlots(Player player) {
    PacketPlayOutSetSlot skillsItemPacket = new PacketPlayOutSetSlot(0, 1, CraftItemStack.asNMSCopy(AIR_ITEM));
    PacketPlayOutSetSlot upgradeItemsPacket = new PacketPlayOutSetSlot(0, 2, CraftItemStack.asNMSCopy(AIR_ITEM));
    
    PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
    playerConnection.sendPacket(skillsItemPacket);
    playerConnection.sendPacket(upgradeItemsPacket);
  }
  
  private boolean isPlayerCraftingInv(InventoryView view) {
    return view.getTopInventory().getSize() == 5;
  }
}
