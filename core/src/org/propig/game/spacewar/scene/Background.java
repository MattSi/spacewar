package org.propig.game.spacewar.scene;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.propig.game.spacewar.BaseActor;

public class Background extends BaseActor {

    public Background(float x, float y, Stage s) {
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
