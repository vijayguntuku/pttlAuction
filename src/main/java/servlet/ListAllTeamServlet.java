package servlet;

import dao.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TeamService;
import utility.ResponseUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/teamlist"})

public class ListAllTeamServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServlet.class);

    @Inject
    TeamService teamService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Response resp = null;

        try {
            resp = teamService.allTeamList();
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "TeamListServlet:doGet() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }

}
