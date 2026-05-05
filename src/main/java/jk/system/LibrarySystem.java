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
}
