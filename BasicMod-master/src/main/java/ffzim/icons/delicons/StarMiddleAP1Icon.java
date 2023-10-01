package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;

public class StarMiddleAP1Icon extends AbstractCustomIcon {
    public static final String ID = makeID("StarMiddleAP1");
    private static StarMiddleAP1Icon singleton;

    public StarMiddleAP1Icon() {
        super(ID, TextureLoader.getTexture("ffzim/icons/StarMiddleAP1.png"));
    }

    public static StarMiddleAP1Icon get()
    {
        if (singleton == null) {
            singleton = new StarMiddleAP1Icon();
        }
        return singleton;
    }
}