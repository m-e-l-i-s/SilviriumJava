package content;

import arc.math.Mathf;
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
            reloadMultiplier = 0.7f;
            damageMultiplier = 0.7f;
            buildSpeedMultiplier = 0.7f;
            speedMultiplier = 0.7f;
            healthMultiplier = 0.7f;
            color = SLPal.silviriumColor;
            applyColor = SLPal.silviriumColor;
            alwaysUnlocked = outline = true;
            effect = SLFx.silvAmb;
        }

        @Override
        public void setStats(){
            super.setStats();
//            stats.add(Stat.range, "15");
        }

        @Override
        public void update(Unit unit, StatusEntry entry){
            unit.aimX += Mathf.random(-5, 5) * 8 * Time.delta;
            unit.aimY += Mathf.random(-5, 5) * 8 * Time.delta;
            unit.speedMultiplier = 0.7f;
            unit.reloadMultiplier = 0.7f;
            unit.damageMultiplier = 0.7f;
            unit.buildSpeedMultiplier = 0.7f;
            unit.healthMultiplier = 0.7f;
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
            unit.speedMultiplier = unit.speedMultiplier < 3 ? 3 : unit.speedMultiplier;
            unit.ammo = unit.ammo < 1 ? 1 : unit.ammo;
            unit.disarmed = false;
            unit.update();
            unit.drownTime = -1;
            super.update(unit, entry);
        }
    
        };
    }
}