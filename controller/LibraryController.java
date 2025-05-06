package controller;

import model.Book;
import model.User;
import model.Loan;
import java.util.*;
import java.time.LocalDate;

public class LibraryController {
    private List<Book> books;
    private List<User> users;
    private List<Loan> loans;
    private int loanIdCounter;

    public LibraryController() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        loans = new ArrayList<>();
        loanIdCounter = 1;
    }

    // Operações com livros
    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBookByCode(int code) {
        for (Book book : books) {
            if (book.getCode() == code)
                return book;
        }
        return null;
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> found = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase()))
                found.add(book);
        }
        return found;
    }

    public List<Book> searchBooksByAuthor(String author) {
        List<Book> found = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                found.add(book);
        }
        return found;
    }

    public List<Book> searchBooksByCategory(String category) {
        List<Book> found = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category))
                found.add(book);
        }
        return found;
    }

    // Operações com usuários
    public void addUser(User user) {
        users.add(user);
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }

    // Empréstimos
    public String lendBook(int bookCode, int userId, LocalDate loanDate, LocalDate expectedReturnDate) {
        Book book = findBookByCode(bookCode);
        User user = findUserById(userId);
        if (book == null)
            return "Livro não encontrado!";
        if (user == null)
            return "Usuário não encontrado!";
        if (book.getAvailableCopies() <= 0)
            return "Nenhuma cópia disponível para empréstimo!";
        if (!user.getActiveLoans().isEmpty())
            return "O usuário já possui um empréstimo ativo!";

        Loan loan = new Loan(loanIdCounter++, book, user, loanDate, expectedReturnDate);
        loans.add(loan);
        user.addLoan(loan);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        book.incrementTimesLoaned();
        return "Empréstimo realizado com sucesso: " + loan;
    }

    // Devolução
    public String returnBook(int loanId, LocalDate returnDate) {
        for (Loan loan : loans) {
            if (loan.getLoanId() == loanId) {
                if (loan.getReturnDate() != null)
                    return "Empréstimo já foi devolvido!";
                loan.setReturnDate(returnDate);
                Book book = loan.getBook();
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                loan.getUser().removeLoan(loan);
                return "Livro devolvido com sucesso! Dias de atraso: " + loan.getDelayDays();
            }
        }
        return "Empréstimo não encontrado!";
    }

    // Relatórios
    public List<Loan> getActiveLoans() {
        List<Loan> active = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getReturnDate() == null)
                active.add(loan);
        }
        return active;
    }

    public List<Loan> getLateLoans() {
        List<Loan> late = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getReturnDate() != null && loan.getDelayDays() > 0)
                late.add(loan);
        }
        // Ordena por dias de atraso em ordem decrescente
        late.sort((l1, l2) -> Long.compare(l2.getDelayDays(), l1.getDelayDays()));
        return late;
    }

    public List<Book> getPopularBooks() {
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort((b1, b2) -> Integer.compare(b2.getTimesLoaned(), b1.getTimesLoaned()));
        return sortedBooks;
    }

    // Métodos para acesso às listas, se necessário
    public List<Book> getAllBooks() {
        return books;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public List<Loan> getAllLoans() {
        return loans;
    }
}