package content;

import static arc.graphics.g2d.Draw.*;

import Entities.HeatAreaAbility;
import Entities.disruptPulseAbility;
import ai.ProxMissileAI;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Scaled;
import arc.scene.ui.layout.Table;
import arc.util.Strings;
import arc.util.Time;
import type.HealtActivationWeapon;
import mindustry.Vars;
import mindustry.ai.types.MissileAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.entities.abilities.Ability;
import mindustry.entities.abilities.EmptyDataAbility;
import mindustry.entities.abilities.RegenAbility;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.*;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class SLUnits {
    public static UnitType silvanon, silvirror, silvokeor, silvbane, silvruner, silv5,
    silvone, silvioros,
    silvot,
    silvistar, silvsile,
    star1, star2, star6;

    public static void load(){
        silvanon = new UnitType("Silvanon"){{
            alwaysUnlocked = true;
            constructor = MechUnit::create;
            health = 50f;
            armor = -5;
            hitSize = 8;
            itemCapacity = 10;
            immunities.add(SLStatusEffects.disrupted);
            outlines = false;
            speed = 0.6f;
            accel = 0.3f;
            drag = 0.2f;
            rotateSpeed = 2f;
            rotateMoveFirst = true;
            hoverable = false;
            mechFrontSway = 0.8f;
            mechSideSway = 0.75f;
            mechStride = 6f;
            range = 240f; 
            maxRange = 240f;
            abilities.add(new disruptPulseAbility(120,600));
            weapons.add(
                new Weapon("sil-Silvanon-cannon"){{
                    x = 0;
                    y = 4;
                    shootY = 4;
                    reload = 0;
                    shootCone = 1;
                    top = true;
                    mirror = rotate = false;
                    shootOnDeath = true;
                    shoot.firstShotDelay = 120;
                    bullet = new BasicBulletType(2,0){{
                        chargeEffect = SLFx.silviriumChargeEffect;
                        knockback = 25;
                        splashDamage = 180;
                        splashDamageRadius = 12;
                        buildingDamageMultiplier = 0.5f;
                        lifetime = 120;
                        scaleLife = true;
                        killShooter = true;
                        range = 240;
                        hittable = false;
                        smokeEffect = Fx.none;
                        frontColor = SLPal.silviriumColor;
                        backColor = SLPal.silviriumDarkColor;
                        shootEffect = SLFx.silviriumHit1Effect;
                        status = SLStatusEffects.disrupted;
                        statusDuration = 120f;
                        sprite = "large-orb";
                        backSprite = "large-orb-back";
                    }};
                }}
            );
        }};
        silvirror = new UnitType("Silvirror"){{
            alwaysUnlocked = true;
            constructor = LegsUnit::create;
            health = 160f;
            armor = 3;
            hitSize = 8;
            itemCapacity = 10;
            immunities.add(SLStatusEffects.disrupted);
            outlines = false;
            speed = 1.6f;
            accel = 0.4f;
            drag = 0.3f;
            rotateSpeed = 5f;
            rotateMoveFirst = true;
            forceMultiTarget = true;
            hoverable = true;

            legCount = 4;
            legLength = 8;
            legMaxLength = 2.5f;
            legMinLength = 0.8f;
            legForwardScl = 0.8f;
            legMoveSpace = 1.6f;
            legSpeed = 0.1f;
            legSplashDamage =  1f;
            legSplashRange =  4f;
            legExtension = 4f;
            legGroupSize = 2;
            legPairOffset = 6f;
            legLengthScl = 1.5f;
            legBaseOffset = 0.5f;

            float brange = range = maxRange = 80f;
            ContinuousFlameBulletType silvirrorBullet = new ContinuousFlameBulletType(){{
                length = brange;
                width = 2f;
                damageInterval = 5f;
                damage = 1f /*12 * damageInterval / 60f*/;
                buildingDamageMultiplier = 0.5f;
                pierceBuilding = true;
                pierceCap = 4;
                status = SLStatusEffects.disrupted;
                statusDuration = 300f;
                knockback = -0.4f;
                impact = true;
                lifetime = 10f;
                range = brange;
                hitEffect = SLFx.silviriumHit1Effect;
                lightStroke = 24f;
                oscScl = 0.04f;
                oscMag = 0.02f;
                drawFlare = false;
                divisions = 25;
                colors = new Color[]{
                    SLPal.silviriumDarkColor,
                    SLPal.silviriumMidColor,
                    SLPal.silviriumColor,
                    SLPal.silviriumligthColor,
                    Color.white.cpy()
                };
                lengthWidthPans = new float[]{
                    1.0f,1.3f,0.1f,
                    0.8f,1f,0.15f,
                    0.6f,0.9f,0.2f,
                    0.5f,0.8f,0.25f,
                    0.35f,0.5f,0.3f
                };
            }};
            weapons.add(
                new HealtActivationWeapon("sli-Silvirror-center", 0.80f, 0f){{
                    x = 0f;
                    y = 2f;
                    reload = 120f;
                    shootCone = 8f;
                    shootX = 0f;
                    shootY = 2f;
                    rotateSpeed = 2f;
                    rotationLimit = 120f;
                    mirror = top = false;
                    rotate = true;
                    bullet = new BasicBulletType(8f,15f){{
                        sprite = "circle";
                        lifetime = 10f;
                        range = 80f;
                        buildingDamageMultiplier = 0.5f;
                        pierce = pierceBuilding = true;
                        pierceCap = 3;
                        frontColor = trailColor  = SLPal.silviriumColor;
                        trailLength = 16;
                        status = SLStatusEffects.disrupted;
                        statusDuration = 1800f;
                    }};
                }},
                new Weapon("silvirror-side-R"){{
                    x = 8f;
                    y = 1f;
                    reload = 60f;
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
                new Weapon("sil-silvirror-side-L"){{
                    x = -8f;
                    y = 1f;
                    reload = 60f;
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
                }}
            );
        }};
        silvokeor = new UnitType("Silvokeor"){{
            alwaysUnlocked = true;
            constructor = LegsUnit::create;
            speed = 1.2f;
            accel = 0.3f;
            rotateMoveFirst = true;
            hitSize = 12;
            health = 480;
            armor = 5;
            createScorch = false;
            immunities.add(SLStatusEffects.disrupted);
            legContinuousMove = true;
            hovering = true;
            
            legCount = 4;
            legLength = 18;
            legMaxLength = 2.5f;
            legMinLength = 0f;
            legForwardScl = 0.5f;
            legMoveSpace = 1.8f;
            legSpeed = 0.4f;
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
                    bullet = new BasicBulletType(2,0){{
                        chargeEffect = new WaveEffect(){{
                            colorFrom = SLPal.silviriumColor;
                            colorTo = SLPal.silviriumDarkColor;
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
                        frontColor = SLPal.silviriumColor;
                        backColor = SLPal.silviriumDarkColor;
                        shootEffect = SLFx.silviriumHit1Effect;
                        status = SLStatusEffects.disrupted;
                        statusDuration = 600f;
                        sprite = "circle-bullet";
                    }};
                }}
            );
        }};
        silvbane = new UnitType("Silvbane"){{
            alwaysUnlocked = true;
            constructor = LegsUnit::create;
            speed = 1f;
            accel = 0.3f;
            rotateMoveFirst = true;
            hitSize = 16;
            health = 1255;
            armor = 7;
            createScorch = false;
            immunities.add(SLStatusEffects.disrupted);
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
            itemCapacity = 50;
            outlineRadius = 0;
            weapons.add(
                new Weapon("Silvbane-side"){{
                    x = 8.75f;
                    y = 7.5f;
                    shootY = 0;
                    reload = 90;
                    rotationLimit = 90f;
                    rotateSpeed = 5f;
                    shootCone = 30f;
                    rotate = alternate = mirror = true;
                    top = false;
                    shoot.shots = 3;
                    shoot.shotDelay = 20f;
                    parts.add(
                        new ShapePart(){{
                            color = SLPal.silviriumColor;
                            colorTo = SLPal.silviriumOtherColor;
                            progress = PartProgress.recoil.inv();
                            hollow = mirror = true;
                            sides = 3;
                            rotateSpeed = 6;
                            radius = 0;
                            radiusTo = 4;
                            stroke = 0;
                            strokeTo = 1;
                            x = 0;
                            moveX = 0;
                            y = 0;
                        }}
                    );
                    bullet = new BasicBulletType(2.5f,20f){{
                        frontColor = trailColor  = SLPal.silviriumColor;
                        trailLength = 8;
                        lifetime = 80;
                        homingPower = 0.1f;
                        homingRange = 48;
                        range = 200;
                        weaveScale = 5f;
                        weaveMag = 5f;
                    }};
                }},
                new Weapon("Silvbane-missile"){{
                    x = 0;
                    y = 3;
                    shootY = 0;
                    reload = 240;
                    rotationLimit = 25f;
                    rotateSpeed = 5f;
                    shootCone = 10f;
                    recoilTime = 220;
                    recoil = 8f;
                    rotate = alternate = true;
                    top = mirror = false;
                    parts.add(
                        new RegionPart(){{
                            name = "sli-Silvsile";
                            progress = PartProgress.recoil.curve(Interp.exp5).inv();
                            layerOffset = -0.1f;
                            mirror = true;
                            x = y = moveX = growX = 0;
                            moveY = -9f;
                            growY = -1;
                        }}
                    );
                    bullet = new /*Prox*/BulletType(){{
                        spawnUnit = silvsile = new MissileUnitType("Silvsile"){{
                            lifetime = 50;
                            speed = 4;
                            outlines = false;
                            maxRange = 32;
                            controller = u -> new ProxMissileAI();
                            weapons.add(
                                new Weapon("Silvsile-exp"){{
                                    x = y = 0;
                                    reload = 1800;
                                    shootCone = 360f;
                                    shootOnDeath = true;
                                    mirror = false;
                                    bullet = new ExplosionBulletType(controller instanceof MissileAI ? 400 : 0,40){{
                                        buildingDamageMultiplier = 0.5f;
                                        range = 32;
                                        splashDamagePierce = pierceBuilding = collidesAir = collidesGround = true;
                                        hittable = false;
                                        smokeEffect = Fx.none;
                                        status = SLStatusEffects.disrupted;
                                        statusDuration = 300f;
                                    }};
                                }}
                            );
                        }};
                    }};
                }}
            );
        }};

        silvruner = new UnitType("Silvruner"){{
            alwaysUnlocked = true;
            constructor = LegsUnit::create;
            speed = 1.3f;
            accel = 0.2f;
            rotateMoveFirst = true;
            hitSize = 28;
            health = 7900;
            armor = 16;
            createScorch = false;
            immunities.add(SLStatusEffects.disrupted);
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
            itemCapacity = 60;
            outlineRadius = 0;
            weapons.add(
                new HealtActivationWeapon("sil-Silvruner-machingun", 1f, 0.1f){{
                    x = 0;
                    y = 2;
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
                    bullet = new BasicBulletType(6,30){{
                        buildingDamageMultiplier = 0.5f;
                        lifetime = 20;
                        range = 120;
                        pierce = pierceBuilding = collidesAir = collidesGround = true;
                        pierceCap = -1;
                        pierceDamageFactor = 0.01f;
                        hittable = false;
                        smokeEffect = Fx.none;
                        frontColor = SLPal.silviriumColor;
                        backColor = SLPal.silviriumDarkColor;
                        shootEffect = SLFx.silviriumHit1Effect;
                        status = SLStatusEffects.disrupted;
                        statusDuration = 600f;
                        sprite = "circle-bullet";
                    }};
                }},
                new HealtActivationWeapon("Silvruner-Explosion", 0.1f, 0f){{
                    x = 0;
                    y = 0;
                    shootY = 0;
                    reload = 30f;
                    rotate = alternate = mirror = false;
                    shootCone = 360f;
                    shootOnDeath = true;
                    shoot.firstShotDelay = 180;
                    shootStatus = SLStatusEffects.rush;
                    shootStatusDuration = 180;
                    bullet = new ExplosionBulletType(220,120){{
                        buildingDamageMultiplier = 0.5f;
                        range = 40;
                        splashDamagePierce = pierceBuilding = collidesAir = collidesGround = true;
                        hittable = false;
                        smokeEffect = Fx.none;
                        status = SLStatusEffects.disrupted;
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
            speed = 1.2f;
            accel = 0.3f;
            rotateMoveFirst = true;
            hitSize = 16;
            health = 480;
            armor = 5;
            createScorch = false;
            immunities.add(SLStatusEffects.disrupted);
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
            itemCapacity = 30;
            outlineRadius = 0;
        }};
        silvone = new UnitType("Silvone"){{
            alwaysUnlocked = true;
            constructor = UnitEntity::create;
            flying = true;
            immunities.add(SLStatusEffects.disrupted);
            outlines = false;
            health = 40;
            speed = 4f;
            accel = 0.1f;
            drag = 0.005f;
            range = 64;
            maxRange = 64;
            engineSize = 0.01f;
            hitSize = 4;
            weapons.add(
                new Weapon("sil-piercer"){{
                    x = 0f;
                    y = 0f;
                    mirror = false;
                    top = false;
                    reload = 120f;
                    rotate = false;
                    bullet = new RailBulletType(){{
                        shootEffect = Fx.none;
                        length = 64;
                        range = 64;
                        pointEffectSpace = 16f;
                        pierceEffect = SLFx.silviriumRailHit;
                        pointEffect = SLFx.silviriumRail;
                        hitEffect = SLFx.silviriumWave1Effect;
                        smokeEffect = Fx.shootSmall;
                        status = SLStatusEffects.disrupted;
                        statusDuration = 180f;
                        damage = 60;
                        pierceDamageFactor = 0.5f;
                    }};
                }}
            );
        }};
        silvioros = new UnitType("Silvioros"){{
            alwaysUnlocked = true;
            constructor = PayloadUnit::create;
            flying = true;
            payloadCapacity = 64/*Mathf.pow(1*8,2) || (1*8)^2*/;
            immunities.add(SLStatusEffects.disrupted);
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
                    color = SLPal.silviriumColor;
                    colorTo = SLPal.silviriumColor;
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
                }}
            );
            weapons.add(new Weapon("Silvioros-cannon"){{
                x = 0;
                y = 9;
                shootY = 0;
                mirror = false;
                top = false;
                reload = 20f;
                rotate = true;
                rotateSpeed = 15f;
                recoil = 10;
                rotationLimit = 300f;
                range = 240f;
                maxRange = 240f;
                bullet = new BasicBulletType(6f, 8f){{
                    width = 8f;
                    lifesteal = 0.1f;
                    height = 12f;
                    range = 240f;
                    collidesAir = false;
                    collidesGround = pierce = true;
                    frontColor = SLPal.silviriumColor;
                    backColor = SLPal.silviriumDarkColor;
                    trailColor = SLPal.silviriumOtherColor;
                    trailLength = 8;
                    trailWidth = 1;
                    shootEffect = Fx.shootSmall;
                    lifetime = 40f;
                    sprite = "shell";
                }
                @Override
                public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
                    super.hitTile(b, build, x, y, initialHealth, direct);
                    build.applySlowdown(0.01f, 60f);
                }};
            }});
        }};
        //TODO:make this a proper class
        silvot = new UnitType("Silvot"){{
            alwaysUnlocked = true;
            constructor = LegsUnit::create;
            hovering = true;
            canDrown = false;
            immunities.add(SLStatusEffects.disrupted);
            outlines = false;
            health = 145f;
            speed = 3.5f;
            accel = 0.25f;
            drag = 0.1f;
            range = 200f;
            hitSize = 9f;
            abilities.add(new disruptPulseAbility(90,80,60));
            weapons.add(
                new Weapon("Silvot-missiles"){{
                    x = 0;
                    y = 12;
                    reload = 180;
                    mirror = false;
                    parts.add(
                        new ShapePart(){{
                            color = SLPal.silviriumDarkestColor;
                            colorTo = SLPal.silviriumligthColor;
                            progress = PartProgress.reload;
                            hollow = mirror = true;
                            sides = 5;
                            rotateSpeed = 3;
                            radius = 0;
                            radiusTo = 4;
                            stroke = 0;
                            strokeTo = 1;
                            x = 0;
                            moveX = 0;
                            y = 0;
                        }}
                    );
                    shootCone = 30;
                    inaccuracy = 40;
                    shoot.firstShotDelay = 120;
                    shoot.shotDelay = 9;
                    shoot.shots = 10;
                    bullet = new MissileBulletType(12,20){{
                        chargeEffect = SLFx.silviriumChargeEffect;
                        backColor = SLPal.silviriumColor;
                        frontColor = SLPal.silviriumDarkColor;
                        lifetime = 17;
                    }};                    
                }}
            );
        }
        @Override
        public void draw(Unit unit){
            float scl = xscl;
            if(unit.inFogTo(Vars.player.team())) return;

            if(buildSpeed > 0f){
                unit.drawBuilding();
            }

            if(unit.mining()){
                drawMining(unit);
            }

            boolean isPayload = !unit.isAdded();

            float z =
                isPayload ? Draw.z() :
                //dead flying units are assumed to be falling, and to prevent weird clipping issue with the dark "fog", they always draw above it
                unit.elevation > 0.5f || (flying && unit.dead) ? (flyingLayer) :
                groundLayer + Mathf.clamp(hitSize / 4000f, 0, 0.01f);

            if(!isPayload && (unit.isFlying() || shadowElevation > 0)){
                Draw.z(Math.min(Layer.darkness, z - 1f));
                drawShadow(unit);
            }

            Draw.z(z - 0.02f);

            if(unit instanceof Legsc && !isPayload && unit.floorOn() != null && unit.floorOn().isLiquid){
                ((Unit & Legsc) unit).resetLegs();
                drawLegs((Unit & Legsc)unit);
            }else{
                ((Unit & Legsc) unit).resetLegs(0);
            }

            Draw.z(Math.min(z - 0.01f, Layer.bullet - 1f));

            if(drawSoftShadow) drawSoftShadow(unit);

            Draw.z(z);

            if(drawBody) drawOutline(unit);
            drawWeaponOutlines(unit);
            if(engineLayer > 0) Draw.z(engineLayer);
            if(trailLength > 0 && !naval && (unit.isFlying() || !useEngineElevation)){
                drawTrail(unit);
            }
            if(engines.size > 0) drawEngines(unit);
            Draw.z(z);
            if(drawBody) drawBody(unit);
            if(drawCell && !(unit instanceof Crawlc)) drawCell(unit);
            Draw.scl(scl); //TODO this is a hack for neoplasm turrets
            drawWeapons(unit);
            if(drawItems) drawItems(unit);
            if(!isPayload){
                drawLight(unit);
            }

            if(unit.shieldAlpha > 0 && drawShields){
                drawShield(unit);
            }

            //TODO how/where do I draw under?
            if(parts.size > 0){
                for(int i = 0; i < parts.size; i++){
                    var part = parts.get(i);

                    WeaponMount mount = unit.mounts.length > part.weaponIndex ? unit.mounts[part.weaponIndex] : null;
                    if(mount != null){
                        DrawPart.params.set(mount.warmup, mount.reload / mount.weapon.reload, mount.smoothReload, mount.heat, mount.recoil, mount.charge, unit.x, unit.y, unit.rotation);
                    }else{
                        DrawPart.params.set(0f, 0f, 0f, 0f, 0f, 0f, unit.x, unit.y, unit.rotation);
                    }

                    if(unit instanceof Scaled s){
                        DrawPart.params.life = s.fin();
                    }

                    applyColor(unit);
                    part.draw(DrawPart.params);
                }
            }

            if(!isPayload){
                for(Ability a : unit.abilities){
                    Draw.reset();
                    a.draw(unit);
                }
            }

            Draw.reset();
        }};

        silvistar = new UnitType("Silvistar"){{
            alwaysUnlocked = true;
            constructor = PayloadUnit::create;
            circleTarget = flying = true;
            payloadCapacity = 256/*Mathf.pow(2*8,2) || (2*8)^2*/;
            hitSize = 20;
            immunities.add(SLStatusEffects.disrupted);
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
                    color = SLPal.starOrangeColor;
                    sides = 6;
                    radius = 12;
                    x = -4.5f;
                    y = -3.5f;
                    layer = 59.95f;
                }},
                new HoverPart(){{
                    color = SLPal.starOrangeColor;
                    sides = 6;
                    radius = 12;
                    x = -4.5f;
                    y = 3.5f;
                    layer = 59.95f;
                }},
                new HoverPart(){{
                    color = SLPal.silviriumColor;
                    sides = 6;
                    radius = 12;
                    x = 4.5f;
                    y = -3.5f;
                    layer = 59.95f;
                }},
                new HoverPart(){{
                    color = SLPal.silviriumColor;
                    sides = 6;
                    radius = 12;
                    x = 4.5f;
                    y = 3.5f;
                    layer = 59.95f;
                }}
            );
            weapons.add(
                new Weapon("Silvirium-orbs"){{
                    x = 7f;
                    y = 0f;
                    mirror = false;
                    top = false;
                    reload = 36f;
                    rotate = false;
                    inaccuracy = 0f;
                    bullet = new BasicBulletType(0.0795f, 5f){{
                        keepVelocity = false;
                        splashDamagePierce = collides = true;
                        mixColorFrom = SLPal.silviriumColor;
                        mixColorTo = SLPal.silviriumMidColor;
                        trailColor = SLPal.silviriumColor;
                        shootEffect = SLFx.silviriumHit1Effect;
                        status = SLStatusEffects.disrupted;
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
                        lifetime = 40f;
                    }};
                }},
                new Weapon("Static-star-mines"){{
                    x = -7f;
                    y = 0f;
                    mirror = false;
                    top = false;
                    reload = 112f;
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
                        mixColorFrom = SLPal.starOrangeColor;
                        mixColorTo = SLPal.starRedDarkColor;
                        shootEffect = Fx.shootSmall;
                        lifetime = 200f;
                    }};
                }}
            );
        }};
        star1 = new UnitType("star1"){{
            alwaysUnlocked = true;
            constructor = MechUnit::create;
            health = 160f;
            armor = 0;
            hitSize = 8;
            itemCapacity = 8;
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
                    bullet = new ContinuousFlameBulletType(){{
                        length = 80f;
                        range = 80f;
                        width = 8f;
                        damageInterval = 0.1f;
                        damage = 0.03f /*21 * damageInterval / 60f*/;
                        buildingDamageMultiplier = 2f;
                        pierceBuilding = true;
                        status = StatusEffects.burning;
                        statusDuration = 60f;
                        knockback = 0;
                        impact = true;
                        lifetime = 10f;
                        hitEffect = SLFx.starHit;
                        lightStroke = 20f;
                        oscScl = 0.04f;
                        oscMag = 0.02f;
                        drawFlare = false;
                        divisions = 2;
                        colors = new Color[]{
                            SLPal.starRedDarkColor,
                                SLPal.starRedColor,
                                SLPal.starOrangeDarkColor,
                                SLPal.starOrangeColor,
                                Color.white.cpy()
                        };
                        lengthWidthPans = new float[]{
                                1.0f,1.0f,0.6f,
                                0.8f,1.0f,0.5f,
                                0.6f,0.9f,0.4f,
                                0.5f,0.8f,0.3f,
                                0.3f,0.5f,0.2f
                        };
                    }};
                }}
            );
        }};
        star2 = new UnitType("star2"){{
            alwaysUnlocked = true;
            constructor = MechUnit::create;
            health = 360f;
            armor = 0;
            hitSize = 12;
            itemCapacity = 24;
            outlines = false;
            speed = 6f;
            accel = 0.08f;
            drag = 0.07f;
            rotateMoveFirst = true;
            hoverable = false;
            mechFrontSway = 0.4f;
            mechSideSway = 0.2f;
            mechStride = 2f;
            weapons.add(
                new Weapon("sil-star2-laser"){{
                    x = 0;
                    y = 4;
                    shootX = 0;
                    shootY = 0;
                    reload = 120;
                    shootCone = 25;
                    minWarmup = 0.99f;
                    mirror = alternate = predictTarget = false;
                    top = rotate = true;
                    bullet = new LaserBulletType(){{
                        length = 80f;
                        range = 80f;
                        width = 12f;
                        damage = 100f;
                        buildingDamageMultiplier = 4f;
                        pierceBuilding = true;
                        status = StatusEffects.burning;
                        statusDuration = 60f;
                        knockback = 10;
                        impact = true;
                        lifetime = 20f;
                        hitEffect = SLFx.starHit;
                        colors = new Color[]{
                                SLPal.starRedDarkColor,
                                SLPal.starRedColor,
                                SLPal.starOrangeDarkColor,
                                SLPal.starOrangeColor,
                                Color.white.cpy()
                        };
                    }};
                }}
            );
        }};
        star6 = new UnitType("star6"){{
            alwaysUnlocked = true;
            constructor = MechUnit::create;
            health = 55000f;
            armor = -1.5f;
            hitSize = 51;
            itemCapacity = 372;
            outlines = false;
            speed = 1f;
            accel = 0.2f;
            drag = 0.1f;
            rotateMoveFirst = true;
            hoverable = false;
            mechFrontSway = 1.2f;
            mechSideSway = 0.9f;
            mechStride = 8f;
            abilities.add(new RegenAbility(){{
                percentAmount = 0.01f;
            }

            @Override
            public void addStats(Table t){
                if(Core.bundle.has(getBundle() + ".description")){
                    t.add(Core.bundle.get(getBundle() + ".description")).wrap().width(descriptionWidth);
                    t.row();
                }

                boolean flat = amount >= 0.001f;
                boolean percent = percentAmount >= 0.001f;

                if(flat || percent){
                    t.add(abilityStat("regen",
                        (flat ? Strings.autoFixed(amount * 60f, 4) + (percent ? " [lightgray]+[stat] " : "") : "")
                            + (percent ? Strings.autoFixed(percentAmount , 4) + "%" : "") + "[lightgray].\nNo shooting multiplier[stat]4x"
                    ));
                }
            }

            @Override
            public void update(Unit unit){
                unit.heal(((unit.maxHealth * percentAmount / 100f + amount) * (unit.isShooting()?1:4)) * Time.delta);
            }},
            new HeatAreaAbility()
            );

            weapons.add(
                new HealtActivationWeapon("sil-star2-laser", /*0.4f*/1f, 0f){{
                    x = 0;
                    y = 6;
                    shootX = shootY = 0;
                    reload = /*120f*/0;
                    shootCone = 25;
                    minWarmup = 0.99f;
                    rotate = mirror = alternate = alwaysContinuous = predictTarget = false;
                    top = continuous = true;
                    bullet = new ContinuousFlameBulletType(){{
                        recoil = 20f;
                        length = 240f;
                        range = 240f;
                        width = 16f;
                        damageInterval = 1.5f;
                        damage = 10f;
                        buildingDamageMultiplier = 5f;
                        pierceBuilding = true;
                        status = StatusEffects.burning;
                        statusDuration = 600f;
                        knockback = 40f;
                        impact = true;
                        lifetime = 600f;
                        lengthInterp = Interp.one;
                        hitEffect = SLFx.starHit;
                        lightStroke = 20f;
                        lightColor = SLPal.starRedColor;
                        oscScl = 1.5f;
                        oscMag = 0.01f;
                        drawFlare = false;
                        divisions = 4;
                        bulletInterval = 12f;
                        intervalBullet = new BasicBulletType(4,80){{
                            lifetime = 60f;
                            homingPower = 0.1f;
                            homingDelay = 40f;
                            homingRange = 80f;
                            parts.addAll(
                                new FlarePart(){{
                                    color1 = SLPal.starRedColor;
                                    color2 = SLPal.starOrangeColor;
                                    progress = PartProgress.time;
                                    sides = 5;
                                    radius = 8;
                                    radiusTo = 32;
                                    rotation = -90;
                                    rotMove = 90;
                                    spinSpeed = 0;
                                    x = y = 0f;
                                    layer = 59.95f;
                                }});
                        }};
                        colors = new Color[]{
                            SLPal.starRedDarkColor,
                            SLPal.starRedColor,
                            SLPal.starOrangeDarkColor,
                            SLPal.starOrangeColor,
                            Color.white.cpy()
                        };
                        lengthWidthPans = new float[]{
                            1.2f,1.2f,0.3f,
                            1.0f,1.0f,0.3f,
                            0.9f,0.9f,0.3f,
                            0.7f,0.7f,0.3f,
                            0.4f,0.4f,0.3f
                        };
                    }};
                }}
            );
        }
        @Override
        public void update(Unit unit){
            for(StatusEffect s:Vars.content.statusEffects()){
                if(s.dynamic||unit.isImmune(s)) return;
                immunities.add(s);
            }

            unit.dragMultiplier = 1;
            unit.speedMultiplier = unit.speedMultiplier < 1 ? 1 : unit.speedMultiplier;
            unit.damageMultiplier = unit.damageMultiplier < 1 ? 1 : unit.damageMultiplier;
            unit.reloadMultiplier = unit.reloadMultiplier < 1 ? 1 : unit.reloadMultiplier;
            unit.healthMultiplier = unit.healthMultiplier < 1 ? 1 : unit.healthMultiplier;
            if(unit.mounts().length != unit.type.weapons.size) unit.setupWeapons(unit.type);
            if(unit.abilities.length != unit.type.abilities.size || (unit.abilities.length > 0 && unit.abilities[0] instanceof EmptyDataAbility)){
                var old = unit.abilities;
                unit.abilities = new Ability[unit.type.abilities.size];
                for(int i = 0; i < unit.type.abilities.size; i ++){
                    unit.abilities[i] = unit.type.abilities.get(i).copy();
                    if(i < old.length){
                        unit.abilities[i].data = old[i].data;
                    }
                }
            }
            super.update(unit);
        }};
    }
}
