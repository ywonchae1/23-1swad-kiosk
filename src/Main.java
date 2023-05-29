import cartpack.*;

public class Main {
    public static void main(String[] args) {
        CartView view = new CartView();
        CartModel model = new CartModel();
        CartController controller = new CartController(model, view);

        view.setVisible(true);
    }
}
