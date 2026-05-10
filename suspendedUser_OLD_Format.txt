package jk.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jk.system.LibrarySystem;

public class SuspendedUser {

    private String id;
    private String customer_id;

    public SuspendedUser(String customer_id) {
        this.id = "";
        this.customer_id = customer_id;
    }

    public SuspendedUser() {
    }

    
    private static String validID() {
        HashSet<SuspendedUser> susList = LibrarySystem.getSusReg().getRegister();
        Set<String> hashId = susList.stream().map(o -> o.getId()).collect(Collectors.toSet());

        for (int i = 1; i < hashId.size() + 2; i++) {
            if (!hashId.contains((String.valueOf(i))))
                return String.valueOf(i);
        }
        throw new IllegalStateException("No more valid ID available");
    }

    public String getId() {
        return id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "SuspendedUser [id=" + id + ", customer_id=" + customer_id + "]";
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SuspendedUser other = (SuspendedUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
