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

    // ??? WHY STATIC and PRIVATE MAKE GOOD BAD OR SECRET THIRD THING

    // ? why have a Literature list and not book and Magazine.
    private static LiteratureRegister litReg = new LiteratureRegister();
    private static LoanRegister loanReg = new LoanRegister();
    private static SuspendedUserRegister susReg = new SuspendedUserRegister();
    private static UserRegister userReg = new UserRegister();

    public static void menu() {

        boolean menuOn = true;
        Gson gson = new Gson();

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
                                2.  Book
                                3.  Magazine
                                4. SuspendedUsers
                                5. Users
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-5): ", 1, 5);
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
                            bodyMags = Client.getAll("books");
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
                                3. SuspendedUsers
                                4. Users
                            ----------------------------""");
                    alt = userInputInt("Chose an Alternative (1-4): ", 1, 4);
                    switch (alt) {
                        case 1:
                            IO.println("GET BOOK");
                            break;
                        case 2:
                            IO.println("GET MAGAZINE");
                            break;
                        case 3:
                            IO.println("GET SUSPENDEDUSER");
                            break;
                        case 4:
                            IO.println("GET USERS");
                            break;
                    }
                    break;

                case 3:
                    IO.println("ADD ITEM");

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

    
}
