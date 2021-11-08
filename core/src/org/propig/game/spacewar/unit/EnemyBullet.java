package org.propig.game.spacewar.unit;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.List;

public class EnemyBullet extends BaseActor{
    public int damage;
    public EnemyBullet(float x, float y, Stage s, float speed, int damage, String textureFileName) {
        super(x, y, s);


        loadTexture(textureFileName);

        addAction(Actions.delay(15));
        addAction(Actions.after(Actions.fadeOut(0.2f)));
        addAction(Actions.after(Actions.removeActor()));

        setSpeed(300);
        setMaxSpeed(300);
        setDeceleration(0);
        setAcceleration(300);

        setScale(1.2f);
        this.damage = damage;

        List<BaseActor> spaceship = BaseActor.getList(s, "org.propig.game.spacewar.unit.Spaceship");

        if(spaceship==null || spaceship.size()==0){
            setRotation(180);
            setMotionAngle(270);
        } else {
            float deltax = ((Spaceship)spaceship.get(0)).getX() - getX();
            float deltay = ((Spaceship)spaceship.get(0)).getY() - getY();
            float degree = MathUtils.atan2(deltay, deltax) * MathUtils.radDeg;
            setMotionAngle(degree);
            setRotation(degree - 90);
        }
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
    }
}
