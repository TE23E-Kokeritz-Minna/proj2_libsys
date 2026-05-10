package jk.system;

import java.lang.reflect.Type;
import java.util.HashSet;

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

    // FIXME get server data upon start?

    private static LiteratureRegister litReg;
    private static LoanRegister loanReg;
    private static SuspendedUserRegister susReg;
    private static UserRegister userReg;
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        litReg = new LiteratureRegister();
        loanReg = new LoanRegister();
        susReg = new SuspendedUserRegister();
        userReg = new UserRegister();
    }

    public static void menu() {

        boolean menuOn = true;

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

            // NOTE move to different methods mayhaps

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

                            litReg.add(getAllDataType(Magazine.class, "magazines"));
                            litReg.add(getAllDataType(Book.class, "books"));

                            break;
                        case 2:
                            IO.println("GET BOOK");

                            litReg.add(getAllDataType(Book.class, "books"));

                            break;
                        case 3:
                            IO.println("GET MAGAZINE");

                            litReg.add(getAllDataType(Magazine.class, "magazines"));

                            break;
                        case 4:
                            IO.println("GET SUSPENDEDUSER");

                            susReg.add(getAllDataType(SuspendedUser.class, "suspended"));

                            break;
                        case 5:
                            IO.println("GET USERS");

                            userReg.add(getAllDataType(User.class, "users"));
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
                    switch (alt) {
                        case 1:
                            IO.println("GET ONE BOOK");
                            litReg.add(getOneID(Book.class, "books"));

                            break;
                        case 2:
                            IO.println("GET ONE MAGAZINE");
                            litReg.add(getOneID(Magazine.class, "magazines"));

                            break;
                        case 3:
                            IO.println("GET ONE SUSPENDEDUSER");
                            susReg.add(getOneID(SuspendedUser.class, "suspended"));

                            break;
                        case 4:
                            IO.println("GET ONE USERS");
                            userReg.add(getOneID(User.class, "users"));

                            break;
                    }
                    break;

                case 3:
                    IO.println("ADD ITEM");
                    // TODO Dangeurous things, be careful not to double the id in some way
                    // THINK THINK THINK
                    // TBC LATER
                    // Solved maybe still be careful, dubbelcheck dubbelcheck dubbelcheck

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
                            // REVIEW - need to implement something specific for genre or except as is?
                            genre = userInputString("State the genre: ", "genre");
                            pages = userInputInt("State nr of pages: ", 1);

                            Book newBook = new Book(title, author, genre, pages);
                            IO.println("New Book:\n" + newBook.toString());
                            String ans = IO.readln("Correct (y/n): ");
                            if (!ans.equalsIgnoreCase("y"))
                                break;
                            else {
                                String jsonBody = gson.toJson(newBook);
                                String response = Client.post("books", jsonBody);
                                newBook = gson.fromJson(response, Book.class);
                                litReg.add(newBook);
                            }

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
                    IO.println("""
                            ---------- REMOVE ----------
                                1. Book
                                2. Magazine
                                3. SuspendedUser
                                4. User
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-4): ", 1, 4);

                    // TODO before writing, cause everything works the same except the Class,
                    // therfore figure out the method for this and above
                    switch (alt) {
                        case 1:
                            IO.println("REMOVE BOOK");
                            // HOW TO DO THIS
                            // ASk for the ID;
                            // show the Relevent Book, ask for confirmation
                            // Try removing it
                            // give Feedback based on the Error Message thatCLIENT sends back
                            // if it doesnt exist good for you
                            // after removing it from server remove it from the local list
                            break;
                        case 2:
                            IO.println("REMOVE MAGAZINE");
                            break;
                        case 3:
                            IO.println("REMOVE SUSPENDEDUSER");
                            break;
                        case 4:
                            IO.println("REMOVE USER");
                            break;
                        case 5:
                            break;

                    }
                    break;

                case 5:
                    IO.println("SEARCH");

                    break;

                case 6:
                    IO.println("WRTIE OUT SORTED");

                    litReg.writeAll();
                    susReg.writeAll();
                    userReg.writeAll();

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

    private static <T> HashSet<T> getAllDataType(Class<T> clazz, String URL) {
        Type type = TypeToken.getParameterized(HashSet.class, clazz).getType();
        String body = Client.getAll(URL);
        if (body.equals("ERROR: server") || body.equals("ERROR: status"))
            throw new IllegalAccessError("Something went wrong with get");
        HashSet<T> list = gson.fromJson(body, type);
        return list;
    }

    private static <T> T getOneID(Class<T> clazz, String URL) {
        int id = userInputInt("state id: ", 0);

        String body = Client.getOne(URL, id);

        if (body.equals("ERROR: status") || body.equals("ERROR: ID")
                || body.equals("ERROR: server")) {
            IO.println("Something went wrong, couldn't find the requested book");
            throw new IllegalAccessError("Something went wrong, could'nt fins the requested item");
        } else {
            T retrived = gson.fromJson(body, clazz);
            IO.println("Retrieved item:\n" + (T) retrived.toString());
            return retrived;
        }
    }

    // HOW TO DO THIS
    // ASk for the ID;
    // show the Relevent Book, ask for confirmation
    // Try removing it
    // give Feedback based on the Error Message thatCLIENT sends back
    // if it doesnt exist good for you
    // after removing it from server remove it from the local list

    // TODO - the "search" feature will be weird with Books and Magazines
    private static <T> void removeID(Class<T> clazz, String URL) {
        int id = userInputInt("state id: ", 0);
        switch (URL) {
            case "books":

                break;

            default:
                break;
        }

    }

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

    public static LiteratureRegister getLitReg() {
        return litReg;
    }

    public static LoanRegister getLoanReg() {
        return loanReg;
    }

    public static SuspendedUserRegister getSusReg() {
        return susReg;
    }

    public static UserRegister getUserReg() {
        return userReg;
    }

}
