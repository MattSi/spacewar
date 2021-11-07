package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Explosion extends BaseActor {
    public Explosion(float x, float y, Stage s) {
        super(x, y, s);
        loadAnimationFromSheet(
                "spacewar/explosion.png", 6,6,0.03f, false);
        setScale(0.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isAnimationFinished())
            remove();
    }
}
