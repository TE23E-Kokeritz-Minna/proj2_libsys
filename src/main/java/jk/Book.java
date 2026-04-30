package jk;

public class Book extends Media {

    private String author;
    private String genre;
    private int pages;

    
    public Book(String id, String title, String author, String genre, int pages, boolean isAvailable) {
        this.author = author;
        this.genre = genre;
        this.pages = pages;

        super(id, title, isAvailable);
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

    @Override
    public String toString() {
        return super.toString() + " author: " + author + " genre: " + genre + " pages: "+ pages;
    }

}
