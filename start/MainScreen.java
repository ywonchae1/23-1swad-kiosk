package start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private List<Food> foodList; //음식 목록
    private Cart cart;

    JFrame frame;
    private JPanel pNorth, foodContainer, pSouth, pCenter;
    private JTable cartTable; //장바구니 테이블
    private DefaultTableModel cartTableModel;
    private JButton cardBtn, pointBtn, couponBtn, paymentBtn, cancelBtn;
    private JLabel title, totalPriceLabel;

    public MainScreen() {
        foodList = new ArrayList<>();
        cart = new Cart();

        //초기 음식 데이터 초기화
        String[] foodNames = {"치즈버거", "새우버거", "베이컨버거", "머쉬룸버거", "더블버거", "불고기버거", "대리버거", "빅맥", "콜라"};
        int[] prices = {4000, 6000, 5000, 5000, 6000, 4000, 3000, 6000, 1000};
        int[] stocks = {10, 20, 1, 15, 5, 15, 30, 8, 50};

        //음식 목록 초기화
        for (int i = 0; i < foodNames.length; i++) {
            Food food = new Food(foodNames[i], prices[i], stocks[i]);
            foodList.add(food);
        }

        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("햄버거 주문 키오스크");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(400, 800);

        //북쪽 ========== 키오스크 타이틀 / 음식 목록 ==========
        pNorth = new JPanel();
        pNorth.setLayout(new BorderLayout());

        title = new JLabel("교수님 힘드시조 키오스크");
        title.setFont(new Font("Malgun Gothic", Font.BOLD, 25));
        title.setOpaque(true);
        title.setBackground(new Color(247, 90, 80));
        title.setForeground(Color.white);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        foodContainer = new JPanel(); //음식 목록이 들어갈 패널
        foodContainer.setLayout(new GridLayout(3, 3, 10, 10)); //3행 3열, 간격 10씩 55fd

        for (Food food : foodList) {
            JPanel foodPanel = new JPanel();
            foodPanel.setPreferredSize(new Dimension(100, 100));
            foodPanel.setLayout(new BorderLayout());
            foodPanel.setBackground(Color.WHITE);
            foodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //각 패널에 테두리 추가

            JLabel nameLabel = new JLabel(food.getFoodName()); //음식 이름
            nameLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            foodPanel.add(nameLabel, BorderLayout.NORTH);

            JLabel priceLabel = new JLabel(Integer.toString(food.getPrice()) + "원");
            priceLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 15));
            priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            foodPanel.add(priceLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2, 5, 5)); // 1행 2열 사이 간격 5씩
            JButton plusButton = new JButton("+"); //+ 버튼
            JButton minusButton = new JButton("-"); //- 버튼

            //+ 버튼 클릭 시 해당 음식 장바구니에 추가
            plusButton.addActionListener(e -> {
                addToCart(food, 1);
                updateCartTable();
                displayCart();
            });

            //- 버튼 클릭 시 해당 음식 장바구니에서 제거
            minusButton.addActionListener(e -> {
                removeFromCart(food, 1);
                updateCartTable();
                displayCart();
            });

            buttonPanel.add(plusButton);
            buttonPanel.add(minusButton);

            foodPanel.add(buttonPanel, BorderLayout.SOUTH);

            foodContainer.add(foodPanel);
        }

        pNorth.add(title, BorderLayout.NORTH);
        pNorth.add(foodContainer, BorderLayout.SOUTH);

        //중앙 ========== 장바구니 ==========
        pCenter = new JPanel();
        pCenter.setBackground(Color.white);
        cartTableModel = new DefaultTableModel(new Object[]{"상품명", "가격", "수량"}, 0);
        cartTable = new JTable(cartTableModel);

        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(new Dimension(385, 600));

        pCenter.add(scrollPane, BorderLayout.CENTER);

        //남쪽 ========== 주문 / 취소 버튼 / 총 가격 ==========
        pSouth = new JPanel();
        pSouth.setBackground(new Color(128, 128, 128));
        pSouth.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); //간격 조정

        //주문 버튼
        paymentBtn = new JButton("주문");
        paymentBtn.setFont(new Font("Malgun Gothic", Font.BOLD, 20)); //폰크 설정
        paymentBtn.setPreferredSize(new Dimension(90, 45));  //크기 설정
        paymentBtn.setForeground(Color.white); //폰트색 설정
        paymentBtn.setBackground(new Color(247, 90, 80));  //배경색 설정

        //취소 버튼
        cancelBtn = new JButton("취소");
        cancelBtn.setFont(new Font("Malgun Gothic", Font.BOLD, 20)); //폰크 설정
        cancelBtn.setPreferredSize(new Dimension(90, 45));  //크기 설정
        cancelBtn.setForeground(Color.white); //폰트색 설정
        cancelBtn.setBackground(new Color(247, 90, 80)); //배경색 설정

        //총 가격 라벨
        totalPriceLabel = new JLabel("총 가격:      0원");
        totalPriceLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 19));
        totalPriceLabel.setForeground(Color.WHITE);

        pSouth.add(paymentBtn);
        pSouth.add(cancelBtn);
        pSouth.add(totalPriceLabel);

        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(pCenter, BorderLayout.CENTER);
        frame.add(pSouth, BorderLayout.SOUTH);
        frame.setVisible(true);

        //결제 관련 버튼 초기화
        cardBtn = new JButton("카드");
        pointBtn = new JButton("포인트");
        couponBtn = new JButton("쿠폰");

        //신용카드 버튼 이벤트 리스너 설정
        cardBtn.addActionListener(e -> startPayment("신용카드"));
        //포인트 버튼 이벤트 리스너 설정
        pointBtn.addActionListener(e -> startPayment("포인트"));
        //쿠폰 버튼 이벤트 리스너 설정
        couponBtn.addActionListener(e -> startPayment("쿠폰"));
        //주문 버튼 이벤트 리스너 설정
        paymentBtn.addActionListener(e -> displayPaymentOptions());
        //취소 버튼 이벤트 리스너 설정
        cancelBtn.addActionListener(e -> cancelPayment());
    }

    //결제방법(신용카드, 포인트, 쿠폰) 표시하기 위한 새로운 프레임 생성
    public void displayPaymentOptions() {
        JFrame paymentFrame = new JFrame("결제 방식 선택");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(cardBtn); //panel에 신용카드 버튼 추가
        panel.add(pointBtn); //panel에 포인트 버튼 추가
        panel.add(couponBtn); //panel에 쿠폰 버튼 추가

        paymentFrame.add(panel);
        paymentFrame.setSize(300, 100); //프레임 크기
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setVisible(true); //화면에 표시
    }

    //취소 버튼 이벤트 핸들러
    private void cancelPayment() {
        cart.getFoodList().clear(); //장바구니 비우기
        updateCartTable(); //장바구니 테이블 업데이트
        displayPrompt("결제가 취소되었습니다.");
    }

    //장바구니 테이블 업데이트
    private void updateCartTable() {
        cartTableModel.setRowCount(0); //테이블 모델 초기화

        for (Food food : cart.getFoodList()) {
            Object[] rowData = {food.getFoodName(), food.getPrice() * food.getQuantity(), food.getQuantity()};
            cartTableModel.addRow(rowData);
        }
        totalPriceLabel.setText("총 가격: " + cart.calcTotalPrice() + "원");
    }

    //음식 목록 표시
    public void displayFood() {
        System.out.println("=============== 메뉴 ===============");
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            System.out.println((i + 1) + ". " + food.getFoodName() + ", 금액: " + food.getPrice() + ", 재고: " + food.getStock() + "\n");
        }
        System.out.println("====================================");
    }

    //장바구니 표시
    public void displayCart() {
        System.out.println("============== 장바구니 ==============");
        for (Food food : cart.getFoodList()) {
            System.out.println(food.getFoodName() + ", 금액: " + food.getPrice() * food.getQuantity() + ", 수량: " + food.getQuantity());
        }
        System.out.println("총 금액: " + cart.calcTotalPrice());
        System.out.println("=====================================");
    }

    //프롬프트 메시지 표시
    public void displayPrompt(String message) {
        System.out.println(message);
        JOptionPane.showMessageDialog(this, message);
    }

    //장바구니에서 음식 제거
    public void removeFromCart(Food food, int quantity) {
        cart.removeFood(food, quantity);
        System.out.println("장바구니에서 " + food.getFoodName() + " " + quantity + "개 제거되었습니다. ");
        displayCart();
        updateCartTable();
    }

    //장바구니에 음식 담기
    public void addToCart(Food food, int quantity) {
        boolean existingItem = false;
        for (Food cartFood : cart.getFoodList()) {
            if (cartFood.getFoodName().equals(food.getFoodName())) {
                cartFood.setQuantity(cartFood.getQuantity() + quantity);
                existingItem = true;
                break;
            }
        }

        if (!existingItem) {
            cart.addFood(food, quantity);
        }

        System.out.println("장바구니에 " + food.getFoodName() + " " + quantity + "개 추가되었습니다.");
        displayCart();
        updateCartTable();
    }

    //결제 수행
    private void startPayment(String paymentMethod) {
        Payment payment = new Payment(paymentMethod, true);
        payment.processing(cart);
        System.exit(0);
    }

    //쿠폰 번호 입력
    public int enterCouponNumber() {
        String couponNumberString = JOptionPane.showInputDialog(null, "쿠폰 번호를 입력하세요:");
        return Integer.parseInt(couponNumberString);
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
    }
}