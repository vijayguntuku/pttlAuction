package servlet;

import dao.Response;
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

@WebServlet(urlPatterns = {"/playerlist"})

public class PlayerListServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerListServlet.class);

    @Inject
    PlayerService playerService;

    Response resp = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Response resp = null;

        try {
            resp = playerService.playerList();
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "playerListServlet:doGet() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }
}
