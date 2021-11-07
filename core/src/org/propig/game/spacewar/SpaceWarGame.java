package org.propig.game.spacewar;

import org.propig.game.spacewar.scene.LevelScreen;
import org.propig.game.spacewar.scene.MenuScreen;


public class SpaceWarGame extends BaseGame{
    @Override
    public void create() {
        super.create();
        setActiveScreen(new LevelScreen());
    }
}
