package jk.system;

/*Author: Minna Kökeritz
    the Client class is a functoanity class and talks directly to the Server
    can return the body the server back, when getting every data, and posting or updating existing data 
    can return the status when deleting 
    Returns ERROR codes 
*/

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class Client {

/* 
    baseURL is the URL for the server
        it is unchangable (due to final) and a classVariable (due to Static)
    used for the begining URL and is needed before every call to the server with the exact URL after
    there are two for the home server and School server, 
*/
    // NOTE switch server based on location
    // REAL / SCHOOL 
    private static final String baseURL = "http://10.151.168.5:3123/";
    // TEMP / HOME
    //private static final String baseURL = "http://localhost:3000/";

  

    /* 
    getAll(String) is a classmethod to get all json data on a page, by the URL
        it returns a String with this data, and various Error codes if something went wrong
    */
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

        if (status != 200) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        // return json data
        return response.getBody();
    }

    /* 
    getOne(String, String) is a class method to get one specific json object based on page and Id
        returns the string of this jsonObject and varrious error code should something go wrong. 
    */
    public static String getOne(String URL, String ID) {
        HttpResponse<String> response;
        // Get the data
        try {
            response = Unirest.get(baseURL + URL + "/" + ID).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }
        // Status
        int status = response.getStatus();

        if (status != 200) {
            if (status == 404) {
                IO.println("ERROR: invalid ID");
                return "ERROR: ID";
            } else {
                IO.println("ERROR: " + status);
                return "ERROR: status";
            }
        }

        // return json data
        return response.getBody();
    }
    
    /* 
    post(String, String) is a class method to post one jsonObject to the server, based on page
        returns the posted json String and varrious Error codes. 
    */
    public static String post(String URL, String jsonBody) {
        
        HttpResponse<String> response;
        // post the data
        try {
            response = Unirest.post(baseURL + URL).header("Content-Type", "application/json").body(jsonBody).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: Server";
        }
        //status 
        int status = response.getStatus();

        if (status != 200 && status != 201) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }
        //returns what was posted 
        return response.getBody();
    }

    /* 
    put(String, String, String) is a method to update data on the server with 
    */
    // put
    public static String put(String URL, String ID, String jsonBody) {
        HttpResponse<String> response;
        // update the data
        try {
            response = Unirest.put(baseURL + URL + "/" + ID).header("Content-Type", "application/json").body(jsonBody)
                    .asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }

        //status
        int status = response.getStatus();

        if (status != 200 && status != 204) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }
        
        //return what was updated
        return response.getBody();
    }

    // delete
    public static String delete(String URL, String ID) {
        int status;

        // try deleting
        try {
            status = Unirest.delete(baseURL + URL + "/" + ID).asEmpty().getStatus();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }
        
        // return status 
        if (status != 200 && status != 204) {
            if (status == 404) return "ERROR: not found";
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }
        return "OK";

    }
}
