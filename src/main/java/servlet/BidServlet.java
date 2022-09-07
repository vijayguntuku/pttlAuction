package servlet;

import dao.Response;
import dto.Bid;
import dto.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.BidService;
import utility.ResponseUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/bid"})

public class BidServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(BidServlet.class);

    @Inject
    BidService bidService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Response resp = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            resp = bidService.findBidById(id);
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "BidServlet:doGet() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Response resp = null;
        try {
           Bid bid = utility.JsonUtils.convertToObject(req, Player.class);
            resp = bidService.saveOrUpdateBid(bid);
            PrintWriter out = response.getWriter();
            out.println(utility.JsonUtils.convertToString(resp));
        }catch (Exception e){
            String message = "BidServlet:doPost() Exception occured while inserting  data into Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }
    }
}
