package dao;

import dto.Bid;
import exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BidDaoImpl implements BidDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(BidDaoImpl.class);


    @Override
    public Bid findBidByID(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = null;
        Bid bid=null;

        try {
            query = "select * from bid where id=?";
            connection = DBConnection.getConnectionNonSingleTon();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            bid = new Bid();
            while (resultset.next()) {
                bid.setId(resultset.getInt("id"));
                bid.setStart_time(resultset.getString("start_time"));
                bid.setEnd_time(resultset.getString("end_time"));
                bid.setPlayer_id(resultset.getInt("player_id"));
                bid.setTeam_id(resultset.getInt("team_id"));
                bid.setSold(resultset.getBoolean("issold"));
                bid.setAmount(resultset.getDouble("amount"));
            }

        } catch (DatabaseException databaseException) {
            LOGGER.error("Exception while creating Database Connection.", databaseException);
            // databaseException.printStackTrace();
            throw databaseException;
        } catch (SQLException exception) {
            LOGGER.error("SQLException occured while reading data from Database.", exception);
            throw new DatabaseException("Exception occured while reading data from Database.");
        } catch (Exception exception) {
            LOGGER.error("Exception occured while reading data from Database.", exception);
            throw new DatabaseException("Exception occured while reading data from Database.");
        } finally {
            DBConnection.closeConnection(connection);
        }
        return bid;
    }

    @Override
    public boolean saveOrUpdateBid(Bid bid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnectionNonSingleTon();
            String query = null;
            if (bid.getId() != 0) {
                query = "update bid set start_time =? ,end_time = ?,player_id = ? ,team_id =? ,issold = ?,amount=? where id =?";
            } else {
                query = "insert into bid (start_time,end_time,player_id,team_id,issold,amount,id) values(?,?,?,?,?,?,?)";
            }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,bid.getStart_time());
            preparedStatement.setString(2, bid.getEnd_time());
            preparedStatement.setInt(3, bid.getPlayer_id());
            preparedStatement.setInt(4, bid.getTeam_id());
            preparedStatement.setBoolean(5, bid.isSold());
            preparedStatement.setDouble(6,bid.getAmount());
            if (bid.getId() != 0) {
                preparedStatement.setInt(7, bid.getId());
            }

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }


        } catch (DatabaseException databaseException) {
            LOGGER.error("Exception while updating data into Database Connection.", databaseException);
            // databaseException.printStackTrace();
            throw databaseException;
        } catch (SQLException exception) {
            LOGGER.error("SQLException occured while updatating data into Database.", exception);
            throw new DatabaseException("Exception occurred while updating data into Database.");
        } catch (Exception exception) {
            LOGGER.error("Exception occurred while updating data into Database.", exception);
            throw new DatabaseException("Exception occurred while updating data into Database.");
        } finally {
            DBConnection.closeConnection(connection);

        }
        return false;
    }
}
