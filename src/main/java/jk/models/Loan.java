package jk.models;

public class Loan {
    private String id;
    private User user;
    private Literature literature;

    public Loan(String id, User user, Literature literature) {
        this.id = id;
        this.user = user;
        this.literature = literature;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Literature getLiterature() {
        return literature;
    }

    public void setLiterature(Literature literature) {
        this.literature = literature;
    }

    public String getUserId() {
        return user.getId();
    }


    // FIXME There might be errors regarding duplicate ids in literature; 
    public String getLiteratureId() {
        return literature.getId();
    }


    @Override
    public String toString() {
        return "Loan [id=" + id + ", user=" + user + ", literature=" + literature;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Loan other = (Loan) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (literature == null) {
            if (other.literature != null)
                return false;
        } else if (!literature.equals(other.literature))
            return false;
        return true;
    }

    

    
}


