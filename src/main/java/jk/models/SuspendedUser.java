package jk.models;

/* 
author: Minna Kökeritz 
SuspendedUser class and implements Comparable (to be able to sort in a list)
    a SuspendedUser includes id, userId and reason 
    the class is used by LibrarySystem and SuspendedRegister
*/
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jk.system.LibrarySystem;

public class SuspendedUser implements Comparable<SuspendedUser> {

    // ————————————————————————— //
    // ------- VARIABLES ------- //
    // ————————————————————————— //

    private String id;
    private String userId;
    private String reason;

    // ————————————————————————— //
    // ------ CONSTRUCTOR ------ //
    // ————————————————————————— //

    public SuspendedUser(String userId, String reason) {
        this.id = "";
        this.userId = userId;
        this.reason = reason;
    }

    public SuspendedUser() {
    }

    // ————————————————————————— //
    // -------- METHOD --------- //
    // ————————————————————————— //

    private static String validID() {
        HashSet<SuspendedUser> susList = LibrarySystem.getSusReg().getRegister();
        Set<String> hashId = susList.stream().map(o -> o.getId()).collect(Collectors.toSet());

        for (int i = 1; i < hashId.size() + 2; i++) {
            if (!hashId.contains((String.valueOf(i))))
                return String.valueOf(i);
        }
        throw new IllegalStateException("No more valid ID available");
    }

    // ————————————————————————— //
    // --- GETTERS & SETTERS --- //
    // ————————————————————————— //

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // ————————————————————————— //
    // -------- OVERRIDE ------- //
    // ————————————————————————— //
    @Override
    public String toString() {
        return "SuspendedUser [id=" + id + ", userId=" + userId + ", reason=" + reason + "]";
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

    @Override
    public int compareTo(SuspendedUser o) {
        SuspendedUser other = (SuspendedUser) o;
        return this.getUserId().compareTo(other.getUserId());
    }

}
