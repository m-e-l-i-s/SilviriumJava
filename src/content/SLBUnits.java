package content;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Time;
import entities.abilities.MoveShootAbility;
import entities.bullet.AntiBuildingBulletType;
import mindustry.content.Fx;
import mindustry.entities.Units;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.ContinuousFlameBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.MechUnit;
import mindustry.gen.PayloadUnit;
import mindustry.gen.Sounds;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class SLBUnits {
    public static UnitType silvirror, Anburt, starBoss;

public static void load(){
     silvirror = new UnitType("Silvirror"){{
        constructor = MechUnit::create;
        description = "the first silvirium unit, equipped with 2 short-range twin conrinuous blowtorch. \nless effective against buildings";

        health = 170f;
        armor = 3;
        hitSize = 8;

        itemCapacity = 10;
        immunities.add(SLBStatusEffects.disrupted);
        
        ammoCapacity = 60;
        //ammoType = SLBItems.silvirium

        outlines = false;

        speed = 1.2f;
        drag = 1f;
	    rotateSpeed = 6f;
        rotateMoveFirst = true;
        forceMultiTarget = true;
        hoverable = true;

        mechFrontSway = 0.1f;
        mechSideSway = 0.3f;
        mechStride = -0.6f;

        range = 60f;
        maxRange = 60f;
        ContinuousFlameBulletType silvirrorBullet = new ContinuousFlameBulletType(){{
            length = 60f;
            width = 2f;
            
            damageInterval = 5f;
            damage = 15 * damageInterval / 60f;  
            buildingDamageMultiplier = 0.5f;

            pierceBuilding = true;
            pierceCap = 4;

            status = SLBStatusEffects.disrupted;
            statusDuration = 900f;
            knockback = -0.25f;
            impact = true;
            lifetime = 300f;

            range = 60f;

            hitEffect = SLBFx.silviriumHit1Effect;
            lightStroke = 24f;
            oscScl = 1.7f;
            oscMag = 0.1f;
            drawFlare = false;
            divisions = 25;
            colors = new Color[]{SLBPal.silviriumDarkColor, SLBPal.silviriumMidColor, SLBPal.silviriumligthDarkColor, SLBPal.silviriumColor, Color.white.cpy()};
            lengthWidthPans = new float[]{
                1.2f, 1.3f, 0.35f,
                0.9f, 1f, 0.3f,
                0.7f, 0.9f, 0.2f,
                0.55f, 0.8f, 0.15f,
                0.3f, 0.5f, 0.1f
            };
        }};
        weapons.add(
            new Weapon("Silvirror-side-R"){{
                x = 8f;
                y = 0f;
                reload = 60f;
                shootSound = Sounds.torch;
                shootCone = 8f;
                shootX = 0f;
                shootY = 2f;
                mirror = false;
                rotate = true;
                top = false;
                parts.add(new RegionPart("Silvirror-side-R"){{
                    name = "Silvirror-side-R";
                    mirror = false;
                    rotation = x = y = moveRot = moveX = moveY = 0f;
                }});
                rotateSpeed = 2f;
                rotationLimit = 50f;
                predictTarget = false;
                continuous = true;
                alwaysContinuous = true;
                chargeSound = Sounds.none;
                bullet = silvirrorBullet;
            }},
            new Weapon("-side-L"){{
                x = -8f;
                y = 0f;
                reload = 60f;
                shootSound = Sounds.torch;
                shootCone = 10f;
                shootX = 0f;
                shootY = 2f;
                mirror = false;
                rotate = true;
                top = false;
                rotateSpeed = 2f;
                rotationLimit = 50f;
                predictTarget = false;
                continuous = true;
                alwaysContinuous = true;
                chargeSound = Sounds.none;
                bullet = silvirrorBullet;
            }});
        }};
        Anburt = new UnitType("Anburt"){{
        constructor = PayloadUnit::create;

        flying = true;
        payloadCapacity = Mathf.pow(1*8,2);
        outlines = false;
        health = 115f;
        speed = 2f;
        range = 160f;
        maxRange = 120f;
        engineSize = 0.8f;
        BulletType bullet = new BasicBulletType(8f,10f){{
            this.lifetime = 20f;
            this.homingPower = 0.2f;
            this.homingRange = 160f;
            this.pierceCap = 3;
            this.pierceBuilding = true;
        }};
        abilities.add(
            new MoveShootAbility(bullet , 45f, 0f, 5f, 1, 2, 1, 1, Fx.shootBig, true, Sounds.pew, true)
        );

        weapons.add(
            new Weapon("Anburt-cannon"){{
                x = 0f;
                y = 0f;
                mirror = false;
                top = false;
                reload = 60f;
                shootSound = Sounds.shootBig;
                rotate = true;
                rotateSpeed = 1f;
                rotationLimit = 25f;
                bullet = new AntiBuildingBulletType(4f, 8f){{
                    width = 12f;
                    height = 12f;
                    range = 160f;
                    collidesAir = collidesGround = true;
                    shootEffect = Fx.shootSmokeSquareBig.followParent(false).rotWithParent(false);
                    lifetime = range/speed;
                }
                @Override
				public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
					super.hitTile(b, build, x, y, initialHealth, direct);

                    SLBFx.silviriumHit1Effect.at(build.x, build.y, b.rotation());
					build.applySlowdown(0f, 120f);
                    build.applyHealSuppression(120f);
				}
            };
        }
    });
}};
        starBoss = new UnitType("Apocaliptic Star"){{
                constructor = MechUnit::create;
                description = "";
        
                health = 40000f;
                hitSize = 8;
        
                itemCapacity = 1200;                
                ammoCapacity = 7000;

                outlines = false;
        
                speed = 0.8f;
                drag = 3.4f;
                rotateSpeed = 5f;
                rotateMoveFirst = true;
                forceMultiTarget = true;
                drownTimeMultiplier = 900;
        
                mechFrontSway = 0.6f;
                mechSideSway = 1f;
                mechStride = -0.2f;
        
                range = 600f;
                maxRange = 600f;
            }
            @Override
            public void update(Unit unit){
            if(unit.item() != null && unit.item().flammability > 0 && unit.itemTime > 0.01f && unit.damaged()){
                unit.heal(Math.min(200f , unit.maxHealth - unit.health)*Time.delta);
            }
            unit.clearStatuses();
            unit.armor = unit.type.armor;
            Units.nearbyEnemies(unit.team, unit.x, unit.y, 800f, e -> {
                if (e.dead || e.isValid() || e.hittable()) {
                    unit.armor += 1f;
                    speed = e.type.speed/armor;
                } else {
                    unit.armor += 0.5f;
                }
            });
            super.update(unit);
        }
    };
}}