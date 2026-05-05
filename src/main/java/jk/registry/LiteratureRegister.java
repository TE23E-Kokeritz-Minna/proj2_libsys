package jk.registry;

import java.util.ArrayList;

import jk.models.Literature;
import jk.models.Magazine;
import jk.models.Book;

public class LiteratureRegister extends Register<Literature> {

    public ArrayList<Literature> register;
    public ArrayList<Book> registerBook;
    public ArrayList<Magazine> registerMagazine;

    public LiteratureRegister() {
        register = new ArrayList<>();
        registerBook = new ArrayList<>();
        registerMagazine = new ArrayList<>();
    }

    @Override
    public void add(Literature item) {
        register.add(item);
        if(item instanceof Book) registerBook.add((Book)item);
        if(item instanceof Magazine) registerMagazine.add((Magazine)item);
    }

    @Override
    public void add(ArrayList<? extends Literature> list) {
        for (Literature literature : list) {
            this.add(literature);
        } 
        
    }

    @Override
    public void remove(Literature item) {
        register.remove(item);
    }

    @Override
    public void writeAll() {
        for (Literature literature : register) {
            if (literature instanceof Book) {
                Book book = (Book) literature;
                IO.println("> " + book.getTitle() + " by: " + book.getAuthor() + " ID: " + book.getId());
            } else if (literature instanceof Magazine) {
                Magazine magazine = (Magazine) literature;
                IO.println("> " + magazine.getTitle() + " (" + magazine.getPublishedYear() + ":" + magazine.getIssueNumber() + ") ID: " + magazine.getId());
            }
        }
    }

    @Override
    public Literature search(String criteria) {
        IO.println("TBC (returns first entry)");
        return register.getFirst();    
    }

}
