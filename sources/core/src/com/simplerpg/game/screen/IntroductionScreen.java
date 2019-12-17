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

public class IntroductionScreen implements Screen {
    private SimpleRPGGame parent;
    private Stage stage;

    public IntroductionScreen(SimpleRPGGame game) {
        parent = game;

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
        //TextButton exit = new TextButton("Exit", skin);

        Label info0_0, info0_1, info2, info3, pause, shot;
        info0_0 = new Label("Find and kill all the enemies" , skin);
        info0_1 = new Label("then go to the yellow house at the top right corner to change map.", skin);
        info2 = new Label("There are 3 maps in each level.", skin);
        info3 = new Label("In the harder level, the enemies are smarter and stronger!", skin);
        pause= new Label("ESC: Pause", style);
        shot = new Label("Space: Shot", style);

//        result.setWrap(true);
//        numOfKilledEnemies.setWrap(true);

        table.add(info0_0);
        table.row().padBottom(10);
        table.add(info0_1);
        table.row();
        table.add(info2);
        table.row();
        table.add(info3);
        table.row();
        table.add(pause);
        table.row().padBottom(10);
        table.add(shot);
        table.row();
        table.add(menu).width(300);
        table.row().pad(10, 0, 10, 0);
        //table.add(exit).width(300);

        stage.getViewport().update(600, 400, true);

//        exit.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Gdx.app.exit();
//            }
//        });

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
