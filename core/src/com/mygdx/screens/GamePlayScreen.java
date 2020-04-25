package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
        initBeerController();
        initLabels();
    }

    private void initLabels() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();

        scoreLabel = new Label("Score: ", style);
        scoreLabel.setPosition(30, MyGdxGame.HEIGHT - 50);

        lifeLabel = new Label("Lifes: ", style);
        lifeLabel.setPosition(30, MyGdxGame.HEIGHT - 70);

        stage.addActor(scoreLabel);
        stage.addActor(lifeLabel);
    }

    public void initBeerController() {
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
        beerFalling(stage);
        mainLoopOverBeers();
        scoreLabel.setText("Score: " + game.points());
        lifeLabel.setText("Lifes: " + player.lifes());
    }

    public void mainLoopOverBeers() {
        for (Actor beer : stage.getActors().items) {
            if (beer instanceof Beer) {
                increasePointsIfPlayerOverlapsBeerAndRemoveBeer((Beer) beer);
                subtractLifeIfBeerWasNotConsumedAndRemoveActor((Beer) beer);
            }
        }
    }

    public void subtractLifeIfBeerWasNotConsumedAndRemoveActor(Beer beer) {
        if (beer.getY() < 0) {
            player.subtractLife();
            beer.remove();
            stopPlayingIfPlayerHasLostAllLifes(player);
        }
    }

    private void increasePointsIfPlayerOverlapsBeerAndRemoveBeer(Beer beer) {
        if (player.bounds().overlaps(beer.getBounds())) {
            game.increasePoints();
            beer.remove();
        }
    }

    public void stopPlayingIfPlayerHasLostAllLifes(Player player) {
        if (player.lifes() == 0) {
            player.setLifes(3);
            game.setPoints(0);
            game.setScreen(new EndGameScreen(game));
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

    public void beerFalling(Stage stage) {
        for (Actor actor : stage.getActors().items) {
            if (actor instanceof Beer) {
                beerController.falling((Beer) actor);
            }
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
