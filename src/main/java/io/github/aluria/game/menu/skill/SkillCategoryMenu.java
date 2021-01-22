package io.github.aluria.game.menu.skill;

import io.github.aluria.common.menu.Menu;
import io.github.aluria.common.menu.MenuHolder;
import io.github.aluria.common.utils.ItemBuilder;
import io.github.aluria.game.GamePlugin;
import io.github.aluria.game.entity.player.skill.category.SkillCategory;
import io.github.aluria.game.entity.player.skill.type.SkillType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SkillCategoryMenu extends Menu {
  
  private static final int[] SLOTS = {
    10, 11, 12, 13, 14, 15, 16,
    19, 20, 21, 22, 23, 24, 25,
    28, 29, 30, 31, 32, 33, 34,
    37, 38, 39, 40, 41, 42, 43
  };
  
  private final SkillCategory category;
  private final GamePlugin gamePlugin = GamePlugin.getInstance();
  
  public SkillCategoryMenu(SkillCategory category) {
    super(category.getName(), 9 * 5);
    this.category = category;
  }
  
  @Override
  protected void render(Player player, MenuHolder holder) {
    Gamer gamer = gamePlugin.getGamerLocalCache().getGamer(player);
    if (gamer == null) {
      return;
    }
    
    List<SkillType> categories = SkillType.getSkillTypesFromCategory(category);
    for (int i = 0; i < categories.size(); i++) {
      SkillType skillType = categories.get(i);
      int slot = SLOTS[i];
      
      slot(slot, transformItem(gamer, skillType))
        .closeOnClick();
    }
    
    
    super.render(player, holder);
  }
  
  public SkillCategory getCategory() {
    return category;
  }
  
  private ItemStack transformItem(Gamer gamer, SkillType type) {
    return new ItemBuilder(type.getMaterial())
      .name("§a" + type.getName())
      .lore(type.getLore()
        .replace("<skill_level>", gamer.getSkillMap().getSkill(type))
        .toArray()
      )
      .build();
  }
}
