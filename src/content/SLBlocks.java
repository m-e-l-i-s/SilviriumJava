package content;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.UnitSorts;
import mindustry.entities.Units;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.ShootSpread;
import mindustry.entities.part.DrawPart.PartProgress;
import mindustry.entities.part.RegionPart;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.type.*;

import arc.graphics.*;
import arc.struct.EnumSet;
import arc.util.Time;
import arc.util.Tmp;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;

import world.meta.SLAttributes;


public class SLBlocks {
    public static Block
    silvOre,slivFloor,slivWall,
    liqsilvfac,silvingfac,starfac,
    silvDrill,
    silvFacT1, silvFacT2, silvFacT3, silvFacT4,starFacT1,
    ABT,HPT,SST,ST,
    decoy;
    
    public static void load(){
        //ore
        silvOre = new OreBlock("ore-silvirium", SLItems.silvirium){{
            alwaysUnlocked = true;
            oreDefault = emitLight = true;
            oreScale = 10f;
            oreThreshold = 0.85f;
            lightRadius = 9f;
            lightColor = SLPal.silviriumColor;
            variants = 2;
        }};
        silvOre = new OreBlock("ore-shapinite", SLItems.shapinite){{
            alwaysUnlocked = true;
            oreDefault = emitLight = true;
            oreScale = 20f;
            oreThreshold = 0.9f;
            lightRadius = 0f;
            lightColor = SLPal.mgray;
            variants = 2;
        }};
        //floors
        slivFloor = new Floor("silvirium-floor", 2){{
            liquidDrop = SLliquids.liquidSilvirium;
            liquidMultiplier = 0.01f;//1500 times less that a LSF(Liquid Silvirium Factory)
            walkEffect = SLFx.silvAmb;
            status = SLStatusEffects.disrupted;
            statusDuration = 6f;
            dragMultiplier = 2;
            speedMultiplier = 0.5f;
            attributes.set(SLAttributes.silvirium,1f);
        }};
        //envirmental walls
        slivWall = new StaticWall("silvirium-wall"){{
            variants = 2;
            slivFloor.asFloor().wall = this;
            attributes.set(SLAttributes.silvirium,1f);
        }};

        //unit factory
        silvFacT1 = new UnitFactory("silvirium-molder"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.lead, 40),
                new ItemStack(SLItems.shapinite, 70)
            });
            size = 2;
            health = 220;
            consumesPower = connectedPower = conductivePower = false;
            consumeLiquid(SLliquids.liquidSilvirium, 0.1f).boost();
            plans.addAll(
                new UnitPlan(
                    SLUnits.silvirror, 1200,
                    new ItemStack[]{
                        new ItemStack(SLItems.silvirium, 30),
                        new ItemStack(SLItems.shapinite, 20)
                    }
                ),
                new UnitPlan(
                    SLUnits.silvone, 300,
                    new ItemStack[]{
                        new ItemStack(SLItems.silvirium, 20),
                        new ItemStack(SLItems.shapinite, 10)
                    }
                ),
                new UnitPlan(
                    SLUnits.silvioros, 1500,
                    new ItemStack[]{
                        new ItemStack(SLItems.silvirium, 20),
                        new ItemStack(SLItems.shapinite, 10),
                        new ItemStack(SLItems.silviriumIng, 10),
                    }
                ),
                new UnitPlan(
                    SLUnits.silvanon, 900,
                    new ItemStack[]{
                        new ItemStack(SLItems.silvirium, 25),
                        new ItemStack(SLItems.silviriumIng, 10),
                    }
                )
            );
        }};
        silvFacT2 = new Reconstructor("silvirium-remolder"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(SLItems.shapinite, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 3;
            consumePower(0f);
            consumeLiquid(SLliquids.liquidSilvirium, 0.1f);
            health = 495;
            consumeItems(new ItemStack[]{
                new ItemStack(SLItems.silvirium, 80),
                new ItemStack(SLItems.shapinite, 40),
            });
            constructTime = 1200f;
            addUpgrade(SLUnits.silvirror, SLUnits.silvokeor);
        }};
        
        silvFacT3 = new Reconstructor("silvirium-regrower"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.graphite, 40),
                new ItemStack(SLItems.shapinite, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 4;
            consumePower(0f);
            consumeLiquid(SLliquids.liquidSilvirium, 0.25f);
            health = 880;
            consumeItems(new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(SLItems.shapinite, 150),
            });
            constructTime = 2400f;
            addUpgrade(SLUnits.silvokeor, SLUnits.silvbane);
        }};
        
        silvFacT4 = new Reconstructor("silvirium-reforge"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(SLItems.shapinite, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 5;
            consumePower(0f);
            consumeLiquid(SLliquids.liquidSilvirium, 0.75f);
            health = 1375;
            consumeItems(new ItemStack[]{
                new ItemStack(SLItems.silvirium, 1200),
                new ItemStack(SLItems.shapinite, 400),
            });
            constructTime = 3300f;
            addUpgrade(SLUnits.silvbane, SLUnits.silvruner);
        }};
        
        starFacT1 = new UnitFactory("star-molder"){{
            payloadSpeed = 500;
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.lead, 40),
                new ItemStack(SLItems.shapinite, 70)
            });
            size = 2;
            health = 220;
            consumePower(0f);
            plans.addAll(
                new UnitPlan(
                    SLUnits.star1, 0,
                    new ItemStack[]{
                      new ItemStack(SLItems.starFrag, 30),
                      new ItemStack(SLItems.shapinite, 20)
                    }
                )
            );
        }};
        //factory
        liqsilvfac = new GenericCrafter("liquid-silvirium-factory"){{
            alwaysUnlocked = true;
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(SLItems.shapinite, 70),
                new ItemStack(SLItems.silviriumIng, 50)
            });
            outputLiquid = new LiquidStack(SLliquids.liquidSilvirium, 0.25f);
            size = 2;
            hasPower = hasItems = hasLiquids = solid = outputsLiquid = true;
            rotate = false;
            envEnabled = Env.any;
            liquidCapacity = 12f;
            craftTime = 60;
            lightLiquid = SLliquids.liquidSilvirium;

            consumePower(0.5f);
            consumeItem(SLItems.silvirium);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawLiquidRegion(SLliquids.liquidSilvirium){{
                suffix = "-liquid";
                }},
                new DrawRegion("-rot"){{
                        layer = 40.4f;
                        rotateSpeed = 12f;
                }},
                new DrawSoftParticles(){{
                        color = SLPal.silviriumColor;
                        color2 = SLPal.silviriumColor;
                        alpha = 0.3f;
                        particles = 7;
                        particleLife = 90f;
                        particleRad = 4f;
                        particleSize = 3f;
                        fadeMargin = 0.2f;
                        rotateScl = 0.4f;
                        particleInterp = Interp.one;
                }},
                new DrawRegion("-top"){{
                        layer = 40.5f;
                }}
            );
        }};
        
        silvingfac = new AttributeCrafter("silvirium-ingot-factory"){{
            alwaysUnlocked = true;
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 100),
                new ItemStack(SLItems.shapinite, 50),
            });
            size = 2;
            hasPower = hasItems = solid = true;
            rotate = false;
            envEnabled = Env.any;
            itemCapacity = 16;
            craftTime = 600;
            attribute = SLAttributes.silvirium;
            maxBoost = 2f;
            boostScale = 0.15f;
            minEfficiency = -1f;

            consumePower(0.5f);
            consumeItem(SLItems.silvirium,4);
            outputItem = new ItemStack(SLItems.silviriumIng, 1);
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("44fff7")));
        }};
        
        starfac = new GenericCrafter("star-factory"){{
            alwaysUnlocked = true;
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(Items.metaglass, 50),
                new ItemStack(SLItems.shapinite, 110),
            });
            size = 2;
            hasPower = hasItems = hasLiquids = solid = true;
            rotate = false;
            envEnabled = Env.any;
            itemCapacity = 1;
            craftTime = 600;
            flammabilityScale = 0;

            consumePower(5f);
            outputItem = new ItemStack(SLItems.starFrag, 1);
        }};
        //drills
        silvDrill = new Drill("silvirium-drill"){{
            requirements(Category.production, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 50)
            });
            health = 200;
            tier = 3;
            drillTime = 700f;
            hardnessDrillMultiplier = 200f;
            size = 3;

            consumeLiquid(SLliquids.liquidSilvirium, 0.1f).boost();
        }};
        
        //turrets
        HPT = new LiquidTurret("higth-presure-turret"){{
            alwaysUnlocked = true;
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 120),
                new ItemStack(Items.graphite, 90),
                new ItemStack(SLItems.shapinite, 100),
                new ItemStack(Items.metaglass, 60)
            });
            ammo(
                SLliquids.liquidSilvirium,new LiquidBulletType(){{
                    speed = 22.5f;
                    damage = 10;
                    lifetime = 65;
                    knockback = 0.2f;
                    drag = 0.1f;
                    layer = Layer.bullet - 1f;
                    status = SLStatusEffects.disrupted;
                    statusDuration = 6000;
                    fragBullet = new BulletType(){{
                        puddleLiquid = SLliquids.liquidSilvirium;
                        puddleAmount = 48;
                        puddles = 1;
                        puddleRange = 48;
                        instantDisappear = true;
                    }};
                    
                }}
            );
            shoot = new ShootSpread(21,0.3f);
            inaccuracy = 0.2f;
            size = 3;
            recoil = 1f;
            reload = 180f;
            inaccuracy = 0f;
            shootCone = 5f;
            liquidCapacity = 10f;
            rotateSpeed = 3f;
            range = 224f;
            health = 1000;
            flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
            shootEffect = Fx.shootLiquid;
        }};
        ST = new ItemTurret("silvirium-turret"){{
            alwaysUnlocked = true;
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 110),
            });
            ammoTypes.putAll(
            SLItems.silvirium, new BasicBulletType(2f,11f){{
                lifetime = 60;
                width = 6;
                height = 10;
                pierce = pierceBuilding = true;
                pierceCap = 2;
                frontColor = SLPal.silviriumColor;
                backColor = SLPal.silviriumColor;
                status = SLStatusEffects.disrupted;
                trailColor  = SLPal.silviriumColor;
                trailLength = 6;
                trailWidth = 1;
                statusDuration = 300;
            }});
            size = 1;
            recoil = 1f;
            reload = 30f;
            inaccuracy = 0f;
            shootCone = 2f;
            maxAmmo = 10;
            rotateSpeed = 5f;
            range = 120f;
            health = 240;
            flags = EnumSet.of(BlockFlag.turret);
            coolant = consume(new ConsumeLiquidFilter(liquid -> liquid==SLliquids.liquidSilvirium, 0.4f));
            coolantMultiplier = 10f;
            coolEffect = SLFx.sliviriumSpark;
            shootEffect = SLFx.silviriumHit1Effect;
        }};
        SST = new ItemTurret("sand-storm-turret"){{
            predictTarget = false;
            alwaysUnlocked = true;
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(Items.sand, 300),
                new ItemStack(SLItems.shapinite, 440)
            });
            ammoTypes.putAll(
                Items.sand, new PointBulletType(){{
                    damage = 20;
                    splashDamage = 80;
                    splashDamageRadius = 48;
                    trailEffect = SLFx.sndLine;
                    ammoMultiplier = 1;
                    trailSpacing = 20;
                    spawnBullets.add(
                        new ExplosionBulletType(20,240){{
                            killShooter = false;
                            fragBullets = 12;
                            shootEffect = Fx.dynamicExplosion;
                            fragBullet = new BasicBulletType(2,20){{
                                lifetime = 300;
                                homingPower = 0.01f;
                                homingRange = 80;
                                pierce = pierceBuilding = true;
                            }
                            @Override
                            public void updateHoming(Bullet b){
                                if(homingPower > 0.0001f && b.time >= homingDelay){
                                    float realAimX = b.aimX < 0 ? b.x : b.aimX;
                                    float realAimY = b.aimY < 0 ? b.y : b.aimY;

                                    Teamc target;
                                    //home in on allies if possible
                                    if(heals()){
                                        target = Units.closestTarget(null, realAimX, realAimY, homingRange, b.team,
                                            e -> e.checkTarget(collidesAir, collidesGround) && e.team != b.team && !b.hasCollided(e.id),
                                            t -> collidesGround && (t.team != b.team || t.damaged()) && !b.hasCollided(t.id));
                                    }else{
                                        if(b.aimTile != null && b.aimTile.build != null && b.aimTile.build.team != b.team && collidesGround && !b.hasCollided(b.aimTile.build.id)){
                                            target = b.aimTile.build;
                                        }else{
                                            target = Units.closestTarget(b.team, realAimX, realAimY, homingRange,
                                                e -> e != null && e.checkTarget(collidesAir, collidesGround) && !b.hasCollided(e.id),
                                                t -> t != null && collidesGround && !b.hasCollided(t.id));
                                        }
                                    }

                                    if(target != null){
                                        b.vel.setAngle(Angles.moveToward(b.rotation(), b.angleTo(target), homingPower * Time.delta * 50f));
                                    }else if(b.owner instanceof Healthc h && h.isValid()){
                                        Tmp.v1.set(h).sub(b);
                                        Tmp.v1.rotate(90f * Mathf.lerp(0f, 1f, 1f - Mathf.clamp((Tmp.v1.len() - Mathf.randomSeed(b.id,110,220)) / 2*(b.id%2==0?1:-1))));
                                        b.vel.add(Tmp.v1.limit(speed * 1 * Time.delta)).limit(speed);
                                    }
                                }
                            }}; 
                        }}
                    );
                }}
            );
            size = 4;
            recoil = 1f;
            reload = 280f;
            inaccuracy = 0f;
            shootCone = .1f;
            maxAmmo = 18;
            ammoPerShot = 9;
            rotateSpeed = 2f;
            range = 280f;
            health = 2200;
            liquidCapacity = 40f;
            coolant = consume(new ConsumeLiquidFilter(liquid -> true, 1f));
            coolantMultiplier = 2f;
            flags = EnumSet.of(BlockFlag.turret);
            shootEffect = SLFx.silviriumHit1Effect;
        }};
        
        ABT = new ItemTurret("anti-building-turret"){{
            alwaysUnlocked = true;
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(Items.copper, 3200),
                new ItemStack(Items.lead, 3200),
                new ItemStack(Items.silicon, 900),
                new ItemStack(SLItems.shapinite, 300),
                new ItemStack(Items.graphite, 700)
            });
            coolantMultiplier = 0.1f;
            liquidCapacity = 900f;
            coolant = consumeCoolant(2f);
            range = 1600;
            size = 6;
            reload = 1200;
            ammoPerShot = 20;
            maxAmmo = 20;
            recoil = 12;
            flags = EnumSet.of(BlockFlag.turret);
            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-barrel"){{
                    progress = PartProgress.heat;
                    mirror = true;
                    under = true;
                    x = -2;
                    y = 18;
                    moveX = -7;
                    moveY = -12;
                    moveRot = 25;
                }});
            }};
            
            unitSort = UnitSorts.strongest;
            unitFilter = (u) -> {
                return false;
            };
            buildingFilter = (b) -> {
                return this.targetUnderBlocks || !b.block.underBullets;
            };
            ammoTypes.putAll(
            Items.silicon, new BasicBulletType(4, 700){{
                ammoMultiplier = 1;
                lifetime = 400;
                width = 16;
                height = 12;
                pierce = pierceBuilding = true;
                absorbable = false;
                pierceCap = 3;
                frontColor = Items.silicon.color;
                backColor = Items.silicon.color.cpy().mul(0.8f);
                homingPower = 0.05f;
                homingRange = 24;
                reloadMultiplier = 4;
            }},
            Items.thorium, new BasicBulletType(16, 5000){{
                ammoMultiplier = 1;
                lifetime = 100;
                width = 16;
                height = 12;
                pierce = pierceBuilding = true;
                absorbable = false;
                pierceDamageFactor = 0.01f;
                frontColor = Items.thorium.color;
                backColor = Items.thorium.color.cpy().mul(0.8f);
            }},
            SLItems.starFrag, new BasicBulletType(8, 2000){{
                ammoMultiplier = 1;
                lifetime = 195;
                width = 16;
                height = 12;
                splashDamageRadius = 40;
                splashDamage = 3000;
                splashDamagePierce = true;
                absorbable = pierce = pierceBuilding = false;
                fragBullets = 5;
                fragSpread = 72;
                fragOffsetMin = fragOffsetMax = fragRandomSpread = 0;
                fragVelocityMin = fragVelocityMax = 1f;
                fragBullet = new ShrapnelBulletType(){{
                    speed = -1;
                    damage = 3000;
                    length = 80;
                    width = 16;
                    fromColor = SLPal.starOrangeColor;
                    toColor = SLPal.starRedColor;
                    pierce = pierceBuilding = hitLarge = true;
                }};
                frontColor = SLItems.starFrag.color;
                backColor = SLItems.starFrag.color.cpy().mul(0.8f);
            }});
        }@Override
            public void init(){
                super.init();
                placeOverlapRange = -50f;
            }
        };
        //util
        decoy = new PowerTurret("decoy"){{
            alwaysUnlocked = true;
            requirements(Category.effect, new ItemStack[]{
                new ItemStack(SLItems.shapinite, 80),
                new ItemStack(Items.copper, 50),
                new ItemStack(Items.graphite, 30)
            });

            shootType = new ExplosionBulletType(10,120){{
                impact = killShooter = splashDamagePierce = false;
                knockback = -15;
            }
            @Override
            public void hitEntity(Bullet b, Hitboxc entity, float health){
                super.hitEntity(b, entity, health);
                if(b.owner !=null && entity instanceof Unit unit && unit.canTarget((Teamc) b.owner)){
                    for(WeaponMount w : unit.mounts){
                        w.target = (Teamc) b.owner;
                        w.aimX = b.x;
                        w.aimY = b.y;
                    }
                    unit.aimX = b.x;
                    unit.aimY = b.y;
                }
            }};

            range = 120;
            reload = 60;
            armor = -999999;
            health = 100000;
            alwaysUpdateInUnits = true;
            priority = 9;
            flags = EnumSet.of(BlockFlag.all);
            variants = 3;
            consumePower(2f);
        }};
    }
}