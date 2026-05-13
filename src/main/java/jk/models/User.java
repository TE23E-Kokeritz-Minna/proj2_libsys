package jk.models;
/* 
author: Minna Kökeritz 
User class and implements Comparable (to be able to sort in a list)
    a User includes id, name and email 
    the class is used by LibrarySystem and UserRegister
*/
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jk.system.LibrarySystem;

public class User implements Comparable {

    // ————————————————————————— //
    // ------- VARIABLES ------- //
    // ————————————————————————— //

    private String id;
    private String name;
    private String email;
    // ————————————————————————— //
    // ------ CONSTRUCTOR ------ //
    // ————————————————————————— //

    public User(String name, String email) {
        this.id = "";
        this.name = name;
        this.email = email;
    }

    public User() {
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ————————————————————————— //
    // -------- OVERRIDE ------- //
    // ————————————————————————— //
    @Override
    public String toString() {
        return "User [id: " + id + " name: " + name + " email: " + email + "]";
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int compareTo(Object o) {
        User other = (User) o;
        return this.getName().compareTo(other.getName());
    }

}
