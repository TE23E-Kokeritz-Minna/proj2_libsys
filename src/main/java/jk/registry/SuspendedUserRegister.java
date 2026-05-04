package jk.registry;

import java.util.ArrayList;

import jk.models.SuspendedUser;

public class SuspendedUserRegister extends Register<SuspendedUser>{
    public static ArrayList<SuspendedUser> register;

    static{
        register = new ArrayList<>();
    }

    @Override
    public void add(SuspendedUser item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(SuspendedUser item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public SuspendedUser search(String criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeAll() {
        // TODO Auto-generated method stub
        
    }
    
    //TODO
}
