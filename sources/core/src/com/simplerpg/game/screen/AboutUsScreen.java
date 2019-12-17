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

public class AboutUsScreen implements Screen {
    private SimpleRPGGame parent;
    private Stage stage;

    public AboutUsScreen(SimpleRPGGame game) {
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
        TextButton exit = new TextButton("Exit", skin);

        Label info1   = new Label("This is a simple RPG game project for OOP subject.", skin);
        Label info2   = new Label("HUST - 20191", skin);
        Label teacher = new Label("Teacher: Nguyen Manh Tuan", skin);
        Label group   = new Label("Group 13", skin);
        Label students= new Label("Students:", skin);
        Label student1= new Label("Hoang The Anh - 20172945", skin);
        Label student2= new Label("Mai Van Hoa - 20173122", skin);
        Label student3= new Label("Mai The Hung - 20173161", skin);
        Label student4= new Label("Ha Hai Phong - 20173299", skin);
        Label student5= new Label("Nguyen Huy Dinh - 20161042", skin);
//        result.setWrap(true);
//        numOfKilledEnemies.setWrap(true);

        table.add(info1);
        table.row();
        table.add(info2);
        table.row();
        table.add(teacher);
        table.row();
        table.add(group);
        table.row();
        table.add(students);
        table.row();
        table.add(student1);
        table.row();
        table.add(student2);
        table.row();
        table.add(student3);
        table.row();
        table.add(student4);
        table.row().padBottom(30);
        table.add(student5);
        table.row();

        table.add(menu).width(300);
        //table.row().pad(10, 0, 10, 0);
        //table.add(exit).width(300);

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
