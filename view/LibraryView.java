package view;

import controller.LibraryController;
import model.Book;
import model.User;
import model.Loan;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LibraryView {
    private LibraryController controller;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;

    public LibraryView(LibraryController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void start() {
        int option = -1;
        do {
            System.out.println("\n===== Menu da Biblioteca =====");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Pesquisar Livro");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Emprestar Livro");
            System.out.println("5. Devolver Livro");
            System.out.println("6. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada inválida! Tente novamente.");
                continue;
            }

            switch (option) {
                case 1:
                    registerBook();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    registerUser();
                    break;
                case 4:
                    lendBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    generateReports();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }

    private void registerBook() {
        try {
            System.out.print("Código: ");
            int code = Integer.parseInt(scanner.nextLine());
            System.out.print("Título: ");
            String title = scanner.nextLine();
            System.out.print("Autor: ");
            String author = scanner.nextLine();
            System.out.print("Ano de Publicação: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Categoria: ");
            String category = scanner.nextLine();
            System.out.print("Número de Exemplares: ");
            int copies = Integer.parseInt(scanner.nextLine());

            Book book = new Book(code, title, author, year, category, copies);
            controller.addBook(book);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o livro: " + e.getMessage());
        }
    }

    private void searchBook() {
        System.out.println("\nPesquisar por:");
        System.out.println("1. Código");
        System.out.println("2. Título");
        System.out.println("3. Autor");
        System.out.println("4. Categoria");
        System.out.print("Opção: ");
        try {
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    System.out.print("Digite o código: ");
                    int code = Integer.parseInt(scanner.nextLine());
                    Book book = controller.findBookByCode(code);
                    if (book != null) {
                        System.out.println(book);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o título: ");
                    String title = scanner.nextLine();
                    List<Book> booksByTitle = controller.searchBooksByTitle(title);
                    if (booksByTitle.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        booksByTitle.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Digite o autor: ");
                    String author = scanner.nextLine();
                    List<Book> booksByAuthor = controller.searchBooksByAuthor(author);
                    if (booksByAuthor.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        booksByAuthor.forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.print("Digite a categoria: ");
                    String category = scanner.nextLine();
                    List<Book> booksByCategory = controller.searchBooksByCategory(category);
                    if (booksByCategory.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        booksByCategory.forEach(System.out::println);
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro na pesquisa: " + e.getMessage());
        }
    }

    private void registerUser() {
        try {
            System.out.print("ID do Usuário: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nome: ");
            String name = scanner.nextLine();
            System.out.print("Telefone: ");
            String phone = scanner.nextLine();
            System.out.print("Endereço: ");
            String address = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            User user = new User(id, name, phone, address, email);
            controller.addUser(user);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o usuário: " + e.getMessage());
        }
    }

    private void lendBook() {
        try {
            System.out.print("Código do Livro: ");
            int bookCode = Integer.parseInt(scanner.nextLine());
            System.out.print("ID do Usuário: ");
            int userId = Integer.parseInt(scanner.nextLine());
            System.out.print("Data do Empréstimo (yyyy-MM-dd): ");
            LocalDate loanDate = LocalDate.parse(scanner.nextLine(), dateFormatter);
            System.out.print("Data prevista para Devolução (yyyy-MM-dd): ");
            LocalDate expectedReturnDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

            String result = controller.lendBook(bookCode, userId, loanDate, expectedReturnDate);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Erro ao realizar o empréstimo: " + e.getMessage());
        }
    }

    private void returnBook() {
        try {
            System.out.print("ID do Empréstimo: ");
            int loanId = Integer.parseInt(scanner.nextLine());
            System.out.print("Data de Devolução (yyyy-MM-dd): ");
            LocalDate returnDate = LocalDate.parse(scanner.nextLine(), dateFormatter);

            String result = controller.returnBook(loanId, returnDate);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Erro ao devolver o livro: " + e.getMessage());
        }
    }

    private void generateReports() {
        System.out.println("\nRelatórios:");
        System.out.println("1. Livros atualmente emprestados");
        System.out.println("2. Empréstimos com devoluções em atraso");
        System.out.println("3. Livros mais populares");
        System.out.print("Opção: ");
        try {
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    List<Loan> activeLoans = controller.getActiveLoans();
                    if (activeLoans.isEmpty()) {
                        System.out.println("Nenhum empréstimo ativo.");
                    } else {
                        activeLoans.forEach(System.out::println);
                    }
                    break;
                case 2:
                    List<Loan> lateLoans = controller.getLateLoans();
                    if (lateLoans.isEmpty()) {
                        System.out.println("Nenhum empréstimo com atraso.");
                    } else {
                        lateLoans.forEach(System.out::println);
                    }
                    break;
                case 3:
                    List<Book> popularBooks = controller.getPopularBooks();
                    if (popularBooks.isEmpty()) {
                        System.out.println("Nenhum livro cadastrado.");
                    } else {
                        popularBooks.forEach(System.out::println);
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatórios: " + e.getMessage());
        }
    }
}