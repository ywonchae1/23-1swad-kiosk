package cartpack;

import javax.swing.*;
import java.awt.event.*;

public class CartView extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JPanel cartPanel;
    private JButton addFoodBtn;

    public CartView() {
        initWindow();
        initComponents();
        addComponents();
    }

    private void initWindow() {
        setSize(225, 200);
        setTitle("Test button");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        panel = new JPanel();
        label = new JLabel("Hello World");

        cartPanel = new JPanel();
        addFoodBtn = new JButton("addFood");
    }

    private void addComponents() {
        panel.add(label);

        addFoodBtn.setActionCommand("addddd");
        cartPanel.add(addFoodBtn);

        add(panel);
        add(cartPanel);
    }

    public void setCartListener(ActionListener listener) {
        addFoodBtn.addActionListener(listener);
    }
}
