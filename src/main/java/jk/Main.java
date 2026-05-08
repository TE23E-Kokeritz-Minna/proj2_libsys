package jk;
/* 
Author: Minna Kökeritz
Contains the programm, klient and meny (all will be move later)
*/

import jk.system.LibrarySystem;

public class Main {
    public static void main() {

        // NOTE - Recognise as different object
        // same id different customer_id
        // different id same customer_id
        // different id different customer_id
        // Conclusion: as long as something is different than its a different objects in
        // the eyes of equal
        // Precausion: could cause problems in future, if does fix equals in models,
        // info: the test codes is stored in "cleaningHouseTEMP.txt"
        // NOTE - think it will only recognise same id as same,

        LibrarySystem.menu();
    }
}