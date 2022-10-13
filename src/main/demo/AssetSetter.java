package demo;

import SuperObject.ObjectSpeed;

import java.awt.*;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void setObject() {
        gamePanel.superObjects[0] = new ObjectSpeed();
        gamePanel.superObjects[0].x = 32;
        gamePanel.superObjects[0].y = 64;
    }


}
