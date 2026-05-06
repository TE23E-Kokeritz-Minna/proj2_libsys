package jk.registry;

import java.util.ArrayList;

import jk.models.User;

public class UserRegister extends Register<User>{

    public ArrayList<User> register;

    public UserRegister(){
        register = new ArrayList<>();
    }

    @Override
    public void add(User item) {
        register.add(item);        
    }

    
    @Override
    public void add(ArrayList<? extends User> list) {
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
    public User search(String criteria) {
        IO.println("TBC (returns first item)");
        return register.getFirst();
    }

    @Override
    public void writeAll() {
        for (User user : register) {
            IO.println("> name: " + user.getName() + " ID: " + user.getId());
        }
        
    }
}
