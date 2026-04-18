package Entities;

import arc.math.Angles;
import arc.math.geom.Geometry;
import arc.util.Time;
import content.SLFx;
import content.SLStatusEffects;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;

public class disruptPulse extends Ability{
    public float 
    reload = 100, range = 60, time = 120,
    deathRange = 120, deathTime = 600;

    protected float cd = 0;

    public disruptPulse(){}

    public disruptPulse(float reload, float range, float time, float deathRange, float deathTime){
        
    }

    @Override
    public void update(Unit unit){
        cd += Time.delta;
        if(cd>=reload){
            cd-=reload;
            Units.nearby(unit.team, unit.x, unit.y, range, e -> {
                if(!e.isImmune(SLStatusEffects.disrupted)){
                    SLFx.silviriumRailHit.at(e.x, e.y, Angles.angle(unit.x, unit.y, e.x, e.y));
                    Geometry.iterateLine(0f, unit.x, unit.y, e.x, e.y, 8, (x, y) -> {
                        SLFx.silvline.at(x, y, Angles.angle(unit.x, unit.y, e.x, e.y));
                    });
                    if(!e.hasEffect(SLStatusEffects.disrupted)){
                        e.apply(SLStatusEffects.disrupted, time);
                    }else{
                        e.apply(SLStatusEffects.disrupted, time * 2f);
                    }
                    if(unit.elevation>0.5) unit.unapply(SLStatusEffects.disrupted);
                }
            });
        }
    }

    @Override
    public void death(Unit unit){
        Units.nearby(unit.team, unit.x, unit.y, deathRange, e -> {
            if(!e.isImmune(SLStatusEffects.disrupted)){
                SLFx.silviriumRailHit.at(e.x, e.y, Angles.angle(unit.x, unit.y, e.x, e.y));
                Geometry.iterateLine(0f, unit.x, unit.y, e.x, e.y, 8, (x, y) -> {
                    SLFx.silvline.at(x, y, Angles.angle(unit.x, unit.y, e.x, e.y));
                });
                if(!e.hasEffect(SLStatusEffects.disrupted)){
                    e.apply(SLStatusEffects.disrupted, deathTime);
                }else{
                    e.apply(SLStatusEffects.disrupted, deathTime * 2f);
                }
                if(unit.elevation>0.5) unit.unapply(SLStatusEffects.disrupted);
            }
        });
    }
}