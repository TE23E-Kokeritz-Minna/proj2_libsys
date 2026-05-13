package jk.models;
/* 
author: Minna Kökeritz 
Book class child to Literature and implements Comparable (to be able to sort in a list)
    a Book includes issueNumber, category and publishedYear and inherits title, id and isAvailable from it's parent 
    the class is used by LibrarySystem and LiteratureRegister
*/

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jk.system.LibrarySystem;

public class Magazine extends Literature implements Comparable {

    // ————————————————————————— //
    // ------- VARIABLES ------- //
    // ————————————————————————— //
    
    private int issueNumber;
    private String category;
    private int publishedYear;

    // ————————————————————————— //
    // ------ CONSTRUCTOR ------ //
    // ————————————————————————— //
    
    public Magazine(String title, int issueNumber, String category, int publishedYear) {
        this.issueNumber = issueNumber;
        this.category = category;
        this.publishedYear = publishedYear;
        String id = "";
        super(id, title, true);
    }

    public Magazine() {
    }

    // ————————————————————————— //
    // -------- METHOD --------- //
    // ————————————————————————— //

    protected static String validID() {
        HashSet<Magazine> magsList = LibrarySystem.getLitReg().getRegisterMagazine();
        Set<String> hashId = magsList.stream().map(o -> o.getId()).collect(Collectors.toSet());

        for (int i = 1; i < hashId.size() + 2; i++) {
            if (!hashId.contains((String.valueOf(i))))
                return String.valueOf(i);
        }
        throw new IllegalStateException("No more valid ID available");
    }

    // ————————————————————————— //
    // --- GETTERS & SETTERS --- //
    // ————————————————————————— //

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    // ————————————————————————— //
    // -------- OVERRIDE ------- //
    // ————————————————————————— //
    @Override
    public String toString() {
        return super.toString() + " IssueNr: " + issueNumber + " Category: " + category + " Year: " + publishedYear;
    }

    @Override
    public int compareTo(Object o) {
        Magazine other = (Magazine) o;
        return this.getTitle().compareTo(other.getTitle());
    }
}
