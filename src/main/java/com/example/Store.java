package com.example;

/**
 * @author lakhan
 * A resource created for each query. 
 */

public class Store {
    String query;
    String uri;
      
    Store(String query, String URI){
        this.query = query;
        this.uri = URI;
    }
    public String getQuery(){
        return this.query;
    }
    public String getUri(){
        return this.uri;
    }
}
