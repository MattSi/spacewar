package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.propig.game.spacewar.utils.EnemyBulletPool;
import org.propig.game.spacewar.utils.Resources;

public class EnemyCraft1 extends Enemy{

    public EnemyCraft1(float x, float y, Stage s, boolean isCircle) {
        super(x, y, s, isCircle);
        loadTexture("spacewar/ships/ship_0009.png");



        setSpeed(150);
        setMaxSpeed(150);
        setAcceleration(150);
        setDeceleration(0);
        setRotation(initRotation);
        setScale(2.f);
        health = 10;
        damage = 15;
        this.isCircle = isCircle;

        setMotionAngle(initMotionAngle);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if(elapsedTime < 2.f)
            return;

        timeInterval += dt;
        if(timeInterval > 1.9f && bulletNumber > 0){
            timeInterval -=1.9f;
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
        b.setSpeed(300);
        b.setMaxSpeed(300);
        b.setDeceleration(0);
        b.setAcceleration(300);
        b.setPosition(getX(), getY());
        b.centerAtActor(this);
        b.setDirection(true, 180);
        b.setAnimation(Resources.getInstance().enemyCraft1Bullet);
        b.alive=true;
    }
}
