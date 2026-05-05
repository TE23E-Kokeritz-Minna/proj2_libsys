package jk.registry;

import java.util.ArrayList;
import jk.models.Loan;

public class LoanRegister extends Register<Loan>{
    public ArrayList<Loan> register;

    public LoanRegister(){
        register = new ArrayList<>();
    }

    @Override
    public void add(Loan item) {
        register.add(item);
    }

    @Override
    public void add(ArrayList<? extends Loan> list) {
        for (Loan loan : list) {
            this.add(loan);  
        }
    }

    @Override
    public void remove(Loan item) {
       register.remove(item);
    }

    @Override
    public Loan search(String criteria) {
        IO.println("TBC (returns first item)");
        return register.getFirst();
    }

    @Override
    public void writeAll() {
        for (Loan loan : register) {
            IO.println("> UserID: " + loan.getUserId() + " LitID: " + loan.getLiteratureId() + " ID: " + loan.getId());
        }
    }
}
