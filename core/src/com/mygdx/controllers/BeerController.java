package com.mygdx.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.entities.Beer;
import com.mygdx.game.MyGdxGame;


public class BeerController {

    private float randomTime;

    public BeerController(MyGdxGame game, Stage stage) {
        init(stage);
    }

    private void init(final Stage stage) {
        randomizeSpawnTime();
        Timer.schedule(new Timer.Task() {
            public void run() {
                Beer beer = new Beer();
                stage.addActor(beer);
                randomizeSpawnTime();
            }
        }, 0, 1);
    }

    private void randomizeSpawnTime() {
        randomTime = MathUtils.random(1);
    }

    public void falling(Beer beer) {
        beer.setY(beer.getY() - beer.speed * Gdx.graphics.getDeltaTime());
    }

}
