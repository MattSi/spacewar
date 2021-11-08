package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public abstract class Enemy extends BaseActor{
    protected final Stage s;

    int bulletNumber = 10;
    float timeInterval = 0.0f;
    float initMotionAngle = 270;
    float initRotation = 180;
    float durationTime = 15;
    float fadeInOutTime = 0.2f;
    public Enemy(float x, float y, Stage s) {
        super(x, y, s);
        this.s = s;
        addAction(Actions.delay(durationTime));
        addAction(Actions.after(Actions.fadeOut(fadeInOutTime)));
        addAction(Actions.after(Actions.removeActor()));
    }
}
