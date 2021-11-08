package org.propig.game.spacewar.unit;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.List;

public class Enemy1 extends Enemy{

    public Enemy1(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("spacewar/ships/ship_0009.png");



        setSpeed(150);
        setMaxSpeed(150);
        setAcceleration(150);
        setDeceleration(0);
        setRotation(initRotation);
        setScale(2.f);
        health = 10;

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
        if(timeInterval > 2.0f && bulletNumber > 0){
            timeInterval =0.0f;
            bulletNumber--;
            shoot();
        }

        float motionAngle = getMotionAngle();
        float rorationAngle = getRotation();
        motionAngle += 90 * dt;
        rorationAngle += 90 *dt;
        setMotionAngle(motionAngle);
        setRotation(rorationAngle);
        accelerationAtAngle(motionAngle);
    }

    public void shoot(){
        EnemyBullet b =  new EnemyBullet(getX(),getY(),s, 300, 8, "spacewar/tiles/tile_0003.png");
        b.centerAtActor(this);

    }

}
