package demo.tile;

import demo.GamePanel;
import demo.ImagePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class TileManager implements ImagePath {
    GamePanel gamePanel;
    Tile grassTile, wallTile, brickTile;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        getTileImage();
    }

    public void getTileImage() {
        try {
            grassTile = new Tile(GRASS_PATH);
            wallTile = new Tile(WALL_PATH);
            brickTile = new Tile(BRICK_PATH);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(grassTile.bufferedImage, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
