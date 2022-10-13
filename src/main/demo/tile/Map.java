package demo.tile;

import demo.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map implements Constant {
    public String[] mapTile;
    public int cols, rows;

    public Map() {
        try {
            loadMap();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadMap() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/res/text/Level1.txt"));
        scanner.nextInt();
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        scanner.nextLine();
        mapTile = new String[rows];
        for(int j = 0; j < rows; j++) {
            mapTile[j] = scanner.nextLine();
            System.out.println(mapTile[j]);
        }
    }

    public static void main(String[] args) {
        Map map = new Map();
        try {
            map.loadMap();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
