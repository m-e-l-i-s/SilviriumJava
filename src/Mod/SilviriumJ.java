package Mod;

import arc.*;
import arc.util.*;
import content.SLlocks;
import content.SLStatusEffects;
import content.SLUnits;
import content.SLItems;
import content.SLliquids;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class SilviriumLib extends Mod{

    public SilviriumLib(){
    Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(30f, () -> {
                BaseDialog dialog = new BaseDialog("Hello message");
                dialog.cont.add("This is Silvirium").row();
                dialog.cont.image(Core.atlas.find("sli-icon")).pad(40f).row();
                dialog.cont.button("CLOSE", dialog::hide).size(150f,75f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        SLStatusEffects.load();
        SLliquids.load();
        SLItems.load();
        SLUnits.load();
        SLlocks.load();
    };
}
