package jk.models;

/* 
author: Minna Kökeritz 
Book class child to Literature 
    contains constructor and getters and setter for author, genre and pages
*/

public class Book extends Literature {

    // variables
    private String author;
    private String genre;
    private int pages;

    // constructor
    public Book(String id, String title, String author, String genre, int pages, boolean isAvailable) {
        this.author = author;
        this.genre = genre;
        this.pages = pages;

        super(id, title, isAvailable);
    }

    //GEETTERS and SETTERS 
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    // TO STRING 
    @Override
    public String toString() {
        return super.toString() + " author: " + author + " genre: " + genre + " pages: "+ pages;
    }

}
