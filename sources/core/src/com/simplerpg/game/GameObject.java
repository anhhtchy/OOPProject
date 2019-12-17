package com.simplerpg.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.simplerpg.game.animation.AnimationController;
import com.simplerpg.game.collision.Collider;

public abstract class GameObject {
    protected String name;
    public Vector2 position;
    protected float rotation;
    protected Vector2 scale;
    protected Sprite sprite;
    protected AnimationController animationController;
    protected Collider collider;
    public GameObject(){

    }

    public GameObject(String name, Vector2 position, float rotation, Vector2 scale, Sprite sprite, AnimationController animationController) {
        this.name = name;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.sprite = sprite;
        this.animationController = animationController;
    }

    public abstract void update();
    public abstract void draw(SpriteBatch batch);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public AnimationController getAnimationController() {
        return animationController;
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
    }
}
