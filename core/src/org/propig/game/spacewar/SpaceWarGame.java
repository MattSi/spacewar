package org.propig.game.spacewar;

public class SpaceWarGame extends BaseGame{
    @Override
    public void create() {
        super.create();
        setActiveScreen(new LevelScreen());
    }
}
