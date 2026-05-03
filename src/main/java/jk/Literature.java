package jk;
/* 
author: Minna Kökeritz 
Literature class parent to Magazine and Book 
    contains constructor and getters and setter for title, id and isAvailable
*/


public class Literature {

    //Variables
    protected String id;
    protected String title;
    protected boolean isAvailable;

    // Constructors
    public Literature(String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    //GETTERS and SETTERS 
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    //TO STRING 
    @Override
    public String toString() {
        return "ID: " + id + " Title: " +title + " Available? " + isAvailable;  
    }
}
