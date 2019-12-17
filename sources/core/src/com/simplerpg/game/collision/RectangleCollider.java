package com.simplerpg.game.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class RectangleCollider implements Collider {

    private Vector2 position;
    private int width;
    private int height;

    public RectangleCollider(Vector2 position, int width, int height){
        this.position = position;
        this.width = width;
        this.height = height;
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
        float diffX = Math.abs(position.x - tar.getPosition().x);
        float diffY = Math.abs(position.y - tar.getPosition().y);
        int widt = width + tar.getWidth();
        int heit = height + tar.getHeight();
        return diffX < widt && diffY < heit;
    }

    private boolean hitCircle(CircleCollider tar){
        Vector2 p1 = new Vector2(position.x + width, position.y);
        Vector2 p2 = new Vector2(position.x - width, position.y);
        Vector2 p3 = new Vector2(position.x, position.y + height);
        Vector2 p4 = new Vector2(position.x, position.y - height);
        float minDst = Math.min(Math.min(p1.dst(tar.getPosition()), p2.dst(tar.getPosition())), Math.min(p3.dst(tar.getPosition()), p4.dst(tar.getPosition())));
        int widt = width + tar.getRadius();
        int heit = height + tar.getRadius();
        float diffX = Math.abs(position.x - tar.getPosition().x);
        float diffY = Math.abs(position.y - tar.getPosition().y);
        return minDst < tar.getRadius() || (diffX < widt && diffY < heit);
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
