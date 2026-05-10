package jk.registry;

import java.util.ArrayList;
import java.util.HashSet;

import jk.models.User;

public class UserRegister extends Register<User>{

    private HashSet<User> register;

    public UserRegister(){
        register = new HashSet<>();
    }

    @Override
    public void add(User item) {
        register.add(item);        
    }

    
    @Override
    public void add(HashSet<? extends User> list) {
        for (User user : list) {
            this.add(user);
        }
    }

    @Override
    public void remove(User item) {
        register.remove(item);        
    }

    //TODO
    @Override
    public ArrayList<User> search(String criteria) {
        IO.println("TBC (returns first item)");
        return  new ArrayList<>(register);
    }

    @Override
    public void writeAll() {
        for (User user : register) {
            IO.println("> name: " + user.getName() + " ID: " + user.getId());
        }
        
    }

    public HashSet<User> getRegister() {
        return register;
    }
}
