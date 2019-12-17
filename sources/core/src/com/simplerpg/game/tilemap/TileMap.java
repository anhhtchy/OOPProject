package com.simplerpg.game.tilemap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class TileMap {
    private TileSet items[][];
    private int mapArray[][]; // luu lai mang truyen vao
    private int cellSize = 30;
    // kich thuoc map theo pixels
    private int mapWidth;
    private int mapHeight;
    private Sprite ground = new TileSet(ItemTileSetType.GROUND_GRASS).getSprite(); // nen cua tile map
    public static final int[][] map1 = {
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
            {6,41,1,1,2,2,2,2,2,2,1,1,1,28,27,6,2,2,2,2,2,2,2,21,20,6,29,29,6,33,6},
            {6,36,37,36,38,36,2,2,7,4,6,1,1,1,28,6,2,29,29,29,29,2,2,2,1,6,30,1,6,1,6},
            {6,2,2,2,2,2,2,2,2,2,6,24,25,24,6,15,2,30,28,28,28,30,2,2,1,6,1,1,6,1,6},
            {6,1,2,2,1,10,7,6,6,0,6,6,6,6,15,15,2,2,29,29,29,2,2,6,0,6,1,31,31,45,6},
            {6,8,1,12,12,12,10,1,2,2,2,2,2,2,13,13,2,2,29,2,2,2,2,6,45,45,45,45,45,45,6},
            {6,1,1,1,1,10,6,6,6,6,6,2,2,2,2,2,2,2,2,2,2,2,6,6,6,6,6,6,6,45,6},
            {6,26,1,1,1,10,2,2,2,2,6,2,2,2,2,13,11,11,2,2,2,2,6,24,1,2,2,2,6,47,6},
            {6,24,25,1,1,1,2,2,2,2,6,30,2,2,2,11,11,11,11,11,6,6,6,6,4,2,2,2,6,46,6},
            {6,24,25,1,1,1,2,2,1,1,6,29,29,3,2,2,2,2,2,2,2,2,6,2,2,2,2,2,2,2,6},
            {6,6,6,6,6,1,2,2,6,0,6,1,1,1,1,3,1,1,2,2,2,2,6,2,2,6,2,2,19,1,6},
            {6,43,1,1,1,48,2,2,6,45,6,9,1,1,1,6,46,1,1,1,2,2,2,2,2,6,6,6,3,1,6},
            {6,5,1,1,1,48,2,2,6,45,45,6,1,1,11,6,46,46,48,6,5,2,1,4,6,3,1,1,1,1,6},
            {6,1,1,6,1,46,46,47,6,3,45,6,1,1,11,6,40,1,1,2,2,2,2,1,1,1,1,1,1,13,6},
            {6,32,1,6,45,45,45,45,45,45,45,6,1,11,11,6,6,39,1,1,1,1,1,1,1,1,1,15,15,15,6},
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}
    };
    public static final int[][] map2 = {
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
            {6,15,15,1,1,1,2,2,2,2,46,47,48,46,6,45,45,31,31,45,45,45,1,1,1,10,6,42,1,33,6},
            {6,15,1,28,6,6,6,6,2,2,2,2,2,46,2,45,29,29,29,29,30,45,1,7,1,1,6,41,1,1,6},
            {6,1,1,27,6,23,24,6,2,1,5,30,2,47,2,2,45,45,45,45,30,45,1,6,6,0,6,45,45,45,6},
            {6,25,1,1,1,1,1,6,2,2,2,30,2,2,2,2,2,2,2,45,45,45,1,6,3,45,6,6,6,45,6},
            {6,24,24,25,23,25,24,6,2,6,6,6,30,2,30,1,1,1,35,34,5,1,1,6,12,45,1,6,3,45,6},
            {6,6,6,6,6,6,6,6,0,6,44,43,6,2,6,1,10,1,34,10,1,1,1,6,12,45,1,6,45,45,6},
            {6,35,34,36,37,1,1,6,45,1,1,1,6,1,6,1,10,10,36,36,34,35,6,6,12,45,22,6,45,45,6},
            {6,35,36,1,1,1,1,4,45,13,1,13,6,1,6,10,10,10,35,1,1,45,45,45,1,45,21,6,6,0,6},
            {6,1,1,1,19,1,1,45,45,15,15,13,6,1,6,6,6,6,3,1,45,45,1,45,45,45,1,1,1,1,6},
            {6,36,1,1,1,1,1,45,6,6,6,6,6,1,1,30,6,11,1,45,45,1,23,1,45,6,6,6,5,1,6},
            {6,6,6,3,45,45,45,45,6,16,1,18,6,29,29,29,3,1,45,45,1,23,23,1,45,45,1,17,38,1,6},
            {6,45,45,45,45,30,29,29,6,5,1,1,1,6,11,11,1,45,45,1,26,23,24,26,1,45,45,38,36,1,6},
            {6,1,1,29,29,30,45,45,6,1,1,1,40,6,11,11,11,1,45,45,1,26,1,1,45,45,37,16,1,1,6},
            {6,32,1,45,45,45,45,45,45,45,1,1,39,9,11,11,11,11,1,45,45,1,45,45,1,37,16,18,1,18,6},
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}
    };
    public static final int[][] map3 = {
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
            {6,25,1,1,2,2,2,2,16,16,16,12,12,6,39,40,6,35,15,13,34,2,6,45,45,6,42,41,1,33,6},
            {6,23,23,24,29,29,29,2,1,1,1,12,12,6,1,1,6,34,15,15,35,2,6,45,45,45,45,45,45,45,6},
            {6,29,29,29,30,2,2,2,17,1,4,1,1,1,2,2,2,36,15,15,37,2,6,6,0,6,6,6,6,45,6},
            {6,35,37,35,6,2,2,6,1,8,6,3,14,1,2,2,2,36,37,38,36,2,2,2,2,2,2,2,6,45,6},
            {6,1,1,1,6,2,11,6,1,7,6,1,1,1,2,2,2,2,2,2,2,2,31,31,31,31,31,2,6,45,6},
            {6,1,6,1,6,2,11,6,1,9,3,1,6,5,2,2,2,45,45,45,45,2,30,23,24,24,30,2,6,45,6},
            {6,0,6,6,6,0,6,6,1,1,1,1,6,2,2,2,2,45,28,27,45,2,30,23,23,24,30,2,6,45,6},
            {6,45,45,45,45,45,45,6,5,1,1,19,6,2,2,2,2,45,28,26,45,2,30,25,24,25,30,6,6,45,6},
            {6,45,10,10,10,10,45,6,44,43,1,20,6,6,6,6,6,6,6,6,6,6,30,29,29,29,30,2,45,45,6},
            {6,45,10,18,18,10,45,6,1,1,1,2,2,2,2,2,2,2,14,14,14,6,6,6,6,2,2,2,45,22,6},
            {6,45,10,18,18,10,45,6,1,6,0,6,6,6,6,6,6,2,2,14,14,6,2,2,2,2,2,2,45,21,6},
            {6,45,10,10,10,10,45,6,6,6,45,6,10,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,1,11,6},
            {6,45,45,45,45,45,45,6,6,6,45,6,47,48,10,10,1,1,2,6,6,6,6,6,2,2,1,11,11,11,6},
            {6,32,1,45,45,45,45,45,45,45,45,6,46,46,47,48,48,1,2,2,2,6,2,2,2,1,11,11,11,11,6},
            {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}
    };
    private int start_i;

    public void loadMapFromFile(String mapFile){

    }


    int[][] rotatematrix(int[][] items) {
        int cols = items[0].length;
        int rows = items.length;
        int[][] newArray = new int[cols][rows];
        for(int i=0; i<cols; i++) {
            for(int j=0; j<rows; j++) {
                newArray[i][j] = items[rows - 1 - j][i];
            }
        }
        return newArray;
    }

    public void loadMapFromArray(int[][] items){
        this.mapArray = rotatematrix(items);
        int rows = items.length, cols = items[0].length;
        ItemTileSetType[][] itemTypes = new ItemTileSetType[cols][rows];
        ItemTileSetType[] mapping = {
                ItemTileSetType.GATE,
                ItemTileSetType.GROUND_GRASS,
                ItemTileSetType.GROUND,
                ItemTileSetType.WALL_1,
                ItemTileSetType.WALL_2,
                ItemTileSetType.WALL_3,
                ItemTileSetType.WALL_4,
                ItemTileSetType.WALL_5,
                ItemTileSetType.WALL_6,
                ItemTileSetType.WALL_7,
                ItemTileSetType.TREE_DUMMY,
                ItemTileSetType.TREE_CROWNS_1,
                ItemTileSetType.TREE_CROWNS_2,
                ItemTileSetType.TREE_CROWNS_3,
                ItemTileSetType.TREE_1,
                ItemTileSetType.TREE_2,
                ItemTileSetType.TREE_3,
                ItemTileSetType.TREE_4,
                ItemTileSetType.TREE_5,
                ItemTileSetType.TREE_STUBS_1,
                ItemTileSetType.TREE_STUBS_2,
                ItemTileSetType.TREE_STUBS_3,
                ItemTileSetType.TREE_STUBS_4,
                ItemTileSetType.BUSHES_BANANAS,
                ItemTileSetType.BUSHES_BANANAS_2,
                ItemTileSetType.BUSHES_BANANAS_3,
                ItemTileSetType.BUSHES_BIG_1,
                ItemTileSetType.BUSHES_BIG_2,
                ItemTileSetType.BUSHES_BIG_3,
                ItemTileSetType.RIDGE,
                ItemTileSetType.RIDGE_2,
                ItemTileSetType.RIDGE_3,
                ItemTileSetType.HOUSE_BOTTOM,
                ItemTileSetType.HOUSE_TOP,
                ItemTileSetType.CACTUSES,
                ItemTileSetType.CACTUSES_2,
                ItemTileSetType.CACTUSES_3,
                ItemTileSetType.CACTUSES_4,
                ItemTileSetType.CACTUSES_BIG,
                ItemTileSetType.WELL,
                ItemTileSetType.WELL_BAIL,
                ItemTileSetType.TELEGA_BRICKS_1,
                ItemTileSetType.TELEGA_BRICKS_2,
                ItemTileSetType.TELEGA_BRICKS_3,
                ItemTileSetType.TELEGA_BRICKS_4,
                ItemTileSetType.TRAILS,
                ItemTileSetType.WOOD_WALLS_1,
                ItemTileSetType.WOOD_WALLS_2,
                ItemTileSetType.WOOD_WALLS_3
        };
        for(int i = 0; i < cols; i++){
            for (int j = 0; j < rows; j++){
                itemTypes[i][j] = mapping[items[rows - 1 - j][i]];
            }
        }
        loadMapFromArray(itemTypes);
    }

    public void loadMapFromArray(ItemTileSetType[][] itemTypes){
        items = new TileSet[itemTypes.length][itemTypes[0].length];
        for(int i = 0; i < itemTypes.length; i++){
            for (int j = 0; j < itemTypes[0].length; j++){
                items[i][j] = new TileSet(itemTypes[i][j]);
            }
        }
        this.mapWidth = itemTypes.length * this.cellSize;
        this.mapHeight = itemTypes[0].length * this.cellSize;
    }

    public void draw(SpriteBatch batch){
        for(int i = 0; i < items.length; i++){
            for (int j = 0; j < items[0].length; j++){
                // luon ve GROUND truoc o duoi moi tile
                ground.setPosition(i * cellSize, j * cellSize);
                ground.draw(batch);
                if (this.mapArray[i][j] != 1 ) { // neu khong phai GROUND moi can ve
                    Sprite sprite = items[i][j].getSprite();
                    sprite.setPosition(i * cellSize, j * cellSize);
                    sprite.setSize(this.cellSize, this.cellSize);
                    sprite.draw(batch);
                }
            }
        }
    }

    public boolean hitAWall(float x, float y, int radius, int reduceR){
        // kiem tra nhan vat tai vi tri (x, y), co ban kinh radius co cham vao tuong hay khong.
        // reduceR la phan ban kinh giam di de nhan vat co the lot qua cac khoang hep.
        float end_x = x + 2 * radius;
        float end_y = y + 2 * radius;
        int start_i = (int) ((x+reduceR) / this.cellSize);
        int end_i = (int) ((end_x-reduceR) / this.cellSize);
        int start_j = (int) ((y+reduceR) / this.cellSize);
        int end_j = (int) ((end_y-reduceR) / this.cellSize);
        for (int i = start_i; i <= end_i; i++){
            for (int j = start_j; j <= end_j; j++){
                if (i < 0 || j < 0 || i >= this.mapArray.length || j >= this.mapArray[0].length){
                    return true;
                }
                if (this.mapArray[i][j] != 2
                        && this.mapArray[i][j] !=0
                        && this.mapArray[i][j] !=1
                        && this.mapArray[i][j] !=10
                        && this.mapArray[i][j] !=11
                        && this.mapArray[i][j] !=12
                        && this.mapArray[i][j] !=13
                        && this.mapArray[i][j] !=14
                        && this.mapArray[i][j] !=15
                        && this.mapArray[i][j] !=32
                        && this.mapArray[i][j] !=33
                        && this.mapArray[i][j] !=45){ // khong di duoc vao tile khac voi GROUND, GATE,...
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkChangeMap(float x, float y, int radius, int reduceR){
        float end_x = x + 2 * radius;
        float end_y = y + 2 * radius;
        int start_i = (int) ((x+reduceR) / this.cellSize);
        int end_i = (int) ((end_x-reduceR) / this.cellSize);
        int start_j = (int) ((y+reduceR) / this.cellSize);
        int end_j = (int) ((end_y-reduceR) / this.cellSize);
        for (int i = start_i; i <= end_i; i++){
            for (int j = start_j; j <= end_j; j++) {
                //System.out.println(this.mapArray[i][j]);
                if (this.mapArray[i][j] == 33) {
                    return true;
                }
            }
        }
        return false;
    }

    public Vector2 randomEnemy(){
        Random r = new Random();
        int i = 0;
        int j = 0;
        do{
            i = r.nextInt(mapArray.length);
            j = r.nextInt(mapArray[0].length);
        } while (this.mapArray[i][j] != 2
                && this.mapArray[i][j] !=0
                && this.mapArray[i][j] !=1
                && this.mapArray[i][j] !=10
                && this.mapArray[i][j] !=11
                && this.mapArray[i][j] !=12
                && this.mapArray[i][j] !=13
                && this.mapArray[i][j] !=14
                && this.mapArray[i][j] !=15
                && this.mapArray[i][j] !=32
                && this.mapArray[i][j] !=33
                && this.mapArray[i][j] !=45);

        Vector2 position = new Vector2(i*this.cellSize, j*this.cellSize);
//        System.out.println("Position " + i + " " + j);
//        System.out.println(mapArray.length);
//        System.out.println(mapArray[0].length);
        return position;
    }

    public TileSet[][] getItems() {
        return items;
    }

    public void setItems(TileSet[][] items) {
        this.items = items;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getMapWidth() { return mapWidth; }

    public int getMapHeight() { return mapHeight; }
}
