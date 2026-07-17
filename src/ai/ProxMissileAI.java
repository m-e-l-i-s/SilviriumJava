package ai;

import arc.util.*;
import mindustry.ai.types.MissileAI;
import mindustry.gen.*;

public class ProxMissileAI extends MissileAI{
    public @Nullable Unit shooter;


    @Override
    public void updateMovement(){
        unloadPayloads();


        if(shooter != null && !shooter.dead()){
            unit.lookAt(shooter.aimX, shooter.aimY);
        }else unit.kill();

        //move forward forever
        unit.moveAt(vec.trns(unit.rotation, unit.speed()));

        if(unit instanceof TimedKillUnit t){ 
            t.time=0;
        }

        if(shooter == null || (shooter != null && shooter.dst(unit.x,unit.y) > shooter.range())){
            unit.lookAt(0, 0);
        }
    }

    @Override
    public boolean retarget(){
        return timer.get(timerTarget, 4f);
    }
}
