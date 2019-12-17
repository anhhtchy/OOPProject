package com.simplerpg.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.simplerpg.game.SimpleRPGGame;

public class EndScreen implements Screen {
    private SimpleRPGGame parent;
    private Stage stage;
    private int point;

    public EndScreen(SimpleRPGGame game, int point) {
        parent = game;
        this.point = point;
        stage = new Stage(new ScreenViewport());

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }


    /**
     * Called when this screen becomes the current screen.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
//        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        BitmapFont font = skin.getFont("font-big");
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;

        TextButton menu = new TextButton("Menu", skin);
        TextButton exit = new TextButton("Exit", skin);

        Label result, numOfKilledEnemies;
        if(point == SimpleRPGGame.WIN){
            result = new Label("You Win!", style);
            numOfKilledEnemies = new Label("You killed all enemies!", style);
        }
        else{
            result = new Label("You Lose!", style);
            numOfKilledEnemies = new Label("You killed " + point + (point < 2 ? " enemy!" : " enemies!"), style);
        }
//        result.setWrap(true);
//        numOfKilledEnemies.setWrap(true);
        table.add(result);
        table.row();
        table.add(numOfKilledEnemies);
        table.row();
        table.add(menu).width(300);
        table.row().pad(10, 0, 10, 0);
        table.add(exit).width(300);

        stage.getViewport().update(600, 400, true);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(SimpleRPGGame.MENU);
            }
        });
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
