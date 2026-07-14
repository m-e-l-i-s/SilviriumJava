package content;


 
import arc.util.Time;
import mindustry.entities.units.StatusEntry;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
//import mindustry.world.meta.Stat;

public class SLStatusEffects {
    public static StatusEffect
    disrupted, rush;

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
        }
    
        };

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
            super.update(unit, entry);
            
        }
    
        };
    }
}

//*/