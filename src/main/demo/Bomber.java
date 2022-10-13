package demo;
import javax.swing.*;


public class Bomber {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        window.setTitle("Bomberman");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); //Cài đặt window sao cho kích cỡ vừa với thành phần phụ trước đó, ở đây là GamePanel.


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}