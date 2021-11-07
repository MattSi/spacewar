package org.propig.game.spacewar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends BaseScreen{

    @Override
    protected void initialize() {
        BaseActor bg = new BaseActor(0,0,mainStage);
        bg.loadTexture("spacewar/space.png");

        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture("spacewar/title.png");

        TextButton startButton = new TextButton("闯关模式", BaseGame.textButtonStyle);
        uiStage.addActor(startButton);

        TextButton optionButton = new TextButton("Options", BaseGame.textButtonStyle);
        uiStage.addActor(optionButton);

        TextButton quitButton = new TextButton("退出", BaseGame.textButtonStyle);
        uiStage.addActor(quitButton);

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.row();
        uiTable.add(optionButton);
        uiTable.row();
        uiTable.add(quitButton);
        uiTable.row();


        startButton.addListener((Event e) ->{
            if (!(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown))
                return false;
            SpaceWarGame.setActiveScreen(new LevelScreen());
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

    }

}
