package model;

public class Book {
    private int code;
    private String title;
    private String author;
    private int publicationYear;
    private String category;
    private int totalCopies;
    private int availableCopies;
    private int timesLoaned; // Para acompanhar a popularidade

    public Book(int code, String title, String author, int publicationYear, String category, int totalCopies) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.timesLoaned = 0;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    public int getAvailableCopies() {
        return availableCopies;
    }
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    public int getTimesLoaned() {
        return timesLoaned;
    }
    public void incrementTimesLoaned() {
        this.timesLoaned++;
    }

    @Override
    public String toString() {
        return "Book{" +
               "code=" + code +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", publicationYear=" + publicationYear +
               ", category='" + category + '\'' +
               ", totalCopies=" + totalCopies +
               ", availableCopies=" + availableCopies +
               '}';
    }
}