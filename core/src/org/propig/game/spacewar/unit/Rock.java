package org.propig.game.spacewar.unit;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.propig.game.spacewar.unit.BaseActor;

public class Rock extends BaseActor {

    float scale=0.8f;

    public Rock(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("spacewar/rock.png");

        float random = MathUtils.random(30);

        setSpeed(80+random);
        setMaxSpeed(80 + random);
        setDeceleration(0);
        health = 20;

        setMotionAngle(MathUtils.random(225,330));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysics(delta);
        wrapBounceWorld();
    }
}
