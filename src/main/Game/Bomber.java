package Game;
import Game.GamePanel;

import javax.swing.*;


public class Bomber {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );


        GamePanel gamePanel = new GamePanel();
        window.setTitle("Bomberman");
        window.add(gamePanel);

        window.pack(); //Cài đặt window sao cho kích cỡ vừa với thành phần phụ trước đó, ở đây là GamePanel.


        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}