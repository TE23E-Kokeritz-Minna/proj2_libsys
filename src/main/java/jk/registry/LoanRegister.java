package jk.registry;

import java.util.HashSet;

import jk.models.Loan;

public class LoanRegister extends Register<Loan>{
    private HashSet<Loan> register;

    public LoanRegister(){
        register = new HashSet<>();
    }

    @Override
    public void add(Loan item) {
        register.add(item);
    }

    @Override
    public void add(HashSet<? extends Loan> list) {
        for (Loan loan : list) {
            this.add(loan);  
        }
    }

    @Override
    public void remove(Loan item) {
       register.remove(item);
    }

    //TODO
    @Override
    public Loan search(String criteria) {
        IO.println("TBC (returns first item)");
        return  register.stream().findFirst().get();
    }

    @Override
    public void writeAll() {
        for (Loan loan : register) {
            IO.println("> UserID: " + loan.getUserId() + " LitID: " + loan.getLiteratureId() + " ID: " + loan.getId());
        }
    }
}
