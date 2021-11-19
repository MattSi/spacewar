package org.propig.game.spacewar.scene;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.propig.game.spacewar.BaseActor;
import org.propig.game.spacewar.BaseGame;
import org.propig.game.spacewar.SpaceWarGame;


public class ResultScreen extends BaseScreen{
    int score;
    Label startLabel;

    public ResultScreen(int score) {
        this.score = score;
    }

    @Override
    protected void initialize() {

        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture("spacewar/title.png");

        startLabel = new Label("Your Score: " + score, BaseGame.labelStyle);
        uiStage.addActor(startLabel);

        TextButton gotoMainButton = new TextButton("Main Menu", BaseGame.textButtonStyle);
        uiStage.addActor(gotoMainButton);


        TextButton quitButton = new TextButton("Exit", BaseGame.textButtonStyle);
        uiStage.addActor(quitButton);

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startLabel);
        uiTable.row();
        uiTable.add(gotoMainButton);
        uiTable.row();
        uiTable.add(quitButton);
        uiTable.row();


        gotoMainButton.addListener((com.badlogic.gdx.scenes.scene2d.Event e) ->{
            if (!(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown))
                return false;
            SpaceWarGame.setActiveScreen(new MenuScreen());
            return false;
        });

        quitButton.addListener((Event e) -> {
            if(!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(InputEvent.Type.touchDown))
                return false;
            Gdx.app.exit();
            return false;
        });
    }

    @Override
    protected void update(float delta) {
        startLabel.setText("Your Score: " + score);
    }
}
