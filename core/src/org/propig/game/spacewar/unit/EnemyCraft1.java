package org.propig.game.spacewar.unit;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.List;

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
        long timeDelta = System.currentTimeMillis() - spawnTime;
        super.act(dt);
        applyPhysics(dt);

        if(timeDelta < 3000)
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
        EnemyBullet b =  new EnemyBullet(getX(),getY(),s, 300, 8, "spacewar/tiles/tile_0003.png");
        b.centerAtActor(this);
        b.setDirection(false, 180);
    }

}
