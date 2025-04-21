package content;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

import arc.graphics.g2d.Fill;
import mindustry.entities.Effect;

public class SLBFx {

    public static Effect
    silviriumHit1Effect = new Effect(60f, e ->{
        float rds = 1f;
        int amnt = 5;
        float dst = 16f;
        color(SLBPal.silviriumColor, SLBPal.silviriumDarkColor, e.fin());
        randLenVectors(e.id, e.fin(), amnt, dst, (x, y, fin, fout) -> {
            Fill.circle(e.x + x, e.y + y, 4f * fout * rds);
        });
    }),
    silviriumHit2Effect = new Effect(60f, e ->{
        float rds = 1f;
        int amnt = 5;
        float dst = 24f;
        color(SLBPal.silviriumColor, SLBPal.silviriumDarkColor, e.fin());
        randLenVectors(e.id, e.finpow(), amnt, dst, (x, y, fin, fout) -> {
            Fill.circle(e.x + x, e.y + y, 4f * fout * rds);
        });
    });
}
