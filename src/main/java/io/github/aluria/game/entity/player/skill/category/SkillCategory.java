package io.github.aluria.game.entity.player.skill.category;

import io.github.aluria.common.utils.Lore;
import org.bukkit.Material;

public enum SkillCategory {
  
  
  COMBAT("Combate", Material.DIAMOND_SWORD, Lore.of(
    "",
    "§7Habilidades de combate contra",
    "§7entidades e jogadores.",
    "",
    "§eClique para ver as habilidades."
  )),
  MANUAL_JOB("Trabalhos Manuais", Material.BREAD, Lore.of(
    "",
    "§7Habilidades que envolvem trabalhos brutos.",
    "",
    "§eClique para ver as habilidades."
  )),
  MANUFACTURING("Fabricação", Material.ANVIL, Lore.of(
    "",
    "§7Criação de ferramentas, armas e items em geral.",
    "",
    "§eClique para ver as habilidades."
  ));
  
  private String name;
  private Material material;
  private Lore description;
  
  SkillCategory(String name, Material material, Lore description) {
    this.name = name;
    this.material = material;
    this.description = description;
  }
  
  public String getName() {
    return name;
  }
  
  public Material getMaterial() {
    return material;
  }
  
  public Lore getDescription() {
    return description;
  }
  
}
