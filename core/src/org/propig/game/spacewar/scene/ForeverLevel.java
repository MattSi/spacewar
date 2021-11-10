package org.propig.game.spacewar.scene;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.propig.game.spacewar.unit.*;

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
        int level = MathUtils.random(1, 6);
        Stage s = getStage();

        List<BaseActor> list =  BaseActor.getList(s,"org.propig.game.spacewar.unit.Enemy");
        if(list!= null  && list.size() > 40){
            return;
        }
        switch (level){
            case 1:
                new EnemyCraft1(MathUtils.random(1, 500), 800, s, false);
                new EnemyCraft1(MathUtils.random(1, 500), 800, s, false);
                new EnemyCraft1(MathUtils.random(1, 500), 800, s, false);
                break;
            case 2:
                new EnemyCraft2(MathUtils.random(1, 500), 800, s, false);
                new EnemyCraft2(MathUtils.random(1, 500), 800, s, false);
                new EnemyCraft2(MathUtils.random(1, 500), 800, s, false);
                break;
            case 3:
                new EnemyRock(MathUtils.random(1, 500), 800, s);
                break;
            case 4:
                new EnemyCraftCircle(0,0, s, 1, 30);
                break;
            case 5:
                new EnemyCraftCircle(0,0, s, 2, 30);
                break;
            case 6:
                new Supply(MathUtils.random(1, 500), 800, s, Supply.SupplyType.SUPPLY_Shield);
                break;
            default:
                break;
        }
    }
}
