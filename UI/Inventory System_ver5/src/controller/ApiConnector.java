
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;


public class ApiConnector {

    public String post(String URL, JSONObject jsonmsg) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URL);
        StringEntity postingString = null;
        HttpResponse response;
        StringBuilder res = null;

        try {
            postingString = new StringEntity(jsonmsg.toString());
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            res = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {

                res.append(line);
                res.append('\r');

            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    public String get(String URL) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URL);
        HttpResponse response;
        StringBuilder res = null;

        try {
            response = httpClient.execute(get);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            res = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                res.append(line);
                res.append('\r');
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res.toString();
    }

    public String delete(String URL) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete delete = new HttpDelete(URL);
        HttpResponse response;
        StringBuilder sb = null;
        try {
            response = httpClient.execute(delete);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
                sb.append('\r');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
