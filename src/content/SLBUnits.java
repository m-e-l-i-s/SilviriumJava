package content;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import type.HealtActivationWeapon;
import mindustry.ai.types.MissileAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.*;
import mindustry.entities.pattern.ShootSpread;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.*;
import mindustry.type.ammo.*;
import ai.ProxMissileAI;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class SLBUnits {
    public static UnitType silvanon, silvirror, silvokeor, silvbane, silvruner, silv5, silvsile, silvone, silvioros, silvistar,star1;
    
    public static void load(){
        silvanon = new UnitType("Silvanon"){{
            alwaysUnlocked = true;
            constructor = MechUnit::create;
            description = "a silvirium unit equipped with a powerful short-range heavy cannon. \nless effective against buildings";
            health = 90f;
            armor = 2;
            hitSize = 8;
            itemCapacity = 10;
            immunities.add(SLBStatusEffects.disrupted);
            ammoCapacity = 60;
            ammoType = new ItemAmmoType(SLBItems.silvirium);
            outlines = false;
            speed = 1.9f;
            accel = 0.3f;
            drag = 0.6f;
            rotateSpeed = 20f;
            rotateMoveFirst = true;
            hoverable = false;
            mechFrontSway = 0.4f;
            mechSideSway = 0.35f;
            mechStride = 3f;
            range = 160f; 
            maxRange = 160f;
            weapons.add(
            new Weapon("slib-Silvanon-cannon"){{
                x = 0;
                y = 4;
                shootY = 4;
                reload = 90;
                shootCone = 3;
                top = true;
                mirror = rotate = false;
                shootSound = Sounds.lasershoot;
                recoil = 5;
                bullet = new BasicBulletType(4,0){{
                        recoil = 15;
                        knockback = 15;
                        splashDamage = 15;
                        splashDamageRadius = 12;
                        buildingDamageMultiplier = 0.65f;
                        lifetime = 40;
                        range = 160;
                        splashDamagePierce = pierce = pierceBuilding = collidesAir = collidesGround = true;
                        pierceCap = 2;
                        hittable = false;
                        smokeEffect = Fx.none;
                        frontColor = SLBPal.silviriumColor;
                        backColor = SLBPal.silviriumDarkColor;
                        shootEffect = SLBFx.silviriumHit1Effect;
                        status = SLBStatusEffects.disrupted;
                        statusDuration = 120f;
                        sprite = "circle-bullet";
                }};}});
            }};
        silvirror = new UnitType("Silvirror"){{
            alwaysUnlocked = true;
            constructor = MechUnit::create;
            description = "the first silvirium unit, equipped with 2 short-range twin conrinuous blowtorch. \nless effective against buildings";
            health = 160f;
            armor = 3;
            hitSize = 8;
            itemCapacity = 10;
            immunities.add(SLBStatusEffects.disrupted);
            ammoCapacity = 60;
            ammoType = new ItemAmmoType(SLBItems.silvirium);
            outlines = false;
            speed = 1.6f;
            accel = 0.4f;
            drag = 0.7f;
            rotateSpeed = 5f;
            rotateMoveFirst = true;
            forceMultiTarget = true;
            hoverable = true;
            mechFrontSway = 0.1f;
            mechSideSway = 0.3f;
            mechStride = -0.6f;
            range = 80f;
            maxRange = 80f;
            ContinuousFlameBulletType silvirrorBullet = new ContinuousFlameBulletType(){{
                length = 80f;
                width = 2f;
                damageInterval = 5f;
                damage = 1f /*12 * damageInterval / 60f*/;
                buildingDamageMultiplier = 0.5f;
                pierceBuilding = true;
                pierceCap = 4;
                status = SLBStatusEffects.disrupted;
                statusDuration = 300f;
                knockback = -0.4f;
                impact = true;
                lifetime = 10f;
                range = 80f;
                hitEffect = SLBFx.silviriumHit1Effect;
                lightStroke = 24f;
                oscScl = 0.04f;
                oscMag = 0.02f;
                drawFlare = false;
                divisions = 25;
                colors = new Color[]{
                        SLBPal.silviriumDarkColor,
                        SLBPal.silviriumMidColor,
                        SLBPal.silviriumColor,
                        SLBPal.silviriumligthColor,
                        Color.white.cpy()
                };
                lengthWidthPans = new float[]{
                        1.0f,1.3f,0.35f,
                        0.8f,1f,0.3f,
                        0.6f,0.9f,0.2f,
                        0.5f,0.8f,0.15f,
                        0.35f,0.5f,0.1f};
            }};
            weapons.add(new HealtActivationWeapon("Silvirror-center"){{
                x = 0f;
                y = 2f;
                reload = 120f;
                shootSound = Sounds.torch;
                maxHealthRange = 0.80f;
                minHealthRange = 0f;
                shootCone = 8f;
                shootX = 0f;
                shootY = 2f;
                mirror = false;
                rotate = true;
                top = false;
                rotateSpeed = 2f;
                rotationLimit = 120f;
                bullet = new BasicBulletType(8f,15f){{
                        sprite = "circle";
                        lifetime = 10f;
                        range = 80f;
                        buildingDamageMultiplier = 0.5f;
                        frontColor = trailColor  = SLBPal.silviriumColor;
                        trailLength = 16;
                        status = SLBStatusEffects.disrupted;
                        statusDuration = 1500f;
                }};
            }},
            new Weapon("slib-silvirror-side-R"){{
                x = 8f;
                y = 1f;
                reload = 60f;
                shootSound = Sounds.torch;
                shootCone = 8f;
                shootX = 0f;
                shootY = 2f;
                mirror = false;
                rotate = true;
                top = false;
                rotateSpeed = 2f;
                rotationLimit = 60f;
                predictTarget = false;
                continuous = true;
                alwaysContinuous = true;
                chargeSound = Sounds.none;
                bullet = silvirrorBullet;
            }},
            new Weapon("slib-silvirror-side-L"){{
                x = -8f;
                y = 1f;
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
        silvokeor = new UnitType("Silvokeor"){{
            alwaysUnlocked = true;
            constructor = LegsUnit::create;
            description = "The second silvirium unit equipped with 2 charge guns.";
            speed = 1.2f;
            accel = 0.3f;
            rotateMoveFirst = true;
            hitSize = 16;
            health = 480;
            armor = 5;
            createScorch = false;
            immunities.add(SLBStatusEffects.disrupted);
            legContinuousMove = true;
            hovering = true;
            
            legCount = 4;
            legLength = 18;
            legMaxLength= 1.2f;
            legMinLength= 0.8f;
            legForwardScl = 1.1f;
            legMoveSpace = 0.8f;
            legSpeed = 0.2f;
            legSplashDamage =  10f;
            legSplashRange =  8f;
            legExtension = 4f;
            legGroupSize = 2;
            legPairOffset = 2f;
            legLengthScl = 1.3f;
            legBaseOffset = 0.5f;
            
            shadowElevation = 0.5f;
            groundLayer = 61;
            rippleScale = 0.5f;
            range = 120;
            maxRange = 120;
            ammoCapacity = 300;
            ammoType = new ItemAmmoType(SLBItems.silvirium);
            itemCapacity = 30;
            outlineRadius = 0;
            weapons.add(
            new Weapon("Silvokeor-orb"){{
                x = 6;
                y = 4;
                shootY = 0;
                reload = 30;
                rotationLimit = 40f;
                rotateSpeed = 5f;
                shootCone = 12f;
                rotate = alternate = mirror = true;
                top = false;
                shoot.firstShotDelay = 30;
                shootSound = Sounds.lasershoot;
                bullet = new BasicBulletType(2,0){{
                        chargeEffect = new WaveEffect(){{
                                colorFrom = SLBPal.silviriumColor;
                                colorTo = SLBPal.silviriumDarkColor;
                                strokeFrom = 0.1f;
                                strokeTo =  0.7f;
                                sizeFrom = 8;
                                sizeTo = 0;
                                lifetime = 30;
                        }};
                        splashDamage = 55;
                        splashDamageRadius = 12;
                        buildingDamageMultiplier = 0.5f;
                        lifetime = 60;
                        range = 120;
                        splashDamagePierce = pierceBuilding = collidesAir = collidesGround = true;
                        hittable = false;
                        smokeEffect = Fx.none;
                        frontColor = SLBPal.silviriumColor;
                        backColor = SLBPal.silviriumDarkColor;
                        shootEffect = SLBFx.silviriumHit1Effect;
                        status = SLBStatusEffects.disrupted;
                        statusDuration = 600f;
                        sprite = "circle-bullet";
                }};}});
            }};
            silvbane = new UnitType("Silvbane"){{
                alwaysUnlocked = true;
                constructor = LegsUnit::create;
                description = "The third silvirium unit equipped with 2 weave homing guns and 1 roket lacucher.";
                speed = 1f;
                accel = 0.3f;
                rotateMoveFirst = true;
                hitSize = 24;
                health = 1255;
                armor = 7;
                createScorch = false;
                immunities.add(SLBStatusEffects.disrupted);
                legContinuousMove = true;
                hovering = true;
                
                legCount = 4;
                legLength = 24;
                legMaxLength = 1.2f;
                legMinLength = 0.8f;
                legForwardScl = 1.2f;
                legMoveSpace = 0.9f;
                legSpeed = 0.2f;
                legSplashDamage =  16f;
                legSplashRange =  10f;
                legExtension = 5f;
                legGroupSize = 2;
                legPairOffset = 3f;
                legLengthScl = 1.35f;
                legBaseOffset = 0.7f;
                
                shadowElevation = 0.6f;
                groundLayer = 61.1f;
                rippleScale = 0.6f;
                range = 200;
                maxRange = 200;
                ammoCapacity = 400;
                ammoType = new ItemAmmoType(SLBItems.silvirium);
                itemCapacity = 50;
                outlineRadius = 0;
                weapons.add(
                new Weapon("Silvbane-side"){{
                        x = 12;
                        y = 3;
                        shootY = 0;
                        reload = 90;
                        rotationLimit = 90f;
                        rotateSpeed = 5f;
                        shootCone = 35f;
                        rotate = alternate = mirror = true;
                        top = false;
                        shoot = new ShootBarrel(){{
                                barrels = new float[]{
                                        0, 0, 0,
                                        0, 0, 0,
                                        -3, -30f, 0,
                                        3, 30f, 0
                                };
                                shots = 3;
                                shotDelay = 25f;
                        }};
                        shootSound = Sounds.lasershoot;
                        bullet = new BasicBulletType(2.5f,60f){{
                                frontColor = trailColor  = SLBPal.silviriumColor;
                                trailLength = 8;
                                lifetime = 80;
                                homingPower = 0.08f;
                                homingRange = 40;
                                range = 200;
                                weaveScale = 3f;
                                weaveMag = 3f;
                        }};
                }},
                new Weapon("Silvbane-missile"){{
                        x = 0;
                        y = 3;
                        shootY = 0;
                        reload = 200;
                        rotationLimit = 25f;
                        rotateSpeed = 5f;
                        shootCone = 10f;
                        rotate = alternate = true;
                        top = mirror = false;
                        shootSound = Sounds.lasershoot;
                        bullet = new /*Prox*/BulletType(){{
                                spawnUnit = silvsile = new MissileUnitType("Silvsile"){{
                                        lifetime = 50;
                                        speed = 4;
                                        outlines = false;
                                        maxRange = 30;
                                        weapons.add(
                                                new Weapon("Silvsile-exp"){{
                                                        x = y = 0;
                                                        reload = 1800;
                                                        shootCone = 360f;
                                                        shootOnDeath = true;
                                                        mirror = false;
                                                        bullet = new ExplosionBulletType(400,40){{
                                                                buildingDamageMultiplier = 0.5f;
                                                                range = 32;
                                                                splashDamagePierce = pierceBuilding = collidesAir = collidesGround = true;
                                                                hittable = false;
                                                                smokeEffect = Fx.none;
                                                                status = SLBStatusEffects.disrupted;
                                                                statusDuration = 300f;
                                                        }};
                                                }}
                                        );
                                }};
                        }};
                }});
            }};
            silvruner = new UnitType("Silvruner"){{
                alwaysUnlocked = true;
                constructor = LegsUnit::create;
                description = "The second silvirium unit equipped with 2 charge guns.";
                speed = 1.2f;
                accel = 0.2f;
                rotateMoveFirst = true;
                hitSize = 40;
                health = 8100;
                armor = 11;
                createScorch = false;
                immunities.add(SLBStatusEffects.disrupted);
                legContinuousMove = true;
                hovering = true;
                
                legCount = 6;
                legLength = 32;
                legMaxLength= 1.2f;
                legMinLength= 0.8f;
                legForwardScl = 1.2f;
                legMoveSpace = 1f;
                legSpeed = 0.6f;
                legSplashDamage =  20f;
                legSplashRange =  12f;
                legExtension = 4f;
                legGroupSize = 2;
                legPairOffset = 2f;
                legLengthScl = 1.3f;
                legBaseOffset = 0.5f;
                
                shadowElevation = 0.7f;
                groundLayer = 61;
                rippleScale = 0.5f;
                range = 120;
                maxRange = 120;
                ammoCapacity = 300;
                ammoType = new ItemAmmoType(SLBItems.silvirium);
                itemCapacity = 60;
                outlineRadius = 0;
                weapons.add(
                        new HealtActivationWeapon("slib-Silvruner-machingun"){{
                                x = 0;
                                y = 2;
                                maxHealthRange = 1f;
                                minHealthRange = 0.10f;
                                shootY = 0;
                                reload = 30;
                                rotationLimit = 40f;
                                rotateSpeed = 5f;
                                shootCone = 12f;
                                top = rotate = true;
                                mirror = false;
                                shoot.shots = 10;
                                shoot.shotDelay = 2;
                                inaccuracy = 10f;
                                shootSound = Sounds.lasershoot;
                                bullet = new BasicBulletType(6,30){{
                                        buildingDamageMultiplier = 0.5f;
                                        lifetime = 20;
                                        range = 120;
                                        pierce = pierceBuilding = collidesAir = collidesGround = true;
                                        pierceCap = 3;
                                        pierceDamageFactor = 0.02f;
                                        hittable = false;
                                        smokeEffect = Fx.none;
                                        frontColor = SLBPal.silviriumColor;
                                        backColor = SLBPal.silviriumDarkColor;
                                        shootEffect = SLBFx.silviriumHit1Effect;
                                        status = SLBStatusEffects.disrupted;
                                        statusDuration = 600f;
                                        sprite = "circle-bullet";
                                }};
                        }},
                        new HealtActivationWeapon("Silvruner-Explosion"){{
                                x = 0;
                                y = 0;
                                maxHealthRange = 0.10f;
                                minHealthRange = 0f;
                                shootY = 0;
                                shootCone = 12f;
                                rotate = alternate = mirror = false;
                                shootCone = 360f;
                                shootOnDeath = true;
                                shoot.firstShotDelay = 180;
                                shootSound = Sounds.lasershoot;
                                shootStatus = SLBStatusEffects.rush;
                                shootStatusDuration = 180;
                                bullet = new ExplosionBulletType(220,120){{
                                        buildingDamageMultiplier = 0.5f;
                                        range = 40;
                                        splashDamagePierce = pierceBuilding = collidesAir = collidesGround = true;
                                        hittable = false;
                                        smokeEffect = Fx.none;
                                        status = SLBStatusEffects.disrupted;
                                        statusDuration = 300f;
                                        killShooter = true;
                                        absorbable = false;
                                }};
                        }}
                );
            }};
            silv5 = new UnitType("Silv5"){{
                alwaysUnlocked = true;
                constructor = LegsUnit::create;
                description = "The 5 silvirium unit (WIP).";
                speed = 1.2f;
                accel = 0.3f;
                rotateMoveFirst = true;
                hitSize = 16;
                health = 480;
                armor = 5;
                createScorch = false;
                immunities.add(SLBStatusEffects.disrupted);
                legContinuousMove = true;
                hovering = true;
                
                legCount = 4;
                legLength = 18;
                legMaxLength= 1.2f;
                legMinLength= 0.8f;
                legForwardScl = 1f;
                legMoveSpace = 1f;
                legSpeed = 0.2f;
                legSplashDamage =  12f;
                legSplashRange =  8f;
                legExtension = 4f;
                legGroupSize = 2;
                legPairOffset = 2f;
                legLengthScl = 1.3f;
                legBaseOffset = 0.5f;
                
                shadowElevation = 0.5f;
                groundLayer = 61;
                rippleScale = 0.5f;
                range = 120;
                maxRange = 120;
                ammoCapacity = 300;
                ammoType = new ItemAmmoType(SLBItems.silvirium);
                itemCapacity = 30;
                outlineRadius = 0;
            }};
            silvone = new UnitType("Silvone"){{
                alwaysUnlocked = true;
                constructor = UnitEntity::create;
                flying = true;
                immunities.add(SLBStatusEffects.disrupted);
                ammoType = new ItemAmmoType(SLBItems.silvirium);
                outlines = false;
                health = 40;
                speed = 4f;
                accel = 0.1f;
                drag = 0.005f;
                range = 64;
                maxRange = 64;
                engineSize = 0.01f;
                hitSize = 4;
                weapons.add(new Weapon("slib-piercer"){{
                        x = 0f;
                        y = 0f;
                        mirror = false;
                        top = false;
                        reload = 120f;
                        shootSound = Sounds.shootBig;
                        rotate = false;
                        bullet = new RailBulletType(){{
                                shootEffect = Fx.none;
                                length = 64;
                                range = 64;
                                pointEffectSpace = 16f;
                                pierceEffect = SLBFx.silviriumRailHit;
                                pointEffect = SLBFx.silviriumRail;
                                hitEffect = SLBFx.silviriumWave1Effect;
                                smokeEffect = Fx.shootSmall;
                                status = SLBStatusEffects.disrupted;
                                statusDuration = 180f;
                                damage = 60;
                                pierceDamageFactor = 0.5f;
                        }};
                }});
            }};
            silvioros = new UnitType("Silvioros"){{
                alwaysUnlocked = true;
                constructor = PayloadUnit::create;
                description = "the T1 flying silvirium unit, equipped with 1 building stuner cannon";
                flying = true;
                payloadCapacity = 64/*Mathf.pow(1*8,2) || (1*8)^2*/;
                immunities.add(SLBStatusEffects.disrupted);
                outlines = false;
                health = 115f;
                hitSize = 8.005f;
                speed = 2f;
                accel = 0.2f;
                range = 160f;
                maxRange = 120f;
                engineSize = 2.2f;
                targetAir = false;
                parts.add(
                new ShapePart(){{
                        color = SLBPal.silviriumColor;
                        colorTo = SLBPal.silviriumColor;
                        progress = PartProgress.recoil.inv();
                        hollow = mirror = true;
                        sides = 3;
                        rotateSpeed = 6;
                        radius = 4;
                        radiusTo = 4;
                        stroke = 0;
                        strokeTo = 1;
                        x = 0;
                        moveX = 0;
                        y = 9;
                }});
                weapons.add(new Weapon("Silvioros-cannon"){{
                        x = 0;
                        y = 9;
                        shootY = 0;
                        mirror = false;
                        top = false;
                        reload = 60f;
                        shootSound = Sounds.shootBig;
                        rotate = true;
                        rotateSpeed = 15f;
                        recoil = 10;
                        rotationLimit = 300f;
                        range = 240f;
                        maxRange = 240f;
                        bullet = new BasicBulletType(4f, 8f){{
                                width = 8f;
                                height = 12f;
                                range = 240f;
                                collidesAir = false;
                                collidesGround = pierce = true;
                                frontColor = SLBPal.silviriumColor;
                                backColor = SLBPal.silviriumDarkColor;
                                trailLength = 8;
                                trailWidth = 1;
                                shootEffect = Fx.shootSmall;
                                lifetime = 40f;
                                sprite = "shell";
                        }
                        @Override
                        public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
                                super.hitTile(b, build, x, y, initialHealth, direct);
                                build.applySlowdown(0f, 300f);
                        }
                };
            }});
        }};
        silvistar = new UnitType("Silvistar"){{
            alwaysUnlocked = true;
            constructor = PayloadUnit::create;
            description = "A flying unit made by mixing tecnologys, equipped with 1 accel orb cannon and 1 static star mines launcher.";
            circleTarget = flying = true;
            payloadCapacity = 256/*Mathf.pow(2*8,2) || (2*8)^2*/;
            hitSize = 20;
            immunities.add(SLBStatusEffects.disrupted);
            outlines = false;
            health = 210f;
            speed = 5f;
            accel = 0.1f;
            drag = 0.04f;
            range = 80f;
            maxRange = 80f;
            engineSize = 0f;
            parts.addAll(
            new HoverPart(){{
                color = SLBPal.starOrangeColor;
                sides = 6;
                radius = 12;
                x = -4.5f;
                y = -3.5f;
                layer = 59.95f;
            }},
            new HoverPart(){{
                color = SLBPal.starOrangeColor;
                sides = 6;
                radius = 12;
                x = -4.5f;
                y = 3.5f;
                layer = 59.95f;
            }},
            new HoverPart(){{
                color = SLBPal.silviriumColor;
                sides = 6;
                radius = 12;
                x = 4.5f;
                y = -3.5f;
                layer = 59.95f;
            }},
            new HoverPart(){{
                color = SLBPal.silviriumColor;
                sides = 6;
                radius = 12;
                x = 4.5f;
                y = 3.5f;
                layer = 59.95f;
            }});
            weapons.add(
            new Weapon("Silvirium-orbs"){{
                x = 7f;
                y = 0f;
                mirror = false;
                top = false;
                reload = 36f;
                shootSound = Sounds.shootBig;
                rotate = false;
                inaccuracy = 0f;
                bullet = new BasicBulletType(0.0795f, 5f){{
                        keepVelocity = false;
                        splashDamagePierce = collides = true;
                        mixColorFrom = SLBPal.silviriumColor;
                        mixColorTo = SLBPal.silviriumMidColor;
                        trailColor = SLBPal.silviriumColor;
                        shootEffect = SLBFx.silviriumInvHit3Effect;
                        status = SLBStatusEffects.disrupted;
                        statusDuration = 600f;
                        sprite = "circle-bullet";
                        width = 6f;
                        height = 6f;
                        shrinkY = 0;
                        range = 80f;
                        drag = -0.1f;
                        homingDelay = 12;
                        homingPower = 0.09f;
                        homingRange = 56;
                        splashDamage = 10;
                        splashDamageRadius = 16;
                        buildingDamageMultiplier = 0.5f;
                }
            };}},
            new Weapon("Static-star-mines"){{
                x = -7f;
                y = 0f;
                mirror = false;
                top = false;
                reload = 112f;
                shootSound = Sounds.shootBig;
                rotate = false;
                inaccuracy = 7f;
                shoot.shots = 3;
                shoot.shotDelay = 6;
                bullet = new FlakBulletType(10f, 0f){{
                        sprite = "large-bomb-back";
                        width = 10f;
                        height = 10f;
                        range = 80f;
                        drag = 0.11f;
                        homingDelay = 15;
                        homingPower = 0.03f;
                        homingRange = 40;
                        splashDamage = 5;
                        buildingDamageMultiplier = 3f;
                        splashDamageRadius = 44;
                        explodeRange = 40;
                        keepVelocity = collides = false;
                        splashDamagePierce = collidesGround = true;
                        mixColorFrom = SLBPal.starOrangeColor;
                        mixColorTo = SLBPal.starRedDarkColor;
                        shootEffect = Fx.shootSmall;
                        lifetime = 200f;
                }};
            }});
        }};
         star1 = new UnitType("star1"){{
             alwaysUnlocked = true;
             constructor = MechUnit::create;
             description = "a star unit equipped with a powerful star blowtorch flame";
             health = 160f;
             armor = 0;
             hitSize = 8;
             itemCapacity = 10;
             ammoCapacity = 20;
             ammoType = new ItemAmmoType(SLBItems.starFrag);
             outlines = false;
             speed = 2.2f;
             accel = 0.2f;
             drag = 0.6f;
             rotateMoveFirst = true;
             hoverable = false;
             mechFrontSway = 0.3f;
             mechSideSway = 0.2f;
             mechStride = 1f;
             weapons.add(
                 new Weapon("star-flame"){{
                    x = 0;
                    y = 3;
                    shootY = 2;
                    reload = 5;
                    shootCone = 2;
                    mirror = rotate = predictTarget = false;
                    top = continuous = alwaysContinuous = true;
                    shootSound = Sounds.flame;
                    bullet = new ContinuousFlameBulletType(){{
                        length = 80f;
                        range = 80f;
                        width = 5f;
                        damageInterval = 0.1f;
                        damage = 0.03f /*21 * damageInterval / 60f*/;
                        buildingDamageMultiplier = 2f;
                        pierceBuilding = true;
                        status = StatusEffects.burning;
                        statusDuration = 60f;
                        knockback = 0;
                        impact = true;
                        lifetime = 10f;
                        hitEffect = SLBFx.starHit;
                        lightStroke = 20f;
                        oscScl = 0.04f;
                        oscMag = 0.02f;
                        drawFlare = false;
                        divisions = 2;
                        colors = new Color[]{
                                SLBPal.starRedDarkColor,
                                SLBPal.starRedColor,
                                SLBPal.starOrangeDarkColor,
                                SLBPal.starOrangeColor,
                                Color.white.cpy()
                        };
                        lengthWidthPans = new float[]{
                                1.0f,1.3f,0.35f,
                                0.8f,1f,0.3f,
                                0.6f,0.9f,0.2f,
                                0.5f,0.8f,0.15f,
                                0.3f,0.5f,0.1f
                        };
                    }};
                }});
            }};
}}

    
