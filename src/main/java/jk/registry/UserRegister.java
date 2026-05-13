package jk.registry;
/* 
author: Minna köekritz
UserRegister is the child to the abstract generic Register class.
is Primarily used by LibrarySystem and uses User class
it contains a hashset of all User objects
public methods for, adding (both list and seperate obj),removing , searching, writeing sorted,  
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jk.models.User;

public class UserRegister extends Register<User> {

    private HashSet<User> register;

    public UserRegister() {
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

    @Override
    public ArrayList<User> search(String criteria) {
        ArrayList<User> searchList = new ArrayList<>();
        register.stream().filter(s -> s.getEmail().equalsIgnoreCase(criteria)).forEach(s -> searchList.add(s));
        return searchList;
    }

    @Override
    public void writeAll() {
        List<User> sortList = register.stream().sorted().toList();
        for (User user : sortList) {
            IO.println("> name: " + user.getName()+ " email: " + user.getEmail() + " ID: " + user.getId());
        }
    }

    public HashSet<User> getRegister() {
        return register;
    }
}
