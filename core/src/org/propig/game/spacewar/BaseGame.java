package org.propig.game.spacewar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import org.propig.game.spacewar.scene.BaseScreen;

public abstract class BaseGame extends Game {

    /**
     * Stores reference to game; used when calling setActiveScreen method
     */
    private static BaseGame game;
    public static TextButton.TextButtonStyle textButtonStyle;
    public static Label.LabelStyle labelStyle;
    public static Label.LabelStyle debugLabelStyle;
    protected AssetManager assetManager;


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
        fontParameter.size = 12;
        fontParameter.color = Color.WHITE;

        // TODO: fix font and Style;
        BitmapFont custFont = new BitmapFont(Gdx.files.internal("spacewar/spacewar.fnt"),
                Gdx.files.internal("spacewar/spacewar.png"),false);


        Texture buttonTex = new Texture(Gdx.files.internal("spacewar/button.png"));
        NinePatch buttonPatch =new NinePatch(buttonTex, 24, 24, 24, 24);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = new NinePatchDrawable(buttonPatch);
        textButtonStyle.font = custFont;

        labelStyle = new Label.LabelStyle();
        labelStyle.font = custFont;

        debugLabelStyle = new Label.LabelStyle();
        debugLabelStyle.font = fontGenerator.generateFont(fontParameter);


    }

    public static void setActiveScreen(BaseScreen s)
    {
        game.setScreen(s);
    }
}
