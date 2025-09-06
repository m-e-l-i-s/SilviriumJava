package content;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import mindustry.entities.Effect;
import mindustry.entities.effect.*;
import mindustry.graphics.Drawf;

public class SLBFx {
    public static Vec2 vect1 = new Vec2();
    public static Vec2 vect2 = new Vec2();
    static float tmpx;
    static float tmpy;
    public static Effect
    silviriumHit1Effect = new Effect(10, e ->{
        float rds = 1f;
        int amnt = 5;
        float dst = 16f;
        color(SLBPal.silviriumColor, SLBPal.silviriumDarkColor, e.fin());
        randLenVectors(e.id, e.fin(), amnt, dst, (x, y, fin, fout) -> {
            Fill.circle(e.x + x, e.y + y, 4f * fout * rds);
        });
    }),
    silviriumWave1Effect = new WaveEffect(){{
        colorFrom=SLBPal.silviriumColor;
        colorTo=SLBPal.silviriumDarkColor;
        strokeFrom=1f;
        strokeTo=0.2f;
        sizeFrom=4f;
        sizeTo=12f;
    }},
    silviriumHit2Effect = new Effect(10, e ->{
        float rds = 1f;
        int amnt = 5;
        float dst = 24f;
        color(SLBPal.silviriumColor, SLBPal.silviriumDarkColor, e.fin());
        randLenVectors(e.id, e.finpow(), amnt, dst, (x, y, fin, fout) -> {
            Fill.circle(e.x + x, e.y + y, 4f * fout * rds);
        });
    }),
    silviriumInvHit3Effect = new ParticleEffect(){{
        colorFrom = SLBPal.silviriumColor;
        colorTo = SLBPal.silviriumDarkColor;
        particles = 4;
        randLength = false;
        length = 24;
        baseLength = 20;
        interp = Interp.pow2Out;
        sizeInterp = Interp.one;
        strokeFrom = 2;
        strokeTo = 1;
        lenFrom = 2;
        lenTo = 4;
        cap = true;
        lifetime = 30;
    }},
    silviriumRailHit = new Effect(20, 200, (e) -> {
        Draw.color(SLBPal.silviriumColor);
        Drawf.tri(e.x, e.y, 8 * e.fout(), 24, e.rotation + 130);
        Drawf.tri(e.x, e.y, 8 * e.fout(), 24, e.rotation + -130);
        Angles.randLenVectors((long)e.id, 3, 1f + 80f * e.fin(), e.rotation, 120f, (x, y) -> {
         Drawf.tri(e.x + x, e.y + y, e.fslope() * 9f + 3f, e.fslope() * 9f + 3f, Mathf.angle(x, y)+180f);
        });
      }),
      silviriumRail = new Effect(40, 200, (e) -> {
        Draw.color(SLBPal.silviriumColor);
        Drawf.tri(e.x, e.y, 8 * e.fout(), 12, e.rotation);
        Drawf.tri(e.x, e.y, 8 * e.fout(), 4, e.rotation + 180);
        Draw.color(SLBPal.silviriumOtherColor);
        Drawf.tri(e.x, e.y, 7 * e.fout(), 11, e.rotation);
        Drawf.tri(e.x, e.y, 7 * e.fout(), 3, e.rotation + 180);
    }),
    silvAmb = new ParticleEffect(){{
        particles = 2;
        length = 20;
        baseLength = 40;
        colorFrom = SLBPal.silviriumColor;
        colorTo = SLBPal.silviriumOtherColor;
        sizeFrom = 1;
        sizeTo = 1.5f;
        lifetime = 60;
    }},
    silvline = new ParticleEffect(){{
        particles = 1;
        length = 0.5f;
        baseLength = 1;
        line = true;
        colorFrom = SLBPal.silviriumColor;
        colorTo = SLBPal.silviriumOtherColor;
        strokeFrom = 2f;
        strokeTo = 0f;
        lenFrom = 12f;
        lenTo = 12f;
        lifetime = 20;
        cone = 0;
    }};
}
