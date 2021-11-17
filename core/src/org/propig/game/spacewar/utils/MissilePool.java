package org.propig.game.spacewar.utils;

import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.hero.Missile;

public class MissilePool extends Pool<Missile> {
    @Override
    protected Missile newObject() {
        return null;
    }
}
