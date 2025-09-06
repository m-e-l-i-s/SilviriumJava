package Mod;

import arc.*;
import arc.util.*;
import content.SLBBlocks;
import content.SLBStatusEffects;
import content.SLBUnits;
import content.SLBItems;
import content.SLBliquids;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class SilviriumLib extends Mod{

    public SilviriumLib(){
    Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(30f, () -> {
                BaseDialog dialog = new BaseDialog("Hello message");
                dialog.cont.add("This is Silvirium").row();
                dialog.cont.image(Core.atlas.find("slib-icon")).pad(40f).row();
                dialog.cont.button("CLOSE", dialog::hide).size(150f,75f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        SLBStatusEffects.load();
        SLBliquids.load();
        SLBItems.load();
        SLBUnits.load();
        SLBBlocks.load();
    };
}
