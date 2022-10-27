package demo.entity;

import Game.GamePanel;
import SuperObject.Bomb.BombManager;
import demo.entity.Entity;
import demo.entity.Player.Player;
import demo.tile.GameMap;

import java.awt.*;
import java.util.ArrayList;

public abstract class EntityManagement {
    public GamePanel gamePanel;
    public GameMap gameMap;
    public BombManager bombManager;

    public ArrayList <Entity> entities = new ArrayList<>();
    public EntityManagement(GamePanel gamePanel, GameMap gameMap) {
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
    }

    public EntityManagement(GamePanel gamePanel, GameMap gameMap, BombManager bombManager) {
        this.gamePanel = gamePanel;
        this.gameMap = gameMap;
        this.bombManager = bombManager;
    }

    public EntityManagement(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }



    public abstract void update(Player player);
    public abstract void update(Player player, BombManager bombManager);

    public abstract void update(BombManager bombManager);

    public abstract void draw(Graphics2D graphics2D);
}
