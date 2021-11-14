package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;
import org.propig.game.spacewar.utils.EnemyBulletPool;
import org.propig.game.spacewar.utils.Resources;

public class EnemyCraft2 extends Enemy{
    public EnemyCraft2(float x, float y, Stage s,  boolean isCircle) {
        super(x, y, s,  isCircle);
        loadTexture("spacewar/ships/ship_0005.png");

        setSpeed(100);
        setMaxSpeed(100);
        setAcceleration(150);
        setDeceleration(0);
        setRotation(initRotation);
        setScale(2.f);
        health = 15;
        damage = 18;

        setMotionAngle(initMotionAngle);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if(elapsedTime < 3.f)
            return;

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
        EnemyBullet b =  EnemyBulletPool.getInstance().obtain(); //new EnemyBullet(getX(),getY(),s, 300, 8, "spacewar/tiles/tile_0003.png");
        b.setSpeed(300);
        b.setMaxSpeed(300);
        b.setDeceleration(0);
        b.setAcceleration(300);
        b.setPosition(getX(), getY());
        b.centerAtActor(this);
        b.setDirection(false, 180);
        b.setAnimation(Resources.getInstance().enemyCraft2Bullet);
        b.alive=true;
    }

}
