package io.github.aluria.game.entity.player.skill;

import io.github.aluria.game.entity.player.skill.type.SkillType;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public final class SkillMap {

  private Map<SkillType, Skill> skillMap = new ConcurrentHashMap<>();
  private int skillLevelLimit = 20;

  public final Skill getSkill(SkillType skillType) {
    Skill skill = this.skillMap.get(skillType);
    if (skill == null) {
      skill = new Skill(skillType, 1, 0);
      skillMap.put(skillType, skill);
    }

    return skill;
  }
}
