package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class StarRightFullIcon extends AbstractCustomIcon {
    public static final String ID = makeID("StarRightFull");
    public static final String ICON_LOCATION = modID + "/icons/StarRightFull.png";
    private static StarRightFullIcon singleton;

    public StarRightFullIcon() {
        super(ID, TextureLoader.getTexture(ICON_LOCATION));
    }

    public static StarRightFullIcon get()
    {
        if (singleton == null) {
            singleton = new StarRightFullIcon();
        }
        return singleton;
    }
}