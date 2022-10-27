package demo.tile;

import Game.GamePanel;
import Implements.Constant;
import Implements.ImagePath;
import demo.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Grass extends Entity {
    private BufferedImage grassImage;
    public Grass(GamePanel gamePanel) {
        try {
            this.grassImage = ImageIO.read(new File(ImagePath.GRASS_PATH));
        } catch (Exception e) {
            System.out.println("Cannot load Grass Image");
        }
        this.gamePanel = gamePanel;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < gamePanel.getGameMap().getRows(); i++) {
            for (int j = 0; j < gamePanel.getGameMap().getCols(); j++) {
                graphics2D.drawImage(grassImage, j * Constant.tileSize,
                        i * Constant.tileSize, Constant.tileSize, Constant.tileSize, null);
            }
        }
    }
}
