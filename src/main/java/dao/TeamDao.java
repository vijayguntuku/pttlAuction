package dao;

import dto.Team;

import java.util.List;

public interface TeamDao {

    Team findTeamByID(int id);

    boolean saveOrUpdateTeam(Team team);

    boolean deleteTeam(int id);

    List<Team> AllTeamList();

    boolean UpdateTeam(int id ,Double value);

}
