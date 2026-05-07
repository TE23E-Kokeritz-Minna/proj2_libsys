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
    public Book(String id, String title, String author, String genre, int pages, boolean isAvailable) {

        // TODO CAPITALIZE GENRE
        // Maybe even the author and title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;

        super(id, title, isAvailable);
    }

    public Book() {
    }
    
    
    
    
    public Book(String id) {
        super(id);
    }
    
    
    
    
    // @Override
    public static String validID(HashSet<Book> test) {
        //HashSet<Book> bookList = LibrarySystem.getLitReg().getRegisterBook();
        
        //HashSet<Book> test = new HashSet<>();
        // I have the list
        // for loop with the size + margin
         for (int i = 1; i < test.size() + 5; i++) {
            if(!test.contains(new Book(String.valueOf(i)))) return String.valueOf(i);
        } 
        // TODO Auto-generated method stub
        return "-1";
    }


    

    @Override
    public String validID() {
        // TODO Auto-generated method stub
        return null;
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
