package content;


import mindustry.type.Liquid;

public class SLBliquids {
public static Liquid liquidSilvirium;

    public static void load(){
        liquidSilvirium = new Liquid("liquid-silvirium"){{
            alwaysUnlocked = true;
            color = SLBPal.silviriumColor;
            gasColor = SLBPal.silviriumligthColor;
            effect = SLBStatusEffects.disrupted;
            heatCapacity = 0.2f;
            boilPoint = 3f;
            temperature = 0.4f;
            flammability = -0.05f;
            viscosity = 0.001f;
            alwaysUnlocked = true;
            moveThroughBlocks = true;
        }};
    }
}
