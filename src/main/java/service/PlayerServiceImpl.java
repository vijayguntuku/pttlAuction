package service;

import dao.PlayerDao;
import dao.Response;
import dto.Player;
import exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.ResponseUtils;

import javax.inject.Inject;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Inject
    PlayerDao playerDao;

    @Override
    public Response findPlayerById(int id) {
        Response resp = null;
        try {
            Player player = playerDao.findPlayerByID(id);
            if (player != null) {
                resp = ResponseUtils.createResponse(true, "Player Retrieved successfully with id=" + id, 200, player);
            } else {
                resp = ResponseUtils.createResponse(true, "No Player found with given id=." + id, 200, null);
            }
        } catch (DatabaseException e) {
            String message = "PlayerServiceImpl:findPlayerById(id) Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "PlayerServiceImpl:findPlayerById(id) Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response saveOrUpdatePlayer(Player player) {
        Response resp = null;
        try {
            boolean inserted = playerDao.saveOrUpdatePlayer(player);
            if (inserted) {
                resp = ResponseUtils.createResponse(true, "Data saved successfully", 200, player);
            }

        } catch (DatabaseException e) {
            String message = "PlayerServiceImpl:saveOrUpdatePlayer Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "PlayerServiceImpl:saveOrUpdatePlayer  Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response deletePlayer(int id) {
        Response resp = null;
        try {
            boolean isDeleted = playerDao.deletePlayer(id);
            if (isDeleted) {
                resp = ResponseUtils.createResponse(true, "Player deleted successfully with given id=" + id, 200, null);
            }
        } catch (DatabaseException e) {
            String message = "PlayerServiceImpl:deletePlayer() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "PlayerServiceImpl:deletePlayer() Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response playerList() {
        Response resp = null;
        try {
            List<Player> playerList = playerDao.playerList();
            resp = ResponseUtils.createResponse(true, "Data Retrieved successfully=",200,playerList);
        }catch (DatabaseException e){
            String message = "PlayerServiceImpl:AllPlayerList() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }catch (Exception e){
            String message ="PlayerServiceImpl:listAllPlayer() Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER,e, message);
        }

        return resp;
    }
    @Override
    public Response getPlayerListByTeamId(int teamId) {
        Response resp = null;
        try {
            List<Player> playerList = playerDao.getPlayerListByTeamId(teamId);
            resp = ResponseUtils.createResponse(true, "Data Retrieved successfully=",200,playerList);
        }catch (DatabaseException e){
            String message = "PlayerServiceImpl:AllPlayerList() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }catch (Exception e){
            String message ="PlayerServiceImpl:listAllPlayer() Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER,e, message);
        }

        return resp;
    }
    }

