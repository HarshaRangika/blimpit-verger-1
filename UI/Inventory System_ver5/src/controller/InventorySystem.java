/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author Tharusha
 */
public class InventorySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        XWPFDocument document = new XWPFDocument();
//        XWPFParagraph tmpParagraph = document.createParagraph();
//        XWPFRun tmpRun = tmpParagraph.createRun();
//        tmpRun.setText("LALALALAALALAAAA");
//        tmpRun.setFontSize(18);
//        document.write(new FileOutputStream(new File("yourpathhere")));
//        document.close();

//        FileHandleingClient filehandelingClient = new FileHandleingClient();
//        filehandelingClient.getFileFromServer("http://localhost:8080/api/fileHandler/getFile?section=Factory&name=pom.xml", "/home/neeshad/Desktop/Blimpit_GIT/blimpit-verger/TestFolder/temp/");
//        Runtime runtime = Runtime.getRuntime();
//            
//        try {
//            Process process = runtime.exec("/home/neeshad/Desktop/des/facpdf101.png");
//            
//        Client client = new Client();
//        JSONObject jsonobject = new JSONObject();
//        jsonobject.put("name", "Blim");
//        jsonobject.put("username", "user");
//        jsonobject.put("password", "helloThere");
//        jsonobject.put("designation", "SE");
//        
//        client.sendData("http://webhook.site/7b45d73f-3f4d-4ce8-b664-1f53676c5b95", jsonobject);
//httppostfun();
//        } catch (IOException ex) {
//            Logger.getLogger(InventorySystem.class.getName()).log(Level.SEVERE, null, ex);
//        }

     FileHandler fileh = new FileHandler("Factory", "/home/neeshad/Desktop/Blimpit_GIT/blimpit-verger/TestFolder/temp/");
     fileh.openFile("/home/neeshad/Desktop/Blimpit_GIT/blimpit-verger/TestFolder/temp/rfc2616.txt");
     
    


    }

//    private static void httppostfun() {
//
//        String url = "http://webhook.site/7b45d73f-3f4d-4ce8-b664-1f53676c5b95";
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpPost post;/// = new HttpPost(url);
//        HttpResponse response;
//
//        //post.addHeader("User-Agent", USER_AGENT);
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//
//        urlParameters.add(new BasicNameValuePair("name", "Tharu"));
//        urlParameters.add(new BasicNameValuePair("age", "23"));
//
//        //System.out.println("URL PARAMETER DATA : " + urlParameters.toString());
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        JSONObject jsonObject = new JSONObject();
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        try {
//            jsonObject.put("method", "guru.test");
//            jsonObject.put("params", "[ \"SUCK\" ]");
//            jsonObject.put("id", 134);
//            post = new HttpPost(url);
//            post.addHeader("Content-Type", String.valueOf(APPLICATION_JSON));
//
//            StringEntity se = new StringEntity(jsonObject.toString());
//            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//            post.setEntity(se);
//
//            //post.setEntity(new UrlEncodedFormEntity(urlParameters));
//            response = client.execute(post);
//            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer result = new StringBuffer();
//            String line = " ";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            System.out.println(result);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    } 
}
