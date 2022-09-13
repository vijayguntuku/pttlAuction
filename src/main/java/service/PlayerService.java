package service;

import dao.Response;
import dto.Player;

public interface PlayerService {

    Response findPlayerById(int id);

    Response saveOrUpdatePlayer(Player player);

    Response deletePlayer(int id);

    Response playerList();

	Response getPlayerListByTeamId(int teamId);

}
