package demo.entity;

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
        if (enemyManager.getEnemies().isEmpty()) {
            open = true;
        }
        if (player.getX() == this.x && player.getY() == this.y && open) {
            win = true;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(bufferedImage, this.x, this.y, Constant.tileSize, Constant.tileSize, null);
    }

    public boolean isWin() {
        return win;
    }
}
