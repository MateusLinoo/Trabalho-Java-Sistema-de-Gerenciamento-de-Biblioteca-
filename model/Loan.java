package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private int loanId;
    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnDate;

    public Loan(int loanId, Book book, User user, LocalDate loanDate, LocalDate expectedReturnDate) {
        this.loanId = loanId;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = null;
    }

    public int getLoanId() {
        return loanId;
    }
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDate getLoanDate() {
        return loanDate;
    }
    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Calcula os dias de atraso
    public long getDelayDays() {
        if (returnDate != null && returnDate.isAfter(expectedReturnDate)) {
            return ChronoUnit.DAYS.between(expectedReturnDate, returnDate);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Loan{" +
               "loanId=" + loanId +
               ", book=" + book.getTitle() +
               ", user=" + user.getName() +
               ", loanDate=" + loanDate +
               ", expectedReturnDate=" + expectedReturnDate +
               ", returnDate=" + (returnDate != null ? returnDate : "Não devolvido") +
               ", delayDays=" + getDelayDays() +
               '}';
    }
}