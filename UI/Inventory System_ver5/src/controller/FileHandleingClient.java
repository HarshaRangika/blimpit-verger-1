package controller;

/**
 * This is the client which could be used in the Backend integration with the UI
 */

import org.blimpit.utils.filehandler.BlimpItFileHandler;
import org.blimpit.utils.filehandler.FileHandler;
import org.blimpit.utils.filehandler.exceptions.FileHandlerException;

public class FileHandleingClient {

   private FileHandler fileHandler = BlimpItFileHandler.getInstance();


    /**
     *
     * @param filePath  File to which is the current existence of the file
     * @param URL Service URl where the File need to be uploaded
     * @return
     */
   
    public String uploadFiles(String filePath,String URL){

        boolean fileUploadStatus = false;
        try {
            //bpfh.copyFile(fileLocation,newLocation);
            fileUploadStatus = fileHandler.uploadFileToService(filePath, URL);
            System.out.println(fileUploadStatus);
        } catch (FileHandlerException e) {
            e.printStackTrace();
        }

        if(fileUploadStatus){
            return "Status: success";
        }else{

            return "Status: Not Uploaded";

        }

    }

    /**
     *
     * @param url  Service URL which retrieves the file from the existing location
     * @param destinationPath Destination path where the file need to be downloaded
     * @return String with the status
     */

    public String getFileFromServer(String url,String destinationPath){

//        System.out.println("url : "+url);
//        System.out.println("Destination : "+destinationPath);
        
        boolean fileDownloadStatus = false;

        try {
           fileDownloadStatus = fileHandler.downloadFileFromRemoteService(url, destinationPath, BlimpItFileHandler.HTTP_SYSTEM_DEFAULT_TIMEOUT, true);
            //System.out.println(fileHandler.downloadFileFromRemoteService(url, destinationPath, BlimpItFileHandler.HTTP_SYSTEM_DEFAULT_TIMEOUT, true));
        } catch (FileHandlerException e) {
            e.printStackTrace();
        }

        //System.out.println("Boolean Out put of the system : "+fileDownloadStatus);

        if(fileDownloadStatus){
            return "Status: success";
        }else{

            return "Status: Not Uploaded";

        }


    }


}

