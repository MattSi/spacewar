package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.propig.game.spacewar.utils.EnemyBulletPool;

public abstract class Enemy extends BaseActor{
    protected final Stage s;
    int bulletNumber = 10;
    float timeInterval = 0.0f;
    float initMotionAngle = 270;
    float initRotation = 180;
    float durationTime = 15;
    float fadeInOutTime = 0.2f;
    public int health;
    public int damage;
    boolean isCircle;

    public Enemy(float x, float y, Stage s, boolean isCircle) {
        super(x, y, s);
        this.s = s;
        this.isCircle = isCircle;
        addAction(Actions.delay(durationTime));
        addAction(Actions.after(Actions.fadeOut(fadeInOutTime)));
        addAction(Actions.after(Actions.removeActor()));
    }

    public abstract void shoot(float dt);

    public void doCicle(float dt){
        float motionAngle = getMotionAngle();
        float rorationAngle = getRotation();
        motionAngle += 80 * dt;
        rorationAngle += 80 * dt;
        setMotionAngle(motionAngle);
        setRotation(rorationAngle);
        accelerationAtAngle(motionAngle);
    }
}
