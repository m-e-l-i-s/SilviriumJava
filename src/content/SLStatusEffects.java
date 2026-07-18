package content;


 
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.entities.units.StatusEntry;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
//import mindustry.world.meta.Stat;
import mindustry.world.meta.Stat;

public class SLStatusEffects {
    public static StatusEffect
    disrupted ,daggered, rush;

    public static void load(){
        disrupted = new StatusEffect("disrupted"){{
            reloadMultiplier = 0.8f;
            damageMultiplier = 0.8f;
            buildSpeedMultiplier = 0.8f;
            speedMultiplier = 0.8f;
            healthMultiplier = 0.8f;
            color = SLPal.silviriumColor;
            applyColor = SLPal.silviriumColor;
            alwaysUnlocked = outline = true;
            effect = SLFx.silvAmb;
        }
/*/
        @Override
        public void setStats(){
            super.setStats();
            stats.add(Stat.range, "15");
        }
//*/

        @Override
        public void update(Unit unit, StatusEntry entry){
            if(unit.isShooting()) entry.time += Time.delta;
            super.update(unit, entry);
            unit.speedMultiplier = 0.8f;
            unit.reloadMultiplier = 0.8f;
            unit.damageMultiplier = 0.8f;
            unit.buildSpeedMultiplier = 0.8f;
            unit.healthMultiplier = 0.8f;
        }};

        daggered = new StatusEffect("daggered"){

        @Override
        public void setStats(){
            stats.add(Stat.unitType, "dagger");
        }

        @Override
        public void update(Unit unit, StatusEntry entry){
            unit.clearStatuses();
            unit.type(UnitTypes.dagger);
            Fx.unitSpawn.at(unit.x,unit.x,unit.rotation,UnitTypes.dagger);
            super.update(unit, entry);
        }};

        rush = new StatusEffect("rush"){{
            alwaysUnlocked = true;
            reloadMultiplier = 0f;
            speedMultiplier = 3f;
            buildSpeedMultiplier = 0f;
            healthMultiplier = 6f;
            color = SLPal.silviriumColor;
            applyColor = SLPal.silviriumColor;
            outline = true;
            effect = SLFx.sliviriumSpark;
        }
        @Override
        public void update(Unit unit, StatusEntry entry){
            entry.speedMultiplier = unit.speedMultiplier < 3 ? (1/unit.speedMultiplier)*3 : 3;
            unit.disarmed = false;
            unit.drownTime = -1;
            unit.clearStatuses();
            unit.apply(rush,entry.time);
            super.update(unit, entry);
            
        }
    
        };
    }
}

//*/