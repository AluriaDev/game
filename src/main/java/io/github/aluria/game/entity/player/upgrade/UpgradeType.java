package io.github.aluria.game.entity.player.upgrade;

import io.github.aluria.common.utils.ItemBuilder;
import io.github.aluria.common.utils.Lore;
import io.github.aluria.game.utils.RomanNumbers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import static io.github.aluria.common.utils.Lore.of;

@Getter
@AllArgsConstructor
public enum UpgradeType {
  
  COMBAT("Habilidades de Combate", 500, Material.IRON_SWORD, of(
    "§7Aumenta o seu §fdano base §7em §b+1",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  SAVAGERY("Selvageria", 750, new MaterialData(351, (byte) 1), of(
    "§7Aumenta a sua chance de §facerto",
    "§fcritico §7em §b+1%.",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  DEFENDER("Defensor", 500, Material.CHAINMAIL_CHESTPLATE, of(
    "§7Aumenta a sua §fdefesa base§7 em §b+1",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  VITALITY("Vitalidade", 750, Material.GOLDEN_APPLE, of(
    "§7Aumenta sua §fvida maxima §7em §b+25",
    "",
    "§fCusto: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )){
    @Override
    public int getCurrentValue(UpgradeMap map) {
      return super.getCurrentValue(map) * 25;
    }
  },
  WISDOM("Sabedoria", 750, Material.BOOK, of(
    "§7Aumenta o seu §fganho de experiência",
    "§7de todas as as fontes em §b+10%",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )){
    @Override
    public int getCurrentValue(UpgradeMap map) {
      return super.getCurrentValue(map) * 10;
    }
  },
  SKILL_MASTERY("Maestria", 2500, Material.NETHER_STAR, of(
    "§7Aumenta o limite de nível das suas",
    "§fhabilidades passivas §7em §b+10",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )){
    @Override
    public int getCurrentValue(UpgradeMap map) {
      return super.getCurrentValue(map) * 10;
    }
  },
  DODGE("Esquiva", 300, Material.FEATHER, of(
    "§7Aumenta a sua chance de esquivar",
    "§7de ataques em §b+1%",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  )),
  PRECISION("Precisão", 300, Material.ARROW, of(
    "§7Aumenta sua chance de acertar",
    "§7ataques em §b+1%",
    "",
    "§7Custo: §a<cost> exp",
    "",
    "§eClique para melhorar."
  ));
  
  private String name;
  private int costPerLevel;
  private MaterialData iconData;
  private Lore lore;
  
  UpgradeType(String name, int costPerLevel, Material iconData, Lore lore) {
    this.name = name;
    this.costPerLevel = costPerLevel;
    this.iconData = new MaterialData(iconData);
    this.lore = lore;
  }
  
  public int getCurrentValue(UpgradeMap map){
    return map.getUpgradeLevel(this);
  }
  
  public int getUpgradePointsCost(int level) {
    return level * costPerLevel;
  }
  
  public ItemStack asItem(Gamer gamer){
    UpgradeMap upgradeMap = gamer.getUpgradeMap();
    
    int currentLevel = upgradeMap.getUpgradeLevel(this);
    
    return ItemBuilder.of(iconData.getItemType() , 1 , iconData.getData())
      .name("§e" + name + " " + RomanNumbers.toRoman(currentLevel))
      .lore(lore.clone()
        .replace("<cost>" , getUpgradePointsCost(currentLevel))
        .space(1 , 1)
        .colorize()
        .toArray()
      )
      .build();
  }
}
