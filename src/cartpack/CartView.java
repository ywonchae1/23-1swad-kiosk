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
    //햄버거
    private JLabel burgerLabel;
    private JLabel burgerCntLabel;
    private JButton subBurgerBtn;
    //콜라
    private JLabel cokeLabel;
    private JLabel cokeCntLabel;
    private JButton subCokeBtn;
    //프라이
    private JLabel friesLabel;
    private JLabel friesCntLabel;
    private JButton subFriesBtn;
    //확정
    private JButton confirmFoodBtn;

    public CartView() {
        initWindow();
        initComponents();
        addComponents();
    }

    private void initWindow() {
        setSize(500, 200);
        setTitle("Test button");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        panel = new JPanel();
        label = new JLabel("Choose your food");

        //음식들
        foodPanel = new JPanel();
        addBurgerBtn = new JButton("Hamberger+");
        addCokeBtn = new JButton("Coke+");
        addFriesBtn = new JButton("Fries+");
        //버튼 기능
        addBurgerBtn.setActionCommand("addBurger");
        addCokeBtn.setActionCommand("addCoke");
        addFriesBtn.setActionCommand("addFries");

        //장바구니
        cartPanel = new JPanel();
        burgerLabel = new JLabel("Burger:");
        burgerCntLabel = new JLabel("0");
        subBurgerBtn = new JButton("-");
        cokeLabel = new JLabel("Coke:");
        cokeCntLabel = new JLabel("0");
        subCokeBtn = new JButton("-");
        friesLabel = new JLabel("Fries:");
        friesCntLabel = new JLabel("0");
        subFriesBtn = new JButton("-");

        confirmFoodBtn = new JButton("Confirm");
        //버튼 기능
        subBurgerBtn.setActionCommand("subBurger");
        subCokeBtn.setActionCommand("subCoke");
        subFriesBtn.setActionCommand("subFries");
        confirmFoodBtn.setActionCommand("confirm");
    }

    private void addComponents() {
        panel.add(label);

        foodPanel.add(addBurgerBtn);
        foodPanel.add(addCokeBtn);
        foodPanel.add(addFriesBtn);

        cartPanel.add(burgerLabel);
        cartPanel.add(burgerCntLabel);
        cartPanel.add(subBurgerBtn);
        cartPanel.add(cokeLabel);
        cartPanel.add(cokeCntLabel);
        cartPanel.add(subCokeBtn);
        cartPanel.add(friesLabel);
        cartPanel.add(friesCntLabel);
        cartPanel.add(subFriesBtn);
        cartPanel.add(confirmFoodBtn);

        add(panel, BorderLayout.NORTH);
        add(foodPanel, BorderLayout.CENTER);
        add(cartPanel, BorderLayout.SOUTH);
    }

    public void setCartListener(ActionListener listener) {
        addBurgerBtn.addActionListener(listener);
        addCokeBtn.addActionListener(listener);
        addFriesBtn.addActionListener(listener);
        subBurgerBtn.addActionListener(listener);
        subCokeBtn.addActionListener(listener);
        subFriesBtn.addActionListener(listener);
        confirmFoodBtn.addActionListener(listener);
    }

    public int getBurgerCntLabel() {
        return Integer.parseInt(burgerCntLabel.getText());
    }

    public void setBurgerCntLabel(int cnt) {
        this.burgerCntLabel.setText(String.valueOf(cnt));
    }

    public int getCokeCntLabel() {
        return Integer.parseInt(cokeCntLabel.getText());
    }

    public void setCokeCntLabel(int cnt) {
        this.cokeCntLabel.setText(String.valueOf(cnt));
    }

    public int getFriesCntLabel() {
        return Integer.parseInt(friesCntLabel.getText());
    }

    public void setFriesCntLabel(int cnt) {
        this.friesCntLabel.setText(String.valueOf(cnt));
    }
}
