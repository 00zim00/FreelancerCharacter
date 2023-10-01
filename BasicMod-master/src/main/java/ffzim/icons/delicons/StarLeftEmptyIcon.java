package ffzim.icons.delicons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;

public class StarLeftEmptyIcon extends AbstractCustomIcon {
    public static final String ID = makeID("StarLeftEmpty");
    private static StarLeftEmptyIcon singleton;

    public StarLeftEmptyIcon() {
        super(ID, TextureLoader.getTexture("ffzim/icons/StarLeftEmpty.png"));
    }

    public static StarLeftEmptyIcon get()
    {
        if (singleton == null) {
            singleton = new StarLeftEmptyIcon();
        }
        return singleton;
    }
}