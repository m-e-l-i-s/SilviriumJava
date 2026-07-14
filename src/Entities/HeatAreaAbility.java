package Entities;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.util.Time;
import arc.util.Tmp;
import content.SLPal;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;

public class HeatAreaAbility extends Ability{
    public float 
    velMin = 0, velMax = 1, range = 40, brange = 40, damage = 20, bdamage = 200;

    protected float vel = 0;

    public HeatAreaAbility(){}

    public HeatAreaAbility(float velMin, float velMax, float range, float damage){
        this.velMin = velMin;
        this.velMax = velMax;
        this.range = range;
        this.damage = damage;
    }

    @Override
    public void update(Unit unit){
        vel = range*(unit.vel.len()-velMax)/(velMin-velMax);
        Units.nearby(null, unit.x, unit.y, range+(brange*vel), e -> {
            e.damage((damage+(bdamage*vel))*Time.delta);
        });
    }

    @Override
    public void draw(Unit unit){
        Draw.color(Tmp.c4.set(SLPal.starOrangeColor).lerp(SLPal.starRedColor,vel).a(0.4f+(0.2f*vel)));
        Lines.circle(unit.x, unit.y, range+(brange*vel));
        
        Draw.reset();
    }

}