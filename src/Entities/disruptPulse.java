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
    reload = 120, range = 60, time = 120,
    deathRange = 120, deathTime = 600;

    public boolean targetsTeam, targetsEnemy, targetsTeamOnDeath, targetsEnemyOnDeath, canReapply;

    protected float cd = 0;

    public disruptPulse(){}

    public disruptPulse(float reload, float range, float time, float deathRange, float deathTime,boolean targetsTeam, boolean targetsEnemy,boolean targetsTeamOnDeath, boolean targetsEnemyOnDeath, boolean canReapply){
        this.reload = reload;
        this.range = range;
        this.time = time;
        this.deathRange = deathRange;
        this.deathTime = deathTime;
        this.targetsTeam = targetsTeam;
        this.targetsEnemy = targetsEnemy;
        this.targetsTeamOnDeath = targetsTeamOnDeath;
        this.targetsEnemyOnDeath = targetsEnemyOnDeath;
        this.canReapply = canReapply;
    }

    public disruptPulse(float deathRange, float deathTime){
        this(-1,0,0,deathRange,deathTime,false, true, false, true, true);
    }

    public disruptPulse(float reload, float range, float time){
        this(reload, range, time, 0, 0, false, true, false, true, true);
    }

    @Override
    public void update(Unit unit){
        if(reload>=0){
            cd += Time.delta;
            if(cd>=reload){
                cd-=reload;
                Units.nearby(null, unit.x, unit.y, range, e -> {
                    if(!e.isImmune(SLStatusEffects.disrupted) && ((targetsEnemy && e.team() != unit.team()) || (targetsTeam && e.team() == unit.team()))){
                        SLFx.silviriumRailHit.at(e.x, e.y, Angles.angle(unit.x, unit.y, e.x, e.y));
                        Geometry.iterateLine(0f, unit.x, unit.y, e.x, e.y, 8, (x, y) -> {
                            SLFx.silvline.at(x, y, Angles.angle(unit.x, unit.y, e.x, e.y));
                        });
                        if(!e.hasEffect(SLStatusEffects.disrupted)){
                            e.apply(SLStatusEffects.disrupted, time);
                        }else if(canReapply){
                            e.apply(SLStatusEffects.disrupted, time * 2f);
                        }
                        if(unit.elevation>0.5) unit.unapply(SLStatusEffects.disrupted);
                    }
                });
            }
        }
    }

    @Override
    public void death(Unit unit){
        Units.nearby(null, unit.x, unit.y, deathRange, e -> {
            if(!e.isImmune(SLStatusEffects.disrupted) && ((targetsEnemyOnDeath && e.team() != unit.team()) || (targetsTeamOnDeath && e.team() == unit.team()))){
                SLFx.silviriumRailHit.at(e.x, e.y, Angles.angle(unit.x, unit.y, e.x, e.y));
                Geometry.iterateLine(0f, unit.x, unit.y, e.x, e.y, 8, (x, y) -> {
                    SLFx.silvline.at(x, y, Angles.angle(unit.x, unit.y, e.x, e.y));
                });
                if(!e.hasEffect(SLStatusEffects.disrupted)){
                    e.apply(SLStatusEffects.disrupted, deathTime);
                }else if(canReapply){
                    e.apply(SLStatusEffects.disrupted, deathTime * 2f);
                }
                if(unit.elevation>0.5) unit.unapply(SLStatusEffects.disrupted);
            }
        });
    }
}