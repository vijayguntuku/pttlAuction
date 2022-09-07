package service;

import dao.BidDao;
import dao.Response;
import dto.Bid;
import exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.ResponseUtils;

import javax.inject.Inject;

public class BidServiceImpl implements BidService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BidServiceImpl.class);

    @Inject
    BidDao bidDao;

    @Override
    public Response findBidById(int id) {
        Response resp = null;
        try {
            Bid bid = bidDao.findBidByID(id);
            if (bid != null) {
                resp = ResponseUtils.createResponse(true, "Bid Retrieved successfully with id=" + id, 200, bid);
            } else {
                resp = ResponseUtils.createResponse(true, "No Bid found with given id=." + id, 200, null);
            }
        } catch (DatabaseException e) {
            String message = "BidServiceImpl:findBidById(id) Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "BidServiceImpl:findBidById(id) Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }

    @Override
    public Response saveOrUpdateBid(Bid bid) {
        Response resp = null;
        try {
            boolean inserted = bidDao.saveOrUpdateBid(bid);
            if (inserted) {
                resp = ResponseUtils.createResponse(true, "Data saved successfully", 200, null);
            }

        } catch (DatabaseException e) {
            String message = "BidServiceImpl:saveOrUpdateBid() Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "BidServiceImpl:saveOrUpdateBid()  Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return resp;
    }
}
