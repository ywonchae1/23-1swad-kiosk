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
        String command = e.getActionCommand();
        if(command.equals("addBurger"))
            System.out.println("You selected a burger");
        else if(command.equals("addCoke"))
            System.out.println("You selected a coke");
        else if(command.equals("addFries"))
            System.out.println("You selected fries");
        else if(command.equals("confirm"))
            System.out.println("You confirmed your choices");
    }
}
