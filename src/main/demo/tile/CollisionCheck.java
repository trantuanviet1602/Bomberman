package demo.tile;

import Implements.Constant;
import Game.GamePanel;
import SuperObject.Bomb.Bomb;
import SuperObject.Bomb.BombManager;

import demo.entity.Enemy.Kondoria.Kondoria;
import demo.entity.Entity;
import demo.tile.TileManager;
import demo.tile.brick.Brick;
import demo.tile.wall.Wall;


public class CollisionCheck implements Constant {
    GamePanel gamePanel;
    TileManager tileManager;
    BombManager bombManager;
    public CollisionCheck(GamePanel gamePanel, BombManager bombManager, TileManager tileManager) {
        this.gamePanel = gamePanel;
        this.tileManager = tileManager;
        this.bombManager = bombManager;
    }

    public void CheckTile(Entity entity) {

        int entityLeft = entity.getX() + entity.getSolidArea().x;
        int entityRight = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTop = entity.getY() + entity.getSolidArea().y;
        int entityDown = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

        for (Wall wall: tileManager.wallManager.walls) {
            if (entity.getDirection().equals("left") &&
                    (wall.checkCollision((entityLeft - entity.getSpeed()) / tileSize, entityDown / tileSize  )
                            || wall.checkCollision((entityLeft - entity.getSpeed()) / tileSize, entityTop / tileSize ))) {
                entity.setLeftCollision(true);
                break;
            }
            if (entity.getDirection().equals("right") &&
                    (wall.checkCollision((entityRight + entity.getSpeed()) / tileSize, entityDown / tileSize  )
                            || wall.checkCollision((entityRight + entity.getSpeed()) / tileSize, entityTop / tileSize ))) {
                entity.setRightCollision(true);
                break;
            }
            if (entity.getDirection().equals("up") &&
                    (wall.checkCollision(entityRight / tileSize, (entityTop - entity.getSpeed()) / tileSize  )
                            || wall.checkCollision(entityLeft / tileSize, (entityTop - entity.getSpeed()) / tileSize ))) {
                entity.setUpCollision(true);
                break;
            }
            if (entity.getDirection().equals("down") &&
                    (wall.checkCollision(entityRight / tileSize, (entityDown + entity.getSpeed()) / tileSize  )
                            || wall.checkCollision(entityLeft / tileSize, (entityDown + entity.getSpeed()) / tileSize ))) {
                entity.setDownCollision(true);
                break;
            }
        }
        if (!(entity instanceof Kondoria)) {
            for (Brick brick: tileManager.brickManager.bricks) {
                if (entity.getDirection().equals("left") &&
                        (brick.checkCollision((entityLeft - entity.getSpeed()) / tileSize, entityDown / tileSize  )
                                || brick.checkCollision((entityLeft - entity.getSpeed()) / tileSize, entityTop / tileSize ))) {
                    entity.setLeftCollision(true);
                    break;
                }
                if (entity.getDirection().equals("right") &&
                        (brick.checkCollision((entityRight + entity.getSpeed()) / tileSize, entityDown / tileSize  )
                                || brick.checkCollision((entityRight + entity.getSpeed()) / tileSize, entityTop / tileSize ))) {
                    entity.setRightCollision(true);
                    break;
                }
                if (entity.getDirection().equals("up") &&
                        (brick.checkCollision(entityRight / tileSize, (entityTop - entity.getSpeed()) / tileSize  )
                                || brick.checkCollision(entityLeft / tileSize, (entityTop - entity.getSpeed()) / tileSize ))) {
                    entity.setUpCollision(true);
                    break;
                }
                if (entity.getDirection().equals("down") &&
                        (brick.checkCollision(entityRight / tileSize, (entityDown + entity.getSpeed()) / tileSize  )
                                || brick.checkCollision(entityLeft / tileSize, (entityDown + entity.getSpeed()) / tileSize ))) {
                    entity.setDownCollision(true);
                    break;
                }
            }

            for (Bomb bomb: bombManager.bombs) {
                if (bomb.isCollision()) {
                    if (entity.getDirection().equals("left") &&
                            (bomb.checkCollision((entityLeft - entity.getSpeed()) / tileSize, entityDown / tileSize  )
                                    || bomb.checkCollision((entityLeft - entity.getSpeed()) / tileSize, entityTop / tileSize ))) {
                        entity.setLeftCollision(true);
                        break;
                    }
                    if (entity.getDirection().equals("right") &&
                            (bomb.checkCollision((entityRight + entity.getSpeed()) / tileSize, entityDown / tileSize  )
                                    || bomb.checkCollision((entityRight + entity.getSpeed()) / tileSize, entityTop / tileSize ))) {
                        entity.setRightCollision(true);
                        break;
                    }
                    if (entity.getDirection().equals("up") &&
                            (bomb.checkCollision(entityRight / tileSize, (entityTop - entity.getSpeed()) / tileSize  )
                                    || bomb.checkCollision(entityLeft / tileSize, (entityTop - entity.getSpeed()) / tileSize ))) {
                        entity.setUpCollision(true);
                        break;
                    }
                    if (entity.getDirection().equals("down") &&
                            (bomb.checkCollision(entityRight / tileSize, (entityDown + entity.getSpeed()) / tileSize  )
                                    || bomb.checkCollision(entityLeft / tileSize, (entityDown + entity.getSpeed()) / tileSize ))) {
                        entity.setDownCollision(true);
                        break;
                    }
                }
            }
        }
    }

}
