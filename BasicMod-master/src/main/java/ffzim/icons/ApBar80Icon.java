package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class ApBar80Icon extends AbstractCustomIcon {
    public static final String ID = makeID("ApBar80");

    public static final String ICON_LOCATION = modID + "/icons/ApBar80.png";
    private static ApBar80Icon singleton;

    public ApBar80Icon() {
        super(ID, TextureLoader.getTexture("ICON_LOCATION"));
    }

    public static ApBar80Icon get()
    {
        if (singleton == null) {
            singleton = new ApBar80Icon();
        }
        return singleton;
    }
}