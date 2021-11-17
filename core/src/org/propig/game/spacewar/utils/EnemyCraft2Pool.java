package org.propig.game.spacewar.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.enemy.EnemyCraft2;

public class EnemyCraft2Pool extends Pool<EnemyCraft2> {
    private static EnemyCraft2Pool instance;
    public static Stage stage;

    public static EnemyCraft2Pool getInstance(){
        if(instance == null){
            instance = new EnemyCraft2Pool(50, 100);
        }

        return instance;
    }

    private EnemyCraft2Pool(int initialCapacity, int max){
        super(initialCapacity, max);
        fill(initialCapacity);
    }

    @Override
    protected EnemyCraft2 newObject() {
        EnemyCraft2 e = new EnemyCraft2(0,0,stage, false);
        e.remove();
        e.setVisible(false);
        return e;
    }

    @Override
    public synchronized EnemyCraft2 obtain() {
        EnemyCraft2 e = super.obtain();
        stage.addActor(e);
        e.setVisible(true);
        e.setAnimation(Resources.getInstance().enemyCraft2);
        e.alive=true;
        return e;
    }
}
