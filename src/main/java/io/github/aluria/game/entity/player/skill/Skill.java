package io.github.aluria.game.entity.player.skill;

import io.github.aluria.game.entity.player.skill.type.SkillType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.MODULE)
public class Skill {

  private final SkillType skillType;
  private int level;
  private int exp;

}
