package org.blimpit.external.verger.inventory.utils;

public interface Constant {

    String PATH_CONFFILE = "config.properties";

    /**
     * File Store Path
     */
    String LOCAL_FILE_PATH = "save.file.path.local";
    String SERVER_FILE_PATH = "server.file.path";

    String FILE_PATH_FINANCES_ARCHIVE = "archive.filePath.finances";
    String FILE_PATH_SHIPMENTS_ARCHIVE = "archive.filePath.shipments";
    String FILE_PATH_ORDERS_ARCHIVE = "archive.filePath.orders";

    /**
     *DB TABLES
     */

    String DB_TABLE_USER = "db.verger.users";
    String DB_TABLE_FEATURES  ="db.verger.features";
    String DB_TABLE_FEATURESMAPPER = "db.verger.featuresmapper";
    String DB_TABLE_DOCUMENT = "db.document.table";


    /**
     * DB TABLES ATTRIBUTES
     */

    String USER_USERNAME = "db.column.username";
    String USER_NAME = "db.column.name";
    String USER_PASSWORD = "db.column.password";
    String USER_DESIGNATION = "db.column.designation";
    String USER_STATUS = "db.column.status";
    String USER_DEPARTMENT = "db.column.department";
    String FEATURES_ID = "db.column.fid" ;
    String FEATURES_USER_ID = "db.column.uid";

    String TABLE_DOCUMENTID = "db.column.documentId";
    String TABLE_DOCUMENTNAME = "db.column.documentName";
    String TABLE_DOCUMENTPATH = "db.column.documentPath";
    String TABLE_SECTION = "db.column.documentSection";
    String TABLE_DATE_DOC = "db.column.createDate";

}
