package com.simplerpg.game.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.simplerpg.game.SimpleRPGGame;
import com.simplerpg.game.animation.AnimationController;
import com.simplerpg.game.character.*;
import com.simplerpg.game.character.Difficulty;
import com.simplerpg.game.tilemap.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen, InputProcessor {
	SpriteBatch batch;
	TileMap tileMap = new TileMap();
	Player player;
	FitViewport viewport; // viewport giup map vua voi man hinh
	Difficulty difficulty;
	boolean isPause = false;
	private SimpleRPGGame parent;
	private Stage stage;
	private Random rd = new Random();
	private Vector2 mousePos = new Vector2();
	private boolean cheatIsOn = false;

	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;

	float countDownNewEnemy = 1;
	int   maxEnemy 			= 10;
	int countEnemy 			= 0;

    public GameScreen(SimpleRPGGame game, Difficulty difficulty, int[][] map){
		parent = game;
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		this.difficulty = difficulty;
		stage = new Stage(new ScreenViewport());
		batch = new SpriteBatch();
		tileMap.loadMapFromArray(map);
		Gdx.input.setInputProcessor(this);
		// fit viewport voi tilemap
		viewport = new FitViewport(tileMap.getMapWidth(), tileMap.getMapHeight());
		try {
			player = new Player("pipyaka", new Vector2(30, 30), 0.0f, new Vector2(1,1), null,
					new AnimationController("anims/player.anim"), tileMap, difficulty);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	public int getScreenWidth() {
		return viewport.getScreenWidth();
	}

	public int getScreenHeight() {
		return viewport.getScreenHeight();
	}

	/**
	 * Called when this screen becomes the current screen for a {@link Game}.
	 */
	@Override
	public void show() {
		isPause = false;
		Gdx.input.setInputProcessor(this);
	}

	/**
	 * Called when the screen should render itself.
	 *
	 * @param delta The time in seconds since the last render.
	 */
	@Override
	public void render(float delta) {
		System.out.println("Class GameScreen - FPS: " + 1/delta); // check FPS
		if(isPause == true){
			return;
		}
		if(player.isChangeMap && parent.point == parent.currentMap*maxEnemy){
			parent.changeMap();
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		viewport.apply();
		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		tileMap.draw(batch);

		countDownNewEnemy -= delta;
		if (countDownNewEnemy <= 0 && countEnemy < maxEnemy) {
			countDownNewEnemy = 5;
			int rand_int = rd.nextInt(2);
			try {
				Enemy newEnemy;
				if (rand_int == 0) {
					newEnemy = new Enemy("Spider", tileMap.randomEnemy(), 0.0f, new Vector2(1, 1), null,
							new AnimationController("anims/spider.anim"), difficulty, player, tileMap, true);
				} else {
					newEnemy = new Enemy("Slug", tileMap.randomEnemy(), 0.0f, new Vector2(1, 1), null,
							new AnimationController("anims/slug.anim"), difficulty, player, tileMap, false);
				}
				enemies.add(newEnemy);
				countEnemy += 1;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		System.out.println("Player: " + player.getHp() + "; Spider: " + spider.getHp());

		for (Enemy enemy: enemies) {
			enemy.countdownToAttack -= delta;
			if (enemy.isAbleToShoot() && enemy.countdownToAttack <= 0 && enemy.getHp() > 0) {
				enemy.countdownToAttack = 1;
				bullets.add(new Bullet(enemy.position, enemy.getDirectionBullet(), tileMap.getMapWidth(), tileMap.getMapHeight(), true));
			}
			// xu ly danh can chien, tru mau nguoi choi khi va cham voi quai:
			if (enemy.getCollisionRect().collidesWith(player.getCollisionRect())) {
				// neu quai dang tan cong can chien nguoi choi roi thi khong tan cong lai nua,
				// tranh viec hp tut theo moi khung hinh.
				if (!enemy.getMeleeAttacking()) {
					player.meleeAttackedBy(enemy);
					parent.playPainPlayerSound();
					enemy.setMeleeAttacking(true);
				}
			} else {
				enemy.setMeleeAttacking(false);
			}
		}

		ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
		ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
		for (Bullet bullet : bullets) {
			bullet.update(delta);
			if (bullet.remove) {
				bulletsToRemove.add(bullet);
			}
		}
		// xu ly danh tam xa, tru mau khi va cham voi dan:
		for (Bullet bullet : bullets) {
			for (Enemy enemy: enemies) {
				if (bullet.getIsEnemy() == false && bullet.getCollisionRect().collidesWith(enemy.getCollisionRect())) {
					bulletsToRemove.add(bullet);
					enemy.rangedAttackBy(player);
					parent.playPainEnemySound();
					if (enemy.getHp() <= 0) {
						enemiesToRemove.add(enemy);
						parent.point++;
						parent.playDeathEnemySound();
					} else {
						enemy.update();
						enemy.draw(batch);
					}
				}
				if (bullet.getIsEnemy() == true && bullet.getCollisionRect().collidesWith(player.getCollisionRect())) {
					bulletsToRemove.add(bullet);
					player.rangedAttackBy(enemy);
					parent.playPainPlayerSound();
				}
			}
		}

		bullets.removeAll(bulletsToRemove);
		enemies.removeAll(enemiesToRemove);

		for (Enemy enemy: enemies) {
			enemy.update();
			enemy.draw(batch);
		}

		if (player.getHp() > 0) {
			player.update();
			player.draw(batch);
		} else {
			parent.endGame(parent.point);
			parent.playDeathPlayerSound();
		}

		for (Bullet bullet: bullets) {
			bullet.draw(batch);
		}

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	/**
	 * Called when this screen is no longer the current screen for a {@link Game}.
	 */
	@Override
	public void hide() {

	}

	public void dispose(){
		batch.dispose();
	}


	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.UP || keycode == Input.Keys.W){
			player.move(Direction.UP);
			return true;
		} else if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
			player.move(Direction.LEFT);
			return true;
		} else if(keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
			player.move(Direction.DOWN);
			return true;
		} else if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
			player.move(Direction.RIGHT);
			return true;
		} else if (keycode == Input.Keys.ESCAPE) {
			isPause = true;
			parent.changeScreen(SimpleRPGGame.PAUSE);
			return true;
		} else if (keycode == Input.Keys.SPACE) {
			bullets.add(new Bullet(player.position, player.getDirection(), tileMap.getMapWidth(), tileMap.getMapHeight(), false));
			return true;
		} else if (keycode == Input.Keys.H){
			// bat CHEAT MODE
			cheatIsOn = true;
			player.setHp(9999);
			player.setRangedDamage(9999);
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.UP || keycode == Input.Keys.W){
			player.idle();
			return true;
		}
		else if(keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
			player.idle();
			return true;
		}
		else if(keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
			player.idle();
			return true;
		}
		else if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
			player.idle();
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// ban theo huong click chuot
		if (button == Input.Buttons.LEFT && cheatIsOn) {
			mousePos = viewport.unproject(mousePos.set(screenX, screenY));
			float x = mousePos.x - player.getPosition().x;
			float y = mousePos.y - player.getPosition().y;
			float denominator = Math.abs(x) + Math.abs(y);
			bullets.add(new Bullet(player.position, new Vector2(x / denominator, y / denominator),
					tileMap.getMapWidth(), tileMap.getMapHeight(), false));
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// ban lien hoan khi re chuot
		if (false){
			mousePos = viewport.unproject(mousePos.set(screenX, screenY));
			float x = mousePos.x - player.getPosition().x;
			float y = mousePos.y - player.getPosition().y;
			float denominator = Math.abs(x) + Math.abs(y);
			bullets.add(new Bullet(player.position, new Vector2(x / denominator, y / denominator),
					tileMap.getMapWidth(), tileMap.getMapHeight(), false));
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
