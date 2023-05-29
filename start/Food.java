package start;

public class Food {
    private String foodName; //Food 이름
    private int price; //가격
    private int stock; //재고
    private int quantity; //장바구니에 담긴 수량

    public Food(String foodName, int price, int stock) {
        this.foodName = foodName;
        this.price = price;
        this.stock = stock;
        this.quantity = 0; //초기 장바구니 수량은 0개임
    }

    public String getFoodName() {
        return foodName;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() { 
        return quantity;
    }

    public void setQuantity(int quantity) { //장바구니 수량 설정
        this.quantity = quantity;
    }

    public void sell(int quantity) { //음식 판매
        if (stock >= quantity) {
            stock -= quantity; //재고 감소
        } else { //재고 부족 시 메시지 출력
            System.out.println("재고 부족: " + foodName);
        }
    }

    //재고 증가
    public void restock(int quantity) {
        stock += quantity;
    }

    // 주어진 수량의 음식 구매 가능 여부 확인
    public boolean checkAvailability(int quantity) {
        return stock >= quantity;
    }

    //품절 여부 확인
    public boolean isSoldOut() {
        return stock <= 0;
    }
}
