package demo.tile;

import Game.GamePanel;
import Implements.Constant;
import Implements.ImagePath;
import demo.entity.Enemy.EnemyManager;
import demo.entity.Entity;
import demo.entity.Player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Portal extends Entity {
    private BufferedImage bufferedImage;
    private boolean open = false;
    private boolean win = false;
    public Portal(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
        try {
            this.bufferedImage = ImageIO.read(new File(ImagePath.PORTAL));
        } catch (Exception e) {
            System.out.println("Cannot load Image from Portal.");
        }
    }

    public void update(Player player, EnemyManager enemyManager) {
        if (enemyManager.getEnemies().size() == 0) {
            open = true;
        }
        if ((player.getX() + Constant.tileSize / 2) / Constant.tileSize == this.x / Constant.tileSize
                && (player.getY() + Constant.tileSize / 2) / Constant.tileSize == this.y / Constant.tileSize && open) {
            win = true;
        }
        if (win) {
            gamePanel.setNextLevel(true);
            gamePanel.nextLevel();
            gamePanel.gameState = Constant.levelState;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(bufferedImage, this.x, this.y, Constant.tileSize, Constant.tileSize, null);
    }

    public boolean isWin() {
        return win;
    }
}
