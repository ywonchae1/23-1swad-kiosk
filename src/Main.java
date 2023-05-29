import displaypack.MainScreen;

public class Main {
    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.displayFood();
        mainScreen.setVisible(true); //화면을 보이도록 설정
    }
}
