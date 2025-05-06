import controller.LibraryController;
import data.DataPreload;
import view.LibraryView;

public class Main {
    public static void main(String[] args) {
        LibraryController controller = new LibraryController();
        // Pré-carga dos dados para facilitar os testes
        DataPreload.preloadData(controller);

        LibraryView view = new LibraryView(controller);
        view.start();
    }
}
