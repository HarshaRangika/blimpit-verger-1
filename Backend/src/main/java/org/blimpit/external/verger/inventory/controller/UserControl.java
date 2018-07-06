package org.blimpit.external.verger.inventory.controller;

import org.blimpit.external.verger.inventory.config.ApplicationProperties;
import org.blimpit.external.verger.inventory.model.Log;
import org.blimpit.external.verger.inventory.utils.Constant;
import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;
import org.blimpit.utils.connectors.mysql.MySQLConnector;
import org.blimpit.utils.connectors.mysql.Record;
import org.blimpit.utils.usermanagement.model.Feature;
import org.blimpit.utils.usermanagement.model.ResponseStatus;
import org.blimpit.utils.usermanagement.model.User;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.ws.rs.core.Response;

public class UserControl {


    private ApplicationProperties applicationProperties = ApplicationProperties.getInstance(Constant.PATH_CONFFILE);
    private static MySQLConnector mySQLConnector;


    private String logUsername= applicationProperties.getValue("db.column.log.username");
    private String logCount=applicationProperties.getValue("db.column.log.logcount");
    private String host;
    private String port;
    private String db;
    private String dbusername;
    private String dbpassword;
    private String tablename;
    private String tablenameLog;

    private Connector connector;

    public UserControl() {

        applicationProperties = ApplicationProperties.getInstance("config.properties");
        this.host = applicationProperties.getValue("db.host");
        this.port = applicationProperties.getValue("db.port");
        this.db = applicationProperties.getValue("db.name");
        this.dbusername = applicationProperties.getValue("db.username");
        this.dbpassword = applicationProperties.getValue("db.password");
        this.tablename = applicationProperties.getValue("db.mgt.users");
        this.tablenameLog=applicationProperties.getValue("db.log.details.table");

        try{
            mySQLConnector = (MySQLConnector)MySQLConnector.getInstance(this.host,this.port,this.db,this.dbusername,this.dbpassword);
        }catch (ConnectorException var2){
            var2.printStackTrace();
        }

   }

    public Response removeUsers(String user){

            boolean delete = false;

            System.out.println("DB Table = "+this.tablename);
            System.out.println("User = "+user);
            try{
                delete = mySQLConnector.delete(this.tablename,"username",user);
            }catch (ConnectorException e){
                e.printStackTrace();
            }

            if (delete){
                return Response.ok("{\"msg\":\"Entry deleted\"}",MediaType.APPLICATION_JSON).build();
            }else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Not Deleted").build();
            }
        }

    public List<Log> getLogCount(){
        List<Log> log_count = new ArrayList<Log>();
        Record[] read;
        Log log;

        try {
            read = mySQLConnector.read(tablenameLog);
            for (int i = 0;i<read.length;i++){

                log=new Log();
                log.setUsername(read[i].getRecordAttributes().get(logUsername));
                log.setLogcount(read[i].getRecordAttributes().get(logCount));

                log_count.add(log);
            }
        }catch (ConnectorException e){
            e.printStackTrace();
        }
            return log_count;
    }

    public ResponseStatus updateLogCount(Log log) {

        ResponseStatus responseStatus = new ResponseStatus();
        Map<String, String> map = new HashMap();

        map.put("log_count", log.getLogcount());
        map.put("username", log.getUsername());

       // System.out.println("Map = "+map);
            try {
                boolean insert = mySQLConnector.update(this.tablenameLog, "username", log.getUsername(), map);
                if (insert) {
                    responseStatus.setSuccess(true);
                    responseStatus.setMessage("Successfully Updated");
                } else {
                    responseStatus.setSuccess(false);
                    responseStatus.setMessage("Cannot Update");
                }
            } catch (ConnectorException var6) {
                //responseStatus.setSuccess(true);
                responseStatus.setMessage("Cannot Update Log Count , check mysql server is up and running, " + var6.getMessage());
            }
        return responseStatus;
        }

    public ResponseStatus passwordReset(User user){
        ResponseStatus responseStatus=new ResponseStatus();
        Map<String,String> map = new HashMap();

        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        try{
            boolean update = mySQLConnector.update(this.tablename,"username",user.getUsername(),map);
            if (update){
                responseStatus.setSuccess(true);
                responseStatus.setMessage("Password Updated");
            }else {
                responseStatus.setSuccess(false);
                responseStatus.setMessage("Password Cannot Update");
            }
        }catch (ConnectorException e){
            responseStatus.setSuccess(true);
            responseStatus.setMessage("Cannot Update Password , Check mysql Server is up and Running");
        }
        return responseStatus;
    }

    public Feature[] getusersFeatures(String user) {
        ArrayList featureList = new ArrayList();

        try {

            String query="select * from features where id in (select f_id from  featureusermapper where u_id = '"+user+"');";
            Record[] records = mySQLConnector.executeQuery(query,null);
            if (records.length > 0) {
                Record[] var3 = records;
                int var4 = records.length;
                for(int var5 = 0; var5 < var4; ++var5) {
                    Record record = var3[var5];
                    Feature feature = new Feature();
                    String f_id = (String)record.getRecordAttributes().get("id");
                    String f_name = (String)record.getRecordAttributes().get("name");

                    feature.setFeatureId(f_id);
                    feature.setFeatureName(f_name);
                    featureList.add(feature);
                }
            }
        } catch (ConnectorException var10) {

        }

        return (Feature[])featureList.toArray(new Feature[featureList.size()]);
    }

    public User[] searchUsers(String us){
        ArrayList userMap = new ArrayList();

        String Accepted="Accepted";
        try{
            String query = " select * from users where username like '"+us+"%' and status='"+Accepted+"';";
            Record[] records = mySQLConnector.executeQuery(query,null);
            if (records.length>0){
                Record[] var6 = records;
                int var7 = records.length;
                for (int var8 = 0; var8<var7;++var8){
                    Record record = var6[var8];
                    Map<String, String> attributes = record.getRecordAttributes();
                    User user = new User();

                    String Name = (String)record.getRecordAttributes().get("name");
                    String UserName = (String)record.getRecordAttributes().get("username");
                    String Designation = (String)record.getRecordAttributes().get("designation");
                    String Authorization = (String)record.getRecordAttributes().get("status");

                    user.setName(Name);
                    user.setUsername(UserName);
                    user.setDesignation(Designation);
                    user.setRegistrationStatus(Authorization);

                    userMap.add(user);
                }
            }
        }catch (ConnectorException var9){

        }
        return (User[])userMap.toArray(new User[userMap.size()]);
    }
}
