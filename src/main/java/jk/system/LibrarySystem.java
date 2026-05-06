package jk.system;

/* 
- Hämta alla böcker/tidningar/användare/avstängda och även en enskild
bok/tidning/användare på server

- Skapa ny bok/tidning/användare/avstängd och lägga upp på server

- Hitta kund med hjälp av email-adress

- Hitta bok/tidning med hjälp av titel

- Ta bort böcker/tidningar/kund/avstängd på server med hjälp av title och ta bort
på server.

- Ta bort kund med hjälp av email och avstängd med id på server.

- Skriva ut sorterat böcker, tidningar, kunder sorterade på (title) för böcker &amp;
tidningar och (name) för kunder. Du behöver implementera gränssnittet
Comparable för detta.

- Avgöra vilka kunder som får låna och inte låna. */

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jk.models.Book;
import jk.models.Magazine;
import jk.models.SuspendedUser;
import jk.models.User;
import jk.registry.LiteratureRegister;
import jk.registry.LoanRegister;
import jk.registry.SuspendedUserRegister;
import jk.registry.UserRegister;

public class LibrarySystem {

    //FIXME get server data upon start? 
    // ? can help in dublicate ID risk   
    // REVIEW how to solve id problems and what not
    
    // REVIEW why have a Literature list and not book and Magazine.
    private static LiteratureRegister litReg = new LiteratureRegister();
    private static LoanRegister loanReg = new LoanRegister();
    private static SuspendedUserRegister susReg = new SuspendedUserRegister();
    private static UserRegister userReg = new UserRegister();

    public static void menu() {

        boolean menuOn = true;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Menu start
        while (menuOn) {
            // int for Menu choice
            int alt = -1;
            IO.println("""
                    ----------------------------
                        1. Get all (by datatype)
                        2. Get one (by ID)
                        3. Add item (by datatype)
                        4. Remove item (by title)
                        5. Search (by email or title)
                        6. Write out sorted (by datatype)
                        7. Loan capabilities
                        8. Close Program
                    ----------------------------""");

            // User choice
            alt = userInputInt("Chose an Alternative (1-8): ", 1, 8);
            // Switch .case for every alternativ

            //NOTE move to different methods mayhaps  

            switch (alt) {
                case 1:
                    IO.println("""
                            ---------- GET ALL ---------
                                1. Literature
                                2.  Books
                                3.  Magazines
                                4. SuspendedUsers
                                5. Users
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-5): ", 1, 5);
                    // NOTE COULD BE METHODS PLS FIX FUTURE ME
                    switch (alt) {
                        case 1:
                            IO.println("GET LIT");
                            String bodyBook = Client.getAll("books");
                            String bodyMags = Client.getAll("magazines");
                            Type bookListType = new TypeToken<ArrayList<Book>>() {
                            }.getType();
                            Type magsListType = new TypeToken<ArrayList<Magazine>>() {
                            }.getType();
                            ArrayList<Book> listBook = gson.fromJson(bodyBook, bookListType);
                            ArrayList<Magazine> listMags = gson.fromJson(bodyMags, magsListType);

                            litReg.add(listMags);
                            litReg.add(listBook);

                            break;
                        case 2:
                            IO.println("GET BOOK");
                            bookListType = new TypeToken<ArrayList<Book>>() {
                            }.getType();
                            bodyBook = Client.getAll("books");
                            listBook = gson.fromJson(bodyBook, bookListType);
                            litReg.add(listBook);

                            break;
                        case 3:
                            IO.println("GET MAGAZINE");
                            magsListType = new TypeToken<ArrayList<Magazine>>() {
                            }.getType();
                            bodyMags = Client.getAll("magazines");
                            listMags = gson.fromJson(bodyMags, magsListType);
                            litReg.add(listMags);

                            break;
                        case 4:
                            IO.println("GET SUSPENDEDUSER");
                            Type susUseListType = new TypeToken<ArrayList<SuspendedUser>>() {
                            }.getType();
                            String bodySusUse = Client.getAll("suspended");
                            ArrayList<SuspendedUser> listSusUse = gson.fromJson(bodySusUse, susUseListType);
                            susReg.add(listSusUse);

                            break;
                        case 5:
                            IO.println("GET USERS");
                            Type userListType = new TypeToken<ArrayList<User>>() {
                            }.getType();
                            String bodyUser = Client.getAll("users");
                            ArrayList<User> listUser = gson.fromJson(bodyUser, userListType);
                            userReg.add(listUser);
                            break;
                    }

                    break;

                case 2:
                    IO.println("GET ONE");
                    IO.println("""
                            ---------- GET ONE ---------
                                1. Book
                                2. Magazine
                                3. SuspendedUser
                                4. User
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-4): ", 1, 4);
                    // TODO COULD BE METHODS, PLS FIX FUTURE ME
                    switch (alt) {
                        case 1:
                            IO.println("GET ONE BOOK");
                            // FIXME would prefer to get a maximum id
                            // ? wouldn't work though would it, cause technically id can start on 101 (like
                            // Users);
                            int id = userInputInt("state id: ", 0);
                            String bodyBook = Client.getOne("books", id);
                            if (bodyBook.equals("ERROR: status") || bodyBook.equals("ERROR: ID")
                                    || bodyBook.equals("ERROR: server"))
                                IO.println("Something went wrong, couldn't find the requested book");
                            else {
                                Book retrivedBook = gson.fromJson(bodyBook, Book.class);
                                IO.println("Retrived book:\n" + retrivedBook.toString());
                                IO.println("Added the retrived book to local list");
                                litReg.add(retrivedBook);
                            }
                            break;
                        case 2:
                            IO.println("GET ONE MAGAZINE");
                            id = userInputInt("state id: ", 0);
                            String bodyMags = Client.getOne("magazines", id);
                            if (bodyMags.equals("ERROR: status") || bodyMags.equals("ERROR: ID")
                                    || bodyMags.equals("ERROR: server"))
                                IO.println("Something went wrong, couldn't find the requested magazine");
                            else {
                                Magazine retrivedMag = gson.fromJson(bodyMags, Magazine.class);
                                IO.println("Retrived megazine:\n" + retrivedMag.toString());
                                IO.println("Added the retrived magazine to local list");
                                litReg.add(retrivedMag);
                            }
                            break;
                        case 3:
                            IO.println("GET ONE SUSPENDEDUSER");
                            id = userInputInt("state id: ", 0);
                            String bodySusUse = Client.getOne("suspended", id);
                            if (bodySusUse.equals("ERROR: status") || bodySusUse.equals("ERROR: ID")
                                    || bodySusUse.equals("ERROR: server"))
                                IO.println("Something went wrong, couldn't find the requested suspended");
                            else {
                                SuspendedUser retrivedSusUse = gson.fromJson(bodySusUse, SuspendedUser.class);
                                IO.println("Retrived suspended:\n" + retrivedSusUse.toString());
                                IO.println("Added the retrived suspended to local list");
                                susReg.add(retrivedSusUse);
                            }
                            break;
                        case 4:
                            IO.println("GET ONE USERS");
                            id = userInputInt("state id: ", 0);
                            String bodyUser = Client.getOne("users", id);
                            if (bodyUser.equals("ERROR: status") || bodyUser.equals("ERROR: ID")
                                    || bodyUser.equals("ERROR: server"))
                                IO.println("Something went wrong, couldn't find the requested user");
                            else {
                                User retrivedUser = gson.fromJson(bodyUser, User.class);
                                IO.println("Retrived user:\n" + retrivedUser.toString());
                                IO.println("Added the retrived user to local list");
                                userReg.add(retrivedUser);
                            }
                            break;
                    }
                    break;

                case 3:
                    IO.println("ADD ITEM");
                    IO.println("""
                            ----------- ADD ------------
                                1. Book
                                2. Magazine
                                3. SuspendedUser
                                4. User
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-4): ", 1, 4);
                    switch (alt) {
                        case 1:
                            IO.println("ADD BOOK");
                            String title = "";
                            String author = "";
                            String genre = "";
                            int pages = -1;

                            title = userInputString("State the BookTitle: ", "title");
                            author = userInputString("State the author: ", "author");
                            // ? need to implement something specific for genre or except as is
                            genre = userInputString("State the genre: ", "genre");
                            pages = userInputInt("State nr of pages: ", 1);
                            //TODO fix the ID risq for duplicates 
                                // the easiest way of doing it is imediatly upon creation get every info 
                            String id = String.valueOf(litReg.getRegisterBook().size()+ 1);
                            Book newBook = new Book(id, title, author, genre, pages, true);
                            IO.println(newBook + " has been created");
                            IO.readln();
                            litReg.add(newBook);
                            String jsonBody = gson.toJson(newBook);
                            IO.println(jsonBody);
                            IO.readln();
                            //Client.post(book, id)
                            break;
                        case 2:
                            IO.println("ADD MAGAZINE");
                            break;
                        case 3:
                            IO.println("ADD SUSPENDEDUSER");
                            break;
                        case 4:
                            IO.println("ADD USER");
                            break;
                        case 5:

                            break;

                    }
                    break;

                case 4:
                    IO.println("REMOVE ITEM");

                    break;

                case 5:
                    IO.println("SEARCH");

                    break;

                case 6:
                    IO.println("WRTIE OUT SORTED");
                    litReg.writeAll();
                    break;

                case 7:
                    IO.println("LOAN CAPABILITES");

                    break;

                case 8:
                    IO.println("CLOSE PROGRAM");
                    menuOn = false;
                    break;
            }
        }
    }

    // TODO FIX THIS SHEIT FIGURE IT OUT

    /*
     * private static void getAllDatatype(Class<?> c, String URL) {
     * Type type = new TypeToken<ArrayList<?>>(){}.getType();
     * String body = Client.getAll(URL);
     * 
     * 
     * }
     */

    /*
     * Type susUseListType=new TypeToken<ArrayList<SuspendedUser>>(){}.getType();
     * String bodySusUse = Client.getAll("suspended");
     * ArrayList<SuspendedUser> listSusUse = gson.fromJson(bodySusUse,
     * susUseListType);susReg.add(listSusUse);
     */

    // TODO FIX THIS SAME PROBLEM AS ABOVE
    /*
     * private static void getOneId(String URL, String parameter) {
     * // TODO would prefer to get a maximum id
     * // ? wouldn't work though would it, cause technically id can start on 101
     * (like
     * // Users);
     * int id = userInputInt("state id: ", 0);
     * String bodyBook = Client.getOne(URL, id);
     * if (bodyBook.equals("ERROR: status") || bodyBook.equals("ERROR: ID")
     * || bodyBook.equals("ERROR: server"))
     * IO.println("Something went wrong, couldn't find the requested " + parameter);
     * else {
     * 
     * }
     * 
     * }
     */

    private static String userInputString(String message, String parameter) {
        String ans = "";
        while (true) {
            try {
                ans = IO.readln(message).trim();
                if (ans == null || ans.isBlank())
                    throw new IllegalArgumentException(parameter + " can't be empty");
                else
                    break;
            } catch (Exception e) {
                IO.println("ERROR: " + e.getMessage());
            }
        }
        return ans;
    }

    private static int userInputInt(String message, int minimum, int maximum) {
        int ans = -1;
        while (true) {
            try {
                ans = Integer.parseInt(IO.readln(message).trim());
                if (ans < minimum || ans > maximum)
                    throw new IllegalArgumentException(
                            "invalid option, must be between " + minimum + " and " + maximum);
                else
                    break;
            } catch (NumberFormatException e) {
                IO.println("ERROR: not an integer");
            } catch (Exception e) {
                IO.println("ERROR: " + e.getMessage());
            }
        }
        return ans;
    }

    private static int userInputInt(String message, int minimum) {
        int ans = -1;
        while (true) {
            try {
                ans = Integer.parseInt(IO.readln(message).trim());
                if (ans < minimum)
                    throw new IllegalArgumentException(
                            "invalid option, can't be smaller than " + minimum);
                else
                    break;
            } catch (NumberFormatException e) {
                IO.println("ERROR: not an integer");
            } catch (Exception e) {
                IO.println("ERROR: " + e.getMessage());
            }
        }
        return ans;
    }

}
