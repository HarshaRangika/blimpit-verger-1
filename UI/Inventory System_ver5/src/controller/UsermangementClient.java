/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//////////////////////////////////////////////////////////////////////////////
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

//////////////////////////////////////////////////////////////////////////////
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.http.Header;
import org.apache.http.StatusLine;

/**
 *
 * @author Tharusha
 */
public class UsermangementClient {

    public String encrypt(String pass) throws UnsupportedEncodingException {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        String value = new String(hash, StandardCharsets.UTF_8);

        return value;
    }

    public void sendData(String apiURL, JSONObject jsonmessage) {

        JSONObject jsonObject;
        jsonObject = jsonmessage;

        //JOptionPane.showMessageDialog(null, jsonmessage.toString());
        
        String url = apiURL;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post;
        HttpResponse response;

        try {

            post = new HttpPost(url);
            post.addHeader("Content-Type", String.valueOf(APPLICATION_JSON));

            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);

            response = client.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = " ";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public JSONObject getData(String apiURL,String callPurpose) {

        JSONObject jsonObject = new JSONObject() ;
        JSONObject json = new JSONObject();
        
                
        String url = apiURL;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post;
        HttpResponse response;

        try {

            jsonObject.put("call", callPurpose);
            
            json.put("name", "Kasun");
            json.put("username", "user");
            json.put("designation", "CEO");
            json.put("log", "user");
            json.put("auth", "ALL");
            
            
            post = new HttpPost(url);
            post.addHeader("Content-Type", String.valueOf(APPLICATION_JSON));

            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);

            response = client.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = " ";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            
            //json = (JSONObject) parser.parse(result.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;

    }
    
    public int login(String apiURL, JSONObject jsonmessage) {

        JSONObject jsonObject;
        jsonObject = jsonmessage;

        int statusype=0;
        
        String url = apiURL;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post;
        HttpResponse response;

        try {

            post = new HttpPost(url);
            post.addHeader("Content-Type", String.valueOf(APPLICATION_JSON));

            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(se);

            response = client.execute(post);
            statusype=response.getStatusLine().getStatusCode();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return statusype;

    }

}
