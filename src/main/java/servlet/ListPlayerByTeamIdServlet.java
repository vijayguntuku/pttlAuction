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

@WebServlet(urlPatterns = {"/listplayerbyteamId"})

public class ListPlayerByTeamIdServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListPlayerByTeamIdServlet.class);

    @Inject
    PlayerService playerService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Response resp = null;
        try {
            int teamId = Integer.parseInt(request.getParameter("teamId"));
            resp = playerService.getPlayerListByTeamId(teamId);
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "ListPlayerByTeamIdServlet:doGet() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }

}
