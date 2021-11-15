package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.utils.EnemyBulletPool;
import org.propig.game.spacewar.utils.EnemyCraft2Pool;
import org.propig.game.spacewar.utils.Resources;

public class EnemyCraft2 extends Enemy implements Pool.Poolable{
    public EnemyCraft2(float x, float y, Stage s,  boolean isCircle) {
        super(x, y, s,  isCircle);
        //loadTexture("spacewar/ships/ship_0005.png");

        setSpeed(100);
        setMaxSpeed(100);
        setAcceleration(150);
        setDeceleration(0);
        setRotation(initRotation);
        setScale(2.f);
        health = 15;
        damage = 18;

        setMotionAngle(initMotionAngle);
        enemyKind = EnemyKind.EnemyCraft2;
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if(elapsedTime < 2.f)
            return;
        if(elapsedTime > durationTime){
            EnemyCraft2Pool.getInstance().free(this);
            return;
        }

        timeInterval += dt;
        if(timeInterval > 1.9f && bulletNumber > 0){
            timeInterval -= 1.9f;
            bulletNumber--;
            shoot(dt);
        }

        if(isCircle) {
            doCicle(dt);
        }
    }

    @Override
    public void shoot(float dt){
        EnemyBullet b =  EnemyBulletPool.getInstance().obtain();
        b.setSpeed(200);
        b.setMaxSpeed(200);
        b.setDeceleration(0);
        b.setAcceleration(200);
        b.setPosition(getX(), getY());
        b.centerAtActor(this);
        b.setDirection(false, 180);
        b.setAnimation(Resources.getInstance().enemyCraft2Bullet);
        b.alive=true;
    }

    @Override
    public void reset() {
        elapsedTime = 0f;
        timeInterval=0f;
        animation = null;
        health = 15;
        alive = true;
        remove();
    }
}
