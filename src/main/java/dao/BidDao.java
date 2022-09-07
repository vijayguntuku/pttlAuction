package dao;

import dto.Bid;

public interface BidDao {

    Bid findBidByID(int id);

    boolean saveOrUpdateBid(Bid bid);

}
