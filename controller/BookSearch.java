package controller;

import model.Book;
import model.Searchable;
import java.util.List;
import java.util.ArrayList;

public class BookSearch implements Searchable<Book> {
    private List<Book> books;

    public BookSearch(List<Book> books) {
        this.books = books;
    }

    // Procura em título, autor e categoria, de forma que o mesmo método atenda aos três critérios.
    @Override
    public List<Book> search(String keyword) {
        List<Book> found = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                found.add(book);
            }
        }
        return found;
    }
}