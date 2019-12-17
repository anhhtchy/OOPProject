package com.simplerpg.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AnimationState {
    private String name;
    private Sprite[] sprites;
    private float speed = 0.7f;
    private Animation animation;
    private boolean loop;

    public AnimationState(String name, String[] spritePaths, float speed, boolean loop){
        this.name = name;
        this.speed = speed;
        this.loop = loop;
        sprites = new Sprite[spritePaths.length];
        for(int i = 0; i < spritePaths.length; i++){
            Texture texture = new Texture(spritePaths[i]);
            sprites[i] = new Sprite(texture, texture.getWidth(), texture.getHeight());
        }
        animation = new Animation(speed, sprites);
    }

    public AnimationState(String name, Sprite[] sprites, float speed, boolean loop) {
        this.name = name;
        this.sprites = sprites;
        this.speed = speed;
        this.loop = loop;
        this.animation = new Animation(speed, sprites);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setSprites(Sprite[] sprites) {
        this.sprites = sprites;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}

