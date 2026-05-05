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


    // TODO There might be errors regarding duplicate ids in literature; 
    public String getLiteratureId() {
        return literature.getId();
    }


    @Override
    public String toString() {
        return "Loan [id=" + id + ", user=" + user + ", literature=" + literature;
    }
}
