package jk.models;
/*
author: Minna Kökeritz
Magazine class child to Literature
    Contians constructur and getter and setters for issueNumber, category and publishedYear 
*/

import java.util.HashSet;

import jk.system.LibrarySystem;

public class Magazine extends Literature {

    // Variables
    private int issueNumber;
    private String category;
    private int publishedYear;

    // Contrsuctors
    public Magazine(String title, int issueNumber, String category, int publishedYear, boolean isAvailable) {
        this.issueNumber = issueNumber;
        this.category = category;
        this.publishedYear = publishedYear;
        String id = validID();
        super(id, title, isAvailable);
    }

    public Magazine(String id) {
        super(id);
    }

    public Magazine() {
    }

    // REVIEW is this better as a child class somehow 
    protected static String validID() {
        HashSet<Magazine> magsList = LibrarySystem.getLitReg().getRegisterMagazine();

        for (int i = 1; i < magsList.size() + 5; i++) {
            if (!magsList.contains(new Magazine(String.valueOf(i))))
                return String.valueOf(i);
        }
        return "-1";
    }

    // GETTERS and SETTERS
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

    // TO STRING
    @Override
    public String toString() {
        return super.toString() + " IssueNr: " + issueNumber + " Category: " + category + " Year: " + publishedYear;
    }
}
