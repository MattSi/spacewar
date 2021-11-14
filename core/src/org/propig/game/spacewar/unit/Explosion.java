package org.propig.game.spacewar.unit;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool.Poolable;
import org.propig.game.spacewar.utils.ExplosionPool;

public class Explosion extends BaseActor implements Poolable {
    ExplosionPool pool;
    public Explosion(float x, float y, Stage s, ExplosionPool pool) {
        super(x, y, s);
        setScale(0.5f);
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

    @Override
    public void reset() {
        animation = null;
        elapsedTime = 0;
        setVisible(false);
    }
}
