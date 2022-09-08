package dao;

import dto.Player;
import exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoImpl implements PlayerDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDaoImpl.class);

    @Override
    public Player findPlayerByID(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = null;
        Player player=null;

        try {
            query = "select * from player where id=?";
            connection = DBConnection.getConnectionNonSingleTon();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();
            player = new Player();
            while (resultset.next()) {
                player.setId(resultset.getInt("id"));
                player.setName(resultset.getString("name"));
                player.setEmail(resultset.getString("email"));
                player.setTeam_id(resultset.getInt("team_id"));
                player.setAuction_price(resultset.getDouble("auction_price"));
                player.setPhone_no(resultset.getString("phone_no"));
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
        return player;
    }

    @Override
    public boolean saveOrUpdatePlayer(Player player) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnectionNonSingleTon();
            String query = null;
            if (player.getId() != 0) {
                query = "update player set name = ? ,email = ?,phone_no = ? ,auction_price=? ,team_id =? ,base_price=?,isSold = ?, isCaptain=?,image=? where id =?";
            } else {
                query = "insert into player (name,email,phone_no,auction_price,team_id,base_price,isSold,isCaptain,image) values(?,?,?,?,?,?,?,?,?)";
           }
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getEmail());
            preparedStatement.setString(3, player.getPhone_no());
            preparedStatement.setDouble(4, player.getAuction_price());
            preparedStatement.setInt(5, player.getTeam_id());
            preparedStatement.setDouble(6, player.getBase_price());
            preparedStatement.setBoolean(7,player.isSold());
            preparedStatement.setBoolean(8,player.isCaptain());
            preparedStatement.setString(9,player.getImage());
            if (player.getId() != 0) {
                preparedStatement.setInt(10, player.getId());
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


    @Override
    public boolean deletePlayer(int id){
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        boolean isDeleted=false;
        try{
            connection = DBConnection.getConnectionNonSingleTon();
            preparedStatement= connection.prepareStatement("delete from player where id=? ");
            preparedStatement.setInt(1,id);

            int count = preparedStatement.executeUpdate();
            isDeleted = count >0 ? true : false ;
        }catch (DatabaseException databaseException){
            LOGGER.error("Exception while deleting Database Connection.", databaseException);
            // databaseException.printStackTrace();
            throw databaseException;
        }catch (SQLException exception){
            LOGGER.error("SQLException occured while deleting data from Database.", exception);
            throw new DatabaseException("Exception occured while deleting data from Database.");
        }catch (Exception exception){
            LOGGER.error("Exception occured while deleting data from Database.", exception);
            throw new DatabaseException("Exception occured while deleting data from Database.");
        }finally {
            DBConnection.closeConnection(connection);
        }
        return isDeleted;
    }

    @Override
    public List<Player> playerList() {
        List<Player> playerList =new ArrayList<>();
        Connection connection= null;
        PreparedStatement preparedStatement = null;
        String query = null;
        Player player=null;
        try{
            query = "select * from player";
            connection = DBConnection.getConnectionNonSingleTon();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                player = new Player();
                player.setId(resultset.getInt("id"));
                player.setName(resultset.getString("name"));
                player.setEmail(resultset.getString("email"));
                player.setTeam_id(resultset.getInt("team_id"));
                player.setAuction_price(resultset.getDouble("aution_price"));
                player.setPhone_no(resultset.getString("phone_no"));
                player.setBase_price(resultset.getDouble("base_price"));
                player.setCaptain(resultset.getBoolean("isCaptain"));
                player.setSold(resultset.getBoolean("isSold"));
                player.setImage(resultset.getString("image"));

                playerList.add(player);
            }

        } catch (DatabaseException databaseException) {
            LOGGER.error("Exception while listing data from Database Connection.", databaseException);
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
        return playerList;
    }
}

