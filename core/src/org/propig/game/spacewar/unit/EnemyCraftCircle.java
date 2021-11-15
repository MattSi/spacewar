package org.propig.game.spacewar.unit;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.propig.game.spacewar.utils.EnemyCraft1Pool;
import org.propig.game.spacewar.utils.EnemyCraft2Pool;

public class EnemyCraftCircle extends BaseActor{
    Enemy.EnemyKind  craftKind;
    int craftNumber;
    float timeInterval = 0f;
    float period = 0.8f;
    int x = 0;
    public EnemyCraftCircle(float x, float y, Stage s, Enemy.EnemyKind craftKind, int craftNumber) {
        super(x, y, s);

        this.craftKind = craftKind;
        this.craftNumber = craftNumber;
        this.x = MathUtils.random(60, 300);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(craftNumber <= 0){
            this.remove();
            return;
        }
        timeInterval += dt;
        if(timeInterval > period){
            if(craftKind == Enemy.EnemyKind.EnemyCraft1){
                EnemyCraft1Pool enemyCraft1Pool = EnemyCraft1Pool.getInstance();
                EnemyCraft1 e1 = enemyCraft1Pool.obtain();
                e1.setPosition(x, 750);
                e1.setCircle(true);

            } else if (craftKind == Enemy.EnemyKind.EnemyCraft2){
                EnemyCraft2Pool enemyCraft2Pool = EnemyCraft2Pool.getInstance();
                EnemyCraft2 e2 = enemyCraft2Pool.obtain();
                e2.setPosition(x, 750);
                e2.setCircle(true);
            } else {
                ;
            }
            timeInterval -= period;
            craftNumber --;
        }
    }
}
