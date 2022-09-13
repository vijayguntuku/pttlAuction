package dao;

import dto.Player;

import java.util.List;

public interface PlayerDao {

    Player findPlayerByID(int id);

    boolean saveOrUpdatePlayer(Player player);

    boolean deletePlayer(int id);

    List<Player> playerList();

	List<Player> getPlayerListByTeamId(int teamId);

}
