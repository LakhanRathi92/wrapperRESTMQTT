package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lakhan
 * GET particular resource
 * 
 */

@Path("response/{id}")
public class response {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respond(@PathParam("id") String id) {
        //Load Response
        //URL->Query
        Store temp = StoreMap.getIt("response/"+id);
        String query = temp.query;
        String result = StoreMap.getResult(query);
        
        //forward results
        return result;
    } 
}
