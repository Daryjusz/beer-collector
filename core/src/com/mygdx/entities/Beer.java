package com.mygdx.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class Beer extends Image {
    public final static int WIDTH = 100;
    public final static int HEIGHT = 100;

    public final static int STARTING_X = 0;
    public final static int STARTING_Y = 0;
    public boolean isConsumed = false;
    Rectangle bounds;

    public Beer(){
        super(new Texture("badlogic.jpg"));

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        int x = MathUtils.random(0, MyGdxGame.WIDTH-100);
        this.setPosition(x, MyGdxGame.HEIGHT - 100);

    }

    public void falling(int speed) {
        this.setY(this.getY() - speed * Gdx.graphics.getDeltaTime());
    }

    public Rectangle getBounds() {
       return bounds = new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }

    public void consumed(){
        this.isConsumed = true;
    }

    public void hideElementIfConsumed(){
        if(this.isConsumed) {
            this.setPosition(-100 ,-100);
        }
    }
}
