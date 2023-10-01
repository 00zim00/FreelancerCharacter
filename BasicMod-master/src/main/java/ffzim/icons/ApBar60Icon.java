package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class ApBar60Icon extends AbstractCustomIcon {
    public static final String ID = makeID("ApBar60");

    public static final String ICON_LOCATION = modID + "/icons/ApBar60.png";
    private static ApBar60Icon singleton;

    public ApBar60Icon() {
        super(ID, TextureLoader.getTexture("ICON_LOCATION"));
    }

    public static ApBar60Icon get()
    {
        if (singleton == null) {
            singleton = new ApBar60Icon();
        }
        return singleton;
    }
}