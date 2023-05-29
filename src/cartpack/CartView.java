package cartpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CartView extends JFrame {
    //제목패널
    private JPanel panel;
    private JLabel label;
    //음식패널
    private JPanel foodPanel;
    private JButton addBurgerBtn;
    private JButton addCokeBtn;
    private JButton addFriesBtn;
    //카트패널
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
        label = new JLabel("Choose your food");

        foodPanel = new JPanel();
        addBurgerBtn = new JButton("Hamberger+");
        addCokeBtn = new JButton("Coke+");
        addFriesBtn = new JButton("Fries+");
        //버튼 기능
        addBurgerBtn.setActionCommand("addBurger");
        addCokeBtn.setActionCommand("addCoke");
        addFriesBtn.setActionCommand("addFries");

        cartPanel = new JPanel();
        addFoodBtn = new JButton("Confirm");
        //버튼 기능
        addFoodBtn.setActionCommand("confirm");
    }

    private void addComponents() {
        panel.add(label);

        foodPanel.add(addBurgerBtn);
        foodPanel.add(addCokeBtn);
        foodPanel.add(addFriesBtn);

        cartPanel.add(addFoodBtn);

        add(panel, BorderLayout.NORTH);
        add(foodPanel, BorderLayout.CENTER);
        add(cartPanel, BorderLayout.SOUTH);
    }

    public void setCartListener(ActionListener listener) {
        addBurgerBtn.addActionListener(listener);
        addCokeBtn.addActionListener(listener);
        addFriesBtn.addActionListener(listener);
        addFoodBtn.addActionListener(listener);
    }
}
