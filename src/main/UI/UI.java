package UI;

import Game.GamePanel;
import Implements.Constant;

import java.awt.*;

public class UI {
    GamePanel gamePanel;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    Graphics2D graphics2D;
    int messageCounter = 0;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        if (gamePanel.gameState == Constant.pauseState) {
            this.drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        this.gamePanel.setBackground(Color.black);
        this.graphics2D.setFont(gamePanel.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        String back = "Press P to resume.";
        int xPause = getXforCenteredText(text);

        int y = Constant.screenHeight / 2;
        graphics2D.drawString(text, xPause, y);
        this.graphics2D.setFont(gamePanel.getFont().deriveFont(Font.PLAIN, 50F));
        int xBack = getXforCenteredText(back);
        graphics2D.drawString(back, xBack, y + 80);
    }

    public int getXforCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = Constant.screenWidth / 2 - length / 2;
        return x;
    }
}
