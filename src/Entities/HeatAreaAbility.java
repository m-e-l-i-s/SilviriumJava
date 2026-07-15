package Entities;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Time;
import arc.util.Tmp;
import content.SLPal;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;

import static mindustry.Vars.*;

public class HeatAreaAbility extends Ability{
    public float
    velMin = 0, velMax = 1, range = 40, brange = 40, damage = 5, bdamage = 95;

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
        vel = Mathf.approachDelta(vel,(velMin-unit.vel.len())/(velMin-velMax),velMax-velMin/4);
        Units.nearby(null, unit.x, unit.y, range+(brange*vel), e -> {
            lStroke();
            Lines.dashCircle(e.x, e.y, e.hitSize);
            e.damage((damage+(bdamage*vel))*Time.delta);
            
        });
    }

    public void lStroke(){
        Lines.stroke(1f+(1f*vel), Tmp.c4.set(SLPal.starOrangeColor).lerp(SLPal.starRedColor,vel).a(0.4f+(0.2f*vel)));
    }

    @Override
    public void draw(Unit unit){
        lStroke();
        Lines.dashCircle(unit.x, unit.y, range+(brange*vel));
        
        Draw.reset();
    }

    @Override
    public void addStats(Table t){
        super.addStats(t);
        t.row();
        t.add(
            abilityStat("velocityRange",
                ":[stat]"+velMin*tilesize + "-" + velMax*tilesize
            )
        ).row();
        t.add(
            abilityStat("range",
                ":[stat]"+range/tilesize + "-" + brange/tilesize
            )
        ).row();
        t.add(
            abilityStat("damage",
                ":[stat]"+damage/tilesize + "-" + bdamage/tilesize
            )
        ).row();
    }

}