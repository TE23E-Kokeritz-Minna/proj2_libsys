package jk.registry;

import java.util.ArrayList;

import jk.models.SuspendedUser;

public class SuspendedUserRegister extends Register<SuspendedUser>{
    public ArrayList<SuspendedUser> register;

    public SuspendedUserRegister(){
        register = new ArrayList<>();
    }

    @Override
    public void add(SuspendedUser item) {
        register.add(item);
    }

    @Override
    public void remove(SuspendedUser item) {
        register.remove(item);
        
    }

    @Override
    public SuspendedUser search(String criteria) {
        IO.println("TBC (returns first item)");
        return register.getFirst();
    }

    @Override
    public void writeAll() {
        for (SuspendedUser suspendedUser : register) {
            IO.println("> ID: " +  suspendedUser.getId() + " userID: " + suspendedUser.getCustomer_id());   
        }    
    }
}
