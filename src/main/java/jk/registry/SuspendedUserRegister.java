package jk.registry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import jk.models.SuspendedUser;

public class SuspendedUserRegister extends Register<SuspendedUser> {
    private HashSet<SuspendedUser> register;

    public SuspendedUserRegister() {
        register = new HashSet<>();
    }

    @Override
    public void add(SuspendedUser item) {
        register.add(item);
    }

    @Override
    public void add(HashSet<? extends SuspendedUser> list) {
        for (SuspendedUser suspendedUser : list) {
            this.add(suspendedUser);
        }
    }

    @Override
    public void remove(SuspendedUser item) {
        register.remove(item);
    }

    @Override
    public ArrayList<SuspendedUser> search(String criteria) {
        ArrayList<SuspendedUser> searchList = new ArrayList<>(
                register.stream().filter(s -> s.getUserId().equals(criteria)).collect(Collectors.toList()));

        return searchList;
    }

    @Override
    public void writeAll() {
        List<SuspendedUser> sortList = register.stream().sorted().toList();
        for (SuspendedUser suspendedUser : sortList) {
            IO.println("> ID: " + suspendedUser.getId() + " userID: " + suspendedUser.getUserId());
        }
    }

    public HashSet<SuspendedUser> getRegister() {
        return register;
    }
}
