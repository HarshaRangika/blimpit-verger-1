package org.blimpit.external.verger.inventory.service;


import org.blimpit.external.verger.inventory.controller.DocumentCtrl;
import org.blimpit.external.verger.inventory.model.FileModel;
import org.blimpit.external.verger.inventory.model.FilePathModel;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;

@Path("fileHandler")
public class FileUploadService {

    private DocumentCtrl documentCtrl = new DocumentCtrl();

    /********************************* File Content handler ************************************/

    /**
     * @param destination         Destination where does the file need to be saved
     * @param enabled             true
     * @param uploadedInputStream Required file in the form of input
     * @param fileDetail          File details like name
     * @return URL example: localhost:8080/api/fileHandler/fileUploader?destination = <Folder_Name>
     */

    @POST
    @Path("/fileUploader")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFiles(@QueryParam("destination") String destination,
                                @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
                                @FormDataParam("file") InputStream uploadedInputStream,
                                @FormDataParam("file") FormDataContentDisposition fileDetail) {

        String destinationLocation = destination + "/" + fileDetail.getFileName().substring(0, fileDetail.getFileName().indexOf("."));
        return documentCtrl.SaveFile(uploadedInputStream, fileDetail, destinationLocation, destination); //Response.status(200).entity(output).build();
    }

    /**
     * @param section  Department
     * @param fileName Name of the file
     * @return URL example: localhost:8080/api/fileHandler/getFile?section=<Section_Folder_Name>&name=<Name Of the File>
     */

    @GET
    @Path("/getFile")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@QueryParam("section") String section,
                                 @QueryParam("name") String fileName) {

        System.out.println("Section : " + section);
        System.out.println("Name : " + fileName);

//        return Response.ok("Section : "+Section, MediaType.APPLICATION_JSON).build();
//                .header("Content-Disposition",  file.getName())
//                .build();
        return documentCtrl.getFileFromServer(section, "\"" + fileName + "\"");
    }

    /**
     * @param filePathModel
     * @return URL example: localhost:8080/api/fileHandler/moveFile
     */

    @POST
    @Path("/moveFile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response moveFile(FilePathModel filePathModel) {
        return documentCtrl.moveFile(filePathModel);
    }

    /****************************************************** Handles the Document Data Base ************************************************************************/

    @GET
    @Path("/getDepartmentFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FileModel> getFilesBySection(@QueryParam("section") String section) {

        return documentCtrl.getFilesBySection(section);
    }

    @POST
    @Path("/createDbEntry")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setFiletodb(FileModel fileModel) {

        return documentCtrl.setFiletodb(fileModel);

    }

}
