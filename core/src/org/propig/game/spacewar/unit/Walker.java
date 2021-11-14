package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Walker extends BaseActor{
    public Walker(float x, float y, Stage s) {
        super(x, y, s);
        loadAnimationFromSheet("sprite-animation4.png", 5, 6, 0.04f, true);
    }

    @Override
    public void act(float dt) {
        super.act(dt);

    }
}
