package jk;
/* 
Author: Minna Kökeritz
Contains the programm, klient and meny (all will be move later)
*/

import jk.models.SuspendedUser;
import jk.registry.SuspendedUserRegister;
import jk.system.LibrarySystem;

// FIXME id will be a problem nearly everywhere
// REVIEW Ideas to fix that
// Get all data - save to repective list (make set to avoid duplicates)
// Method that returns next valid ID
    // for loop test current i if equal to ID (start at 1), if not return that else continue?
    // That might ectually be the plan,
// ?how to do that: 
    // Start by switching the register to HashSet 
    // implement HashCode in the Models (ID.toint)
        //TODO this ain't working not HashSet or something allowed duplicates
    // static block in libSys to get all and all that
    // before continue with the add method (Risky don't want duplicate ID)
        // make the validID method
        // use that 

public class Main {
    public static void main() {

        //NOTE  THIS WORKS SO DOES STATIC WHYYYYYY ?
    /*     SuspendedUserRegister susReg =new SuspendedUserRegister(); 

        SuspendedUser test1 = new SuspendedUser("1", "20");
        SuspendedUser test2 = new SuspendedUser("2", "3");

        
        susReg.add(test1);
        susReg.add(test1);
        susReg.add(test2);

        susReg.writeAll(); */

        LibrarySystem.menu();
    }
}