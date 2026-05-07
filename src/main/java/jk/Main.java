package jk;
/* 
Author: Minna Kökeritz
Contains the programm, klient and meny (all will be move later)
*/



// FIXME id will be a problem nearly everywhere
// REVIEW Ideas to fix that
// Get all data - save to repective list (make set to avoid duplicates)
// Method that returns next valid ID
    // for loop test current i if equal to ID (start at 1), if not return that else continue?
    // That might ectually be the plan,
// ?how to do that: 
    // Start by switching the register to HashSet 
    // implement HashCode in the Models (ID.toint)
    // static block in libSys to get all and all that
    // before continue with the add method (Risky don't want duplicate ID)
        // make the validID method
        // use that 

public class Main {
    public static void main() {

        // NOTE - Recognise as different object
            // same id different customer_id
            // different id same customer_id 
            // different id different customer_id
        // Conclusion: as long as something is different than its a different objects in the eyes of equal 
        // Precausion: could cause problems in future, if does fix equals in models, 
        // info: the test codes is stored in "cleaningHouseTEMP.txt"

        



       
       //  LibrarySystem.menu();
    }
}