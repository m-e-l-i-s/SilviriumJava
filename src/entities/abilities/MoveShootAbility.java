package entities.abilities;

import arc.audio.Sound;
import arc.math.Angles;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Sounds;
import mindustry.gen.Unit;

public class MoveShootAbility extends Ability {
    /** interval betwen shoots (at max speed)*/
    public float reload = 60f;
    /** Speeds for when to start bulleting and when to stop getting faster */
    public float minSpeed = 0.8f, maxSpeed = 1.2f;
    /** Shifts where the bullet spawns along the Y axis */
    public float y = 0f;
    /** Offset along the X axis */
    public float x = 0f;
    /** Whether the spawn side alternates */
    public boolean alternate = true;
    /** Bullet type that is fired*/
    public BulletType bullet;
    /** Bullet angle parameters */
    public float bulletAngle = 0f, bulletSpread = 0f;
    
    public Effect shootEffect = Fx.sparkShoot;
    public boolean parentizeEffects;
    public Sound shootSound = Sounds.spark;

    protected float side = 1f;
    protected float timer = 0f;

    public MoveShootAbility(BulletType bullet, float reload, float bulletAngle, float bulletSpread, float minSpeed, float maxSpeed, float x, float y, Effect shootEffect, boolean parentizeEffects, Sound shootSound, boolean alternate){
        this.bullet = bullet;
        this.reload = reload;
        this.bulletAngle = bulletAngle;
        this.bulletSpread = bulletSpread;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.x = x;
        this.y = y;
        this.shootEffect = shootEffect;
        this.parentizeEffects = parentizeEffects;
        this.shootSound = shootSound;
        this.alternate = alternate;

    }

    public MoveShootAbility(BulletType bullet, float reload, float minSpeed, float maxSpeed){
        this.bullet = bullet;
        this.reload = reload;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public MoveShootAbility(){};
    
    @Override
    public void addStats(Table t){
        super.addStats(t);
        t.add("Reload: ", reload).fontScale(1).row();
        t.add("Range: ", bullet.range).fontScale(1).row();
        t.add("speed: ", minSpeed).fontScale(1);
        t.add( " - ", maxSpeed).fontScale(1);
    }

    @Override
public void update(Unit unit){
    float scl = Mathf.clamp((unit.vel().len() - minSpeed) / (maxSpeed - minSpeed), 0f, 1f);
    timer += Time.delta;

    if(scl > 0f && timer >= reload / scl && bullet != null){
        timer = 0f;
        float rot = unit.rotation + Mathf.range(bulletSpread) - 90;
        float shootX = Angles.trnsx(rot + bulletAngle * side, x * side, y);
        float shootY = Angles.trnsy(rot + bulletAngle * side, x * side, y);

        bullet.create(unit, unit.team, unit.x + shootX, unit.y + shootY, rot);

        if(shootEffect != null) shootEffect.at(unit.x + shootX, unit.y + shootY, 0, rot - 90);
        if(shootSound != null) shootSound.at(unit.x, unit.y);

        if(alternate) side *= -1f;
    }
}

}
