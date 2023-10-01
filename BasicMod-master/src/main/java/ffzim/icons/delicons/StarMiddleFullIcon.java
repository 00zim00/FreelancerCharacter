package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class StarMiddleFullIcon extends AbstractCustomIcon {
    public static final String ID = makeID("StarMiddleFull");
    public static final String ICON_LOCATION = modID + "/icons/StarMiddleFull.png";
    private static StarMiddleFullIcon singleton;

    public StarMiddleFullIcon() {
        super(ID, TextureLoader.getTexture(ICON_LOCATION));
    }

    public static StarMiddleFullIcon get()
    {
        if (singleton == null) {
            singleton = new StarMiddleFullIcon();
        }
        return singleton;
    }
}