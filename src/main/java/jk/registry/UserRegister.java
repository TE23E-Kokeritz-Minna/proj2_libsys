package jk.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import jk.models.SuspendedUser;
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
        ArrayList<User> searchList = new ArrayList<>(
                register.stream().filter(s -> s.getEmail().equals(criteria)).collect(Collectors.toList()));

        return searchList;
    }

    @Override
    public void writeAll() {
        List<User> sortList = register.stream().sorted().toList();
        for (User user : sortList) {
            IO.println("> name: " + user.getName() + " ID: " + user.getId());
        }

    }

    public HashSet<User> getRegister() {
        return register;
    }
}
