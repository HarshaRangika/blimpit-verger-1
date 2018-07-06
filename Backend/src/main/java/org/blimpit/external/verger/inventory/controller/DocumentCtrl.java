package org.blimpit.external.verger.inventory.controller;

import org.blimpit.external.verger.inventory.model.FileModel;
import org.blimpit.external.verger.inventory.model.FilePathModel;
import org.blimpit.external.verger.inventory.utils.Constant;
import org.blimpit.utils.connectors.Connector;
import org.blimpit.utils.connectors.ConnectorException;
import org.blimpit.utils.connectors.Constants;
import org.blimpit.utils.connectors.mysql.MySQLConnector;
import org.blimpit.utils.connectors.mysql.Record;
import org.blimpit.utils.filehandler.BlimpItFileHandler;
import org.blimpit.utils.filehandler.FileHandler;
import org.blimpit.utils.filehandler.exceptions.FileHandlerException;
import org.blimpit.utils.usermanagement.config.ApplicationProperties;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DocumentCtrl {

    private FileHandler fileHandler;
    private Connector connector;
    private ApplicationProperties applicationProperties = ApplicationProperties.getInstance(Constant.PATH_CONFFILE);

    private String tableDocument = applicationProperties.getValue(Constant.DB_TABLE_DOCUMENT);

    private String documentId = applicationProperties.getValue(Constant.TABLE_DOCUMENTID);
    private String documentName = applicationProperties.getValue(Constant.TABLE_DOCUMENTNAME);
    private String documentPath = applicationProperties.getValue(Constant.TABLE_DOCUMENTPATH);
    private String section = applicationProperties.getValue(Constant.TABLE_SECTION);
    private String creationDate = applicationProperties.getValue(Constant.TABLE_DATE_DOC);

    private String localPath = applicationProperties.getValue(Constant.LOCAL_FILE_PATH);
    private String serverPath = applicationProperties.getValue(Constant.SERVER_FILE_PATH);


    public DocumentCtrl() {
        try {
            connector = MySQLConnector.getInstance(applicationProperties.getValue(Constants.DB_HOST),
                    applicationProperties.getValue(Constants.DB_PORT),
                    applicationProperties.getValue(Constants.DB_NAME),
                    applicationProperties.getValue(Constants.DB_USERNAME),
                    applicationProperties.getValue(Constants.DB_PASSWORD));
        } catch (ConnectorException e) {
            e.printStackTrace();
        }
        this.fileHandler = BlimpItFileHandler.getInstance();
    }

    public Response SaveFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, String destinationLocation, String saveto) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy/MM/dd");
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        String y = String.format("%1$ty/%1$tm/%1$td", now);

        System.out.println(serverPath + "/" + destinationLocation);
        String temp = serverPath + "/" + destinationLocation;

        boolean storefile = false;

        try {
            storefile = fileHandler.copyFile(uploadedInputStream, temp);
            String data[] = temp.split("/");

            FileModel fileModel = new FileModel();

            fileModel.setFilePath(temp);
            fileModel.setFileName(data[data.length - 1]);
            fileModel.setFileId(data[data.length - 1]);
            fileModel.setCreationDate(y);
            fileModel.setSection(data[data.length - 2]);
            setFiletodb(fileModel);

        } catch (FileHandlerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.toString()).build();
        }

        System.out.println("Temp Value : " + temp);

        if (storefile) {
            return Response.ok("{\"msg\":\"New Entry was Inserted\"}", MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("CheckIn was Not installed").build();
        }

    }

    public Response getFileFromServer(String section, String fileName) {

        String filePath = "";
        Record[] records;
        Map<Integer, String> map = new HashMap<>();

        try {
            records = connector.executeQuery("SELECT " + documentPath + " FROM " + tableDocument + " WHERE "
                    + this.section + "=\"" + section + "\" and " + this.documentName + "=" + fileName + ";", map);
            for (int i = 0; i < records.length; i++) {

                filePath = records[i].getRecordAttributes().get(documentPath);

                System.out.println(filePath);

            }
            System.out.println(filePath);
        } catch (ConnectorException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        File file = new File(filePath); // Find your file
        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", file.getName())
                .build();

    }

    public Response moveFile(FilePathModel filePathModel) {

        boolean filemove = false;

        String Data[] = filePathModel.getLocalFileLocation().split("/");
        int dataSize = Data.length;


        try {
            filemove = fileHandler.copyFile(filePathModel.getLocalFileLocation(), filePathModel.getDestination() + "/" + Data[dataSize - 1]);
        } catch (FileHandlerException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.toString()).build();
        }

        if (filemove) {
            return Response.ok("{\"msg\":\"File Moved Successfully\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"msg\":\"File not Moved\"}").build();
        }
    }


    /*****************************************  Controllers of the Data Base Related Activities  ****************************************************/

    public List<FileModel> getFilesBySection(String section) {

        List<FileModel> filelist = new ArrayList<>();

        try {
            Record[] read_rec = connector.read(tableDocument, this.section, section);
            int recodeSize = read_rec.length;
            for (int i = 0; i < recodeSize; i++) {

                FileModel file = new FileModel();
                file.setFileId(read_rec[i].getRecordAttributes().get(documentId));
                file.setFileName(read_rec[i].getRecordAttributes().get(documentName));
                file.setFilePath(read_rec[i].getRecordAttributes().get(documentPath));

                filelist.add(file);
            }
        } catch (ConnectorException e) {
            e.printStackTrace();
        }


        return filelist;

    }

    public Response setFiletodb(FileModel fileModel) {

        boolean insert = false;
        Map<String, String> map = new HashMap<>();

        map.put(documentId, fileModel.getFileId());
        map.put(documentName, fileModel.getFileName());
        map.put(section, fileModel.getSection());
        map.put(creationDate, fileModel.getCreationDate());
        map.put(documentPath, fileModel.getFilePath());

        try {
            insert = connector.insert(tableDocument, map);
        } catch (ConnectorException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.toString()).build();
        }

        System.out.println(map.toString());

        if (insert) {
            return Response.ok("{\"msg\":\"File Details Add to the DB Correctly\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"msg\":\"File Details are Not entered\"}").build();
        }

    }


//        switch (section) {
//            case "Factory":
//                System.out.println("Factory");
//
//                break;
//            case "Office":
//                System.out.println("Office");
//                break;
//            case "Direction":
//                System.out.println("Directions");
//                break;
//            case "Laboratory":
//                System.out.println("Laboratory");
//                break;
//            case "Marketing":
//                System.out.println("Marketing");
//                break;
//            default:
//                break;
//
//        }


//    public Response SaveFile(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, String uploadedFileLocation){
//
//        System.out.println(uploadedFileLocation);
//        // save it
//        File objFile=new File(uploadedFileLocation);
//        if(objFile.exists())
//        {
//            objFile.delete();
//
//        }
//
//        saveToFile(uploadedInputStream, uploadedFileLocation);
//
//
//        String output = "File uploaded via Jersey based RESTFul Webservice to: " + uploadedFileLocation;
//
//        return Response.status(200).entity(output).build();
//
//    }
//
//    private void saveToFile(InputStream uploadedInputStream,
//                            String uploadedFileLocation) {
//
//        try {
//            OutputStream out = null;
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//    }


}
