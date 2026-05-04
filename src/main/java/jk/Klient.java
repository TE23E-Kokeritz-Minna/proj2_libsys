package jk;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class Klient {

    // base url FINAL
    private static final String baseURL = "http://10.151.168.5:3123/";


    //getall
    public static String getAll(String URL) {
        HttpResponse<String> response;
        // Get the data 
        try {
            response = Unirest.get(baseURL + URL).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }
        // Status 
        int status = response.getStatus();
        
        // ? Does this work ?
        //Sends back Error message if not working
            // need some sort of error handeling when called   
        if (status != 200) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        // return json data 
        String body = response.getBody();
        return body;
    }

    //getone 
    public static String getOne(String URL, int ID) {
        HttpResponse<String> response;
        // Get the data 
        try {
            response = Unirest.get(baseURL + URL + "/" +ID).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }
        // Status 
        int status = response.getStatus();
        
        // ? Does this work ?
        //Sends back Error message if not working
            // need some sort of error handeling when called   
        if (status != 200) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        // return json data 
        String body = response.getBody();
        return body;
    }
    //post 
    
    //put 

    //delete
}
