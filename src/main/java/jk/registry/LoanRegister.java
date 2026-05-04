package jk.registry;

import java.util.ArrayList;
import jk.models.Loan;

public class LoanRegister extends Register<Loan>{
    public static ArrayList<Register> register;

    static {
        register = new ArrayList<>();
    }

    @Override
    public void add(Loan item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(Loan item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Loan search(String criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeAll() {
        // TODO Auto-generated method stub
        
    }

    
    //TODO
}
