package content;

import mindustry.type.Item;

public class SLItems {
public static Item silvirium, silviriumIng, starFrag, antintuitivemater;

    public static void load(){
        silvirium = new Item("silvirium"){{
            alwaysUnlocked = true;
            hardness = 2;
            cost = 0.5f;
            color = SLPal.silviriumColor;
        }};
        silviriumIng = new Item("silvirium-ingot"){{
            alwaysUnlocked = true;
            cost = 2.2f;
            color = SLPal.silviriumOtherColor;
        }};
        starFrag = new Item("star-fragment"){{
            alwaysUnlocked = true;
            explosiveness = 0.8f;
            charge = 0.05f;
            flammability = 0.6f;
            radioactivity = 0.2f;
            color = SLPal.starOrangeColor;
        }};
        silvirium = new Item("shapinite"){{
            alwaysUnlocked = true;
            hardness = 3;
            cost = 2f;
            color = SLPal.silviriumColor;
        }};
        antintuitivemater = new Item("antintuitive-matter"){{
            alwaysUnlocked = true;
            explosiveness = 5f;
            charge = 5;
            flammability = 5f;
            radioactivity = 5f;
            frames = 4;
            frameTime = 3;
        }};
    }
}
