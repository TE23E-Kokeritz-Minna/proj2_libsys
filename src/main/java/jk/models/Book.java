package jk.models;
/* 
author: Minna Kökeritz 
Book class child to Literature 
    contains constructor and getters and setter for author, genre and pages
*/

import jk.registry.LiteratureRegister;
import jk.system.LibrarySystem;
import java.util.HashSet;


public class Book extends Literature {

    // variables
    private String author;
    private String genre;
    private int pages;

    // constructor
    public Book(String title, String author, String genre, int pages, boolean isAvailable) {

        // TODO CAPITALIZE GENRE
        // Maybe even the author and title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        String id = validID();

        super(id, title, isAvailable);
    }

    public Book() {
    }    
    
    public Book(String id) {
        super(id);
    }
    
    // REVIEW is this better as a child class somehow 
    protected static String validID() {
        HashSet<Book> bookList = LibrarySystem.getLitReg().getRegisterBook();
        
        for (int i = 1; i < bookList.size() + 5; i++) {
            if(!bookList.contains(new Book(String.valueOf(i)))) return String.valueOf(i);
        } 
        return "-1";        
    }

    // GEETTERS and SETTERS
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
        return super.toString() + " author: " + author + " genre: " + genre + " pages: " + pages;
    }
}
