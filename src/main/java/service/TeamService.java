package service;

import dao.Response;
import dto.Team;

public interface TeamService {
    Response findTeamById(int id);

    Response saveOrUpdateTeam(Team team);

    Response deleteTeam(int id);

    Response allTeamList();

    Response UpdateTeam(int id ,Double value);
}
