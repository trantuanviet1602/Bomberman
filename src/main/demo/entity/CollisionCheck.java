package demo.entity;

import Implements.Constant;
import Game.GamePanel;
import SuperObject.Bomb.Bomb;
import SuperObject.Bomb.BombManager;
import SuperObject.Bomb.Explosion;
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

        int entityLeft = entity.x + entity.solidArea.x;
        int entityRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityDown = entity.y + entity.solidArea.y + entity.solidArea.height;


        for (Wall wall: TileManager.wallManager.walls) {
            if (entity.direction.equals("left") &&
                    (wall.checkCollision((entityLeft - entity.speed) / tileSize, entityDown / tileSize  )
                            || wall.checkCollision((entityLeft - entity.speed) / tileSize, entityTop / tileSize ))) {
                entity.leftCollision = true;
                break;
            }
            if (entity.direction.equals("right") &&
                    (wall.checkCollision((entityRight + entity.speed) / tileSize, entityDown / tileSize  )
                            || wall.checkCollision((entityRight + entity.speed) / tileSize, entityTop / tileSize ))) {
                entity.rightCollision = true;
                break;
            }
            if (entity.direction.equals("up") &&
                    (wall.checkCollision(entityRight / tileSize, (entityTop - entity.speed) / tileSize  )
                            || wall.checkCollision(entityLeft / tileSize, (entityTop - entity.speed) / tileSize ))) {
                entity.upCollision = true;
                break;
            }
            if (entity.direction.equals("down") &&
                    (wall.checkCollision(entityRight / tileSize, (entityDown + entity.speed) / tileSize  )
                            || wall.checkCollision(entityLeft / tileSize, (entityDown + entity.speed) / tileSize ))) {
                entity.downCollision = true;
                break;
            }
        }

        for (Brick brick: TileManager.brickManager.bricks) {
            if (entity.direction.equals("left") &&
                    (brick.checkCollision((entityLeft - entity.speed) / tileSize, entityDown / tileSize  )
                            || brick.checkCollision((entityLeft - entity.speed) / tileSize, entityTop / tileSize ))) {
                entity.leftCollision = true;
                break;
            }
            if (entity.direction.equals("right") &&
                    (brick.checkCollision((entityRight + entity.speed) / tileSize, entityDown / tileSize  )
                            || brick.checkCollision((entityRight + entity.speed) / tileSize, entityTop / tileSize ))) {
                entity.rightCollision = true;
                break;
            }
            if (entity.direction.equals("up") &&
                    (brick.checkCollision(entityRight / tileSize, (entityTop - entity.speed) / tileSize  )
                            || brick.checkCollision(entityLeft / tileSize, (entityTop - entity.speed) / tileSize ))) {
                entity.upCollision = true;
                break;
            }
            if (entity.direction.equals("down") &&
                    (brick.checkCollision(entityRight / tileSize, (entityDown + entity.speed) / tileSize  )
                            || brick.checkCollision(entityLeft / tileSize, (entityDown + entity.speed) / tileSize ))) {
                entity.downCollision = true;
                break;
            }
        }

        for (Bomb bomb: bombManager.bombs) {
            if (bomb.collision) {
                if (entity.direction.equals("left") &&
                        (bomb.checkCollision((entityLeft - entity.speed) / tileSize, entityDown / tileSize  )
                                || bomb.checkCollision((entityLeft - entity.speed) / tileSize, entityTop / tileSize ))) {
                    entity.leftCollision = true;
                    break;
                }
                if (entity.direction.equals("right") &&
                        (bomb.checkCollision((entityRight + entity.speed) / tileSize, entityDown / tileSize  )
                                || bomb.checkCollision((entityRight + entity.speed) / tileSize, entityTop / tileSize ))) {
                    entity.rightCollision = true;
                    break;
                }
                if (entity.direction.equals("up") &&
                        (bomb.checkCollision(entityRight / tileSize, (entityTop - entity.speed) / tileSize  )
                                || bomb.checkCollision(entityLeft / tileSize, (entityTop - entity.speed) / tileSize ))) {
                    entity.upCollision = true;
                    break;
                }
                if (entity.direction.equals("down") &&
                        (bomb.checkCollision(entityRight / tileSize, (entityDown + entity.speed) / tileSize  )
                                || bomb.checkCollision(entityLeft / tileSize, (entityDown + entity.speed) / tileSize ))) {
                    entity.downCollision = true;
                    break;
                }
            }
        }
    }

}
