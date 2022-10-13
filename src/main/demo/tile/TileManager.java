package demo.tile;

import demo.Constant;
import demo.GamePanel;
import demo.ImagePath;

import java.awt.*;


public class TileManager implements ImagePath, Constant {
    GamePanel gamePanel;
    Tile grassTile, wallTile, brickTile;



    public Map map = new Map();

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
        wallTile.collision = false;
        brickTile.collision = false;
    }


    public void draw(Graphics2D graphics2D) {
        try {
            int worldCol = 0;
            int worldRow = 0;
            while (worldCol < map.cols && worldRow < map.rows) {
                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;

                if(map.mapTile[worldRow].charAt(worldCol) == '#') {
                    graphics2D.drawImage(wallTile.bufferedImage,worldX, worldY , tileSize, tileSize, null);
                } else if(map.mapTile[worldRow].charAt(worldCol) == '*') {
                    graphics2D.drawImage(brickTile.bufferedImage,worldX, worldY , tileSize, tileSize, null);
                } else {
                    graphics2D.drawImage(grassTile.bufferedImage,worldX, worldY , tileSize, tileSize, null);
                }


                worldCol++;

                if(worldCol == map.cols) {
                    worldCol = 0;
                    worldRow ++ ;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
