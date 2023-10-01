package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;

public class StarMiddleAP3Icon extends AbstractCustomIcon {
    public static final String ID = makeID("StarMiddleAP3");
    private static StarMiddleAP3Icon singleton;

    public StarMiddleAP3Icon() {
        super(ID, TextureLoader.getTexture("ffzim/icons/StarMiddleAP3.png"));
    }

    public static StarMiddleAP3Icon get()
    {
        if (singleton == null) {
            singleton = new StarMiddleAP3Icon();
        }
        return singleton;
    }
}