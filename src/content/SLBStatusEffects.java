package content;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.entities.Units;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class SLBStatusEffects {
    public static StatusEffect
            disrupted, deathTimer;

    public static void load() {
        disrupted = new StatusEffect("disrupted"){{
            reloadMultiplier = 0.7f;
            speedMultiplier = 0.7f;
            damageMultiplier = 0.7f;
            buildSpeedMultiplier = 0.7f;
            healthMultiplier = 0.7f;
            color = SLBPal.silviriumColor;
            applyColor = SLBPal.silviriumColor;
            outline = true;
            effect = new ParticleEffect() {{
                particles = 2;
                length = 1;
                baseLength = 3;
                colorFrom = SLBPal.silviriumColor;
                colorTo = SLBPal.silviriumColor;
                sizeFrom = 3;
                sizeTo = 0;
            }};
        }
        @Override
        public void update(Unit unit, float time){
            super.update(unit, time);
            Units.nearby(unit.team, unit.x, unit.y, unit.range()/2, e -> {
                if(!e.isImmune(disrupted) && !e.hasEffect(disrupted)){
                    e.apply(disrupted, time * 0.5f);
                }else if(!e.isImmune(disrupted)){
                    e.rotation += Mathf.random(-5, 5) * Time.delta;
                }
            });
        }
    };
    deathTimer = new StatusEffect("DeathTimer"){
        @Override
        public void update(Unit unit, float time){
            if(time <= 0){
                unit.kill();
            }
            super.update(unit, time);
        };
    };
    }
}
