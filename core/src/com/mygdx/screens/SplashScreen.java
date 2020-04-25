package com.mygdx.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameObject;
import com.mygdx.game.MyGdxGame;

import com.badlogic.gdx.graphics.Texture;

public class SplashScreen extends AbstractScreen{

    private Texture splashImg;
    private GameObject gameObject;

    public SplashScreen(final MyGdxGame game) {
        super(game);
        init();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                System.out.println("After 1 second");
                game.setScreen(new GamePlayScreen(game));
            }
        },1 );
    }

    private void init() {
        System.out.println("Initialization");
        // TODO implement better assets loading when game grows
        splashImg = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(splashImg, MyGdxGame.WIDTH/3 , MyGdxGame.HEIGHT/3);
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


}
