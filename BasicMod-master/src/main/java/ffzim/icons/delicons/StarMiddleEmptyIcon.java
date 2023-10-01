package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;

public class StarMiddleEmptyIcon extends AbstractCustomIcon {
    public static final String ID = makeID("StarMiddleEmpty");
    private static StarMiddleEmptyIcon singleton;

    public StarMiddleEmptyIcon() {
        super(ID, TextureLoader.getTexture("ffzim/icons/StarMiddleEmpty.png"));
    }

    public static StarMiddleEmptyIcon get()
    {
        if (singleton == null) {
            singleton = new StarMiddleEmptyIcon();
        }
        return singleton;
    }
}