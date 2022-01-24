/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.jsonrecipeapi2022;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author: Steven Vallarsa
 *   email: stevenvallarsa@gmail.com
 *    date: 2022-00-00
 * purpose: 
 */
public class App {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(new FileReader("data.json"));
            JSONObject jObj = (JSONObject)obj;
            JSONArray recipies = (JSONArray)jObj.get("recipes") ;
            
            Iterator<JSONObject> iterator = recipies.iterator();
            while (iterator.hasNext()) {
                JSONObject recipe = new JSONObject(iterator.next());
                System.out.println(recipe.get("name"));
                System.out.println(recipe.get("ingredients"));
                System.out.println(recipe.get("instructions"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
