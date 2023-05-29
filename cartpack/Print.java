package cartpack;
import java.util.List;
import javax.swing.JOptionPane;

public class Print {
    private int waitingNumber; //대기 번호
    private String receipt; //영수증

    public Print() {
        waitingNumber = 0;
        receipt = "";
    }

    public void setWaitingNumber(Cart cart, Payment payment) {
        waitingNumber++; // 대기번호를 1씩 증가시킴
        printWaitingNumber();
        printReceipt(cart, payment);
    }

    public void printWaitingNumber() {
        System.out.println("대기번호: " + waitingNumber);
        JOptionPane.showMessageDialog(null, "대기번호: " + waitingNumber);
    }

    public void printReceipt(Cart cart, Payment payment) {
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
