package jk.system;
/* 
author: Minna Kökeritz
The Class LibrarySystem contains, all overreaching logic and is used by Main
it is used by calling the Menu method, which contains all alternative that can be chosen, such as adding, removing and searching for items and more.

    it contains the methods for getting (via Client) and converting json data from the server, 
    it contains method for creating a new item, both to add it to the correct register and the server
    it contains the methods for deleting an object both from server and correct register
    it also contains som UserInput methods that take in msg and some sort of criteria or what is asked, and will continue til a valid ans are given

*/

import java.lang.reflect.Type;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jk.models.Book;
import jk.models.Literature;
import jk.models.Magazine;
import jk.models.SuspendedUser;
import jk.models.User;
import jk.registry.LiteratureRegister;
import jk.registry.LoanRegister;
import jk.registry.Register;
import jk.registry.SuspendedUserRegister;
import jk.registry.UserRegister;

public class LibrarySystem {

    // ————————————————————————— //
    // ------- VARIABLES ------- //
    // ————————————————————————— //

    private static LiteratureRegister litReg;
    private static LoanRegister loanReg;
    private static SuspendedUserRegister susReg;
    private static UserRegister userReg;

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // ————————————————————————— //
    // ------ STATIC BLOCK ----- //
    // ————————————————————————— //

    static {
        litReg = new LiteratureRegister();
        loanReg = new LoanRegister();
        susReg = new SuspendedUserRegister();
        userReg = new UserRegister();
    }

    // ————————————————————————— //
    // --------- MENU ---------- //
    // ————————————————————————— //

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
                    switch (alt) {
                        case 1:
                            IO.println("GET LIT");
                            try {
                                litReg.add(getAllDataType(Magazine.class, "magazines"));
                                litReg.add(getAllDataType(Book.class, "books"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }

                            break;
                        case 2:
                            IO.println("GET BOOK");
                            try {
                                litReg.add(getAllDataType(Book.class, "books"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }

                            break;
                        case 3:
                            IO.println("GET MAGAZINE");

                            try {
                                litReg.add(getAllDataType(Magazine.class, "magazines"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }

                            break;
                        case 4:
                            IO.println("GET SUSPENDEDUSER");
                            try {
                                susReg.add(getAllDataType(SuspendedUser.class, "suspended"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }

                            break;
                        case 5:
                            IO.println("GET USERS");
                            try {
                                userReg.add(getAllDataType(User.class, "users"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }
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
                            try {
                                litReg.add(getOneID(Book.class, "books"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }

                            break;
                        case 2:
                            IO.println("GET ONE MAGAZINE");
                            try {
                                litReg.add(getOneID(Magazine.class, "magazines"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }

                            break;
                        case 3:
                            IO.println("GET ONE SUSPENDEDUSER");
                            try {
                                susReg.add(getOneID(SuspendedUser.class, "suspended"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
                            }

                            break;
                        case 4:
                            IO.println("GET ONE USERS");

                            try {
                                userReg.add(getOneID(User.class, "users"));
                            } catch (Exception e) {
                                IO.println("ERROR: " + e.getMessage());
                                return;
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

                            String title;
                            String author;
                            String genre;
                            int pages;

                            title = userInputString("State the BookTitle: ", "title");
                            author = userInputString("State the author: ", "author");
                            genre = userInputString("State the genre: ", "genre");
                            pages = userInputInt("State nr of pages: ", 1);

                            Book newBook = new Book(title, author, genre, pages);
                            createNewItem(newBook, "books", litReg);

                            break;

                        case 2:
                            IO.println("ADD MAGAZINE");

                            title = "";
                            int issueNumber = -1;
                            String category = "";
                            int publishedYear = -1;

                            title = userInputString("State the MagTitle: ", title);
                            issueNumber = userInputInt("State the issue Nr: ", 1);
                            category = userInputString("State the category: ", "category");
                            int currentYear = Year.now().getValue();
                            publishedYear = userInputInt("State the publishing year: ", 1663, currentYear);

                            Magazine newMag = new Magazine(title, issueNumber, category, publishedYear);
                            createNewItem(newMag, "magazines", litReg);
                            break;

                        case 3:
                            IO.println("ADD SUSPENDEDUSER");
                            String userId;
                            String reason;

                            // FIXME - yankee to put in user id, could not be refering to anyone.
                            userId = userInputString("State the UserID to suspend: ", "userID");
                            reason = userInputString("State the reason: ", " reason");
                            SuspendedUser newSusUser = new SuspendedUser(userId, reason);
                            createNewItem(newSusUser, "suspended", susReg);
                            break;
                        case 4:
                            IO.println("ADD USER");
                            String name;
                            String email;

                            name = userInputString("State the name: ", " name");
                            email = userInputString("State the email: ", "email");
                            User newUser = new User(name, email);
                            createNewItem(newUser, "users", userReg);
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

                    switch (alt) {
                        case 1:
                            IO.println("REMOVE BOOK");
                            removeTitle(Book.class, "books");
                            break;
                        case 2:
                            IO.println("REMOVE MAGAZINE");
                            removeTitle(Magazine.class, "magazines");
                            break;
                        case 3:
                            IO.println("REMOVE SUSPENDEDUSER");
                            removeUserId("suspended");
                            break;
                        case 4:
                            IO.println("REMOVE USER");
                            removeEmail("users");
                            break;
                    }
                    break;

                case 5:
                    IO.println("SEARCH");
                    IO.println("""
                            ---------- SEARCH ----------
                                1. Title (Books and Magazines)
                                2. Email (Users)
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-2): ", 1, 2);
                    switch (alt) {
                        case 1:
                            IO.println("SEARCH TITLE");
                            ArrayList<Literature> matchingTitle = searchTitle();
                            for (Literature literature : matchingTitle) {
                                IO.println("> " + literature);
                            }
                            break;

                        case 2:
                            IO.println("SEARCH EMAIL");
                            ArrayList<User> matchingEmail = searchEmail();
                            for (User user : matchingEmail) {
                                IO.println("> " + user);
                            }
                            break;
                    }
                    break;

                case 6:
                    IO.println("WRTIE OUT SORTED");
                    IO.println("""
                            ----------- WRITE ----------
                                1. All
                                2. Book
                                3. Magazine
                                4. SuspendedUser
                                5. User
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-5): ", 1, 5);
                    switch (alt) {
                        case 1:
                            IO.println("WRITE ALL");
                            litReg.writeAll();
                            susReg.writeAll();
                            userReg.writeAll();
                            break;
                        case 2:
                            IO.println("WRITE BOOK");
                            litReg.writeAll(Book.class);
                            break;
                        case 3:
                            IO.println("WRITE MAGAZINE");
                            litReg.writeAll(Magazine.class);
                            break;
                        case 4:
                            IO.println("WRITE SUSPENDEDUSER");
                            susReg.writeAll();
                            break;
                        case 5:
                            IO.println("WRITE USER");

                            userReg.writeAll();
                            break;
                    }
                    break;

                case 7:
                    IO.println("LOAN CAPABILITES");
                    Boolean canBorrow = canBorrow();
                    IO.println("Can borrow? " + canBorrow);
                    break;

                case 8:
                    IO.println("CLOSE PROGRAM");
                    menuOn = false;
                    break;
            }
        }
    }

    // ————————————————————————— //
    // ------ CLIENT TALK ------ //
    // ————————————————————————— //

    private static <T> HashSet<T> getAllDataType(Class<T> clazz, String URL) {

        // create a TYPE, its a HashSet<> of clazz
        Type type = TypeToken.getParameterized(HashSet.class, clazz).getType();
        String body = Client.getAll(URL);

        // if soemthing went wrong Throw exception
        if (body.equals("ERROR: server") || body.equals("ERROR: status"))
            throw new IllegalAccessError("Something went wrong with get");

        // return the objects
        HashSet<T> list = gson.fromJson(body, type);
        return list;
    }

    private static <T> T getOneID(Class<T> clazz, String URL) {

        // ask for id
        String id = userInputString("state id: ", "id");
        String body = Client.getOne(URL, id);

        // if Errror code is sent Throw new Exception
        if (body.equals("ERROR: status") || body.equals("ERROR: ID")
                || body.equals("ERROR: server")) {
            IO.println("Something went wrong, couldn't find the requested book");
            throw new IllegalAccessError("Something went wrong, could'nt fins the requested item");
        } else {
            // create the object
            T retrived = gson.fromJson(body, clazz);
            IO.println("Retrieved item:\n" + (T) retrived.toString());
            return retrived;
        }
    }

    private static <T> void createNewItem(Object obj, String URL, Register reg) {

        // present the object
        IO.println("New " + obj.getClass().getSimpleName() + " :\n" + obj.toString());
        String ans = IO.readln("Correct (y/n): ");
        // check that it looks good
        if (!ans.equalsIgnoreCase("y"))
            return;
        else {
            // convert to json
            String jsonBody = gson.toJson(obj);

            // post it on server, add the returnd object to list
            String response = Client.post(URL, jsonBody);
            obj = gson.fromJson(response, obj.getClass());
            reg.add(obj);
        }
    }

    private static <T> void removeTitle(Class<T> clazz, String URL) {

        Literature removedObj;
        String ans = "";
        ArrayList<Literature> allMatching = searchTitle();

        for (Literature literature : allMatching) {
            IO.println("> " + literature.toString());
        }
        if (allMatching.size() < 2 && !allMatching.isEmpty()) {
            ans = userInputString("Correct ? (y/n): ", "y", "n", "answer");
            if (ans.equalsIgnoreCase("y"))
                removedObj = allMatching.getFirst();
            else {
                IO.println("returning to menu");
                return;
            }
        } else if (allMatching.isEmpty()) {
            IO.println("no " + clazz.getSimpleName() + " matched search, returning to menu");
            return;
        } else {
            ans = String.valueOf(userInputInt("Which one? (row): ", 1, allMatching.size()));
            removedObj = allMatching.get(Integer.parseInt(ans) + 1);
            IO.println("atempting to remove, " + removedObj);
        }

        String id = removedObj.getId();

        // Delete from server and list if not error.
        String response = Client.delete(URL, id);
        if (!response.equals("ERROR: server") && !response.equals("ERROR: status")) {
            litReg.remove(removedObj);
        }
    }

    private static <T> void removeEmail(String URL) {

        User removedUser;
        String ans = "";
        ArrayList<User> allMatching = searchEmail();

        for (User user : allMatching) {
            IO.println("> " + user.toString());
        }
        if (allMatching.size() < 2 && !allMatching.isEmpty()) {
            ans = userInputString("Correct ? (y/n): ", "y", "n", "answer");
            if (ans.equalsIgnoreCase("y"))
                removedUser = allMatching.getFirst();
            else {
                IO.println("returning to menu");
                return;
            }
        } else if (allMatching.isEmpty()) {
            IO.println("no User matches that email");
            return;
        } else {
            ans = String.valueOf(userInputInt("Which one? (row): ", 1, allMatching.size()));
            removedUser = allMatching.get(Integer.parseInt(ans) + 1);
            IO.println("Attemting to remove, " + removedUser);
        }

        String id = removedUser.getId();

        // delete from server and list if nothing went wrong
        String response = Client.delete(URL, id);
        if (!response.equals("ERROR: server") && !response.equals("ERROR: status")) {
            userReg.remove(removedUser);
        }

    }

    private static <T> void removeUserId(String URL) {

        String userId = userInputString("State the userId: ", "userId");
        SuspendedUser removedSuspendedUser;
        String ans = "";
        ArrayList<SuspendedUser> allMatching = susReg.search(userId);

        for (SuspendedUser suspendedUser : allMatching) {
            IO.println("> " + suspendedUser.toString());
        }
        if (allMatching.size() < 2 && !allMatching.isEmpty()) {
            ans = userInputString("Correct ? (y/n): ", "y", "n", "answer");
            if (ans.equalsIgnoreCase("y"))
                removedSuspendedUser = allMatching.getFirst();
            else {
                IO.println("returning to menu");
                return;
            }
        } else if (allMatching.isEmpty()) {
            IO.println("no suspension on user " + userId);
            return;
        } else {
            ans = String.valueOf(userInputInt("Which one? (row): ", 1, allMatching.size()));
            removedSuspendedUser = allMatching.get(Integer.parseInt(ans) + 1);
            IO.println("Attempting to remove, " + removedSuspendedUser);
        }

        String id = removedSuspendedUser.getId();

        // deleting Suspendded from server and from list of succesfull
        String response = Client.delete(URL, id);
        if (!response.equals("ERROR: server") && !response.equals("ERROR: status")) {
            susReg.remove(removedSuspendedUser);
        }

    }

    private static ArrayList<Literature> searchTitle() {
        // ask for Title
        String title = userInputString("State the title: ", "title");

        // search in the litreg and return the matching
        ArrayList<Literature> allMatching = litReg.search(title);
        return allMatching;
    }

    private static ArrayList<User> searchEmail() {
        // ask for email
        String email = userInputString("State the Email: ", "email");

        // search in userReg and retun matching
        ArrayList<User> allMatching = userReg.search(email);
        return allMatching;
    }

    private static Boolean canBorrow() {

        ArrayList<User> all = searchEmail();
        User searchedUser;

        if (all.isEmpty()) {
            IO.println("no user with that Email");
            return false;
        } else if (all.size() > 1) {
            IO.println("More than one user with that email, returns false");
            String ans = String.valueOf(userInputInt("Which one? (row): ", 1, all.size()));
            searchedUser = all.get(Integer.parseInt(ans) + 1);
            IO.println("aksing about, " + searchedUser);
        }
        searchedUser = all.getFirst();
        // get the userID
        String userID = searchedUser.getId();
        susReg.add(getAllDataType(SuspendedUser.class, "suspended"));

        // if no matching between userId on suspended and id and the searcg for ID than
        // returns true
        return susReg.getRegister().stream().filter(u -> u.getUserId().equals(userID)).toList().isEmpty();

    }

    // method for userInputString with no criteria on content
    private static String userInputString(String message, String parameter) {

        String ans = "";
        // Runns til valid ans
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

    private static String userInputString(String message, String alt1, String alt2, String parameter) {
        String ans = "";
        while (true) {
            try {
                ans = IO.readln(message).trim();
                if (ans == null || ans.isBlank())
                    throw new IllegalArgumentException(parameter + " can't be empty");
                if (!ans.equalsIgnoreCase(alt1) && !ans.equalsIgnoreCase(alt2))
                    throw new IllegalArgumentException("Needs to be either " + alt1 + " or " + alt2);
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
