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
                    IO.println("ERROR: " + e.getMessage());
                }

            }
            switch (alt) {
                case 1:
                    IO.println("GET ALL BOOKS");
                    Type bookListType = new TypeToken<ArrayList<Book>>() {
                    }.getType();
                    String body = getAll(baseURL + "books");
                    allBooks = gson.fromJson(body, bookListType);

                    break;
                case 2:
                    IO.println("GET ALL MAGAZINES");
                    Type magsListType = new TypeToken<ArrayList<Magazine>>() {
                    }.getType();
                    body = getAll(baseURL + "magazines");
                    allMagazines = gson.fromJson(body, magsListType);
                    break;
                case 3:
                    IO.println("WRITE OUT LIT");
                    String ans = "";
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
                            for (Magazine magazine : allMagazines) {
                                IO.println("> " + magazine.title + " (" + magazine.getPublishedYear() + ":"
                                        + magazine.getIssueNumber() + ")");
                            }
                            break;
                        case "B":
                            for (Book book : allBooks) {
                                IO.println("> " + book.title + " by: " + book.getAuthor());
                            }
                            break;
                    }

                    break;
                case 4:
                    IO.println("ADD BOOK");
                    String title = "";
                    String author = "";
                    String genre = "";
                    int pages = -1;

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
                    // TODO GENRE only works with correct capitalization
                    while (true) {
                        try {
                            genre = IO.readln("Write the genre: ").trim();
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

                    String id = String.valueOf(allBooks.size());
                    Book newBook = new Book(id, title, author, genre, pages, true);
                    allBooks.add(newBook);
                    IO.println(newBook + " is added to the local list ");
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

    private static String getAll(String URL) {
        HttpResponse<String> response;
        try {
            response = Unirest.get(URL).asString();
        } catch (UnirestException e) {
            IO.println("ERROR (server): " + e.getLocalizedMessage());
            return "ERROR: server";
        }
        int status = response.getStatus();
        if (status != 200) {
            IO.println("ERROR: " + status);
            return "ERROR: status";
        }

        String body = response.getBody();
        return body;
    }

}