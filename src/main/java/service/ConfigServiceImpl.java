package service;

import dao.ConfigDao;
import dao.Response;
import dto.Config;
import exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.ResponseUtils;

import javax.inject.Inject;

public class ConfigServiceImpl implements ConfigService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);


    @Inject
    ConfigDao configDao;


//    @Override
//    public Config getConfigItemById(int i) {
//       Config item = new Config();
//       item.setId(1);
//       item.setBasePrice(10000);
//       item.setMinPlayerCount(10);
//       item.setAllotmentAmount(1000000);
//       return item;
//    }

    @Override
    public Config findConfigById(int id) {
        Response resp = null;
        Config config=null;
        try {
            config=configDao.GetConfigbyId(id);
        } catch (DatabaseException e) {
            String message = "ConfigServiceImpl:findBidById(id) Exception occured while reading data from Database.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        } catch (Exception e) {
            String message = "ConfigServiceImpl:findBidById(id) Exception occured while logging in to the application.";
            resp = ResponseUtils.createInternalServlerErrorResponse(LOGGER, e, message);
        }

        return config;

    }
}
