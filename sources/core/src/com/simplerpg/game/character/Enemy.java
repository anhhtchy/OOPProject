package com.simplerpg.game.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.simplerpg.game.GameObject;
import com.simplerpg.game.animation.AnimationController;
import com.simplerpg.game.tilemap.TileMap;
import com.simplerpg.game.character.Difficulty;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.abs;

public class Enemy extends Characters {
    public static final String TAG = Enemy.class.getName();

    private Player player; // dung de xac dinh vi tri player
    private Random rd = new Random();
    private boolean isAbleToShoot;
    public float countdownToAttack = 1;
    private Direction directionBullet = Direction.DOWN;
    private boolean meleeAttacking = false; // quai co dang tan cong can chien nguoi choi khong?
    private int cellSize;
    static final private int MEDIUM_DEPTH = 50;
    static final private int HARD_DEPTH = 100;


    public Enemy(String name, Vector2 position, float rotation, Vector2 scale, Sprite sprite,
                 AnimationController animationController, Difficulty difficulty, Player player, TileMap tileMap,
                 boolean isAbleToShoot) {
        super(name, position, rotation, scale, sprite, animationController, tileMap, difficulty);
        this.player = player;
        this.isAbleToShoot = isAbleToShoot;

        if (difficulty == Difficulty.EASY){
            this.hp = 10;
            this.rangedDamage = 1;
            this.meleeDamage = 2;
            this.speed = 1;
        } else if (difficulty == Difficulty.MEDIUM){
            this.hp = 10;
            this.rangedDamage = 1;
            this.meleeDamage = 3;
            this.speed = 1;
        } else { // Difficulty.HARD
            this.hp = 10;
            this.rangedDamage = 2;
            this.meleeDamage = 4;
            this.speed = 2;
        }
        if (!isAbleToShoot){
            this.hp += 10;
            this.meleeDamage += 2;
            this.speed += 1;
        }
        cellSize = tileMap.getCellSize();
    }

    @Override
    public void update() {
        Direction nextMove = this.nextMove();
        if (nextMove != this.direction){
            if (nextMove != Direction.IDLE) {
                this.directionBullet = nextMove;
            }
            move(nextMove);
        }
        if (!tileMap.hitAWall(this.position.x + velocity.x, this.position.y + velocity.y, 15, 6)) {
            position.x += velocity.x;
            position.y += velocity.y;
        }
    }

    private Direction nextMove() {
        if (difficulty == Difficulty.EASY) {
            // che do EASY, bot di chuyen ngau nhien
            // trung binh 1 s doi huong 1 lan:
            int rand_int = rd.nextInt(15);
            if (rand_int == 0) {
                return Direction.getRandom();
            }
            return this.direction;
        }
//        quai chay theo heuristic best-first dung khoang cach manhattan don gian, hay dam dau vao tuong nen tam bo di
//        else if(difficulty == Difficulty.MEDIUM) {
//            Direction next = Direction.IDLE;
//            float min = Integer.MAX_VALUE;
//            if (manhattan_dist(this.position.x + this.speed, this.position.y) < min &&
//                    !tileMap.hitAWall(this.position.x + this.speed, this.position.y, 15, 6)){
//                min = manhattan_dist(this.position.x + this.speed, this.position.y);
//                next = Direction.RIGHT;
//            }
//            if (manhattan_dist(this.position.x - this.speed, this.position.y) < min &&
//                    !tileMap.hitAWall(this.position.x - this.speed, this.position.y, 15, 6)){
//                min = manhattan_dist(this.position.x - this.speed, this.position.y);
//                next = Direction.LEFT;
//            }
//            if (manhattan_dist(this.position.x, this.position.y + this.speed) < min &&
//                    !tileMap.hitAWall(this.position.x, this.position.y + this.speed, 15, 6)){
//                min = manhattan_dist(this.position.x, this.position.y + this.speed);
//                next = Direction.UP;
//            }
//            if (manhattan_dist(this.position.x, this.position.y - this.speed) < min &&
//                    !tileMap.hitAWall(this.position.x, this.position.y - this.speed, 15, 6)){
//                min = manhattan_dist(this.position.x, this.position.y - this.speed);
//                next = Direction.DOWN;
//            }
//            return next;
//        }
        else if (difficulty == Difficulty.MEDIUM) {
            // quai tim duong bang BFS + heuristic A* dung khoang cach manhattan
            // duyet truoc voi do sau MEDIUM_DEPTH (duyet truoc 50 buoc)
            // (dam bao quai se lao thang vao nguoi choi neu khoang cach la 50 buoc, neu > 50, dung heuristic de xac dinh nuoc tiep theo)
            Direction next = Direction.IDLE;
            float min = Integer.MAX_VALUE;
            float dist = bfs_dist(this.position.x + this.speed, this.position.y, MEDIUM_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x + this.speed, this.position.y, 15, 6)){
                min = dist;
                next = Direction.RIGHT;
            }
            dist = bfs_dist(this.position.x - this.speed, this.position.y, MEDIUM_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x - this.speed, this.position.y, 15, 6)){
                min = dist;
                next = Direction.LEFT;
            }
            dist = bfs_dist(this.position.x, this.position.y + this.speed, MEDIUM_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x, this.position.y + this.speed, 15, 6)){
                min = dist;
                next = Direction.UP;
            }
            dist = bfs_dist(this.position.x, this.position.y - this.speed, MEDIUM_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x, this.position.y - this.speed, 15, 6)){
                min = dist;
                next = Direction.DOWN;
            }
            return next;
        } else { // Difficulty.HARD
            // cung dung BFS nhung voi duyet truoc 160 buoc
            Direction next = Direction.IDLE;
            float min = Integer.MAX_VALUE;
            float dist = bfs_dist(this.position.x + this.speed, this.position.y, HARD_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x + this.speed, this.position.y, 15, 6)){
                min = dist;
                next = Direction.RIGHT;
            }
            dist = bfs_dist(this.position.x - this.speed, this.position.y, HARD_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x - this.speed, this.position.y, 15, 6)){
                min = dist;
                next = Direction.LEFT;
            }
            dist = bfs_dist(this.position.x, this.position.y + this.speed, HARD_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x, this.position.y + this.speed, 15, 6)){
                min = dist;
                next = Direction.UP;
            }
            dist = bfs_dist(this.position.x, this.position.y - this.speed, HARD_DEPTH);
            if (dist < min &&
                    !tileMap.hitAWall(this.position.x, this.position.y - this.speed, 15, 6)){
                min = dist;
                next = Direction.DOWN;
            }
            return next;
        }
    }

    private float manhattan_dist(float x, float y){
        // khoang cach manhattan tu (x, y) toi player
        return abs(x - this.player.getPosition().x) + abs(y - this.player.getPosition().y);
    }

    private float bfs_dist(float x, float y, int bfs_depth){
        // tra ve khoang cach ngan nhat tu (x, y) toi player (duyet bfs)
        // neu khong tim duoc (qua do sau), tra ve 1000 lan khoang cach manhattan nho nhat trong cac diem da xet
        Map<Vector2, Integer> visited = new HashMap<Vector2, Integer>();
        LinkedList<Vector2> queue = new LinkedList<Vector2>();
        visited.put(new Vector2(x, y), 0);
        queue.add(new Vector2(x, y));
        float min_dist = Integer.MAX_VALUE;
        while (queue.size() != 0) {
            Vector2 s = queue.poll();
            int depth = visited.get(s);
            float dist = manhattan_dist(s.x, s.y) + 0.01f * depth;
            min_dist = Math.min(min_dist, dist);
            if (manhattan_dist(s.x, s.y) <= cellSize){
                return depth;
            }
            if (depth == bfs_depth){
                return min_dist * 1000;
            }
            if (!tileMap.hitAWall(s.x + this.speed, s.y, 15, 6) && !visited.containsKey(new Vector2(s.x + this.speed, s.y))){
                visited.put(new Vector2(s.x + this.speed, s.y), depth + 1);
                queue.add(new Vector2(s.x + this.speed, s.y));
            }
            if (!tileMap.hitAWall(s.x - this.speed, s.y, 15, 6) && !visited.containsKey(new Vector2(s.x - this.speed, s.y))){
                visited.put(new Vector2(s.x - this.speed, s.y), depth + 1);
                queue.add(new Vector2(s.x - this.speed, s.y));
            }
            if (!tileMap.hitAWall(s.x, s.y + this.speed, 15, 6) && !visited.containsKey(new Vector2(s.x, s.y + this.speed))){
                visited.put(new Vector2(s.x, s.y + this.speed), depth + 1);
                queue.add(new Vector2(s.x, s.y + this.speed));
            }
            if (!tileMap.hitAWall(s.x, s.y - this.speed, 15, 6) && !visited.containsKey(new Vector2(s.x, s.y - this.speed))){
                visited.put(new Vector2(s.x, s.y - this.speed), depth + 1);
                queue.add(new Vector2(s.x, s.y - this.speed));
            }
        }
        return Integer.MAX_VALUE;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getDirectionBullet() {
        return directionBullet;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public boolean isAbleToShoot() {
        return isAbleToShoot;
    }

    public boolean getMeleeAttacking(){
        return meleeAttacking;
    }

    public void setMeleeAttacking(boolean meleeAttacking){
        this.meleeAttacking = meleeAttacking;
    }
}
