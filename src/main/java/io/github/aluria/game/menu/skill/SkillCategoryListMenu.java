package io.github.aluria.game.menu.skill;

import io.github.aluria.common.menu.Menu;
import io.github.aluria.common.utils.ItemBuilder;
import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.entity.player.skill.category.SkillCategory;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class SkillCategoryListMenu extends Menu {
  
  private static final Menu MENU = new SkillCategoryListMenu();
  
  public static void open(Player player) {
    MENU.show(player);
  }
  
  
  private final GamePlugin plugin = GamePlugin.getInstance();
  
  public SkillCategoryListMenu() {
    super("Proficiências - Categorias", 9 * 3);
    
    slot(11, transform(SkillCategory.MANUAL_JOB))
      .soundOnClick(Sound.ENTITY_ITEM_PICKUP, 10, 10)
      .withAction((menuHolder, event) -> openSkillCategoryMenu(event.getWhoClicked(), SkillCategory.MANUAL_JOB));
    
    slot(13, transform(SkillCategory.COMBAT))
      .soundOnClick(Sound.ENTITY_ITEM_PICKUP, 10, 10)
      .withAction((menuHolder, event) -> openSkillCategoryMenu(event.getWhoClicked(), SkillCategory.COMBAT));
    
    slot(15, transform(SkillCategory.MANUFACTURING))
      .soundOnClick(Sound.ENTITY_ITEM_PICKUP, 10, 10)
      .withAction((menuHolder, event) -> openSkillCategoryMenu(event.getWhoClicked(), SkillCategory.MANUFACTURING));
  }
  
  private void openSkillCategoryMenu(HumanEntity clicker, SkillCategory category) {
    SkillCategoryMenu categoryMenu = new SkillCategoryMenu(category);
    categoryMenu.show(((Player) clicker));
  }
  
  private ItemStack transform(SkillCategory category) {
    return new ItemBuilder(category.getMaterial())
      .name("§e" + category.getName())
      .lore(category.getDescription().toArray())
      .build();
  }
}
