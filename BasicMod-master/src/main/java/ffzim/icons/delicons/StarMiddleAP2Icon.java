package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;

public class StarMiddleAP2Icon extends AbstractCustomIcon {
    public static final String ID = makeID("StarMiddleAP2");
    private static StarMiddleAP2Icon singleton;

    public StarMiddleAP2Icon() {
        super(ID, TextureLoader.getTexture("ffzim/icons/StarMiddleAP2.png"));
    }

    public static StarMiddleAP2Icon get()
    {
        if (singleton == null) {
            singleton = new StarMiddleAP2Icon();
        }
        return singleton;
    }
}