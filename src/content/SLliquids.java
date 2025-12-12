package content;


import mindustry.type.Liquid;

public class SLliquids {
public static Liquid liquidSilvirium;

    public static void load(){
        liquidSilvirium = new Liquid("liquid-silvirium"){{
            alwaysUnlocked = true;
            color = SLPal.silviriumColor;
            gasColor = SLPal.silviriumligthColor;
            effect = SLStatusEffects.disrupted;
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
