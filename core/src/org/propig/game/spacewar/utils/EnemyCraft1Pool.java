package org.propig.game.spacewar.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import org.propig.game.spacewar.enemy.EnemyCraft1;

public class EnemyCraft1Pool extends Pool<EnemyCraft1> {
    private static EnemyCraft1Pool instance;
    public static Stage stage;

    public static EnemyCraft1Pool getInstance(){
        if(instance == null){
            instance = new EnemyCraft1Pool(50, 100);
        }

        return instance;
    }

    private EnemyCraft1Pool(int initialCapacity, int max){
        super(initialCapacity, max);
        fill(initialCapacity);
    }

    @Override
    protected EnemyCraft1 newObject() {
        EnemyCraft1 e = new EnemyCraft1(0,0,stage, false);
        e.remove();
        e.setVisible(false);
        return e;
    }

    @Override
    public synchronized EnemyCraft1 obtain() {
        EnemyCraft1 e = super.obtain();
        stage.addActor(e);
        e.setVisible(true);
        e.setAnimation(Resources.getInstance().enemyCraft1);
        e.alive=true;
        return e;
    }
}
