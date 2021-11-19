package org.propig.game.spacewar.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.hero.Laser;

public class LaserPool extends Pool<Laser> {
    private static LaserPool instance;
    public static Stage stage;

    public static LaserPool getInstance(){
        if(instance == null){
            instance = new LaserPool(50, 80);
        }

        return instance;
    }

    private LaserPool(int initialCapacity, int max) {
        super(initialCapacity, max);
        fill(initialCapacity);
    }

    @Override
    public Laser obtain() {
        Laser e = super.obtain();
        e.setAnimation(Resources.getInstance().laser);
        stage.addActor(e);
        return e;
    }


    @Override
    protected Laser newObject() {
        Laser e = new Laser(0,0,stage);
        e.remove();
        e.setVisible(true);
        return e;
    }
}
