package dao;

import dto.Config;
import exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigDaoImpl implements ConfigDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDao.class);


    @Override
    public Config GetConfigbyId(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = null;
        Config config = null;

        try {
            query = "select * from config where id=?";
            connection = DBConnection.getConnectionNonSingleTon();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                config = new Config();
                config.setBasePrice(resultset.getInt("base_price"));
                config.setAllotmentAmount(resultset.getInt("allotment_amount"));
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
        return config;
    }
}
