import cartpack.*;

public class Main {
    public static void main(String[] args) {
        CartView view = new CartView();
        CartController controller = new CartController(view);

        view.setVisible(true);
    }
}
