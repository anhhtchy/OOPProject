package com.simplerpg.game.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class CircleCollider implements Collider {

    private Vector2 position;
    private int radius;

    public CircleCollider(Vector2 position, int radius){
        this.position = position;
        this.radius = radius;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public boolean hit(Collider tar) {
        if(tar instanceof  RectangleCollider) return hitRectangle((RectangleCollider) tar);
        else if (tar instanceof Circle) return hitCircle((CircleCollider) tar);
        else return false;
    }

    private boolean hitRectangle(RectangleCollider tar){
        return tar.hit(this);
    }

    private boolean hitCircle(CircleCollider tar){
        float distance2 = position.dst(tar.getPosition());
        return distance2 < radius - tar.getRadius();
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
