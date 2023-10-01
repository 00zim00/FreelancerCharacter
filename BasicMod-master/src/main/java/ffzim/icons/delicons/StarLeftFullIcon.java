package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class StarLeftFullIcon extends AbstractCustomIcon {
    public static final String ID = makeID("StarLeftFull");
    public static final String ICON_LOCATION = modID + "/icons/StarLeftFull.png";
    private static StarLeftFullIcon singleton;

    public StarLeftFullIcon() {
        super(ID, TextureLoader.getTexture(ICON_LOCATION));
    }

    public static StarLeftFullIcon get()
    {
        if (singleton == null) {
            singleton = new StarLeftFullIcon();
        }
        return singleton;
    }
}