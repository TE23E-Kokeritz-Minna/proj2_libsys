package jk;
/*
author: Minna Kökeritz
Magazine class child to Literature
    Contians constructur and getter and setters for issueNumber, category and publishedYear 
*/


public class Magazine extends Literature {

    // Variables 
    private int issueNumber;
    private String category;
    private int publishedYear;
    
    // Contrsuctors
    public Magazine(String id, String title, int issueNumber, String category, int publishedYear, boolean isAvailable) {
        this.issueNumber = issueNumber;
        this.category = category;
        this.publishedYear = publishedYear;
        
        super(id, title, isAvailable);
    } 

    //GETTERS and SETTERS 
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

    //TO STRING
    @Override
    public String toString() {
        
        return super.toString() + " IssueNr: " + issueNumber + " Category: " + category + " Year: " + publishedYear; 
    }
}
