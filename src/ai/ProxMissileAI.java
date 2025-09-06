package ai;

import arc.math.*;
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
        }

        //move forward forever
        unit.moveAt(vec.trns(unit.rotation, unit.speed()));

        var build = unit.buildOn();
        //kill instantly on enemy building contact
        if(build != null && build.team != unit.team && (build == target || !build.block.underBullets)){
            unit.kill();
        }
        
        if(shooter == null || (shooter != null && shooter.dst(unit.x,unit.y) > shooter.range())){
            unit.lookAt(0, 0);
        }
    }

    @Override
    public boolean retarget(){
        //more frequent retarget due to high speed. TODO won't this lag?
        return timer.get(timerTarget, 4f);
    }
}
