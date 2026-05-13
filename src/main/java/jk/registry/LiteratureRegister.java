package jk.registry;
/* 
author: Minna köekritz
LiteratureRegister is the child to the abstract generic Register class.
is Primarily used by LibrarySystem and uses Literature class
it contains a hashset of all Literature objects, seperate for Books and Magazines
public methods for, adding (both list and seperate obj),removing , searching, writeing sorted,  
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jk.models.Literature;
import jk.models.Magazine;
import jk.models.Book;

public class LiteratureRegister extends Register<Literature> {

    private HashSet<Book> registerBook;
    private HashSet<Magazine> registerMagazine;

    public LiteratureRegister() {
        registerBook = new HashSet<>();
        registerMagazine = new HashSet<>();
    }

    @Override
    public void add(Literature item) {
        if (item instanceof Book)
            registerBook.add((Book) item);
        if (item instanceof Magazine)
            registerMagazine.add((Magazine) item);
    }

    @Override
    public void add(HashSet<? extends Literature> list) {
        for (Literature literature : list) {
            this.add(literature);
        }
    }

    @Override
    public void remove(Literature item) {
        if (item instanceof Book)
            registerBook.remove(item);
        else if (item instanceof Magazine)
            registerMagazine.remove(item);
    }

    @Override
    public void writeAll() {
        List<Book> sortBook = registerBook.stream().sorted().toList();
        List<Magazine> sortMags = registerMagazine.stream().sorted().toList();
        for (Book book : sortBook) {
            IO.println("> " + book.getTitle() + " by: " + book.getAuthor() + " ID: " + book.getId());
        }
        for (Magazine magazine : sortMags) {
            IO.println("> " + magazine.getTitle() + " (" + magazine.getPublishedYear() + ":"
                    + magazine.getIssueNumber() + ") ID: " + magazine.getId());
        }
    }

    public void writeAll(Class<? extends Literature> clazz) {
        switch (clazz.getSimpleName()) {
            case "Book":
                List<Book> sortBook = registerBook.stream().sorted().toList();
                for (Book book : sortBook) {
                    IO.println("> " + book.getTitle() + " by: " + book.getAuthor() + " ID: " + book.getId());
                }
                break;

            case "Magazine":
                List<Magazine> sortMags = registerMagazine.stream().sorted().toList();
                for (Magazine magazine : sortMags) {
                    IO.println("> " + magazine.getTitle() + " (" + magazine.getPublishedYear() + ":"
                            + magazine.getIssueNumber() + ") ID: " + magazine.getId());
                }
                break;
        }
    }

    @Override
    public ArrayList<Literature> search(String criteria) {
        ArrayList<Literature> searchList = new ArrayList<>();
        registerBook.stream().filter(b -> b.getTitle().equals(criteria)).forEach(b -> searchList.add(b));
        registerMagazine.stream().filter(b -> b.getTitle().equals(criteria)).forEach(b -> searchList.add(b));
        return searchList;
    }

    public HashSet<Book> getRegisterBook() {
        return registerBook;
    }

    public HashSet<Magazine> getRegisterMagazine() {
        return registerMagazine;
    }

}
