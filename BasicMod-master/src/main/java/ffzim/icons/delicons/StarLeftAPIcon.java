package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;

public class StarLeftAPIcon extends AbstractCustomIcon {
    public static final String ID = makeID("StarLeftAP");
    private static StarLeftAPIcon singleton;

    public StarLeftAPIcon() {
        super(ID, TextureLoader.getTexture("ffzim/icons/StarLeftAP.png"));
    }

    public static StarLeftAPIcon get()
    {
        if (singleton == null) {
            singleton = new StarLeftAPIcon();
        }
        return singleton;
    }
}