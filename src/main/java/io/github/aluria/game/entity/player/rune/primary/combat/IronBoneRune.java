package io.github.aluria.game.entity.player.rune.combat;

import io.github.aluria.common.utils.Lore;
import io.github.aluria.game.entity.GameEntity;
import io.github.aluria.game.entity.player.Gamer;
import io.github.aluria.game.entity.player.rune.PrimaryRune;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public final class IronBoneRune extends PrimaryRune {
  
  public IronBoneRune() {
    super(1, "Osso de Ferro", Material.BONE, Lore.of(
      "",
      "§7Os primeiros 3 ataques que você sofre",
      "§7tem o dano reduzido em §b<scalingValue>%.",
      "",
      "§7Este efeito tem um tempo de recarga",
      "§7de §b<scalingValue>s."
    ));
  }
  
  @Override
  public boolean handleReceivedDamage(GameEntity damager, Gamer target, double damage) {
    
    
    return super.handleReceivedDamage(damager, target, damage);
  }
  
  @Override
  public ItemStack createIcon(Gamer gamer) {
    return null;
  }
  
  
  public class IronBoneRuneCounter {
    
    private Map<String , >
    
  }
}
