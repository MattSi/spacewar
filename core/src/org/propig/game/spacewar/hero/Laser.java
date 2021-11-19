package org.propig.game.spacewar.hero;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.BaseActor;
import org.propig.game.spacewar.utils.LaserPool;

public class Laser extends BaseActor implements Pool.Poolable {
    public int damage;
    private float MAX_LIFETIME = 1.0f;
    public Laser(float x, float y, Stage s) {
        super(x, y, s);
        //loadTexture("spacewar/laser.png");

//        addAction(Actions.delay(1));
//        addAction(Actions.after(Actions.fadeOut(0.2f)));
//        addAction(Actions.after(Actions.removeActor()));

        setSpeed(2000);
        setAcceleration(2000);
        setMaxSpeed(2000);
        setDeceleration(0);
        setScale(0.4f);
        damage = 11;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(elapsedTime > MAX_LIFETIME){
            LaserPool.getInstance().free(this);
        }

        applyPhysics(delta);
    }

    @Override
    public void reset() {
        elapsedTime = 0f;
        animation = null;
        remove();
    }

}
