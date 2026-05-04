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

        // Variable
        String baseURL = "http://10.151.168.5:3123/";
        Gson gson = new Gson();

        boolean menuOn = true;

        // dynamic local ArrayList for Book and Magazie
        ArrayList<Book> allBooks = new ArrayList<>();
        ArrayList<Magazine> allMagazines = new ArrayList<>();

        // Menu start
        while (menuOn) {
            // int for Menu choice
            int alt = -1;
            IO.println("""
                    ----------------------------
                        1. Get all books
                        2. Get all Magazines
                        3. Write out Literature
                        4. Add Book
                        5. Add magazine
                        6. Close Program
                    ----------------------------""");

            // User choice
            while (true) {
                try {
                    alt = Integer.parseInt(IO.readln("Chose an Alternative (1-6): ").trim());
                    // if invalid opption
                    if (alt < 1 || alt > 6)
                        throw new IllegalArgumentException();
                    break;
                } catch (NumberFormatException e) {
                    // ERROR message for parse
                    IO.println("ERROR: not an integer");
                } catch (IllegalArgumentException e) {
                    // ERROR message for invalid choice
                    IO.println("ERROR: not valid option");
                } catch (Exception e) {
                    // other ERRORs that can ocur
                    IO.println("ERROR: " + e.getMessage());
                }
            }
            // Switch .case for every alternativ
            switch (alt) {
                case 1:
                    IO.println("GET ALL BOOKS");

                    // Type to parse all Book json data
                    Type bookListType = new TypeToken<ArrayList<Book>>() {
                    }.getType();
                    // Gather the json data from other method
                    String body = getAll(baseURL + "books");
                    // Save it to a list
                    allBooks = gson.fromJson(body, bookListType);
                    break;

                case 2:
                    IO.println("GET ALL MAGAZINES");

                    // Type to parse all Magazine json data
                    Type magsListType = new TypeToken<ArrayList<Magazine>>() {
                    }.getType();
                    // Gather the json data from other method
                    body = getAll(baseURL + "magazines");
                    // save it to a list
                    allMagazines = gson.fromJson(body, magsListType);
                    break;

                case 3:
                    IO.println("WRITE OUT LIT");

                    String ans = "";
                    // User choice between showcases Books or Magazine 
                    while (true) {
                        try {
                            ans = IO.readln("Magazines or Books (M/B): ").toUpperCase().trim();
                            if (!ans.equals("M") && !ans.equals("B"))
                                throw new IllegalArgumentException();
                            break;
                        } catch (IllegalArgumentException e) {
                            IO.println("ERROR: not valid choice");
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    switch (ans) {
                        case "M":
                            // Write out Magazine with title, year and issue nr
                            for (Magazine magazine : allMagazines) {
                                IO.println("> " + magazine.title + " (" + magazine.getPublishedYear() + ":"
                                        + magazine.getIssueNumber() + ")");
                            }
                            break;
                        case "B":
                            // Write out Books with title author 
                            for (Book book : allBooks) {
                                IO.println("> " + book.title + " by: " + book.getAuthor());
                            }
                            break;
                    }
                    break;

                case 4:
                    IO.println("ADD BOOK");

                    // Variables for book parameters
                    String title = "";
                    String author = "";
                    String genre = "";
                    int pages = -1;

                    // User writes Booktitle 
                    while (true) {
                        try {
                            title = IO.readln("Write the bookTitle: ");
                            if (title == null || title.isBlank())
                                throw new IllegalArgumentException("title is empty");
                            else
                                break;
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // User writes author
                    while (true) {
                        try {
                            author = IO.readln("Write the author: ");
                            if (author == null || author.isBlank())
                                throw new IllegalArgumentException("author is empty");
                            else
                                break;
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // User writes Genre 
                    while (true) {
                        try {
                            genre = IO.readln("Write the genre (Big first letter): ").trim();
                            if (genre == null || genre.isBlank())
                                throw new IllegalArgumentException("author is empty");

                            else if (genre.equals("Crime") || genre.equals("Drama") || genre.equals("Mystery")
                                    || genre.equals("Adventure") || genre.equals("Romance") || genre.equals("Fantasy")
                                    || genre.equals("Thriller") || genre.equals("Science Fiction"))
                                break;
                            else
                                throw new IllegalArgumentException("not valid genre");
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // User writes Pages 
                    while (true) {
                        try {
                            pages = Integer.parseInt(IO.readln("Write nr of Pages: ").trim());
                            if (pages < 0)
                                throw new IllegalArgumentException("not valid page number");
                            else
                                break;
                        } catch (NumberFormatException e) {
                            IO.println("ERROR: not an integer");
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // Get the next id
                    String id = String.valueOf(allBooks.size());
                    
                    // Create and add the new book to the list                    
                    Book newBook = new Book(id, title, author, genre, pages, true);
                    allBooks.add(newBook);
                    IO.println(newBook + " is added to the local list ");
                    break;

                case 5:
                    IO.println("ADD MAGAZINE");

                    // Variables for book parameters
                    title = "";
                    int issueNumber = -1;
                    String category = "";
                    int publishedYear = -1;

                    //User writes magazine title
                    while (true) {
                        try {
                            title = IO.readln("Write the magTitle: ");
                            if (title == null || title.isBlank())
                                throw new IllegalArgumentException("title is empty");
                            else
                                break;
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // User writes issuenumber 
                    while (true) {
                        try {
                            issueNumber = Integer.parseInt(IO.readln("Write the issueNumber: ").trim());
                            if (issueNumber < 0)
                                throw new IllegalArgumentException("invalid issueNumber");
                            else
                                break;
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // User writes category 
                    while (true) {
                        try {
                            category = IO.readln("Write the category: ").trim();
                            if (category == null || category.isBlank())
                                throw new IllegalArgumentException("category is empty");
                            else
                                break;
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // User writes publishing year  
                    while (true) {
                        try {
                            publishedYear = Integer.parseInt(IO.readln("Write publishing year: ").trim());
                            // TODO: change to localyear
                            if (2026 - publishedYear < 0)
                                throw new IllegalArgumentException("not valid year");
                            else
                                break;
                        } catch (NumberFormatException e) {
                            IO.println("ERROR: not an integer");
                        } catch (Exception e) {
                            IO.println("ERROR: " + e.getMessage());
                        }
                    }
                    // Get next id 
                    id = String.valueOf(allMagazines.size());

                    //Creat and add new magazine to list
                    Magazine newMag = new Magazine(id, title, issueNumber, category, publishedYear, true);
                    allMagazines.add(newMag);
                    IO.println(newMag + " is added to the local list ");
                    break;

                case 6:
                    IO.println("CLOSE PROGRAM");
                    menuOn = false;
                    break;
            }

        }

    }

    // Method for Getting all json data from an URL
    private static String getAll(String URL) {
        
        HttpResponse<String> response;
        // Get the data 
        try {
            response = Unirest.get(URL).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }
        // Status 
        int status = response.getStatus();
        // send error message if status is not okay 
        if (status != 200) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        // return json data 
        String body = response.getBody();
        return body;
    }
}