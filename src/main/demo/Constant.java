package demo;

public interface Constant {
    int originalTileSize =  16;
    int scale = 2;
    int tileSize = originalTileSize * scale;
    int maxScreenCol = 31;
    int maxScreenRow = 13;
    int screenWidth = maxScreenCol * tileSize;
    int screenHeight = tileSize * maxScreenRow;


    //World.

}
