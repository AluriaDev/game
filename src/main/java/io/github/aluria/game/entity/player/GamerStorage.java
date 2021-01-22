package io.github.aluria.game.entity.player;

import io.github.aluria.common.database.provider.sql.SQLConnectionProvider;
import io.github.aluria.game.entity.player.skill.SkillMap;
import io.github.aluria.game.entity.player.status.StatusMap;
import io.github.aluria.game.entity.player.upgrade.UpgradeMap;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.github.aluria.game.utils.GameSerializers.*;

public class GamerStorage {
  
  private static final String CREATE_TABLE =
    "CREATE TABLE IF NOT EXISTS gamers (" +
      "player_name VARCHAR(16) PRIMARY KEY NOT NULL, " +
      "level INTEGER NOT NULL, " +
      "exp INTEGER NOT NULL, " +
      "skill_map TEXT NOT NULL, " +
      "status_map TEXT NOT NULL, " +
      "upgrade_map TEXT NOT NULL, " +
      "last_location TEXT NOT NULL, " +
      "inventory TEXT NOT NULL" +
      ")";
  
  private static final String SELECT_ONE =
    "SELECT * FROM gamers WHERE player_name = ?";
  
  private static final String REPLACE_ONE =
    "REPLACE INTO gamers " +
      "(player_name, level, exp, skill_map, status_map, upgrade_map, last_location, inventory) " +
      "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  
  private final SQLConnectionProvider provider;
  
  public GamerStorage(SQLConnectionProvider provider) {
    this.provider = provider;
  }
  
  public boolean createTable() {
    return provider.execute(CREATE_TABLE);
  }
  
  public int updateGamer(Gamer gamer) {
    return provider.executeUpdate(
      REPLACE_ONE,
      gamer.getName().toLowerCase(),
      gamer.getLevel(),
      gamer.getExperience(),
      toJson(gamer.getSkillMap()),
      toJson(gamer.getStatusMap()),
      toJson(gamer.getUpgradeMap()),
      serializeLocation(gamer.getLastLocation()),
      toBase64List(gamer.getInventory())
    );
  }
  
  public Gamer selectGamer(String playerName) {
    return provider.getFirstFromQuery(SELECT_ONE, this::fromResultSet, playerName.toLowerCase());
  }
  
  private Gamer fromResultSet(ResultSet resultSet) throws SQLException {
    return Gamer.builder()
      .name(resultSet.getString("player_name"))
      .level(resultSet.getInt("level"))
      .experience(resultSet.getInt("exp"))
      .skillMap(fromJson(resultSet.getString("skill_map"), SkillMap.class))
      .statusMap(fromJson(resultSet.getString("status_map"), StatusMap.class))
      .upgradeMap(fromJson(resultSet.getString("upgrade_map"), UpgradeMap.class))
      .lastLocation(deserializeLocation(resultSet.getString("last_location")))
      .inventory(fromBase64List(resultSet.getString("inventory")))
      .build();
  }
}
