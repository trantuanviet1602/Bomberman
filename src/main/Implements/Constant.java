package Implements;

import demo.tile.GameMap;

public interface Constant {
    int originalTileSize =  16;
    int scale = 2;

    int tileSize = originalTileSize * scale;
    int maxScreenCol = 31;
    int maxScreenRow = 13;
    int screenWidth = maxScreenCol * tileSize;
    int screenHeight = tileSize * maxScreenRow;


    int titleState = 0;

    int playState = 1;
    int pauseState = 2;
    int levelState = 3;
    int gameOverState = 4;


    //World.

}
