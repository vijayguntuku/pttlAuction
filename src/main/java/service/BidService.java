package service;

import dao.Response;
import dto.Bid;

public interface BidService {

    Response findBidById(int id);

    Response saveOrUpdateBid(Bid bid);
}
