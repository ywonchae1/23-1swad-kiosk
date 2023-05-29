package start;

import javax.swing.JOptionPane;

public class CustomerInfo {
    private String phoneNumber; // 고객 전화번호
    private String pointCode; // 포인트 코드
    private int point; // 보유 포인트

    public CustomerInfo(String phoneNumber, String pointCode, int point) {
        this.phoneNumber = phoneNumber;
        this.pointCode = pointCode;
        this.point = point;
    }

    // 고객 전화번호 반환
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // 포인트 코드 반환
    public String getPointCode() {
        return pointCode;
    }

    // 보유 포인트 반환
    public int getPoint() {
        return point;
    }

    // 포인트 계산
    public void calcPoint(double totalPrice) {
        double earnedPoint = (double) (totalPrice * 0.03); // 결제 금액의 3%를 포인트로 적립
        point += earnedPoint;
    }

    // 포인트 사용
    public void usePoint(int amount) {
        if (point >= amount) { //포인트가 사용할 포인트 양보다 크거나 같으면
            point -= amount;
            System.out.println("포인트 사용: " + amount + "원"); //포인트를 얼마나 사용했는지 출력
            JOptionPane.showMessageDialog(null, "포인트 사용: " + amount + "원");
        } else {
            System.out.println("포인트가 부족합니다."); //포인트가 충분하지 않음을 출력
            JOptionPane.showMessageDialog(null, "포인트가 부족합니다.");
        }
    }
}
