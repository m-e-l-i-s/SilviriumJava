package content;

import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.Angles;
import arc.util.Time;
import mindustry.entities.Units;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class SLStatusEffects {
    public static StatusEffect
    disrupted, rush;
    
    public static void load(){
        disrupted = new StatusEffect("disrupted"){{
            hidden = false;
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
            stats.add(Stat.range, "15");
        }

        @Override
        public void update(Unit unit, float time){
            unit.aimX += Mathf.random(-5, 5) * 8 * Time.delta;
            unit.aimY += Mathf.random(-5, 5) * 8 * Time.delta;
            unit.speedMultiplier = 0.7f;
            unit.reloadMultiplier = 0.7f;
            unit.damageMultiplier = 0.7f;
            unit.buildSpeedMultiplier = 0.7f;
            unit.healthMultiplier = 0.7f;
            Units.nearby(unit.team, unit.x, unit.y, 120, e -> {
                if(!e.isImmune(disrupted) && unit.dead()){
                    SLFx.silviriumRailHit.at(e.x, e.y, Angles.angle(unit.x, unit.y, e.x, e.y));
                    Geometry.iterateLine(0f, unit.x, unit.y, e.x, e.y, 8, (x, y) -> {
                        SLFx.silvline.at(x, y, Angles.angle(unit.x, unit.y, e.x, e.y));
                    });
                    if(!e.hasEffect(disrupted)){
                        e.apply(disrupted, time *);
                    }else{
                        e.apply(disrupted, time * 2f);
                    }
                    if(unit.elevation>0) unit.unapply(disrupted);
                }
            });
            super.update(unit, time);
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
        }};    
    }
}
/*
for(int i = 0; i < unit.type.weapons.size; i++){
unit.mounts(new WeaponMount[]{new WeaponMount(unit.type.weapons.get(i-1))});
};
*/
