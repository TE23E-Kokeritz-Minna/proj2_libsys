package jk.system;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jk.models.Book;
import jk.models.Magazine;
import jk.registry.LiteratureRegister;
import jk.registry.LoanRegister;
import jk.registry.SuspendedUserRegister;
import jk.registry.UserRegister;

public class LibrarySystem {

    // ??? WHY STATIC and PRIVATE MAKE GOOD BAD OR SECRET THIRD THING

    private static LiteratureRegister litReg = new LiteratureRegister();
    private static LoanRegister loanReg = new LoanRegister();
    private static SuspendedUserRegister susReg = new SuspendedUserRegister();
    private static UserRegister userReg = new UserRegister();

    public static void menu() {
        // Variable
        String baseURL = "http://10.151.168.5:3123/";
        Gson gson = new Gson();

        boolean menuOn = true;

        // dynamic local ArrayList for Book and Magazie
        ArrayList<Book> allBooks = new ArrayList<>();
        ArrayList<Magazine> allMagazines = new ArrayList<>();

        LiteratureRegister litreg = new LiteratureRegister();

        litreg.add(null);

        /*
         * String testALL = Klient.getAll("magazines");
         * String testONE = Klient.getOne("books", 3);
         * 
         * IO.println(testALL);
         * IO.readln();
         * IO.println(testONE);
         * IO.readln();
         */

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
            alt = userInputInt("Chose an Alternative (1-6): ", 1, 6);
            // Switch .case for every alternativ
            switch (alt) {
                case 1:
                    IO.println("GET ALL BOOKS");

                    break;

                case 2:
                    IO.println("GET ALL MAGAZINES");

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
