package com.mygdx.controllers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.entities.Beer;
import com.mygdx.game.MyGdxGame;


public class BeerController {

    private int randomTime;

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
        }, randomTime, randomTime);
    }

    private void randomizeSpawnTime() {
        randomTime = MathUtils.random(1, 2);
    }

    public void beerFalling(Stage stage) {
        int speed = MathUtils.random(100, 500);
        for (Actor actor : stage.getActors().items) {
            if (actor instanceof Beer) {
                ((Beer) actor).falling(speed);
            }
        }
    }

}
