package jk.registry;

import java.util.ArrayList;
import java.util.HashSet;

import jk.models.SuspendedUser;

public class SuspendedUserRegister extends Register<SuspendedUser>{
    private HashSet<SuspendedUser> register;

    public SuspendedUserRegister(){
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
        //TODO
        IO.println("TBC (returns first item)");
        return  new ArrayList<>(register);
    }

    @Override
    public void writeAll() {
        for (SuspendedUser suspendedUser : register) {
            IO.println("> ID: " +  suspendedUser.getId() + " userID: " + suspendedUser.getCustomer_id());   
        }    
    }

    public HashSet<SuspendedUser> getRegister() {
        return register;
    }
}
