package com.simplerpg.game.tilemap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
// import sun.nio.cs.ext.TIS_620;

public class TileSet {
    private Texture texture;
    private int spriteIndex;
    private int spriteWidth;
    private int spriteHeight;
    private Sprite sprite;

    public TileSet(){

    }

    public TileSet(ItemTileSetType itemType){
        switch (itemType){
            case GATE:
                texture = new Texture("tilesets/gates.png");
                sprite = new Sprite(texture, 12, 12, 85, 60);
                break;
            case GROUND_GRASS:
                texture = new Texture("tilesets/ground_grass.png");
                sprite = new Sprite(texture, 0, 0, 30, 30);
                break;
            case GROUND:
                texture = new Texture("tilesets/ground.png");
                sprite = new Sprite(texture, 0, 0, 30, 30);
                break;
            case WALL_1:
                texture = new Texture("tilesets/wall_1.png");
                sprite = new Sprite(texture, 7, 15, 35, 35);
                break;
            case WALL_2:
                texture = new Texture("tilesets/wall_2.png");
                sprite = new Sprite(texture,0 , 10, 40, 40);
                break;
            case WALL_3:
                texture = new Texture("tilesets/wall_3.png");
                sprite = new Sprite(texture,2 , 12, 32, 37);
                break;
            case WALL_4:
                texture = new Texture("tilesets/walls.png");
                sprite = new Sprite(texture, 9, 11, 28, 33);
                break;
            case WALL_5:
                texture = new Texture("tilesets/wall_5.png");
                sprite = new Sprite(texture,4 , 14, 33, 37);
                break;
            case WALL_6:
                texture = new Texture("tilesets/wall_6.png");
                sprite = new Sprite(texture, 0, 16, 35, 35);
                break;
            case WALL_7:
                texture = new Texture("tilesets/wall_7.png");
                sprite = new Sprite(texture,3 , 10, 34, 45);
                break;
            case TREE_DUMMY:
                texture = new Texture("tilesets/tree_dummy.png");
                sprite = new Sprite(texture, 0, 0, 105, 89);
                // (0, 0) la vi tri cua tile trong file tree_dummy.org
                // (105, 89) la chieu dai, chieu rong cua tile.
                break;
            case TREE_CROWNS_1:
                texture = new Texture("tilesets/tree_crowns_1.png");
                sprite = new Sprite(texture, 0, 9, 102, 75);
                break;
            case TREE_CROWNS_2:
                texture = new Texture("tilesets/tree_crowns_2.png");
                sprite = new Sprite(texture, 0, 2, 103, 87);
                break;
            case TREE_CROWNS_3:
                texture = new Texture("tilesets/tree_crowns_3.png");
                sprite = new Sprite(texture, 0, 0, 105, 89);
                break;
            case TREE_1:
                texture = new Texture("tilesets/tree_1.png");
                sprite = new Sprite(texture, 0, 7, 102, 89);
                break;
            case TREE_2:
                texture = new Texture("tilesets/tree_2.png");
                sprite = new Sprite(texture, 4, 9, 93, 79);
                break;
            case TREE_3:
                texture = new Texture("tilesets/tree_3.png");
                sprite = new Sprite(texture, 10, 10, 85, 79);
                break;
            case TREE_4:
                texture = new Texture("tilesets/tree_4.png");
                sprite = new Sprite(texture, 0, 5, 95, 80);
                break;
            case TREE_5:
                texture = new Texture("tilesets/tree_5.png");
                sprite = new Sprite(texture, 12, 0, 90, 89);
                break;
            case TREE_STUBS_1:
                texture = new Texture("tilesets/tree_stubs_1.png");
                sprite = new Sprite(texture, 0, 0, 50, 70);
                break;
            case TREE_STUBS_2:
                texture = new Texture("tilesets/tree_stubs_2.png");
                sprite = new Sprite(texture, 0, 0, 40, 60);
                break;
            case TREE_STUBS_3:
                texture = new Texture("tilesets/tree_stubs_3.png");
                sprite = new Sprite(texture, 0, 0, 45, 65);
                break;
            case TREE_STUBS_4:
                texture = new Texture("tilesets/tree_stubs_4.png");
                sprite = new Sprite(texture, 0, 0, 40, 60);
                break;
            case BUSHES_BANANAS:
                texture = new Texture("tilesets/bushes_bananas.png");
                sprite = new Sprite(texture, 5, 1, 54, 56);
                break;
            case BUSHES_BANANAS_2:
                texture = new Texture("tilesets/bushes_bananas_2.png");
                sprite = new Sprite(texture, 0, 1, 54, 56);
                break;
            case BUSHES_BANANAS_3:
                texture = new Texture("tilesets/bushes_bananas_3.png");
                sprite = new Sprite(texture, 0, 0, 54, 56);
                break;
            case BUSHES_BIG_1:
                texture = new Texture("tilesets/bushes_big_1.png");
                sprite = new Sprite(texture, 0, 0, 46, 50);
                break;
            case BUSHES_BIG_2:
                texture = new Texture("tilesets/bushes_big_2.png");
                sprite = new Sprite(texture, 0, 2, 45, 53);
                break;
            case BUSHES_BIG_3:
                texture = new Texture("tilesets/bushes_big_3.png");
                sprite = new Sprite(texture, 0, 0, 45, 50);
                break;
            case RIDGE:
                texture = new Texture("tilesets/ridge.png");
                sprite = new Sprite(texture, 70, 3, 69, 57);
                break;
            case RIDGE_2:
                texture = new Texture("tilesets/ridge_2.png");
                sprite = new Sprite(texture, 0, 5, 55, 57);
                break;
            case RIDGE_3:
                texture = new Texture("tilesets/ridge_3.png");
                sprite = new Sprite(texture, 0, 8, 69, 57);
                break;
            case HOUSE_BOTTOM:
                texture = new Texture("tilesets/house_bottom.png");
                sprite = new Sprite(texture, 18, 30, 111, 112);
                break;
            case HOUSE_TOP:
                texture = new Texture("tilesets/house_top.png");
                sprite = new Sprite(texture, 16, -1, 114, 112);
                break;
            case CACTUSES:
                texture = new Texture("tilesets/cactuses.png");
                sprite = new Sprite(texture, 0, 4, 42, 36);
                break;
            case CACTUSES_2:
                texture = new Texture("tilesets/cactuses_2.png");
                sprite = new Sprite(texture, 3, 3, 41, 41);
                break;
            case CACTUSES_3:
                texture = new Texture("tilesets/cactuses_3.png");
                sprite = new Sprite(texture, 0, 3, 41, 44);
                break;
            case CACTUSES_4:
                texture = new Texture("tilesets/cactuses_4.png");
                sprite = new Sprite(texture, 5, 4, 43, 36);
                break;
            case CACTUSES_BIG:
                texture = new Texture("tilesets/cactuses_big.png");
                sprite = new Sprite(texture, 5, 7, 73, 65);
                break;
            case WELL:
                texture = new Texture("tilesets/well.png");
                sprite = new Sprite(texture, 11, 0, 54, 56);
                break;
            case WELL_BAIL:
                texture = new Texture("tilesets/well_bail.png");
                sprite = new Sprite(texture, 30, 0, 31, 30);
                break;
            case TELEGA_BRICKS_1:
                texture = new Texture("tilesets/telega_bricks_1.png");
                sprite = new Sprite(texture, 0, 0, 51, 50);
                break;
            case TELEGA_BRICKS_2:
                texture = new Texture("tilesets/telega_bricks_2.png");
                sprite = new Sprite(texture, 0, 0, 51, 50);
                break;
            case TELEGA_BRICKS_3:
                texture = new Texture("tilesets/telega_bricks_3.png");
                sprite = new Sprite(texture, 0, 1, 51, 50);
                break;
            case TELEGA_BRICKS_4:
                texture = new Texture("tilesets/telega_bricks_4.png");
                sprite = new Sprite(texture, 0, 0, 52, 50);
                break;
            case TRAILS:
                texture = new Texture("tilesets/trails.png");
                sprite = new Sprite(texture, 25, 6, 38, 21);
                break;
            case WOOD_WALLS_1:
                texture = new Texture("tilesets/wood_walls_1.png");
                sprite = new Sprite(texture, 0, 2, 43, 47);
                break;
            case WOOD_WALLS_2:
                texture = new Texture("tilesets/wood_walls_2.png");
                sprite = new Sprite(texture, 0, 0, 41, 48);
                break;
            case WOOD_WALLS_3:
                texture = new Texture("tilesets/wood_walls_3.png");
                sprite = new Sprite(texture, 2, 0, 42, 48);
                break;
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getSpriteIndex() {
        return spriteIndex;
    }

    public void setSpriteIndex(int spriteIndex) {
        this.spriteIndex = spriteIndex;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}

