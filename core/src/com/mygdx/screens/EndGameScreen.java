package com.mygdx.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;

public class EndGameScreen extends AbstractScreen {

    private Label endGameInformationLabel;

    public EndGameScreen(MyGdxGame game) {
        super(game);
        init();
    }

    private void init() {
        initEndGameLabel();
        endGameCountingDown();
        moveToNewGameScreenInFiveSeconds();
    }

    private void initEndGameLabel() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        endGameInformationLabel = new Label("You are dead!\n New game will start in 5 seconds", style);
        endGameInformationLabel.setPosition(MyGdxGame.WIDTH / 2 - 100, MyGdxGame.HEIGHT / 2);
        stage.addActor(endGameInformationLabel);
    }

    private void moveToNewGameScreenInFiveSeconds() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GamePlayScreen(game));
            }
        }, 5);
    }

    private void endGameCountingDown() {
        Timer.schedule(new Timer.Task() {
            int i = 5;

            @Override
            public void run() {
                endGameInformationLabel.setText(
                        String.format("You are dead!\nNew game starts in %d seconds", i));
                i--;
            }
        }, 0, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
