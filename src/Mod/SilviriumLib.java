package Mod;

import arc.*;
import arc.util.*;
import content.SLBBlocks;
import content.SLBStatusEffects;
import content.SLBUnits;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class SilviriumLib extends Mod{

    public SilviriumLib(){
    Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(30f, () -> {
                BaseDialog dialog = new BaseDialog("Hello");
                dialog.cont.add("This is Silvirium").row();
                dialog.cont.image(Core.atlas.find("SLib-icon")).pad(20f).row();
                dialog.cont.button("close", dialog::hide).size(140f,60f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        SLBStatusEffects.load();
        SLBUnits.load();
        SLBBlocks.load();
    };
}
