package org.propig.game.spacewar.scene;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.propig.game.spacewar.unit.*;
import org.propig.game.spacewar.utils.EnemyCraft1Pool;
import org.propig.game.spacewar.utils.EnemyCraft2Pool;

import java.util.List;

public class ForeverLevel extends BaseActor {
    float timeSeconds = 0f;
    float period = 2.f;

    public ForeverLevel(float x, float y, Stage s) {
        super(x, y, s);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        timeSeconds += dt;
        if(timeSeconds > period){
            timeSeconds -= period;
            spwanEnemy();
        }
    }

    public void spwanEnemy(){
        int level = MathUtils.random(1, 7);
        Stage s = getStage();

        List<BaseActor> list =  BaseActor.getList(s,"org.propig.game.spacewar.unit.Enemy");
        if(list!= null  && list.size() > 40){
            return;
        }
        switch (level){
            case 1:
                EnemyCraft1Pool enemyCraft1Pool = EnemyCraft1Pool.getInstance();
                for(int i=0; i<3; i++){
                    EnemyCraft1 e = enemyCraft1Pool.obtain();
                    e.setPosition(MathUtils.random(1, 500), 800);
                    e.setCircle(false);
                }
                break;
            case 2:
                EnemyCraft2Pool enemyCraft2Pool = EnemyCraft2Pool.getInstance();
                for(int i=0; i<3; i++){
                    EnemyCraft2 e = enemyCraft2Pool.obtain();
                    e.setPosition(MathUtils.random(1, 500), 800);
                    e.setCircle(false);
                }
                break;
            case 3:
               new EnemyRock(MathUtils.random(1, 500), 800, s);
                break;
            case 4:
               new EnemyCraftCircle(0,0, s, Enemy.EnemyKind.EnemyCraft1, 10);
                break;
            case 5:
                new EnemyCraftCircle(0,0, s, Enemy.EnemyKind.EnemyCraft2, 10);

                break;
            case 6:
                new Supply(MathUtils.random(1, 500), 800, s, Supply.SupplyType.SUPPLY_Shield);
                break;
            case 7:
                new Supply(MathUtils.random(1, 500), 800, s, Supply.SupplyType.SUPPLY_LAZERPROMOTION);
                break;
            default:
                break;
        }
    }
}
