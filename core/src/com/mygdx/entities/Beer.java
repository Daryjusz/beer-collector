package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class Beer extends Image {

    public final static int WIDTH = MyGdxGame.WIDTH/7;
    public final static int HEIGHT = MyGdxGame.HEIGHT/10;

    public int speed = 0 ;
    Rectangle bounds;

    public Beer(){
        super(new Texture("badlogic.jpg"));

        this.setOrigin(WIDTH/2, HEIGHT/2);
        this.setSize(WIDTH, HEIGHT);

        int x = MathUtils.random(0, MyGdxGame.WIDTH-100);
        this.setPosition(x, MyGdxGame.HEIGHT - 100);

        int speed = MathUtils.random(100, 500);
        this.speed = speed;
    }

    public Rectangle getBounds() {
       return bounds = new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }
}
