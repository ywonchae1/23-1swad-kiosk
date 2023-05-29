package cartpack;

import java.awt.event.*;

public class CartController implements ActionListener {
    private CartModel model;
    private CartView view;

    public CartController(CartModel argModel, CartView argView) {
        this.model = argModel;
        this.view = argView;
        this.view.setCartListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("addBurger")) {
            view.setBurgerCntLabel(view.getBurgerCntLabel() + 1);
            System.out.println("You selected a burger");
        }
        else if(command.equals("addCoke")) {
            view.setCokeCntLabel(view.getCokeCntLabel() + 1);
            System.out.println("You selected a coke");
        }
        else if(command.equals("addFries")) {
            view.setFriesCntLabel(view.getFriesCntLabel() + 1);
            System.out.println("You selected fries");
        }
        else if(command.equals("subBurger")) {
            int cnt = view.getBurgerCntLabel();
            if(cnt > 0)
                view.setBurgerCntLabel(view.getBurgerCntLabel() - 1);
            System.out.println("You removed a burger");
        }
        else if(command.equals("subCoke")) {
            int cnt = view.getCokeCntLabel();
            if(cnt > 0)
                view.setCokeCntLabel(view.getCokeCntLabel() - 1);
            System.out.println("You removed a coke");
        }
        else if(command.equals("subFries")) {
            int cnt = view.getFriesCntLabel();
            if(cnt > 0)
                view.setFriesCntLabel(view.getFriesCntLabel() - 1);
            System.out.println("You removed fries");
        }
        else if(command.equals("confirm"))
            System.out.println("You confirmed your choices");
    }
}
