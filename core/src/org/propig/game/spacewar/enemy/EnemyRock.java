package org.propig.game.spacewar.enemy;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EnemyRock extends Enemy {

    float scale=0.8f;

    public EnemyRock(float x, float y, Stage s) {
        super(x, y, s,  false);
        loadTexture("spacewar/rock.png");

        float random = MathUtils.random(30);

        setSpeed(80+random);
        setMaxSpeed(80 + random);
        setDeceleration(0);
        health = 20;
        damage = 18;

        setMotionAngle(MathUtils.random(225,330));
        enemyKind = EnemyKind.EnemyRock;
    }

    @Override
    public void shoot(float dt) {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        applyPhysics(delta);
        wrapBounceWorld();
        if(elapsedTime > 13.0f){
            remove();
        }
    }
}
