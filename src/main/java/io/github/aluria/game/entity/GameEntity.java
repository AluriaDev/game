package io.github.aluria.game.entity;

import com.sun.istack.internal.NotNull;
import io.github.aluria.game.entity.combat.DamageType;
import io.github.aluria.game.entity.player.status.StatusMap;

import javax.annotation.Nullable;

public interface GameEntity {
  
  /**
   * Level atual da entidade.
   */
  int getLevel();
  
  /**
   * Experiência atual da entidade.
   */
  int getExperience();
  
  /**
   * Mapa de status da entidade.
   *
   * @return um mapa de status contendo as informações
   * desta entidade.
   */
  StatusMap getStatusMap();
  
  /**
   * Recebe dano causado por uma entidade
   * devidamente registrada do jogo.
   *
   * @param damageAmount quantidade de dano recebido
   * @param damageType   tipo de dano recebido
   * @param source       fonte do dano recebido.
   * @return true se o dano foi devidamente recebido.
   */
  boolean receiveDamage(int damageAmount, @NotNull DamageType damageType, @Nullable GameEntity source);
  
  /**
   * Aplica dano em uma entidade devidamente
   * registrada do jogo.
   *
   * @param damageDealt quantidade de dano que será causado
   * @param damageType  tipo de dano que será causado.
   * @param target      alvo que receberá o dano.
   * @return true se o dano foi devidamente aplicado.
   */
  boolean applyDamage(int damageDealt, @NotNull DamageType damageType, @NotNull GameEntity target);
}
