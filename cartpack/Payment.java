package cartpack;
import javax.swing.JOptionPane;

public class Payment {
    private String paymentMethod; //결제 방법
    private int couponNumber; //쿠폰 번호
    private int couponPrice; //쿠폰 금액
    private boolean paymentComplete; //결제 완료 여부
    private int finalPrice; //최종 결제 금액
    private boolean eatIn; //매장 내 식사 여부

    //결제 처리
    public Payment() {
        paymentMethod = "";
        couponNumber = 0;
        couponPrice = 0;
        eatIn = false;
        paymentComplete = false;
        finalPrice = 0;
    }

    public Payment(String paymentMethod, boolean eatIn) {
        this.paymentMethod = paymentMethod;
        couponNumber = 0;
        couponPrice = 0;
        this.eatIn = eatIn;
        paymentComplete = false;
        finalPrice = 0;
    }

    public void processing(Cart cart){
        int totalPrice = cart.calcTotalPrice(); //장바구니 가격 총 계산
        System.out.println(totalPrice);
        switch (paymentMethod) {
            case "포인트":
                CustomerInfo customerInfo = new CustomerInfo("010-1234-5678","1234",250);
                int usedPoint = calcUserPoint(totalPrice, customerInfo);
                finalPrice = calcFinalPrice(totalPrice, usedPoint); // 포인트 적용하여 최종 결제 금액 계산
                if (finalPrice > 0)
                    payCredit(finalPrice);
                break;
            case "신용카드":
                finalPrice = totalPrice; // 장바구니의 총 가격 계산
                payCredit(finalPrice); // 신용카드로 결제
                break;
            case "쿠폰":
                finalPrice = totalPrice; // 장바구니의 총 가격 계산
                useCoupon();
                break;
            default:
                System.out.println("유효하지 않은 결제 방법입니다.");
                return;
        }
        if (!isComplete()) {
            System.out.println("결제 실패");
            JOptionPane.showMessageDialog(null, "결제 실패");
        } else {
            System.out.println("결제 성공");
            JOptionPane.showMessageDialog(null, "결제 성공");
        }

        Print print = new Print();
        print.setWaitingNumber(cart, this);
    }

    private int calcUserPoint(int totalPrice, CustomerInfo customerInfo){
        customerInfo.calcPoint(totalPrice); //고객의 포인트 계산

        int availablePoint = customerInfo.getPoint(); //사용 가능한 포인트
        int usedPoint = Math.min(availablePoint, totalPrice); //사용할 포인트 (최대로 사용 가능한 포인트는 최종 결제 금액)

        customerInfo.usePoint(usedPoint); //포인트 사용

        return usedPoint; //사용할 포인트 양
    }

    // 포인트 적용하여 최종 결제 금액 계산
    private int calcFinalPrice(int totalPrice, int usedPoint) {
        return totalPrice - usedPoint;
    }

    //결제 완료 여부 확인
    private boolean isComplete() {
        return paymentComplete;
    }

    //쿠폰 번호 유효성 체크
    private boolean checkCouponValidity() {
        MainScreen mainScreen = new MainScreen();
        couponNumber = mainScreen.enterCouponNumber();
        int[][] validCoupons = {{1111, 4000}, {1112, 5000}, {1113, 6000}, {1114, 7000}, {1115, 8000}, {1116, 9000}, {1117, 10000}}; //쿠폰 번호와 금액이 저장된 2차원 배열
        for (int[] coupon : validCoupons) {
            if (coupon[0] == couponNumber) { //쿠폰 번호가 일치하는 경우
                couponPrice = coupon[1]; //쿠폰 금액 저장
                break;
            }
        }
        if (couponPrice == finalPrice) return true; //쿠폰 금액과 최종 금액이 같으면 유효한 쿠폰으로 판단
        return false; //아니면 유효하지 않은 쿠폰으로 판단
    }

    //쿠폰 사용
    private void useCoupon() {
        if (checkCouponValidity() == true) { //쿠폰이 유효한 경우
            paymentComplete = true; //결제 완료
        } else { //쿠폰이 유효하지 않은 경우
            MainScreen mainScreen = new MainScreen();
            mainScreen.displayPrompt("쿠폰번호가 유효하지 않습니다. "); //메인스크린에 출력
            paymentComplete = false; //결제 실패
        }
    }

    //신용카드 결제
    private void payCredit(int totalPrice) {
        System.out.println("신용카드로 " + totalPrice + "원 결제가 완료되었습니다.");
        paymentComplete = true;
    }

    //최종 결제 금액 반환
    public int getFinalPrice() {
        return finalPrice;
    }
}
