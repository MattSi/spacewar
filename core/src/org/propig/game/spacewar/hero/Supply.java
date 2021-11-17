package org.propig.game.spacewar.hero;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.propig.game.spacewar.BaseActor;

public class Supply extends BaseActor {

    public SupplyType supplyType;
    public Supply(float x, float y, Stage s, SupplyType supplyType) {
        super(x, y, s);

        this.supplyType = supplyType;
        health = 80;
        if(supplyType == SupplyType.SUPPLY_Health){
            loadTexture("spacewar/tiles/tile_0024.png");
        } else if(supplyType == SupplyType.SUPPLY_Shield){
            loadTexture("spacewar/tiles/tile_0026.png");
        } else if( supplyType == SupplyType.SUPPLY_LAZERPROMOTION){
            loadTexture("spacewar/tiles/tile_0025.png");
        }

        Action pulse = Actions.sequence(
                Actions.scaleTo(2.f, 2.f, 1),
                Actions.scaleTo(3.f, 3.f, 1));

        addAction(Actions.repeat(10, pulse));
        addAction(Actions.after(Actions.fadeOut(1.5f)));
        addAction(Actions.after(Actions.removeActor()));


        setSpeed(80);
        setMaxSpeed(80);
        setAcceleration(80);
        setDeceleration(0);
        setMotionAngle(270);
    }


    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
        wrapBounceWorld();

        if(elapsedTime < 2.f){
            return;
        }

        float rndDegree = getMotionAngle() + MathUtils.random(-90, 90) * dt ;
        if(rndDegree<180 && getY()> getWorldBounds().getHeight()/3){
            rndDegree += 180;
        }
        setMotionAngle(rndDegree);
        accelerationAtAngle(rndDegree);



    }


    public enum SupplyType {
        SUPPLY_Health,
        SUPPLY_Shield,
        SUPPLY_LAZERPROMOTION;
    }
}
