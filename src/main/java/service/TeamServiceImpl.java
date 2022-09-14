package service;

import dao.Response;
import dao.TeamDao;
import dto.Team;
import exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.ResponseUtils;

import javax.inject.Inject;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Inject
    TeamDao teamDao;

    @Override
    public Response findTeamById(int id) {
        Response resp = null;
        try {
            Team team = teamDao.findTeamByID(id);
            if (team != null) {
                resp = ResponseUtils.createResponse(true, "Team data Retrieved successfully with id=" + id, 200, team);
            } else {
                resp = ResponseUtils.createResponse(true, "No Team found with given id=." + id, 200, null);
            }
        } catch (DatabaseException e) {
            String message = "TeamServiceImpl:findTeamById(id) Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "TeamServiceImpl:findTeamById(id) Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response saveOrUpdateTeam(Team team) {
        Response resp = null;
        try {
            boolean inserted = teamDao.saveOrUpdateTeam(team);
            if (inserted) {
                resp = ResponseUtils.createResponse(true, " Data saved successfully", 200, null);
            }

        } catch (DatabaseException e) {
            String message = "TeamServiceImpl:saveOrUpdateTeam Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "TeamServiceImpl:saveOrUpdateTeam  Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response deleteTeam(int id) {
        Response resp = null;
        try {
            boolean isDeleted = teamDao.deleteTeam(id);
            if (isDeleted) {
                resp = ResponseUtils.createResponse(true, "Team deleted successfully with given id=" + id, 200, null);
            }
        } catch (DatabaseException e) {
            String message = "TeamServiceImpl:deleteTeam() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "TeamServiceImpl:deleteTeam() Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response allTeamList() {
        Response resp = null;
        try {

            List<Team> teamList = teamDao.AllTeamList();
            resp = ResponseUtils.createResponse(true, "Data Retrieved successfully=", 200, teamList);
        } catch (DatabaseException e) {
            String message = "TeamServiceImpl:AllTeamList() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "TeamServiceImpl:listAllTeam() Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response UpdateTeam(int id, Double value) {
        Response resp = null;
        try {
            boolean inserted = teamDao.UpdateTeam(id, value);
            if (inserted) {
                resp = ResponseUtils.createResponse(true, " Data saved successfully", 200, null);
            }

        } catch (DatabaseException e) {
            String message = "TeamServiceImpl:UpdateTeam Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "TeamServiceImpl:UpdateTeam  Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }
}
