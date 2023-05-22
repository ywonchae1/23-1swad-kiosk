import java.swing.*

public class cartController implements ActionListener {
    private cartModel model;
    private cartView view;

    public cartController(cartModel model, cartView view) {
	this.model = model;
	this.view = view;
	this.view.setCartListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.print("cart action triggered");
    }
}
