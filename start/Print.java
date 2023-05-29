package start;

import javax.swing.JOptionPane;
import java.util.List;

public class Print {
    private int waitingNumber; //대기 번호
    private String receipt; //영수증

    public Print() {
        waitingNumber = 0;
        receipt = "";
    }

    public void setWaitingNumber(Cart cart, Payment payment) { //대기번호 설정
        waitingNumber++;
        printWaitingNumber(); //대기번호 출력 호출
        printReceipt(cart, payment); //영수증 출력 호출
    }

    public void printWaitingNumber() { //대기번호 출력
        System.out.println("대기번호: " + waitingNumber);
        JOptionPane.showMessageDialog(null, "대기번호: " + waitingNumber);
    }

    public void printReceipt(Cart cart, Payment payment) { //영수증 출력
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("<영수증>\n주문 내역:\n");
        List<Food> orderedFoods = cart.getFoodList();
        for (Food food : orderedFoods) {
            receiptBuilder.append("- " + food.getFoodName() + " " + food.getQuantity() + "개 " + food.getPrice()* food.getQuantity() + "원\n");
        }
        receiptBuilder.append("총 가격: " + payment.getFinalPrice() + "원");

        receipt = receiptBuilder.toString();

        System.out.println(receipt);
        JOptionPane.showMessageDialog(null, receipt);
    }
}
