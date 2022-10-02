package demo;

public interface Constant {
    int originalTileSize =  16;
    int scale = 3;
    int tileSize = originalTileSize * scale;
    int maxScreenCol = 16;
    int maxScreenRow = 12;
    int screenWidth = maxScreenCol * tileSize;
    int screenHeight = tileSize * maxScreenRow;
}
