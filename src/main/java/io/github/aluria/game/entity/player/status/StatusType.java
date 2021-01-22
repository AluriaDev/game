package io.github.aluria.game.entity.player.status;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusType {
	
	HEALTH("Vida"),
	MAX_HEALTH("Vida Máxima"),
	HEALTH_REGENERATION("Regeneração de Vida"),
	
	MANA("Mana"),
	MAX_MANA("Mana Máxima"),
	MANA_REGENERATION("Regeneração de Mana");
	
	private String name;
}
