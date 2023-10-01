package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class ApBarFullIcon extends AbstractCustomIcon {
    public static final String ID = makeID("ApBarFull");

    public static final String ICON_LOCATION = modID + "/icons/ApBarFull.png";
    private static ApBarFullIcon singleton;

    public ApBarFullIcon() {
        super(ID, TextureLoader.getTexture("ICON_LOCATION"));
    }

    public static ApBarFullIcon get()
    {
        if (singleton == null) {
            singleton = new ApBarFullIcon();
        }
        return singleton;
    }
}