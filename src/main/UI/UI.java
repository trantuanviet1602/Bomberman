package UI;

import Game.GamePanel;
import Implements.Constant;
import Implements.ImagePath;
import demo.Sound.SoundPath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class UI {
    GamePanel gamePanel;
    private int choiceCounter = 0;
    Graphics2D graphics2D;
    private int counter = 0;

    BufferedImage bomberImage, onealImage;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            bomberImage = ImageIO.read(new File(ImagePath.RIGHT_PATH));
            onealImage = ImageIO.read(new File(ImagePath.ONEAL_LEFT[0]));
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
        if (gamePanel.gameState == Constant.levelState){
            this.drawLevelScreen();
        }
        if (gamePanel.gameState == Constant.gameOverState) {
            this.drawGameOver();
        }
    }

    public void drawTitleScreen() {
        this.gamePanel.setBackground(Color.black);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 96F));
        String text = "BOMBERMAN";
        int x = getXforCenteredText(text);
        int y = Constant.tileSize * 3;
        graphics2D.setColor(Color.gray);
        graphics2D.drawString(text, x, y);

        graphics2D.drawImage(bomberImage, 100, 100, Constant.tileSize * 3, Constant.tileSize * 3, null);
        graphics2D.drawImage(onealImage, Constant.screenWidth - 200, 100, Constant.tileSize * 3, Constant.tileSize * 3, null);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 36F));
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y = Constant.tileSize * 8;
        graphics2D.drawString(text, x, y);
        if (choiceCounter == 0) {
            graphics2D.drawString(">", x - 2 * Constant.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y = Constant.tileSize * 10;
        graphics2D.drawString(text, x, y);
        if (choiceCounter == 1) {
            graphics2D.drawString(">", x - 2 * Constant.tileSize, y);
        }

    }

    public void drawLevelScreen() {
        counter++;

        String text = "Level " + gamePanel.getCurrentLevel();
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 96F));
        graphics2D.setColor(Color.gray);
        int x = getXforCenteredText(text);
        int y = Constant.screenHeight / 2;
        graphics2D.drawString(text, x, y);
        if (counter > 100) {
            gamePanel.gameState = GamePanel.playState;
        }
    }

    public void drawPauseScreen() {
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
        return Constant.screenWidth / 2 - length / 2;
    }

    public void drawGameOver() {
        graphics2D.setColor(Color.white);
        this.graphics2D.setFont(gamePanel.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Game Over";
        int x = getXforCenteredText(text);
        int y = Constant.screenHeight / 2;
        graphics2D.drawString(text, x, y);
    }

    public void changeChoiceCounter(int i) {
        choiceCounter = (choiceCounter + i) % 2;
    }

    public int getChoiceCounter() {
        return choiceCounter;
    }

}
