package cartpack;
import foodpack.Food;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Food> foodList;

    public Cart() {
        foodList = new ArrayList<>();
    }

    //장바구니에 음식 담기
    public boolean addFood(Food food, int quantity) {
        boolean is_in = false;
        if (food.checkAvailability(quantity)) { //재고 확인
            for (Food cartFood : foodList) {
                if (cartFood.getFoodName().equals(food.getFoodName())) {
                    //동일한 음식이 있다면 수량을 더해줌
                    cartFood.setQuantity(cartFood.getQuantity() + 1);
                    is_in = true;
                    break;
                }
            }
            if (!is_in) {
                foodList.add(food); //장바구니에 음식 담기
                food.setQuantity(quantity); //장바구니에 담긴 수량 설정
            }
            food.sell(1); //재고 감소
            return true;
        } else {
            System.out.println(food.getFoodName() + "의 재고가 부족합니다. ");
            return false;
        }
    }

    //장바구니에서 음식 빼기
    public boolean removeFood(Food food, int quantity) {
        if (foodList.contains(food)) { // 음식이 장바구니에 있는지 확인
            int currentQuantity = food.getQuantity(); // 현재 음식의 수량
            if (quantity > currentQuantity) { // 선택한 수량이 장바구니에 담긴 수량보다 많은 경우
                System.out.println("선택하신 수량이 장바구니에 담긴 " + food.getFoodName() + "의 재고보다 많습니다. 장바구니에서 모든 수량을 제거합니다.");
                quantity = currentQuantity; // 장바구니에 담긴 수량으로 제한하여 모두 제거
            }
            food.setQuantity(currentQuantity - 1); // 음식의 수량 감소
            if (food.getQuantity() == 0) { // 음식의 수량이 0이 되면 장바구니에서 완전히 제거
                foodList.remove(food);
            }
            food.restock(1); // 재고 증가
            return true;
        } else {
            System.out.println(food.getFoodName() + "은(는) 장바구니에 존재하지 않습니다.");
            return false;
        }
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public int calcTotalPrice() {
        int totalPrice = 0;
        for (Food food : foodList) {
            totalPrice += (food.getPrice() * food.getQuantity());;
        }
        return totalPrice;
    }
}
