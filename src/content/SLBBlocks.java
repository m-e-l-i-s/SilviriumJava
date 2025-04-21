package content;

import mindustry.world.Block;
import world.blocks.ItemWall;

public class SLBBlocks {
    public static Block variWall;

public static void load(){
    variWall = new ItemWall("Vari Wall"){{
        size = 2;
        health = 10;
    }};
}}
