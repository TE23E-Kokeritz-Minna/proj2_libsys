package jk.registry;

import java.util.HashSet;

import jk.models.Literature;
import jk.models.Magazine;
import jk.models.Book;

public class LiteratureRegister extends Register<Literature> {

    // NOTE NO REAL REASON WHY REGISTER EXIST
    //FIXME
    private HashSet<Literature> register;
    private HashSet<Book> registerBook;
    private HashSet<Magazine> registerMagazine;

    public LiteratureRegister() {
        register = new HashSet<>();
        registerBook = new HashSet<>();
        registerMagazine = new HashSet<>();
    }

    @Override
    public void add(Literature item) {
        register.add(item);
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
                IO.println("> " + magazine.getTitle() + " (" + magazine.getPublishedYear() + ":"
                        + magazine.getIssueNumber() + ") ID: " + magazine.getId());
            }
        }
    }

    //TODO
    @Override
    public Literature search(String criteria) {
        IO.println("TBC (returns first entry)");
        return register.stream().findFirst().get();
    }

    public HashSet<Book> getRegisterBook() {
        return registerBook;
    }

    public HashSet<Magazine> getRegisterMagazine() {
        return registerMagazine;
    }

}
