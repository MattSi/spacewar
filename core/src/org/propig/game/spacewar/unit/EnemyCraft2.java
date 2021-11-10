package org.propig.game.spacewar.unit;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.List;

public class EnemyCraft2 extends Enemy{
    public EnemyCraft2(float x, float y, Stage s, boolean isCircle) {
        super(x, y, s, isCircle);
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
        long timeDelta = System.currentTimeMillis() - spawnTime;
        applyPhysics(dt);

        if(timeDelta < 3000)
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
        EnemyBullet b =  new EnemyBullet(getX(),getY(),s, 200, 12, "spacewar/tiles/tile_0012.png");
        b.centerAtActor(this);
        b.setDirection(true, 0);
    }

}
