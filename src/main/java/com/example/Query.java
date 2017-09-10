/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;
/**
 *
 * @author lakhan
 */
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("query/{id}")
public class Query {
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response QueryHandle(@PathParam("id") String id, String query) throws URISyntaxException {
        WoTClient subs = new WoTClient();
        
        URI createdUri = new URI("response/"+id);
        
        Store newQuery =  new Store( query , "response/"+id );        
        StoreMap.putIt("response/"+id, newQuery);
        
        boolean resultX = subs.handleSubscribe(query);
        
        if(resultX){
            return Response.created(createdUri).status(Response.Status.CREATED).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
