package demo.tile;

import demo.Constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Map implements Constant {
    public char mapTile[][];
    public Map() {
    }
    public void loadMap() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/res/text/Level1.txt"));
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        mapTile = new char[c][b];
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
