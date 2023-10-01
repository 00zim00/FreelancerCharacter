package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class ApBarEmptyIcon extends AbstractCustomIcon {
    public static final String ID = makeID("ApBarEmpty");

    public static final String ICON_LOCATION = modID + "/icons/ApBarEmpty.png";
    private static ApBarEmptyIcon singleton;

    public ApBarEmptyIcon() {
        super(ID, TextureLoader.getTexture("ICON_LOCATION"));
    }

    public static ApBarEmptyIcon get()
    {
        if (singleton == null) {
            singleton = new ApBarEmptyIcon();
        }
        return singleton;
    }
}