package jk;

public class Book extends Literature {

    private String author;
    private String genre;
    private int pages;

    
    public Book(String id, String title, String author, String genre, int pages, boolean isAvailable) {
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

}
