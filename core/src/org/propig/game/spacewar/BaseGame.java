package org.propig.game.spacewar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public abstract class BaseGame extends Game {

    /**
     * Stores reference to game; used when calling setActiveScreen method
     */
    private static BaseGame game;
    public static TextButton.TextButtonStyle textButtonStyle;
    public static Label.LabelStyle labelStyle;

    /**
     * Called when game is initialized
     */
    public BaseGame(){
        game = this;
    }

    @Override
    public void create() {
        // prepare for multiple classes/stages/actors to receive discrete input
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor(im);

        // parameters for generating a custom bitmap font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("spacewar/OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 36;
        fontParameter.color = Color.WHITE;

        // TODO: fix font and Style;
        BitmapFont custFont = new BitmapFont(Gdx.files.internal("spacewar/spacewar.fnt"),
                Gdx.files.internal("spacewar/spacewar.png"),false);

        textButtonStyle = new TextButton.TextButtonStyle();
        Texture buttonTex = new Texture(Gdx.files.internal("spacewar/button.png"));
        textButtonStyle.font = custFont;

        labelStyle = new Label.LabelStyle();
        labelStyle.font = custFont;


    }

    public static void setActiveScreen(BaseScreen s)
    {
        game.setScreen(s);
    }
}
