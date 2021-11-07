package org.propig.game.spacewar;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Laser extends BaseActor {
    public Laser(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("spacewar/laser.png");

        addAction(Actions.delay(2));
        addAction(Actions.after(Actions.fadeOut(0.2f)));
        addAction(Actions.after(Actions.removeActor()));

        setSpeed(400);
        setMaxSpeed(400);
        setDeceleration(0);
        setScale(0.4f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysics(delta);
    }
}
