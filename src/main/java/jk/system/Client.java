package jk.system;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class Client {

    // NOTE switch server based on location
    // REAL / SCHOOL 
    private static final String baseURL = "http://10.151.168.5:3123/";

    // TEMP / HOME
    // private static final String baseURL = "http://localhost:3000/";

    // REVIEW is the ERROR codes right maybe something else should be returnd 
    // check the criticims on catch

    // getall
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
        // Sends back Error message if not working
        // need some sort of error handeling when called
        if (status != 200) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        // return json data
        return response.getBody();
    }

    // getone
    public static String getOne(String URL, int ID) {
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

        // ? Does this work ?
        // Sends back Error message if not working
        // need some sort of error handeling when called
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
    // post

    public static String post(String URL, String jsonBody) {

        HttpResponse<String> response;

        try {
            response = Unirest.post(baseURL + URL).header("Content-Type", "application/json").body(jsonBody).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: Server";
        }
        int status = response.getStatus();

        if (status != 200 && status != 201) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        return response.getBody();
    }

    // put
    public static String put(String URL, int ID, String jsonBody) {
        HttpResponse<String> response;
        try {
            response = Unirest.put(baseURL + URL + "/" + ID).header("Content-Type", "application/json").body(jsonBody)
                    .asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }

        int status = response.getStatus();

        if (status != 200 && status != 204) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        return response.getBody();
    }

    // delete
    public static String put(String URL, int ID) {
        int status;

        try {
            status = Unirest.delete(baseURL + URL + "/" + ID).asEmpty().getStatus();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }

        if (status != 200 && status != 204) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }
        return "OK";

    }
}
