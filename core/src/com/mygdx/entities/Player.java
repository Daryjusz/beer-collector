package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {

    public final static int WIDTH = 50;
    public final static int HEIGHT = 50;

    public final static int STARTING_X = 0;
    public final static int STARTING_Y = 0;
    public static int lifes = 3;

    Rectangle bounds;

    public Player(){
        super(new Texture("badlogic.jpg"));

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(STARTING_X, STARTING_Y);
    }

    public Rectangle getBounds() {
        return bounds = new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }

    public int getLifes(){
        return lifes;
    }

    public void subtractLife(){
        lifes -= 1;
    }
}
