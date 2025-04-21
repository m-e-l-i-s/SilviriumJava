package entities.bullet;

import arc.math.Angles;
import arc.util.Time;
import mindustry.entities.Units;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Teamc;


public class AntiBuildingBulletType extends BasicBulletType{

    public AntiBuildingBulletType(float speed, float damage){
        super(speed, damage);
	}

	public AntiBuildingBulletType(){
		super(0f, 0f);
	}

	@Override
	public void updateHoming(Bullet b){
        if(homingPower > 0.0001f && b.time >= homingDelay){
            float realAimX = b.aimX < 0 ? b.x : b.aimX;
            float realAimY = b.aimY < 0 ? b.y : b.aimY;

            Teamc target = null;
            //home in on allies if possible
            if(heals()){
                target = Units.closestTarget(null, realAimX, realAimY, homingRange,
                e -> e.checkTarget(collidesAir, collidesGround) && e.team != b.team && !b.hasCollided(e.id),
                t -> (t.team != b.team || t.damaged()) && !b.hasCollided(t.id)
                );
            }else{
                if(b.aimTile != null && b.aimTile.build != null && b.aimTile.build.team != b.team && !b.hasCollided(b.aimTile.build.id)){
                    target = b.aimTile.build;
                }
            }

            if(target != null){
                b.vel.setAngle(Angles.moveToward(b.rotation(), b.angleTo(target), homingPower * Time.delta * 50f));
            }
        }
    }
    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health){
        if(entity instanceof Healthc h){
            if(pierceArmor){
                h.damagePierce(0f);
            }else{
                h.damage(0f);
            }
        }
    }
}
