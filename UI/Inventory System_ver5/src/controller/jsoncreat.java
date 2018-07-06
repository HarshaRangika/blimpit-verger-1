/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author Tharusha
 */
public class jsoncreat {
    
    public JSONObject strJson(Map<String,String> recodes){
       
       for(String keySet :recodes.keySet()){
           System.out.println(keySet);
       }
        
        return null;
        
    }
    
}
