package org.propig.game.spacewar.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.hero.Missile;

public class MissilePool extends Pool<Missile> {
    private static MissilePool instance;
    public static Stage stage;

    public static MissilePool getInstance(){
        if(instance == null){
            instance = new MissilePool(50, 80);
        }

        return instance;
    }

    public MissilePool(int initialCapacity, int max) {
        super(initialCapacity, max);
        fill(initialCapacity);
    }

    @Override
    public Missile obtain() {
        Missile e = super.obtain();
        e.setVisible(true);
        e.setAnimation(Resources.getInstance().missile);
        stage.addActor(e);
        return e;
    }

    @Override
    protected Missile newObject() {
        Missile e = new Missile(0,0, stage);
        e.remove();
        e.setVisible(true);
        return e;
    }
}
