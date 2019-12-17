package com.simplerpg.game.collision;

import com.badlogic.gdx.math.Vector2;

public interface Collider {
    public enum Type {
        CIRCLE,
        RECTANGLE
    }
    public boolean hit(Collider tar);
    public void setPosition(Vector2 position);
}
