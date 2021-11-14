package org.propig.game.spacewar.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class BaseScreen implements Screen, InputProcessor {

    protected Stage mainStage;
    protected Stage uiStage;
    protected Table uiTable;
    protected int worldWidth;
    protected int worldHeigth;


    public BaseScreen() {
        mainStage = new Stage();
        uiStage = new Stage();
        uiTable = new Table();

        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);

        worldWidth = 550;
        worldHeigth = 700;
        initialize();
    }

    protected abstract void initialize();

    protected abstract void update(float delta);


    /**
     * Gameloop:
     * 1) process input (discrete handled by listener; continuous in update)
     * 2) update game logic
     * 3) render the graphics
     * @param delta
     */
    @Override
    public void render(float delta) {

        // limit amount of time that can pass while window is being dragged
        delta = Math.min(delta, 1/30f);

        // act methods
        uiStage.act(delta);
        mainStage.act(delta);

        // defined by user
        update(delta);

        // clear the screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // draw the graphics
        mainStage.draw();
        uiStage.draw();

    }

    public void resize(int width, int height){
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    /**
     *  Called when this becomes the active screen in a Game.
     *  Set up InputMultiplexer here, in case screen is reactivated at a later time.
     */
    @Override
    public void show(){
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();

        im.addProcessor(this);
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);
    }

    @Override
    public void hide() {
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(uiStage);
        im.removeProcessor(mainStage);
    }

    /**
     * Useful for checking for touch-down events.
     * @param e
     * @return
     */
    public boolean isTouchDownEvent(Event e){
        return (e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown);
    }

    // methods required by InputProcessor interface
    public boolean keyDown(int keycode)
    {  return false;  }

    public boolean keyUp(int keycode)
    {  return false;  }

    public boolean keyTyped(char c)
    {  return false;  }

    public boolean mouseMoved(int screenX, int screenY)
    {  return false;  }

    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {  return false;  }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {  return false;  }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {  return false;  }
}
