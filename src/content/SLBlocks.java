package content;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.part.DrawPart.PartProgress;
//import mindustry.entities.part.DrawPart.PartProgress;
import mindustry.entities.part.RegionPart;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.consumers.*;
import arc.graphics.*;
import arc.struct.EnumSet;
import arc.util.Time;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.type.*;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;


public class SLBlocks {
    public static Block
    liqsilvfac,silvingfac,starfac,
    silvFacT1, silvFacT2, silvFacT3, silvFacT4,starFacT1,
    silvOre,slivFloor,slivWall,
    ABT,HPT,SST,ST,
    decoy;
    
    public static void load(){
        //unit factory
        silvFacT1 = new UnitFactory("silvirium-molder"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.lead, 40),
                new ItemStack(Items.silicon, 70)
            });
            size = 2;
            health = 220;
            consumePower(0.5f);
            consumeLiquid(SLliquids.liquidSilvirium, 0.5f);
            plans.addAll(
                new UnitPlan(
                    SLUnits.silvirror, 1200,
                    new ItemStack[]{
                      new ItemStack(SLItems.silvirium, 30),
                      new ItemStack(Items.silicon, 20)
                    }
                ),
                new UnitPlan(
                    SLUnits.silvone, 900,
                    new ItemStack[]{
                      new ItemStack(SLItems.silvirium, 20),
                      new ItemStack(Items.silicon, 10)
                    }
                ),
                new UnitPlan(
                    SLUnits.silvioros, 1200,
                    new ItemStack[]{
                      new ItemStack(SLItems.silvirium, 20),
                      new ItemStack(Items.silicon, 10)
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
                new ItemStack(Items.silicon, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 3;
            consumePower(0f);
            health = 495;
            consumeItems(new ItemStack[]{
                new ItemStack(SLItems.silvirium, 80),
                new ItemStack(Items.silicon, 40),
            });
            constructTime = 900f;
            addUpgrade(SLUnits.silvirror, SLUnits.silvokeor);
        }};
        
        silvFacT3 = new Reconstructor("silvirium-regrower"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.graphite, 40),
                new ItemStack(Items.silicon, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 4;
            consumePower(0f);
            health = 880;
            consumeItems(new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.silicon, 150),
            });
            constructTime = 1800f;
            addUpgrade(SLUnits.silvokeor, SLUnits.silvbane);
        }};
        
        silvFacT4 = new Reconstructor("silvirium-reforge"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(Items.silicon, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 5;
            consumePower(0f);
            health = 1375;
            consumeItems(new ItemStack[]{
                new ItemStack(SLItems.silvirium, 1200),
                new ItemStack(Items.silicon, 400),
            });
            constructTime = 2400f;
            addUpgrade(SLUnits.silvbane, SLUnits.silvruner);
        }};
        
        starFacT1 = new UnitFactory("star-molder"){{
            alwaysUnlocked = true;
            requirements(Category.units, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.lead, 40),
                new ItemStack(Items.silicon, 70)
            });
            size = 2;
            health = 220;
            consumePower(0f);
            plans.addAll(
                new UnitPlan(
                    SLUnits.star1, 0,
                    new ItemStack[]{
                      new ItemStack(SLItems.starFrag, 30),
                      new ItemStack(Items.silicon, 20)
                    }
                )
            );
        }};
        //factory
        liqsilvfac = new GenericCrafter("liquid-silvirium-factory"){{
            alwaysUnlocked = true;
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(Items.silicon, 70),
                new ItemStack(SLItems.silviriumIng, 50)
            });
            outputLiquid = new LiquidStack(SLliquids.liquidSilvirium, 0.25f);
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
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
                        rotateSpeed = 10f;
                }},
                new DrawSoftParticles(){{
                        color = SLPal.silviriumColor;
                        color2 = SLPal.silviriumColor;
                        alpha = 0.3f;
                        particles = 7;
                        particleLife = 70f;
                        particleRad = 4f;
                        particleSize = 3f;
                        fadeMargin = 0.2f;
                        rotateScl = 0.1f;
                        particleInterp = Interp.one;
                }},
                new DrawRegion("-top"){{
                        layer = 40.5f;
                }}
            );
        }};
        
        silvingfac = new GenericCrafter("silvirium-ingot-factory"){{
            alwaysUnlocked = true;
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 100),
                new ItemStack(Items.metaglass, 10),
                new ItemStack(Items.silicon, 50),
            });
            size = 2;
            hasPower = true;
            hasItems = true;
            rotate = false;
            solid = true;
            envEnabled = Env.any;
            itemCapacity = 16;
            craftTime = 600;

            consumePower(0.5f);
            consumeItem(SLItems.silvirium,4);
            outputItem = new ItemStack(SLItems.silviriumIng, 1);
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
        }};
        
        starfac = new GenericCrafter("star-factory"){{
            alwaysUnlocked = true;
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(Items.metaglass, 50),
                new ItemStack(Items.silicon, 110),
            });
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            envEnabled = Env.any;
            itemCapacity = 1;
            craftTime = 1200;

            consumePower(0.75f);
            outputItem = new ItemStack(SLItems.starFrag, 2);
        }};
        //ore
        silvOre = new OreBlock("ore-silvirium", SLItems.silvirium){{
            alwaysUnlocked = true;
            oreDefault = true;
            oreScale = 10f;
            oreThreshold = 0.5f;
            emitLight = true;
            lightRadius = 9f;
            lightColor = SLPal.silviriumColor;
            variants = 2;
        }};
        //floors
        slivFloor = new Floor("silvirium-floor", 2){{
            liquidDrop = SLliquids.liquidSilvirium;
            liquidMultiplier = 0.001f;
            walkEffect = SLFx.silvAmb;
            status = SLStatusEffects.disrupted;
            statusDuration = 6f;
            dragMultiplier = 5;
            //attributes.set(Attribute.silvirium,1f);
        }};
        //envirmental walls
        slivWall = new StaticWall("silvirium-wall"){{
            variants = 2;
            slivFloor.asFloor().wall = this;
            //attributes.set(Attribute.silvirium,1f);
        }};

        //turrets
        HPT = new LiquidTurret("higth-presure-turret"){{
            alwaysUnlocked = true;
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(SLItems.silvirium, 120),
                new ItemStack(Items.graphite, 90),
                new ItemStack(Items.silicon, 100),
                new ItemStack(Items.metaglass, 60)
            });
            ammo(
                SLliquids.liquidSilvirium,new LiquidBulletType(SLliquids.liquidSilvirium){{
                    damage = 30;
                    speed = 16;
                    lifetime = 40;
                    knockback = 0.2f;
                    drag = 0.07f;
                    layer = Layer.bullet - 1f;
                    status = SLStatusEffects.disrupted;
                    statusDuration = 300;
                    pierce = pierceBuilding = true;
                    pierceCap = 2;
                    pierceDamageFactor = 0.01f;
                    puddleSize = 16;
                    orbSize = 3;
                    boilTime = 600;
                    
                }}
            );
            shoot.shots = 7;
            shoot.shotDelay = 5f;
            size = 3;
            recoil = 1f;
            reload = 180f;
            inaccuracy = 0f;
            shootCone = 2f;
            liquidCapacity = 10f;
            rotateSpeed = 3f;
            range = 200f;
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
                lifetime = 51;
                width = 6;
                height = 10;
                pierce = pierceBuilding = true;
                pierceCap = 2;
                frontColor = SLPal.silviriumColor;
                backColor = SLPal.silviriumColor;
                status = SLStatusEffects.disrupted;
                trailColor  = SLPal.silviriumColor;
                trailLength = 6;
                statusDuration = 300;
            }});
            size = 1;
            recoil = 1f;
            reload = 30f;
            inaccuracy = 0f;
            shootCone = 2f;
            maxAmmo = 10;
            rotateSpeed = 5f;
            range = 100f;
            health = 240;
            flags = EnumSet.of(BlockFlag.turret);
            coolant = consume(new ConsumeLiquid(SLliquids.liquidSilvirium, 0.05f));
            shootEffect = SLFx.silviriumHit1Effect;
        }};
        SST = new ItemTurret("sand-storm-turret"){{
            predictTarget = false;
            alwaysUnlocked = true;
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(Items.sand, 110),
                new ItemStack(Items.silicon, 110)
            });
            ammoTypes.putAll(
                Items.sand, new PointBulletType(){{
                    damage = 0;
                    splashDamage = 75;
                    splashDamageRadius = 48;
                    trailEffect = SLFx.sndLine;
                    ammoMultiplier = 1;
                    spawnBullets.add(
                        new ExplosionBulletType(10,120){{
                            killShooter = false;
                            fragBullets = 4;
                            fragBullet = new BasicBulletType(12,10){{
                                lifetime = 600;
                                homingPower = 1f;
                                homingRange = 24;
                            }
                            @Override
                            public void updateWeaving(Bullet b){
                                b.vel.rotate((b.dst(b.originX,b.originY) * Mathf.PI * Time.delta) / b.vel.len());
                            }
                            }; 
                        }},
                        new ExplosionBulletType(10,240){{
                            killShooter = false;
                            fragBullets = 4;
                            fragBullet = new BasicBulletType(12,10){{
                                lifetime = 600;
                                homingPower = 1f;
                                homingRange = 24;
                            }
                            @Override
                            public void updateWeaving(Bullet b){
                                b.vel.rotate((-b.dst(b.originX,b.originY) * Mathf.PI * Time.delta) / b.vel.len());
                            }
                            }; 
                        }}
                    );
                }}
            );
            size = 4;
            recoil = 1f;
            reload = 280f;
            inaccuracy = 0f;
            shootCone = 1f;
            maxAmmo = 18;
            ammoPerShot = 9;
            rotateSpeed = 2f;
            range = 280f;
            health = 2200;
            flags = EnumSet.of(BlockFlag.turret);
            shootEffect = SLFx.silviriumHit1Effect;
        }};
        
        ABT = new ItemTurret("anti-building-turret"){{
            alwaysUnlocked = true;
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(Items.copper, 3000),
                new ItemStack(Items.lead, 3000),
                new ItemStack(Items.silicon, 1000),
                new ItemStack(Items.graphite, 500)
            });
            range = 1600;
            size = 6;
            reload = 1200;
            ammoPerShot = 20;
            maxAmmo = 40;
            recoil = 12;
            flags = EnumSet.of(BlockFlag.turret);
            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-barrel"){{
                    progress = PartProgress.recoil.add(PartProgress.heat.mul(0.5f));
                    mirror = true;
                    under = true;
                    x = -2;
                    y = 18;
                    moveY = -8;
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
            Items.silicon, new BasicBulletType(4, 400){{
                lifetime = 400;
                width = 16;
                height = 12;
                pierce = pierceBuilding = true;
                pierceCap = 3;
                frontColor = Items.silicon.color;
                backColor = Items.silicon.color.mul(0.8f);
                homingPower = 0.05f;
                homingRange = 24;
            }},
            Items.thorium, new BasicBulletType(16, 5000){{
                lifetime = 100;
                width = 16;
                height = 12;
                pierce = pierceBuilding = true;
                pierceDamageFactor = 0.6f;
                frontColor = Items.thorium.color;
                backColor = Items.thorium.color.mul(0.8f);
            }},
            SLItems.starFrag, new BasicBulletType(8, 2100){{
                lifetime = 200;
                width = 16;
                height = 12;
                pierce = pierceBuilding = true;
                pierceDamageFactor = 0.1f;
                frontColor = SLItems.starFrag.color;
                backColor = SLItems.starFrag.color.mul(0.8f);
            }});
        }@Override
            public void init(){
                super.init();
                placeOverlapRange = -50f;
            }
        };
        //util
        decoy = new Wall("decoy"){{
            alwaysUnlocked = true;
            requirements(Category.effect, new ItemStack[]{
                new ItemStack(Items.silicon, 80),
                new ItemStack(Items.copper, 50),
                new ItemStack(Items.graphite, 30)
            });
            health = 111;
            priority = 9;
            flags = EnumSet.of(BlockFlag.all);
            variants = 3;
        }};
    }}
