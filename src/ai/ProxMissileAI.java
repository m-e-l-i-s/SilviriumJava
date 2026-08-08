package ai;

import mindustry.ai.types.MissileAI;

public class ProxMissileAI extends MissileAI{

    @Override
    public void updateMovement(){
        unloadPayloads();

        if(shooter == null || (shooter != null && shooter.dst(unit.x,unit.y) > shooter.range())){
            unit.kill();
        }else{
            unit.lookAt(shooter.aimX, shooter.aimY);
        }

        //move forward forever
        unit.moveAt(vec.trns(unit.rotation, unit.speed()));

    }

    @Override
    public boolean retarget(){
        return timer.get(timerTarget, 4f);
    }
}
