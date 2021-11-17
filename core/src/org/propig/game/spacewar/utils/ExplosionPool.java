package org.propig.game.spacewar.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.explosion.Explosion;


public class ExplosionPool extends Pool<Explosion> {
    Stage s;
    public ExplosionPool(int initialCapacity, int max, Stage s) {
        super(initialCapacity, max);
        this.s = s;
        fill(initialCapacity);
    }

    public ExplosionPool() {
        super();
    }

    public Stage getS() {
        return s;
    }

    @Override
    public Explosion obtain() {
        Explosion e = super.obtain();
        s.addActor(e);
        e.setVisible(true);
        e.setAnimation(Resources.getInstance().explosionAsset);
        return e;
    }

    public void setS(Stage s) {
        this.s = s;
    }

    @Override
    protected Explosion newObject() {
        Explosion explosion = new Explosion(0,0, s, this);
        explosion.remove();
        explosion.setVisible(false);
        return explosion;
    }

    @Override
    public void free(Explosion object) {
        super.free(object);
    }
}
