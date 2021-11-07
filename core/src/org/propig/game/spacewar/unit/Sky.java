package org.propig.game.spacewar.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.propig.game.spacewar.unit.BaseActor;

public class Sky extends BaseActor {

    public Sky(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("spacewar/space.png");
        setSpeed(100);
        setMotionAngle(270);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if(getY() + getHeight() < 0){
            moveBy(0,  2 * 700);
        }
    }


}
