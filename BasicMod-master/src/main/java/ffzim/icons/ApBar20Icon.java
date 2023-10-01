package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class ApBar20Icon extends AbstractCustomIcon {
    public static final String ID = makeID("ApBar40");

    public static final String ICON_LOCATION = modID + "/icons/ApBar40.png";
    private static ApBar20Icon singleton;
    public ApBar20Icon() {
        super(ID, TextureLoader.getTexture("ICON_LOCATION"));
    }

    public static ApBar20Icon get()
    {
        if (singleton == null) {
            singleton = new ApBar20Icon();
        }
        return singleton;
    }
}