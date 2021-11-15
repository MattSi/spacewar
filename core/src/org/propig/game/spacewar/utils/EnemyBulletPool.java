package org.propig.game.spacewar.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.unit.EnemyBullet;

public class EnemyBulletPool extends Pool<EnemyBullet> {
    private static EnemyBulletPool instance;
    public static Stage stage;


    public static EnemyBulletPool getInstance(){
        if (instance == null){
            instance = new EnemyBulletPool(50, 80);
        }

        return instance;
    }

    private EnemyBulletPool(int initialCapacity, int max) {
        super(initialCapacity, max);
        fill(initialCapacity);
    }

    @Override
    protected EnemyBullet newObject() {
        EnemyBullet e = new EnemyBullet(0,0, stage);
        e.remove();
        e.setVisible(true);
        return e;
    }

    @Override
    public EnemyBullet obtain() {
        EnemyBullet e = super.obtain();
        stage.addActor(e);
        e.setVisible(true);
        return e;
    }
}
