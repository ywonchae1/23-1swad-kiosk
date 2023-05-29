package cartpack;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private List<Food> foodList; //음식 목록
    private Cart cart;

    private JButton paymentBtn; //결제 버튼
    private JButton cancelBtn; //취소 버튼
    private JButton cardBtn; //결제옵션(신용카드)
    private JButton pointBtn; //결제옵션(포인트)
    private JButton couponBtn; //결제옵션(쿠폰)
    private JTable cartTable; //장바구니를 표시하는 테이블
    private DefaultTableModel cartTableModel;
    private JLabel totalPriceLabel;

    public MainScreen() {
        foodList = new ArrayList<>();
        cart = new Cart();

        //초기 음식 데이터 설정
        String[] foodNames = {"치즈버거", "새우버거", "베이컨버거", "머쉬룸버거", "콜라"};
        int[] prices = {4000, 6000, 5000, 6000, 1000};
        int[] stocks = {10, 7, 8, 5, 50};

        //음식 목록 초기화
        for (int i = 0; i < foodNames.length; i++) {
            Food food = new Food(foodNames[i], prices[i], stocks[i]);
            foodList.add(food);
        }

        //장바구니 테이블
        cartTableModel = new DefaultTableModel(new Object[]{"음식 이름", "가격", "수량"}, 0);
        cartTable = new JTable(cartTableModel);

        setTitle("키오스크");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        initComponents();
        addComponents();
    }

    private void initComponents() {
        //결제 관련 버튼 초기화
        paymentBtn = new JButton("결제");
        cancelBtn = new JButton("취소");
        cardBtn = new JButton("신용카드");
        pointBtn = new JButton("포인트");
        couponBtn = new JButton("쿠폰");
        totalPriceLabel = new JLabel("총 가격 :     0원");

        //버튼 색상 설정
        paymentBtn.setBackground(Color.pink);
        cancelBtn.setBackground(Color.LIGHT_GRAY);
    
        //결제 버튼 이벤트 리스너 설정
        paymentBtn.addActionListener(e -> displayPaymentOptions());
        //취소 버튼 이벤트 리스너 설정
        cancelBtn.addActionListener(e -> cancelPayment());
        //신용카드 버튼 이벤트 리스너 설정
        cardBtn.addActionListener(e -> startPayment("신용카드"));
        //포인트 버튼 이벤트 리스너 설정
        pointBtn.addActionListener(e -> startPayment("포인트"));
        //쿠폰 버튼 이벤트 리스너 설정
        couponBtn.addActionListener(e -> startPayment("쿠폰"));
    }

    //결제방법(신용카드, 포인트, 쿠폰) 표시하기 위한 새로운 프레임 생성
    public void displayPaymentOptions() {
        JFrame paymentFrame = new JFrame("결제 방법 선택");
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

    private void addComponents() {
        setLayout(new BorderLayout());

        // 음식 목록을 표시할 패널 생성
        JPanel foodPanel = new JPanel();
        foodPanel.setLayout(new GridLayout(foodList.size(),1));

        for (Food food:foodList) {
            JPanel foodItemPanel = new JPanel();
            foodItemPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JLabel nameLabel = new JLabel(food.getFoodName());
            JLabel priceLabel = new JLabel("(가격: " + food.getPrice() + "원)");

            JButton minusButton = new JButton("-"); //수량 감소 버튼
            JButton plusButton = new JButton("+"); //수량 증가 버튼

            //수량 감소 버튼 이벤트 리스너 설정
            minusButton.addActionListener(e -> {
                removeFromCart(food, 1); //장바구니에서 음식 제거
                updateCartTable(); // 장바구니 테이블 업데이트
            });
            //수량 증가 버튼 이벤트 리스너 설정
            plusButton.addActionListener(e -> {
                addToCart(food, 1); // 장바구니에 음식 추가
                updateCartTable(); // 장바구니 테이블 업데이트
            });

            foodItemPanel.add(nameLabel); //음식 이름 레이블 추가
            foodItemPanel.add(priceLabel); //음식 가격 레이블 추가
            foodItemPanel.add(minusButton); //수량 감소 버튼 추가
            foodItemPanel.add(plusButton); //수량 증가 버튼 추가
            foodItemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            foodPanel.add(foodItemPanel); //음식 패널을 음식 목록에 추가
        }

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        paymentPanel.add(totalPriceLabel); //총 가격
        paymentPanel.add(cancelBtn); // 취소 버튼 추가
        paymentPanel.add(paymentBtn); // 결제 버튼 추가

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(cartTable.getTableHeader(), BorderLayout.PAGE_START); // 장바구니 테이블 헤더 추가
        tablePanel.add(cartTable, BorderLayout.CENTER); // 장바구니 테이블 추가

        add(foodPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(paymentPanel, BorderLayout.SOUTH);
    }

    //취소 버튼 이벤트 핸들러
    private void cancelPayment() {
        cart.getFoodList().clear(); //장바구니 비우기
        updateCartTable(); // 장바구니 테이블 업데이트
        displayPrompt("결제가 취소되었습니다.");
    }

    //결제 수행
    private void startPayment(String paymentMethod) {
        Payment payment = new Payment(paymentMethod, true);
        payment.processing(cart);

        System.exit(0);
    }

    //장바구니 테이블 업데이트
    private void updateCartTable() {
        cartTableModel.setRowCount(0); //테이블 모델 초기화

        for (Food food : cart.getFoodList()) {
            Object[] rowData = {food.getFoodName(), food.getPrice()*food.getQuantity(), food.getQuantity()};
            cartTableModel.addRow(rowData);
        }
        totalPriceLabel.setText("총 가격 : " + cart.calcTotalPrice() + "원");
    }

    //음식 목록 표시
    public void displayFood() {
        System.out.println("=============== 메뉴 ===============");
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            System.out.println((i + 1) + ". " + food.getFoodName() + ", 금액: " + food.getPrice() + ", 재고: " + food.getStock());
        }
        System.out.println("====================================");
    }

    //장바구니 표시
    public void displayCart() {
        System.out.println("============== 장바구니 ==============");
        for (Food food : cart.getFoodList()) {
            System.out.println(food.getFoodName() + ", 금액: " + food.getPrice()*food.getQuantity() + ", 수량: " + food.getQuantity());
        }
        System.out.println("총 금액: " + cart.calcTotalPrice());
        System.out.println("=====================================");
    }

    //프롬프트 메시지 표시
    public void displayPrompt(String message) {
        System.out.println(message);
        JOptionPane.showMessageDialog(this, message);
    }

    //장바구니에 음식 담기
    public void addToCart(Food food, int quantity) {
        boolean existingItem = false;
        //장바구니에 동일한 음식이 있는지 확인
        for (Food cartFood : cart.getFoodList()) {
            if (cartFood.getFoodName().equals(food.getFoodName())) {
                //동일한 음식이 있다면 수량을 더해줌
                cartFood.setQuantity(cartFood.getQuantity() + quantity);
                existingItem = true;
                break;
            }
        }
       //장바구니에 동일한 음식이 없는 경우 추가
        if (!existingItem) {
            cart.addFood(food, quantity);
        }

        System.out.println("장바구니에 " + food.getFoodName() + " " + quantity + "개 추가되었습니다.");
        displayCart(); //장바구니 내용 표시
        updateCartTable(); //장바구니 테이블 업데이트
    }

    //장바구니에서 음식 제거
    public void removeFromCart(Food food, int quantity) {
        cart.removeFood(food, quantity);
        System.out.println("장바구니에서 " + food.getFoodName() + " " + quantity + "개 제거되었습니다. ");
        displayCart();
        updateCartTable();
    }

    //쿠폰 번호 입력
    public int enterCouponNumber() {
        String couponNumberString = JOptionPane.showInputDialog(null, "쿠폰 번호를 입력하세요:");
        return Integer.parseInt(couponNumberString);
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.displayFood();
        mainScreen.setVisible(true); //화면을 보이도록 설정
    }
}
