package org.propig.game.spacewar;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Rock extends BaseActor{
    float health;
    float scale=0.8f;

    public Rock(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("spacewar/rock.png");

        float random = MathUtils.random(30);

        addAction(Actions.forever(Actions.rotateBy(30 + random, 1)));
        setSpeed(50+random);
        setMaxSpeed(80 + random);
        setDeceleration(0);
        //setScale(0.5f);

        setMotionAngle(MathUtils.random(180,360));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysics(delta);
        wrapBounceWorld();
    }
}
