package jk.registry;

import java.util.ArrayList;

import jk.models.Literature;

public class LiteratureRegister extends Register<Literature>{

    public static ArrayList<Literature> register;

    static{
        register = new ArrayList<>();
    }
    @Override
    public void add(Literature item) {
        IO.println("HIIIIIIIIII");

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void remove(Literature item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public void writeAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeAll'");
    }

    @Override
    public Literature search(String criteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

   

    

}
