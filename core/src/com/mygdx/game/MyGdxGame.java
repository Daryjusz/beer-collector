package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.screens.SplashScreen;


public class MyGdxGame extends Game {

    public final static int WIDTH = 1080;
    public final static int HEIGHT = 700;

    private int points = 0;

    @Override
    public void create () {
        this.setScreen(new SplashScreen(this));
    }

    public void increasePoints(){
        points++;
    }

    public int points() {
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }
}
