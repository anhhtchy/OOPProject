package com.simplerpg.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.simplerpg.game.GameObject;
import com.simplerpg.game.animation.AnimationController;
import com.simplerpg.game.collision.CollisionRect;

import java.io.IOException;

public class Bullet extends GameObject {
    public static final int SPEED       = 500;
    public static final int WIDTH       = 5;
    public static final int HEIGHT      = 5;
    public static final float MAX_DISTANCE = 400;
    private static Texture textureBulletHorizontal;
    private static Texture textureBulletVertical;
    boolean isEnemy;
    int MAP_HEIGHT, MAP_WIDTH;

    Vector2 position;
    Vector2 startPosition;

    Direction direction;
    private Vector2 velocity;
    private AnimationController animationController;
    CollisionRect rect;
    public boolean remove = false;
    private boolean isFlying = false;

    // ban dan theo 4 huong thang UP, DOWN, LEFT, RIGHT
    public Bullet (Vector2 position, Direction direction, int MAP_WIDTH, int MAP_HEIGHT, boolean isEnemy) {
        this.position = new Vector2(position.x, position.y);
        this.startPosition = new Vector2(position.x, position.y);
        this.MAP_HEIGHT = MAP_HEIGHT;
        this.MAP_WIDTH = MAP_WIDTH;
        this.direction = direction;
        this.isEnemy = isEnemy;
        this.rect = new CollisionRect(this.position, WIDTH, HEIGHT);
        if (isEnemy) {
            if (direction == Direction.UP || direction == Direction.DOWN) {
                textureBulletVertical = new Texture("bullets/bullet/bullet_02.png");
            }

            if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                textureBulletHorizontal = new Texture("bullets/bullet/bullet_01.png");
            }
        } else {
            try {
                animationController = new AnimationController("anims/brick.anim");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ban dan theo huong con tro
    public Bullet (Vector2 position, Vector2 velocity, int MAP_WIDTH, int MAP_HEIGHT, boolean isEnemy) {
        this.position = new Vector2(position.x, position.y);
        this.startPosition = new Vector2(position.x, position.y);
        this.MAP_HEIGHT = MAP_HEIGHT;
        this.MAP_WIDTH = MAP_WIDTH;
        this.isEnemy = isEnemy;
        this.rect = new CollisionRect(this.position, WIDTH, HEIGHT);
        this.velocity = velocity;
        try {
            animationController = new AnimationController("anims/brick.anim");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update (float deltaTime) {
        if (direction == null){ // ban theo huong click chuot
            position.x += SPEED * deltaTime * velocity.x;
            position.y += SPEED * deltaTime * velocity.y;
            if (!isFlying){
                animationController.play("flying");
                isFlying = true;
            }
        } else {
            if (!isEnemy && !isFlying){ // dan nguoi choi ban theo 4 huong
                animationController.play("flying");
                isFlying = true;
            }
            switch (direction) {
                case UP:
                    position.y += SPEED * deltaTime;
                    break;
                case DOWN:
                    position.y -= SPEED * deltaTime;
                    break;
                case LEFT:
                    position.x -= SPEED * deltaTime;
                    break;
                case RIGHT:
                    position.x += SPEED * deltaTime;
                    break;
                case IDLE:
                    break;
            }
        }

        if (position.y < 0 || position.y > MAP_HEIGHT
                || position.x < 0 || position.x > MAP_WIDTH
                || position.dst(startPosition) > MAX_DISTANCE) {
            remove = true;
        } else {
            rect.move(position);
        }
    }

    @Override
    public void update() {

    }

    public void draw(SpriteBatch batch) {
        if (remove) {
            return;
        }
        if (direction == null){ // dan cua player, ban theo huong click chuot
            Sprite sprite = animationController.getKeyFrame();
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        } else if (!isEnemy){ // dan cua player, ban theo 4 huong co dinh
            Sprite sprite = animationController.getKeyFrame();
            sprite.setPosition(position.x, position.y);
            sprite.draw(batch);
        } else if (direction == Direction.UP || direction == Direction.DOWN) { // dan cua quai, ban theo 4 huong co dinh
                batch.draw(textureBulletVertical, position.x, position.y);
        } else { // RIGHT, LEFT
                batch.draw(textureBulletHorizontal, position.x, position.y);
        }
    }

    public CollisionRect getCollisionRect () {
        return rect;
    }

    public  boolean getIsEnemy() {
        return isEnemy;
    }
}
