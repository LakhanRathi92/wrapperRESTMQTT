/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lakhan
 * handle errors
 */

public class StoreMap {
    //In memory store.
    
    //URL{ID} -> Query
    public static ConcurrentHashMap<String, Store> store = new ConcurrentHashMap<String, Store>();
    
    //Query -> Result
    public static ConcurrentHashMap<String, String> result = new ConcurrentHashMap<String, String>();
    
    public static void putIt(String k, Store temp){
        store.put(k, temp);
    }
    
    public static Store getIt(String k){
       return store.get(k);
    }
    
    public static void putResult(String k, String val){
        result.put(k, val);
    }
    public static String getResult(String k){
        //Debug
        String temp = result.get(k);
        return temp;
    } 
}
