package jk.registry;

import java.util.ArrayList;

import jk.models.Literature;
import jk.models.Magazine;
import jk.models.Book;

public class LiteratureRegister extends Register<Literature> {

    public ArrayList<Literature> register;

    public LiteratureRegister() {
        this.register = new ArrayList<>();
    }

    @Override
    public void add(Literature item) {
        register.add(item);
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
