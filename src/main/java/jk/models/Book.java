package jk.models;
/* 
author: Minna Kökeritz 
Book class child to Literature and implements Comparable (to be able to sort in a list)
    a Book includes author, genre and pages and inherits title, id and isAvailable from it's parent 
    the class is used by LibrarySystem and LiteratureRegister
*/

import jk.system.LibrarySystem;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Book extends Literature implements Comparable<Book>{

    // ————————————————————————— //
    // ------- VARIABLES ------- //
    // ————————————————————————— //
    private String author;
    private String genre;
    private int pages;

    // ————————————————————————— //
    // ------ CONSTRUCTOR ------ //
    // ————————————————————————— //
    public Book(String title, String author, String genre, int pages) {

        // TODO CAPITALIZE GENRE
        // Maybe even the author and title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        String id = "";

        super(id, title, true);
    }

    public Book() {
    }


    // ————————————————————————— //
    // -------- METHOD --------- //
    // ————————————————————————— //

    protected static String validID() {
        HashSet<Book> bookList = LibrarySystem.getLitReg().getRegisterBook();
        Set<String> hashId = bookList.stream().map(o -> o.getId()).collect(Collectors.toSet());

        for (int i = 1; i < hashId.size() + 2; i++) {
            if (!hashId.contains((String.valueOf(i))))
                return String.valueOf(i);
        }
        throw new IllegalStateException("No more valid ID available");
    }

    // ————————————————————————— //
    // --- GETTERS & SETTERS --- //
    // ————————————————————————— //
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

    // ————————————————————————— //
    // -------- OVERRIDE ------- //
    // ————————————————————————— //
    @Override
    public String toString() {
        return super.toString() + " author: " + author + " genre: " + genre + " pages: " + pages;
    }

    @Override
    public int compareTo(Book o) {
        Book other = o;
        return this.getTitle().compareTo(other.getTitle());
    }

}
