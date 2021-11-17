package org.propig.game.spacewar.utils;

import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.hero.Laser;

public class LaserPool extends Pool<Laser> {
    @Override
    protected Laser newObject() {
        return null;
    }
}
