package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class Player extends Image {

    public final static int WIDTH = MyGdxGame.WIDTH / 7;
    public final static int HEIGHT = MyGdxGame.HEIGHT / 10;

    public final static int STARTING_X = 0;
    public final static int STARTING_Y = 0;
    public static int lifes = 3;

    Rectangle bounds;

    public Player() {
        super(new Texture("badlogic.jpg"));

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(STARTING_X, STARTING_Y);
    }

    public int lifes() {
        return lifes;
    }

    public int setLifes(int lifes) {
        return this.lifes = lifes;
    }

    public void subtractLife() {
        lifes -= 1;
    }

    public Rectangle bounds() {
        return bounds = new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }


}
