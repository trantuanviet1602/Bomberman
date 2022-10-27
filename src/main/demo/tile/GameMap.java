package demo.tile;

import Implements.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameMap implements Constant {
    public String[] mapTile;

    public int cols, rows;

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public GameMap(int level) {
        try {
            loadMap(level);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadMap(int level) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/res/text/Level" + level + ".txt"));
        scanner.nextInt();
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        scanner.nextLine();
        mapTile = new String[rows];
        for(int j = 0; j < rows; j++) {
            mapTile[j] = scanner.nextLine();
          //  System.out.println(mapTile[j]);
        }
    }

}
