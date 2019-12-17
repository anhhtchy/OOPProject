package com.simplerpg.game.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.simplerpg.game.animation.AnimationController;
import com.simplerpg.game.tilemap.TileMap;
import com.simplerpg.game.character.Difficulty;

public class Player extends Characters {
    public boolean isChangeMap = false;
    public Player(String name, Vector2 position, float rotation, Vector2 scale, Sprite sprite,
                  AnimationController animationController, TileMap tileMap, Difficulty difficulty) {
        super(name, position, rotation, scale, sprite, animationController, tileMap, difficulty);
        if (difficulty == Difficulty.EASY){
            this.hp = 30;
            this.rangedDamage = 3;
            this.meleeDamage = 5;
            this.speed = 5;
        } else if (difficulty == Difficulty.MEDIUM){
            this.hp = 30;
            this.rangedDamage = 3;
            this.meleeDamage = 5;
            this.speed = 5;
        } else { // Difficulty.HARD
            this.hp = 30;
            this.rangedDamage = 3;
            this.meleeDamage = 5;
            this.speed = 6;
        }
    }
    @Override
    public void update() {
        isChangeMap = false;
        super.update();
        if(tileMap.checkChangeMap(this.position.x + velocity.x, this.position.y + velocity.y, 15, 6)){
            isChangeMap = true;
        }
    }
}
