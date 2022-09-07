package servlet;

import dao.Response;
import dto.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PlayerService;
import utility.ResponseUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/player"})

public class PlayerServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerServlet.class);

    @Inject
    PlayerService playerService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Response resp = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            resp = playerService.findPlayerById(id);
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "playerServlet:doGet() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Response resp = null;
        try {
            Player player = utility.JsonUtils.convertToObject(req, Player.class);
            resp = playerService.saveOrUpdatePlayer(player);
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "PlayerServlet:doPost() Exception occured while inserting  data into Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//        Response resp = null;
//
//        try {
//            Player player =utility.JsonUtils.convertToObject(req, Player.class);
//            resp = playerService.saveOrUpdatePlayer(player);
//            PrintWriter out = response.getWriter();
//            out.println(utility.JsonUtils.convertToString(resp));
//
//        }catch (Exception e){
//            String message = "playerServlet:doPut Exception occurred while inserting data from Database.";
//            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
//        }
//    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Response resp = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            resp = playerService.deletePlayer(id);
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "PlayerServlet:doDelete() Exception occurred while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }
}
