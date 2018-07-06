package org.blimpit.external.verger.inventory.controller;

import org.blimpit.external.verger.inventory.utils.Constant;
import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;
import org.blimpit.utils.connectors.Constants;
import org.blimpit.utils.connectors.mysql.MySQLConnector;
import org.blimpit.utils.filehandler.BlimpItFileHandler;
import org.blimpit.utils.usermanagement.config.ApplicationProperties;

public class Controller {

    private Connector connector;

    private ApplicationProperties applicationProperties = ApplicationProperties.getInstance(Constant.PATH_CONFFILE);

    private String tableDocument = applicationProperties.getValue(Constant.DB_TABLE_DOCUMENT);

    public Controller() {

        try {
            connector = MySQLConnector.getInstance(applicationProperties.getValue(Constants.DB_HOST),
                    applicationProperties.getValue(Constants.DB_PORT),
                    applicationProperties.getValue(Constants.DB_NAME),
                    applicationProperties.getValue(Constants.DB_USERNAME),
                    applicationProperties.getValue(Constants.DB_PASSWORD));
        } catch (ConnectorException e1) {
            e1.printStackTrace();
        }


    }
}
