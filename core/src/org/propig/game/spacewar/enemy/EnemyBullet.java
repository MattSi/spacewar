package org.propig.game.spacewar.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.BaseActor;
import org.propig.game.spacewar.utils.EnemyBulletPool;

import java.util.List;

public class EnemyBullet extends BaseActor implements Pool.Poolable {
    public int damage;
    private float MAX_LIFETIME = 6.0f; // 5 seconds to auto-destruct

    public EnemyBullet(float x, float y, Stage s) {
        super(x, y, s);
        setScale(1.2f);
    }

    public void setDirection(boolean target, float direction){
        if(target){
            Stage s = getStage();
            List<BaseActor> spaceship = BaseActor.getList(s, "org.propig.game.spacewar.hero.Spaceship");
            if(spaceship != null && spaceship.size()>0) {
                float deltax = (spaceship.get(0)).getX() - getX();
                float deltay = (spaceship.get(0)).getY() - getY();
                float degree = MathUtils.atan2(deltay, deltax) * MathUtils.radDeg;
                setMotionAngle(degree);
                setRotation(degree - 90);
            } else {
                setRotation(180);
                setMotionAngle(270);
            }
        } else {
            setRotation(direction);
            setMotionAngle(direction + 90);
        }
    }


    @Override
    public void act(float dt) {
        super.act(dt);
        if(elapsedTime > MAX_LIFETIME){
            EnemyBulletPool.getInstance().free(this);
        }
        applyPhysics(dt);
    }

    @Override
    public void reset() {
        elapsedTime = 0f;
        animation = null;
        remove();
    }
}
