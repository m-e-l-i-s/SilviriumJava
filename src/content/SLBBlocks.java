package content;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.part.RegionPart;
import mindustry.graphics.Layer;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.blocks.units.Reconstructor;
import arc.struct.EnumSet;
import arc.math.Interp;
import content.SLBliquids;
import mindustry.type.*;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.*;
import static mindustry.type.LiquidStack.*;


public class SLBBlocks {
    public static Block
    liqsilvfac,silvingfac,starfac,
    silvFacT1, silvFacT2, silvFacT3, silvFacT4,starFacT1,
    silvOre,
    ABT,HPT,ST,
    decoy;
    
    public static void load(){
        //unit factory
        silvFacT1 = new UnitFactory("silvirium-molder"){{
        requirements(Category.units, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 200),
                new ItemStack(Items.lead, 40),
                new ItemStack(Items.silicon, 70)
            });
            size = 2;
            health = 220;
            consumePower(0f);
            consumeLiquid(SLBliquids.liquidSilvirium, 0.5f);
            plans.addAll(
                new UnitPlan(
                    SLBUnits.silvirror, 1200,
                    new ItemStack[]{
                      new ItemStack(SLBItems.silvirium, 30),
                      new ItemStack(Items.silicon, 20)
                    }
                ),
                new UnitPlan(
                    SLBUnits.silvone, 1200,
                    new ItemStack[]{
                      new ItemStack(SLBItems.silvirium, 20),
                      new ItemStack(Items.silicon, 10)
                    }
                ),
                new UnitPlan(
                    SLBUnits.silvioros, 1200,
                    new ItemStack[]{
                      new ItemStack(SLBItems.silvirium, 20),
                      new ItemStack(Items.silicon, 10)
                    }
                ),
                new UnitPlan(
                    SLBUnits.silvanon, 900,
                    new ItemStack[]{
                      new ItemStack(SLBItems.silvirium, 35),
                    }
                )
            );
        }};
        silvFacT2 = new Reconstructor("silvirium-remolder"){{
        requirements(Category.units, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(Items.silicon, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 3;
            consumePower(0f);
            health = 495;
            consumeItems(new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 80),
                new ItemStack(Items.silicon, 40),
            });
            constructTime = 900f;
            addUpgrade(SLBUnits.silvirror, SLBUnits.silvokeor);
        }};
        silvFacT3 = new Reconstructor("silvirium-regrower"){{
        requirements(Category.units, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(Items.silicon, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 4;
            consumePower(0f);
            health = 880;
            consumeItems(new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 200),
                new ItemStack(Items.silicon, 150),
            });
            constructTime = 1800f;
            addUpgrade(SLBUnits.silvokeor, SLBUnits.silvbane);
        }};
        silvFacT4 = new Reconstructor("silvirium-reforge"){{
        requirements(Category.units, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(Items.silicon, 70),
                new ItemStack(Items.metaglass, 50)
            });
            size = 5;
            consumePower(0f);
            health = 40;
            consumeItems(new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 1200),
                new ItemStack(Items.silicon, 400),
            });
            constructTime = 2400f;
            addUpgrade(SLBUnits.silvbane, SLBUnits.silvruner);
        }};
        starFacT1 = new UnitFactory("silvirium-molder"){{
        requirements(Category.units, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 200),
                new ItemStack(Items.lead, 40),
                new ItemStack(Items.silicon, 70)
            });
            size = 2;
            health = 220;
            consumePower(0f);
            plans.addAll(
                new UnitPlan(
                    SLBUnits.star1, 0,
                    new ItemStack[]{
                      new ItemStack(SLBItems.starFrag, 30),
                      new ItemStack(Items.silicon, 20)
                    }
                )
            );
        }};
        //factory
        liqsilvfac = new GenericCrafter("liquid-silvirium-factory"){{
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 200),
                new ItemStack(Items.metaglass, 40),
                new ItemStack(Items.silicon, 70),
                new ItemStack(SLBItems.silviriumIng, 50)
            });
            outputLiquid = new LiquidStack(SLBliquids.liquidSilvirium, 0.25f);
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
            lightLiquid = SLBliquids.liquidSilvirium;

            consumePower(0.5f);
            consumeItem(SLBItems.silvirium);
            drawer = new DrawMulti(
                new DrawDefault(),
                new DrawLiquidRegion(SLBliquids.liquidSilvirium){{
                suffix = "-liquid";
                }},
                new DrawRegion("-rot"){{
                        layer = 40.4f;
                        rotateSpeed = 10f;
                }},
                new DrawSoftParticles(){{
                        color = SLBPal.silviriumColor;
                        color2 = SLBPal.silviriumColor;
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
            requirements(Category.crafting, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 100),
                new ItemStack(Items.metaglass, 10),
                new ItemStack(Items.silicon, 50),
            });
            size = 2;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            envEnabled = Env.any;
            itemCapacity = 16f;
            craftTime = 600;

            consumePower(0.5f);
            consumeItem(SLBItems.silvirium,4);
            outputItem = new ItemStack(SLBItems.silviriumIng, 1);
        }};
        starfac new GenericCrafter("liquid-silvirium-factory"){{
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
            itemCapacity = 32f;
            craftTime = 600;

            consumePower(8f);
            outputItem = new ItemStack(SLBItems.starFrag, 1);
        }};
        //ore
        silvOre = new OreBlock("ore-silvirium", SLBItems.silvirium){{
            status = SLBStatusEffects.disrupted;
            statusDuration = 300f;
            speedMultiplier = 0.6f;
            oreDefault = true;
            oreScale = 9f;
            oreThreshold = 0.7f;
            emitLight = true;
            lightRadius = 12f;
            lightColor = SLBPal.silviriumColor;
            variants = 2;
        }};
        //turrets
        HPT = new LiquidTurret("higth-presure-turret"){{
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 120),
                new ItemStack(Items.graphite, 90),
                new ItemStack(Items.silicon, 100),
                new ItemStack(Items.metaglass, 60)
            });
            ammo(
                SLBliquids.liquidSilvirium,new LiquidBulletType(SLBliquids.liquidSilvirium){{
                    damage = 30;
                    speed = 16;
                    lifetime = 40;
                    knockback = 0.2f;
                    drag = 0.07f;
                    layer = Layer.bullet - 2f;
                    status = SLBStatusEffects.disrupted;
                    statusDuration = 300;
                    pierce = pierceBuilding = true;
                    pierceCap = 2;
                    pierceDamageFactor = 0.4f;
                    puddles = 12;
                    puddleRange = 80;
                    puddleAmount = 32;
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
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(SLBItems.silvirium, 110),
            });
            ammoTypes.putAll(
            SLBItems.silvirium, new BasicBulletType(2f,10f){{
                lifetime = 51;
                width = 6;
                height = 10;
                pierce = pierceBuilding = true;
                pierceCap = 2;
                frontColor = SLBPal.silviriumColor;
                backColor = SLBPal.silviriumColor;
                status = SLBStatusEffects.disrupted;
                trailColor  = SLBPal.silviriumColor;
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
            health = 270;
            flags = EnumSet.of(BlockFlag.turret);
            shootEffect = SLBFx.silviriumHit2Effect;
        }};
        ABT = new ItemTurret("anti-building-turret"){{
            requirements(Category.turret, new ItemStack[]{
                new ItemStack(Items.copper, 3000),
                new ItemStack(Items.lead, 3000),
                new ItemStack(Items.silicon, 1000),
                new ItemStack(Items.graphite, 500)
            });
            range = 1600;
            size = 6;
            reload = 1200;
            ammoPerShot = 10;
            maxAmmo = 30;
            recoil = 10;
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
            }});
        }};
        //util
        decoy = new Wall("decoy"){{
            requirements(Category.effect, new ItemStack[]{
                new ItemStack(Items.silicon, 80),
                new ItemStack(Items.copper, 50),
                new ItemStack(Items.graphite, 30)
            });
            health = 100;
            priority = 8;
            flags = EnumSet.of(BlockFlag.all);
            variants = 3;
        }};
    }}
