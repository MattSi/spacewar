package org.propig.game.spacewar.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.unit.Explosion;

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
        return e;
    }

    public void setS(Stage s) {
        this.s = s;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(0,0, s, this);
    }

    @Override
    public void free(Explosion object) {
        super.free(object);
    }
}
