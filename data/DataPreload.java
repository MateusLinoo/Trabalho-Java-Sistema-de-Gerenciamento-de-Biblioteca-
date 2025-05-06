package data;

import controller.LibraryController;
import model.Book;
import model.User;
import java.time.LocalDate;

public class DataPreload {
    public static void preloadData(LibraryController controller) {
        // Cadastrar alguns livros
        controller.addBook(new Book(1001, "Java Como Programar", "Deitel", 2007, "Programming", 5));
        controller.addBook(new Book(1002, "Effective Java", "Joshua Bloch", 2018, "Programming", 3));
        controller.addBook(new Book(1003, "Clean Code", "Robert C. Martin", 2008, "Programming", 4));

        // Cadastrar alguns usuários
        controller.addUser(new model.User(1, "Alice", "123456789", "Rua A, 123", "alice@example.com"));
        controller.addUser(new model.User(2, "Bob", "987654321", "Rua B, 456", "bob@example.com"));

        // Pré-cadastrar um empréstimo de demonstração
        // Emprestando o livro de código 1001 para o usuário 1
        controller.lendBook(1001, 1, LocalDate.now().minusDays(5), LocalDate.now().minusDays(2));
        // Realiza a devolução para simular atraso (se a data de devolução for superior à prevista)
        controller.returnBook(1, LocalDate.now());
    }
}