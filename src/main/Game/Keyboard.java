package Game;

import Implements.Constant;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private final GamePanel gamePanel;
    public boolean up, down, left, right;
    public boolean space;

    public Keyboard(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        //TODO: Thực hiện các thay đổi về game trong khi chơi.
        if (gamePanel.gameState == Constant.playState || gamePanel.gameState == Constant.pauseState) {
            if (code == KeyEvent.VK_P) {
                if (gamePanel.gameState == Constant.pauseState) gamePanel.gameState = Constant.playState;
                else {
                    gamePanel.gameState = Constant.pauseState;
                    gamePanel.stopMusic();
                }
            }
            if (code == KeyEvent.VK_UP) {
                up = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                down = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                space = true;
            }
        }
        //TODO: Thay đổi trạng thái lựa chọn khi ở Menu.
        if (gamePanel.gameState == Constant.titleState) {
            if (code == KeyEvent.VK_UP) {
                gamePanel.getUi().changeChoiceCounter(-1);
            }
            if (code == KeyEvent.VK_DOWN) {
                gamePanel.getUi().changeChoiceCounter(1);
            }
            if (code == KeyEvent.VK_SPACE) {
                if (gamePanel.getUi().getChoiceCounter() == 0) {

                    gamePanel.gameState = Constant.levelState;
                }
                if (gamePanel.getUi().getChoiceCounter() == 1) {
                    System.exit(0);
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            up = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            space = false;
        }
    }
}
