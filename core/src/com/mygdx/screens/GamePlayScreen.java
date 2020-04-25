package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.controllers.BeerController;
import com.mygdx.entities.Beer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.entities.Player;

public class GamePlayScreen extends AbstractScreen {

    private Player player;
    private BeerController beerController;
    private Label scoreLabel;
    private Label lifeLabel;


    public GamePlayScreen(MyGdxGame game) {
        super(game);
        init();
    }

    public void init() {
        initPlayer();
        initNewBeerController();
        initLabels();
    }

    private void initLabels() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();

        scoreLabel = new Label("Score: ", style);
        scoreLabel.setPosition(50, 650);

        lifeLabel = new Label("Lifes: ", style);
        lifeLabel.setPosition(50, 630);

        stage.addActor(scoreLabel);
        stage.addActor(lifeLabel);
    }

    public void initNewBeerController() {
        beerController = new BeerController(game, stage);
    }

    public void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    public void update() {
        stage.act();
        keyboardControlsHandling();
        beerController.beerFalling(stage);
        increasePointsWhenCollisionTakesPlace();
        scoreLabel.setText("Score: " + game.points());
        lifeLabel.setText("Lifes: " + player.lifes());
        subtractLifeIfBeerWasNotConsumed();
        removeBeersWhenTheyLeaveGameArea();
    }

    public void increasePointsWhenCollisionTakesPlace() {
        for (Actor actor : stage.getActors().items) {
            if (actor instanceof Beer) {
                increasePointsIfBeerIsNotConsumed((Beer) actor);
            }
        }
    }

    public void removeBeersWhenTheyLeaveGameArea() {
        for (Actor actor : stage.getActors().items) {
            if (actor instanceof Beer) {
                if (actor.getY() < 0) {
                    actor.remove();
                }
            }
        }
    }

    public void subtractLifeIfBeerWasNotConsumed() {
        for (Actor actor : stage.getActors().items) {
            if (actor instanceof Beer) {
                if (!((Beer) actor).isConsumed && actor.getY() < 0) {
                    player.subtractLife();
                    stopPlayingIfPlayerHasLostAllLifes(player);
                }
            }
        }
    }

    public void stopPlayingIfPlayerHasLostAllLifes(Player player){
        if(player.lifes() == 0){
            player.setLifes(3);
            game.setPoints(0);
            game.setScreen(new EndGameScreen(game));
        }
    }


    private void increasePointsIfBeerIsNotConsumed(Beer beer) {
        if (player.bounds().overlaps(beer.getBounds()) && !beer.isConsumed) {
            game.increasePoints();
            beer.consumed();
            beer.hideElementIfConsumed();
        }
    }

    private void keyboardControlsHandling() {
        int moveDistance = 700;
        if (Gdx.input.isKeyPressed(Input.Keys.A) && player.getX() > 0) {
            player.setX(player.getX() - moveDistance * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) & player.getX() < MyGdxGame.WIDTH - player.getWidth()) {
            player.setX(player.getX() + moveDistance * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
