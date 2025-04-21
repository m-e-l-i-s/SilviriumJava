package world.blocks;

import arc.util.Time;
import mindustry.type.Item;
import mindustry.world.blocks.defense.Wall;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.entities.bullet.LightningBulletType;

public class ItemWall extends Wall {

    public float bChar, bExpl, bFlam, bRadi, bHard;

    protected BulletType CBullet = new LightningBulletType() {{
        damage = speed = 0;
        collidesAir = collidesGround = collidesTiles = true;
    }};
    protected BulletType EBullet = new ExplosionBulletType() {{
        damage = speed = 0;
        collidesAir = collidesGround = collidesTiles = true;
    }};
    protected BulletType FBullet = new BulletType() {{
        damage = speed = 0;
        collidesAir = collidesGround = collidesTiles = true;
    }};
    protected BulletType RBullet = new ExplosionBulletType() {{
        damage = speed = 0;
        collidesAir = collidesGround = collidesTiles = true;
    }};

    public ItemWall(String name) {
        super(name);
        buildType = ItemWallBuild::new;
    }

    public class ItemWallBuild extends WallBuild {
        private float RadiTimer = 0f;

        @Override
        public void updateTile() {
            super.updateTile();

            if (bRadi > 0 && RadiTimer >= 60f) {
                RBullet.splashDamage = bRadi * timeScale;
                RBullet.splashDamageRadius = bRadi * 0.8f;
                RBullet.create(this, x, y, 0);
                RadiTimer = 0f;
            } else {
                RadiTimer += Time.delta;
            }

            if (items.total() > 0) {
                Item item = items.first();
                bChar = item.charge;
                bExpl = item.explosiveness;
                bFlam = item.flammability;
                bRadi = item.radioactivity;
                bHard = item.healthScaling * item.cost;
            }
        }
    }
}