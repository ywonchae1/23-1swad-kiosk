package cartpack;

import java.awt.event.*;

public class CartController implements ActionListener {
    private CartModel model;
    private CartView view;

    public CartController(CartView argView) {
        this.view = argView;
        this.view.setCartListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("cart action triggered");
    }
}
