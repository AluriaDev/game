package io.github.aluria.game.entity.player.skill.type;

import io.github.aluria.common.utils.Lore;
import io.github.aluria.game.entity.player.skill.category.SkillCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum SkillType {
  
  //COMBAT
  
  SWORD("Espada", SkillCategory.COMBAT, Material.STONE_SWORD, Lore.of(
    "",
    "§7Aumenta a sua §fchance de acerto e dano",
    "§7com espadas contra monstros e jogadores.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  AXE("Machado", SkillCategory.COMBAT, Material.STONE_AXE, Lore.of(
    "",
    "§7Aumenta o seu §fdano com machados",
    "§7contra monstros e jogadores.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  ARCHERY("Artilharia", SkillCategory.COMBAT, Material.BOW, Lore.of(
    "",
    "§7Aumenta o seu §fdano com arcos",
    "§7contra monstros e jogadores.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  ACROBATICS("Acrobacia", SkillCategory.COMBAT, Material.IRON_BOOTS, Lore.of(
    "",
    "§7Aumenta a chance de &fcair rolando",
    "§7e evitar dano de queda.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  
  // MANUAL JOB
  
  MINING("Mineração", SkillCategory.MANUAL_JOB, Material.IRON_PICKAXE, Lore.of(
    "",
    "§7Aumenta a sua chance de obter",
    "§7minerios ao quebrar pedras.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  EXCAVATION("Escavação", SkillCategory.MANUAL_JOB, Material.IRON_SPADE, Lore.of(
    "",
    "§7Aumenta a sua chance de achar",
    "§7fragmentos raros ao escavar blocos.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  WOODCUTTING("Corte de Arvores", SkillCategory.MANUAL_JOB, Material.IRON_AXE, Lore.of(
    "",
    "§7Aumenta sua chance de sucesso",
    "§7ao coletar madeiras das arvores.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  FARMING("Agricultura", SkillCategory.MANUAL_JOB, Material.SEEDS, Lore.of(
    "",
    "§7Melhora o crescimento dos grãos",
    "§7e vegetais que você panta.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  FISHING("Pescaria", SkillCategory.MANUAL_JOB, Material.FISHING_ROD, Lore.of(
    "",
    "§7Aumenta a chance de encontrar",
    "§7peixes raros ao pescar..",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  
  // FABRICAÇÃO
  
  REPAIRING("Reparação de Items", SkillCategory.MANUFACTURING, Material.ANVIL, Lore.of(
    "",
    "§7Aumenta a quantidade de reparo",
    "§7em items danificados.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  CRAFTING("Criação de Items", SkillCategory.MANUFACTURING, Material.WORKBENCH, Lore.of(
    "",
    "§7Desbloqueia items melhores para",
    "§7serem criados na mesa de trabalho.",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  )),
  ALCHEMY("Alquimia", SkillCategory.MANUFACTURING, Material.POTION, Lore.of(
    "",
    "§7Desbloqueia poções melhores",
    "§7para serem feitas no altar de poções..",
    "",
    "§7Nível Atual: §b<skill_level>",
    ""
  ));
  
  
  private String name;
  private SkillCategory category;
  private Material material;
  private Lore lore;
  
  public static List<SkillType> getSkillTypesFromCategory(SkillCategory category) {
    return Arrays.stream(values())
      .filter(type -> type.getCategory().equals(category))
      .sorted(Comparator.comparingInt(SkillType::ordinal))
      .collect(Collectors.toList());
  }
}
