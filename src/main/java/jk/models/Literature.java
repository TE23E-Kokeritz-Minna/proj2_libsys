package jk.models;
/* 
author: Minna Kökeritz 
Literature class parent to Magazine and Book 
    contains constructor and getters and setter for title, id and isAvailable
*/

public abstract class Literature {

    // Variables
    protected String id;
    protected String title;
    protected boolean isAvailable;

    // Constructors
    public Literature(String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    public Literature() {
    }


    // GETTERS and SETTERS
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // TO STRING
    @Override
    public String toString() {
        return "ID: " + id + " Title: " + title + " Available? " + isAvailable;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
        // final int prime = 31;
        // int result = 1;
        // result = prime * result + ((id == null) ? 0 : id.hashCode()); 
        // return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Literature other = (Literature) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
