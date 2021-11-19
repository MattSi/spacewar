package org.propig.game.spacewar;

import com.badlogic.gdx.assets.AssetManager;
import org.propig.game.spacewar.scene.LevelScreen;
import org.propig.game.spacewar.scene.MenuScreen;


public class SpaceWarGame extends BaseGame{
    private AssetManager assetManager;

    @Override
    public void create() {
        super.create();

        setActiveScreen(new MenuScreen());
    }

    private void loadAssets(){

    }
}
