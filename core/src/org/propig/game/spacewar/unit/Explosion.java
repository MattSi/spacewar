package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool.Poolable;
import org.propig.game.spacewar.utils.AssetManager;
import org.propig.game.spacewar.utils.ExplosionPool;

public class Explosion extends BaseActor implements Poolable {
    ExplosionPool pool;
    public Explosion(float x, float y, Stage s, ExplosionPool pool) {
        super(x, y, s);
//        loadAnimationFromSheet(
//                "spacewar/explosion.png", 6,6,0.03f, false);
        setScale(0.5f);
        animationPaused = true;
        this.pool = pool;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isAnimationFinished()) {
            remove();
            pool.free(this);
        }
    }
    public void setAnimationPaused(boolean paused){
        animationPaused = false;
        elapsedTime = 0;
        if(animation == null) {
            setAnimation(AssetManager.explosionAsset);
        }
    }

    @Override
    public void reset() {
        animationPaused = true;
        elapsedTime = 0;
    }
}
