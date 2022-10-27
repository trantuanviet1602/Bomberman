package demo.entity.Enemy.Oneal;

import Game.GamePanel;
import Game.Keyboard;
import Implements.Constant;
import SuperObject.Bomb.BombManager;
import demo.entity.Enemy.Enemy;
import demo.entity.Player.Player;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Oneal extends Enemy {
    private final OnealImage onealImage = new OnealImage();
    private final double[][] lengthToCoordinate;
    private int rowPlayer, colPlayer;
    private int colX, rowY;
    private int moveCounter = 0;
    private int delayCounter = 100, currentPosition;
    private ArrayList<Pair<Integer, Integer>> closed = new ArrayList<>(), opened = new ArrayList<>(),
            chasePath = new ArrayList<>();
    public Oneal(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        solidArea = new Rectangle(32, 32, Constant.tileSize - 1 , Constant.tileSize - 1);
        this.gamePanel = gamePanel;
        this.direction = "left";
        this.speed = 1;
        this.lengthToCoordinate = new double[gamePanel.tileManager.gameMap.rows][gamePanel.tileManager.gameMap.cols];
    }

    /**
     * TODO: di chuyển Oneal đến vị trí chỉ định khi thực hiện truy đuổi.
     */
    public void moveTo(int x, int y) {
        if (this.x != x) {
            if (this.x > x) {
                this.x -= speed;
                direction = "left";
            }
            if(this.x < x) {
                this.x += speed;
                direction = "right";
            }
        } else if (this.y != y) {
            if (this.y > y) {
                this.y -= speed;
                direction = "up";
            }
            if (this.y < y) {
                this.y += speed;
                direction = "down";
            }
        }

    }

    public boolean isPosition(Pair<Integer, Integer> pair) {
        return this.x == pair.getValue() * Constant.tileSize && pair.getKey() * Constant.tileSize == this.y;
    }


    /**
     * TODO: Set các giá trị để thực hiện thuật toán A*.
     */
    public void setupCoordinate(Player player) {
        colPlayer = (player.getX() + Constant.tileSize / 2) / Constant.tileSize;
        rowPlayer = (player.getY() + Constant.tileSize / 2) / Constant.tileSize;
        colX = (this.x + Constant.tileSize / 2) / Constant.tileSize;
        rowY = (this.y + Constant.tileSize / 2) / Constant.tileSize;
        if (distance(colPlayer, rowPlayer) <= 100) {
            for (int i = 0; i < gamePanel.getGameMap().getRows(); i++) {
                for (int j = 0; j < gamePanel.getGameMap().getCols(); j++) {
                    if(!gamePanel.tileManager.mapCollision[i][j])
                        lengthToCoordinate[i][j] = Math.abs(colX - j) + Math.abs(rowY - i)
                                + Math.sqrt(Math.pow(Math.abs(colPlayer - j) + 1, 2) + Math.pow(Math.abs(rowPlayer - i) + 1, 2));
                    else lengthToCoordinate[i][j] = 0;
                }
            }
            lengthToCoordinate[rowPlayer][colPlayer] =
                    Math.sqrt(Math.pow(Math.abs(colX - colPlayer) + 1, 2) + Math.pow(Math.abs(rowY - rowPlayer) + 1, 2));
        }
    }

    /**
     * i: Row. j : Col.
     * TODO: Thăm 1 điểm được xác định. Sẽ đưa vào các list opened và closed để thực hiện thuật toán A*.
     */
    public void visit(int i, int j) {
        closed.add(new Pair<>(i,j));
        opened.remove(new Pair<>(i,j));
        Pair<Integer, Integer> temp1 = new Pair<>(i + 1, j);
        Pair<Integer, Integer> temp2 = new Pair<>(i - 1, j);
        Pair<Integer, Integer> temp3 = new Pair<>(i, j + 1);
        Pair<Integer, Integer> temp4 = new Pair<>(i, j - 1);
        if (!closed.contains(temp1) && !opened.contains(temp1) && !gamePanel.tileManager.mapCollision[i + 1][j]) {
            opened.add(temp1);
        }
        if (!closed.contains(temp2) && !opened.contains(temp2) && !gamePanel.tileManager.mapCollision[i - 1][j]) {
            opened.add(temp2);
        }
        if (!closed.contains(temp3) && !opened.contains(temp3) && !gamePanel.tileManager.mapCollision[i][j + 1]) {
            opened.add(temp3);
        }
        if (!closed.contains(temp4) && !opened.contains(temp4) && !gamePanel.tileManager.mapCollision[i][j - 1]) {
            opened.add(temp4);
        }
    }

    /**
     * TODO: Sử dụng thuật toán A* để tạo AI cho Oneal.
     */
    public ArrayList<Pair<Integer, Integer>> AstarFindingPath() {
        ArrayList<Pair<Integer, Integer>> finalPath = new ArrayList<>();
        opened = new ArrayList<>();
        closed = new ArrayList<>();
        visit(rowY, colX);

        //TODO: Tạo danh sách closed chứa các điểm đã xét. sẽ dừng lại vòng lặp khi visit được đến vị trí Player.

        Pair<Integer, Integer> temp;
        while(!closed.contains(new Pair<>(rowPlayer, colPlayer))) {
            temp = new Pair<>(0, 0);
            for (Pair<Integer, Integer> pair : opened) {

                if(gamePanel.tileManager.mapCollision[temp.getKey()][temp.getValue()]) {
                    temp = pair;
                }
                else if(Math.abs(lengthToCoordinate[pair.getKey()][pair.getValue()] - lengthToCoordinate[rowPlayer][colPlayer])
                < Math.abs(lengthToCoordinate[temp.getKey()][temp.getValue()] - lengthToCoordinate[rowPlayer][colPlayer])) {
                    temp = pair;
                }
            }
            if (temp.getKey()!=0 &&temp.getValue()!=0){
                visit(temp.getKey(), temp.getValue());
            }
            if(opened.isEmpty() && !closed.contains(new Pair<>(rowPlayer, colPlayer))) {
                break;
            }
        }

        //TODO: Tìm ra đường đi ngắn nhất từ danh sách Closed bằng cách lấy phần tử gần nhất thỏa mãn kế bên điểm cuối.

        int index = closed.size() - 1;
        if (closed.contains(new Pair<>(rowPlayer, colPlayer))) {
            finalPath.add(new Pair<>(rowPlayer, colPlayer));
            while (finalPath.get(finalPath.size() - 1) != closed.get(0)) {
                for (int i = 0; i <= index; i++) {
                    if ((Math.abs(finalPath.get(finalPath.size() - 1).getKey() - closed.get(i).getKey()) == 1
                    && finalPath.get(finalPath.size() - 1).getValue() == closed.get(i).getValue())
                   || (Math.abs(finalPath.get(finalPath.size() - 1).getValue() - closed.get(i).getValue()) == 1
                            && finalPath.get(finalPath.size() - 1).getKey() == closed.get(i).getKey())) {
                        finalPath.add(closed.get(i));
                        index = i;
                        break;
                    }
                    if (index == 0) {
                        finalPath.add(closed.get(0));
                        break;
                    }
                }
            }
        } else {
            currentPosition = 0;
            return null;
        }
        currentPosition = finalPath.size() - 1;
        return finalPath;
    }

    /**
     * TODO: Tính toán khoảng cách giữa Player và Oneal, nếu nhỏ hơn khoảng cho trước thì có thể truy đuổi.
     */
    public double distance(int x, int y) {
        return Math.sqrt(Math.pow(x - colX, 2) + Math.pow(y - rowY, 2));
    }

    /**
     * TODO: Di chuyển ngẫu nhiên nếu như chưa thể truy đuổi đến nhân vật. Cài đặt tương tự như Ballom.
     */
    public void moveRandom() {
        upCollision = false;
        downCollision = false;
        leftCollision = false;
        rightCollision = false;
        if (direction.equals("up")) {
            gamePanel.collisionCheck.CheckTile(this);
            if (!upCollision) {
                moveCounter ++;
                if (moveCounter >= 2) {
                    this.y -= speed;
                    moveCounter = 0;
                }
            } else {
                int newDirection = (int) (Math.random() * 100) % 3;
                if (newDirection == 0) {
                    direction = "down";
                } else if (newDirection == 1) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            }
        }

        if (direction.equals("down")) {
            gamePanel.collisionCheck.CheckTile(this);
            if (!downCollision) {
                moveCounter ++;
                if (moveCounter >= 2) {
                    this.y += speed;
                    moveCounter = 0;
                }
            } else {
                int newDirection = (int) (Math.random() * 100) % 3;
                if (newDirection == 0) {
                    direction = "up";
                } else if (newDirection == 1) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            }
        }

        if (direction.equals("left")) {
            gamePanel.collisionCheck.CheckTile(this);
            if (!leftCollision) {
                moveCounter ++;
                if (moveCounter >= 2) {
                    this.x -= speed;
                    moveCounter = 0;
                }
            } else {
                int newDirection = (int) (Math.random() * 100) % 3;
                if (newDirection == 0) {
                    direction = "down";
                } else if (newDirection == 1) {
                    direction = "up";
                } else {
                    direction = "right";
                }
            }
        }

        if (direction.equals("right")) {
            gamePanel.collisionCheck.CheckTile(this);
            if (!rightCollision) {
                moveCounter ++;
                if (moveCounter >= 2) {
                    this.x += speed;
                    moveCounter = 0;
                }
            } else {
                int newDirection = (int) (Math.random() * 100) % 3;
                if (newDirection == 0) {
                    direction = "down";
                } else if (newDirection == 1) {
                    direction = "left";
                } else {
                    direction = "up";
                }
            }
        }
    }

    /**
     * TODO: Update trạng thái và vị trí cho Oneal.
     */
    @Override
    public void update(Player player, BombManager bombManager) {
        if(!death) {
            delayCounter ++;
            setupCoordinate(player);
            if (delayCounter >= 100) {
                chasePath = AstarFindingPath();
                delayCounter = 0;
            }

            if (chasePath == null || distance(colPlayer, rowPlayer) >= 15) {
                moveRandom();
            } else {
                if (distance((player.getX() + Constant.tileSize / 2) / Constant.tileSize,
                        (player.getY() + Constant.tileSize / 2) / Constant.tileSize) <= 1) {
                    moveTo((player.getX() + Constant.tileSize / 2) / Constant.tileSize * Constant.tileSize,
                            (player.getY() + Constant.tileSize / 2) / Constant.tileSize * Constant.tileSize);

                } else if (!isPosition(chasePath.get(currentPosition)) && currentPosition >= 0) {
                    moveTo(chasePath.get(currentPosition).getValue() * Constant.tileSize,
                            chasePath.get(currentPosition).getKey() * Constant.tileSize);
                } else if (currentPosition >= 0) {
                    delayCounter += 15;
                    currentPosition --;
                    if (chasePath.size() - currentPosition == 3) {
                        setupCoordinate(player);
                        chasePath = AstarFindingPath();
                        currentPosition --;
                    }

                } else {
                    setupCoordinate(player);
                    chasePath = AstarFindingPath();
                }
            }


            killPlayer(player);
            bombManager.killEntity(this);
            //TODO: Tạo Animation cho Oneal.
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = (spriteNum + 1) % 3;
                spriteCounter = 0;
            }
        } else {
            spriteCounter ++ ;
            if (spriteCounter > 10) {
                if (deathSpriteNum != 3) deathSpriteNum = deathSpriteNum + 1;
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D graphics2D) {

        BufferedImage bufferedImage ;

        if (!death) {
            //TODO: Thay đổi BufferedImage dựa trên hướng đi của nhân vật và tạo animation.
            //case "left" -> bufferedImage = onealImage.left[spriteNum];
            if ("right".equals(direction)) {
                bufferedImage = onealImage.right[spriteNum];
            } else {
                bufferedImage = onealImage.left[spriteNum];
            }
        } else {
            bufferedImage = onealImage.death;
        }
        graphics2D.drawImage(bufferedImage, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }


    public static void main(String[] args) {
        Oneal oneal = new Oneal(Constant.tileSize * 16, Constant.tileSize * 9, new GamePanel());
        oneal.setupCoordinate(new Player(new GamePanel(), new Keyboard(new GamePanel()), new BombManager(new GamePanel())));

    }
}
