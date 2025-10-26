package content;

import mindustry.type.Item;

public class SLBItems {
public static Item silvirium, silviriumIng, starFrag, antintuitivemater;

    public static void load(){
        silvirium = new Item("silvirium"){{
            hardness = 2;
            color = SLBPal.silviriumColor;
        }};
        silviriumIng = new Item("silvirium-ingot"){{
                color = SLBPal.silviriumOtherColor;
        }};
        starFrag = new Item("star-fragment"){{
            explosiveness = 0.8f;
            charge = 0.05f;
            flammability = 0.6f;
            radioactivity = 0.2f;
            color = SLBPal.starOrangeColor;
        }};
        antintuitivemater = new Item("antintuitive-matter"){{
            explosiveness = 0.2f;
            charge = 1;
            flammability = 0.05f;
            radioactivity = 0.6f;
            frames = 2;
            frameTime = 30;
        }};
    }
}
