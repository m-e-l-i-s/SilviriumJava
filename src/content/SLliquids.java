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
            heatCapacity = 0.5f;
            boilPoint = 3f;
            temperature = 0.2f;
            flammability = -0.1f;
            viscosity = 0.95f;
            alwaysUnlocked = true;
            moveThroughBlocks = true;
        }};
    }
}
