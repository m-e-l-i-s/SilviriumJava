package content;

import mindustry.type.Item;

public class SLItems {
public static Item silvirium, silviriumIng, starFrag, antintuitivemater;

    public static void load(){
        silvirium = new Item("silvirium"){{
            alwaysUnlocked = true;
            hardness = 2;
            color = SLPal.silviriumColor;
        }};
        silviriumIng = new Item("silvirium-ingot"){{
            alwaysUnlocked = true;
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
        antintuitivemater = new Item("antintuitive-matter"){{
            alwaysUnlocked = true;
            explosiveness = 2f;
            charge = 2;
            flammability = 2f;
            radioactivity = 2f;
            frames = 2;
            transitionFrames = 2;
            frameTime = 10;
        }};
    }
}
