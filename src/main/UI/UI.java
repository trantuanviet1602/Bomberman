package UI;

import Game.GamePanel;
import Implements.Constant;
import Implements.ImagePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class UI {
    GamePanel gamePanel;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    Graphics2D graphics2D;
    int messageCounter = 0;

    BufferedImage bufferedImage;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            bufferedImage = ImageIO.read(new File(ImagePath.TITLE_PATH));
        } catch (Exception e) {
            System.out.println("Cannot load file from UI");
        }
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        if (gamePanel.gameState == Constant.pauseState) {
            this.drawPauseScreen();
        }
        if (gamePanel.gameState == Constant.titleState) {
            this.drawTitleScreen();
        }
    }

    public void drawTitleScreen() {
        this.gamePanel.setBackground(Color.black);
        graphics2D.drawImage(bufferedImage, 0, 0,
                Constant.maxScreenCol * Constant.tileSize, Constant.maxScreenRow * Constant.tileSize, null);
    }

    public void drawPauseScreen() {
        this.gamePanel.setBackground(Color.black);
        graphics2D.setColor(Color.white);
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
