package demo.entity;

import demo.Constant;
import demo.GamePanel;


public class CollisionCheck implements Constant {
    GamePanel gamePanel;
    public CollisionCheck(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void CheckTile(Entity entity) {
        int entityLeft = entity.x + entity.solidArea.x;
        int entityRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityDown = entity.y + entity.solidArea.y + entity.solidArea.height;


        int entityLeftCol = entityLeft / tileSize;
        int entityRightCol = (entityRight - 1) / tileSize;
        int entityTopRow = entityTop / tileSize;
        int entityDownRow = (entityDown - 1) / tileSize;

        char tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTop - entity.speed) / tileSize;
                tileNum1 = gamePanel.tileManager.map.mapTile[entityTopRow - 1].charAt(entityLeftCol - 1);
                tileNum2 = gamePanel.tileManager.map.mapTile[entityTopRow - 1].charAt(entityRightCol - 1);
                if ((tileNum1 == '#' || tileNum2 == '*' || tileNum1 == '*' || tileNum2 == '#')) {
                    entity.upCollision = true;
                }
            }
            case "down" -> {
                entityDownRow = (entityDown + entity.speed) / tileSize;
                tileNum1 = gamePanel.tileManager.map.mapTile[entityDownRow - 1].charAt(entityLeftCol - 1);
                tileNum2 = gamePanel.tileManager.map.mapTile[entityDownRow - 1].charAt(entityRightCol - 1);
                if ((tileNum1 == '#' || tileNum2 == '*' || tileNum1 == '*' || tileNum2 == '#')) {
                    entity.downCollision = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeft - entity.speed) / tileSize;
                tileNum1 = gamePanel.tileManager.map.mapTile[entityTopRow - 1].charAt(entityLeftCol - 1);
                tileNum2 = gamePanel.tileManager.map.mapTile[entityDownRow - 1].charAt(entityLeftCol - 1);
                if ((tileNum1 == '#' || tileNum2 == '*' || tileNum1 == '*' || tileNum2 == '#')) {
                    entity.leftCollision = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRight + entity.speed) / tileSize;
                tileNum1 = gamePanel.tileManager.map.mapTile[entityTopRow - 1].charAt(entityRightCol - 1);
                tileNum2 = gamePanel.tileManager.map.mapTile[entityDownRow - 1].charAt(entityRightCol - 1);
                if ((tileNum1 == '#' || tileNum2 == '*' || tileNum1 == '*' || tileNum2 == '#')) {
                    entity.rightCollision = true;
                }
            }
        }

    }
}
