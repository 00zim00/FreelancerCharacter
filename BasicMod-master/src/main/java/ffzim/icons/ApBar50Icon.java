package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class ApBar50Icon extends AbstractCustomIcon {
    public static final String ID = makeID("ApBar50");

    public static final String ICON_LOCATION = modID + "/icons/ApBar50.png";
    private static ApBar50Icon singleton;

    public ApBar50Icon() {
        super(ID, TextureLoader.getTexture("ICON_LOCATION"));
    }

    public static ApBar50Icon get()
    {
        if (singleton == null) {
            singleton = new ApBar50Icon();
        }
        return singleton;
    }
}