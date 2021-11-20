package org.propig.game.spacewar.hero;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.BaseActor;
import org.propig.game.spacewar.enemy.Enemy;
import org.propig.game.spacewar.utils.MissilePool;

public class Missile extends BaseActor implements Pool.Poolable {
    public float MAX_LIFETIME = 10f;
    Enemy target;
    boolean isTargeted; // 只追踪的一个目标
    public int damage;

    public Missile(float x, float y, Stage s) {
        super(x, y, s);

        setSpeed(100);
        setAcceleration(600);
        setMaxSpeed(200);
        setDeceleration(0);
        setScale(1.1f);
        target=null;
        isTargeted=false;
        damage = 20;

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
        if ( elapsedTime> MAX_LIFETIME) {
            // 自我销毁
            selfDestruct();
        }else {
            if(target == null || !target.alive){
                retarget();
            } else {
                float deltax = target.getX() - getX();
                float deltay = target.getY() - getY();
                float degree = MathUtils.atan2(deltay, deltax) * MathUtils.radDeg;
                setMotionAngle(degree);
                setRotation(degree - 90);
            }

        }



    }

    public void retarget(){
        if(getStage() == null || isTargeted){
            return;
        }
        Array<BaseActor> targetRange = new Array<>();
        for(BaseActor enemy : BaseActor.getList(getStage(),"org.propig.game.spacewar.enemy.Enemy")){
            if(!onScreen(enemy)) continue;
            float currentDistance = Vector2.dst(getX(),getY(), enemy.getX(), enemy.getY());
            if(currentDistance < 650){
                target = (Enemy) enemy;
                targetRange.add(enemy);
            }
        }

        if(targetRange.size > 0){
            target = (Enemy) targetRange.get(MathUtils.random(0, targetRange.size-1));
            isTargeted=true;
            setMaxSpeed(500);
            setSpeed(500);

        } else {
            target = null;
            setSpeed(200);
            setMaxSpeed(200);
        }
    }

    private boolean onScreen(BaseActor ba){
        return (
                (ba.getX() > 0 && ba.getX()< getWorldBounds().width) &&
                        (ba.getY() > 0 && ba.getY() < getWorldBounds().height));
    }
    public void selfDestruct(){
        MissilePool.getInstance().free(this);
    }


    @Override
    public void reset() {
        elapsedTime = 0f;
        animation = null;
        remove();
    }
}
