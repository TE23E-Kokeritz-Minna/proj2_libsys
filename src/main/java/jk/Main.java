package jk;

/* 
Author: Minna Kökeritz
Contains the programm, klient and meny (all will be move later)
*/

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;

public class Main {
    public static void main() {

        String baseURL = "http://10.151.168.5:3123/";
        Gson gson = new Gson();

        boolean on = true;

        ArrayList<Book> allBooks = new ArrayList<>();
        ArrayList<Magazine> allMagazines = new ArrayList<>();

        while (on) {
            int alt = -1;
            IO.println("""
                    ----------------------------
                        1. Get all books
                        2. Get all Magazine
                        3. Write out Literature
                        4. Add Book
                        5. Add magazine
                        6. Close Program
                    ----------------------------""");
            while (true) {

                try {
                    alt = Integer.parseInt(IO.readln("Chose an Alternative (1-6): ").trim());
                    if (alt < 1 || alt > 6)
                        throw new IllegalArgumentException();
                    break;
                } catch (NumberFormatException e) {
                    IO.println("ERROR: not an integer");
                } catch (IllegalArgumentException e) {
                    IO.println("ERROR: not valid option");
                } catch (Exception e) {
                    IO.println("ERROR" + e.getMessage());
                }
     
            }
            switch (alt) {
                case 1:
                    IO.println("GET ALL BOOKS");
                    Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();
                    String body = getAll(baseURL + "books");
                    allBooks = gson.fromJson(body,bookListType);
              /*       for (Book book : allBooks) {
                                        // WHY DONT getTitle() WORK???????????????
                        IO.println("> " + book.title + " by: " + book.getAuthor());
                    }
 */
                    break;
                case 2:
                    IO.println("GET ALL MAGAZINES");
                    Type magsListType = new TypeToken<ArrayList<Magazine>>(){}.getType();
                    body = getAll(baseURL + "magazines");
                    allMagazines = gson.fromJson(body, magsListType);
                    break;
                case 3:
                    IO.println("WRITE OUT LIT");
                    break;
                case 4:
                    IO.println("ADD BOOK");
                    break;
                case 5:
                    IO.println("ADD MAGAZINE");
                    break;
                case 6:
                    IO.println("CLOSE PROGRAMM");
                    on = false;
                    break;
            }

        }

    }

    private static String getAll(String URL){
        HttpResponse<String> response; 
        try {
            response = Unirest.get(URL).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }
        int status = response.getStatus();
        if(status != 200){
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        String body = response.getBody();
        return body;
    }  



}