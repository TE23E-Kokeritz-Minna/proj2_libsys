package jk.registry;

import java.util.ArrayList;

import jk.models.User;

public class UserRegister extends Register<User>{

    public static ArrayList<User> register;

    static{
        register = new ArrayList<>();
    }

    @Override
    public void add(User item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(User item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User search(String criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeAll() {
        // TODO Auto-generated method stub
        
    }

    
    //TODO
}
