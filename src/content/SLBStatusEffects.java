package content;

import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.Angles;
import arc.util.Time;
import mindustry.entities.Units;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class SLBStatusEffects {
    public static StatusEffect
    disrupted, rush;
    
    public static void load(){
        disrupted = new StatusEffect("disrupted"){{
            reloadMultiplier = 0.7f;
            speedMultiplier = 0.7f;
            damageMultiplier = 0.7f;
            buildSpeedMultiplier = 0.7f;
            healthMultiplier = 0.7f;
            color = SLBPal.silviriumColor;
            applyColor = SLBPal.silviriumColor;
            alwaysUnlocked = outline = true;
            effect = SLBFx.silvAmb;
        }
        @Override
        public void update(Unit unit, float time){
            unit.aimX += Mathf.random(-5, 5) * 8 * Time.delta;
            unit.aimY += Mathf.random(-5, 5) * 8 * Time.delta;
            Units.nearby(unit.team, unit.x, unit.y, unit.range(), e -> {
                if(!e.isImmune(disrupted) && unit.dead()){
                    if(!e.hasEffect(disrupted)){
                        SLBFx.silviriumRailHit.at(e.x, e.y, Angles.angle(unit.x, unit.y, e.x, e.y));
                        Geometry.iterateLine(0f, unit.x, unit.y, e.x, e.y, 8, (x, y) -> {
                                SLBFx.silvline.at(x, y, Angles.angle(unit.x, unit.y, e.x, e.y));
                        });
                        e.apply(disrupted, time * 1.4f);
                    }else{
                        e.apply(disrupted, time * 2f);
                    }
                    unit.unapply(disrupted);
                }
            });
            super.update(unit, time);
        }};
        rush = new StatusEffect("rush"){{
            reloadMultiplier = 0f;
            speedMultiplier = 2.5f;
            buildSpeedMultiplier = 0f;
            healthMultiplier = 5f;
            color = SLBPal.silviriumColor;
            applyColor = SLBPal.silviriumColor;
            outline = true;
            effect = new ParticleEffect(){{
                particles = 3;
                line = true;
                length = 0.8f;
                baseLength = 2;
                colorFrom = SLBPal.silviriumColor;
                colorTo = SLBPal.silviriumOtherColor;
                sizeFrom = 0;
                sizeTo = 24;
            }};
        }};
        
        }
}
/*
for(int i = 0; i < unit.type.weapons.size; i++){
unit.mounts(new WeaponMount[]{new WeaponMount(unit.type.weapons.get(i-1))});
};
*/
